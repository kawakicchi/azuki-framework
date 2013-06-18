package jp.azuki.job.server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import jp.azuki.core.util.StringUtility;
import jp.azuki.job.commandline.CommandLineArgument;
import jp.azuki.job.commandline.CommandLineArgumentPurser;
import jp.azuki.job.commandline.StandardCommandLineArgumentPurser;
import jp.azuki.job.context.JobContext;
import jp.azuki.job.job.Job;
import jp.azuki.job.store.JobSessionStore;
import jp.azuki.job.worker.JobWorker;
import jp.azuki.persistence.context.Context;
import jp.azuki.persistence.context.ContextSupport;
import jp.azuki.persistence.proterty.Property;
import jp.azuki.persistence.proterty.PropertyFile;
import jp.azuki.persistence.proterty.PropertyManager;
import jp.azuki.persistence.proterty.PropertySupport;
import jp.azuki.persistence.session.SessionSupport;
import jp.azuki.plugin.PluginManager;
import jp.azuki.plugin.exception.PluginServiceException;

/**
 * このクラスは、標準のジョブサーバクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/15
 * @author Kawakicchi
 */
public final class StandardJobServer extends AbstractJobServer {

	/**
	 * メイン処理
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		CommandLineArgumentPurser purser = new StandardCommandLineArgumentPurser();
		CommandLineArgument arg = purser.purse(args);

		String baseDir = arg.getOptionValue("baseDir");
		String config = arg.getOptionValue("config");

		StandardJobServer server = null;
		if (StringUtility.isNotEmpty(baseDir)) {
			server = new StandardJobServer(baseDir);
		} else {
			server = new StandardJobServer();
		}
		if (StringUtility.isNotEmpty(config)) {
			server.setConfig(config);
		}
		server.run();
	}

	/**
	 * コンテキスト
	 */
	private Context context;

	/**
	 * コンフィグ
	 */
	private String config;

	/**
	 * ワーカリスト
	 */
	private List<JobWorker> workers;

	private String stopFile;

	private boolean stopRequest = false;

	/**
	 * コンストラクタ
	 */
	public StandardJobServer() {
		super(StandardJobServer.class);
		context = new JobContext();
		workers = new ArrayList<JobWorker>();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aBaseDir ベースディレクトリ
	 */
	public StandardJobServer(final String aBaseDir) {
		super(StandardJobServer.class);
		context = new JobContext(aBaseDir);
		workers = new ArrayList<JobWorker>();
	}

	/**
	 * 設定ファイルを設定する
	 * 
	 * @param aConfig 設定
	 */
	public void setConfig(final String aConfig) {
		config = aConfig;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected boolean doRun() {
		boolean result = false;

		Class<? extends Job> jobClass = null;
		int workerCount = 1;
		Properties p = new Properties();
		try {
			if (StringUtility.isNotEmpty(config)) {
				InputStream stream = context.getResourceAsStream(config);
				if (null != stream) {
					p.load(stream);
				} else {
					System.out.println("Not found config file.[" + config + "]");
					return false;
				}
			}
			String job = p.getProperty("server.worker.job");
			if (StringUtility.isEmpty(job)) {
				System.out.println("Not setting job class.[server.worker.job]");
				return false;
			}
			jobClass = (Class<? extends Job>) Class.forName(job);

			String plugin = p.getProperty("server.plugin");
			if (StringUtility.isNotEmpty(plugin)) {
				InputStream stream = context.getResourceAsStream(plugin);
				if (null != stream) {
					PluginManager.initialize();
					PluginManager.load(stream, context);
				}
			}

			String strCount = p.getProperty("server.worker.thread");
			if (StringUtility.isNotEmpty(strCount)) {
				workerCount = Integer.parseInt(strCount);
			}
			stopFile = p.getProperty("server.control.stop.file");

		} catch (IOException ex) {
			fatal(ex);
			return false;
		} catch (ClassNotFoundException ex) {
			fatal(ex);
			return false;
		} catch (PluginServiceException ex) {
			fatal(ex);
			return false;
		}

		try {
			createWorker(jobClass, workerCount);
			startWorker();
			Thread.sleep(1 * 1000);
			while (!isStopWorker()) {
				checkStop();

				Thread.sleep(30 * 1000);
			}
			result = true;
		} catch (IllegalAccessException ex) {
			fatal(ex);
		} catch (InstantiationException ex) {
			fatal(ex);
		} catch (InterruptedException ex) {
			fatal(ex);
		}

		info("Server stop.");
		return result;
	}

	private void checkStop() {
		if (!stopRequest) {
			if (StringUtility.isNotEmpty(stopFile)) {
				File file = new File(stopFile);
				if (file.isFile()) {
					info("Found server stop request file.[" + stopFile + "]");
					for (JobWorker worker : workers) {
						worker.stop();
					}
					stopRequest = true;
				}
			}
		}
	}

	private void createWorker(final Class<? extends Job> aJob, final int aCount) throws IllegalAccessException, InstantiationException {
		info("Create worker.[count: " + aCount + "]");
		Property property = null;
		PropertyFile propertyFile = aJob.getAnnotation(PropertyFile.class);
		if (null != propertyFile) {
			String value = propertyFile.value();
			if (StringUtility.isNotEmpty(value)) {
				property = PropertyManager.get(aJob);
				if (null == property) {
					property = PropertyManager.load(aJob, context);
				}
			}
		}

		for (int i = 0; i < aCount; i++) {
			Job job = aJob.newInstance();
			if (job instanceof SessionSupport) {
				((SessionSupport) job).setSession(new JobSessionStore());
			}
			if (job instanceof ContextSupport) {
				((ContextSupport) job).setContext(context);
			}
			if (null != property) {
				if (job instanceof PropertySupport) {
					((PropertySupport) job).setProperty(property);
				} else {
					warn("This job is not property support.[" + job.getClass().getName() + "]");
				}
			}
			JobWorker worker = new JobWorker(job);
			workers.add(worker);
		}
	}

	private void startWorker() {
		info("Start worker.");
		for (JobWorker worker : workers) {
			Thread t = new Thread(worker);
			t.start();
		}
	}

	private boolean isStopWorker() {
		int runWorker = 0;
		for (JobWorker worker : workers) {
			if (worker.isWorking()) {
				runWorker++;
			}
		}

		if (0 == runWorker) {
			info("All worker stop.");
			return true;
		} else {
			if (stopRequest) {
				info("Stop waiting of worker.(" + (workers.size() - runWorker) + "/" + workers.size() + ")");
			}
			return false;
		}
	}
}
