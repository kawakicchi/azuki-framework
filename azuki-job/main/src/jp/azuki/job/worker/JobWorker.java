package jp.azuki.job.worker;

import jp.azuki.core.lang.LoggingObject;
import jp.azuki.job.exception.JobServiceException;
import jp.azuki.job.job.Job;
import jp.azuki.job.parameter.Parameter;
import jp.azuki.job.result.JobResult;

/**
 * このクラスは、ジョブ用のワーカークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/15
 * @author Kawakicchi
 */
public class JobWorker extends LoggingObject implements Runnable {

	/**
	 * ジョブ
	 */
	private Job job;

	/**
	 * パラメータ情報
	 */
	private Parameter parameter;

	/**
	 * 稼働フラグ
	 */
	private boolean working;

	/**
	 * 停止依頼
	 */
	private boolean stop;

	/**
	 * コンストラクタ
	 * 
	 * @param aJob ジョブ
	 */
	public JobWorker(final Job aJob) {
		super(JobWorker.class);
		job = aJob;
		working = false;
		stop = false;
		parameter = new Parameter();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aJob ジョブ
	 */
	public JobWorker(final Job aJob, final Parameter aParameter) {
		super(JobWorker.class);
		job = aJob;
		working = false;
		stop = false;
		parameter = aParameter;
	}

	public void stop() {
		info("Request stop.");
		stop = true;
	}

	@Override
	public void run() {
		if (working) {
			warn("Worker has already run.");
			return;
		}
		stop = false;
		working = true;
		info("Worker start.[job: \"" + job.getClass().getName() + "\"]");

		try {

			job.initialize();

			while (!stop) {

				JobResult rslt = job.execute(parameter);

				if (null == rslt) {
					error("JobResult is null.");
					break;
				}
				if (!rslt.isResult()) {
					info("Job result.[error]");
					break;
				}
				if (!rslt.isContinue()) {
					info("Job result.[success]");
					break;
				}
				if (stop) {
					break;
				}
				Thread.sleep(rslt.getWait());
			}

		} catch (JobServiceException ex) {
			fatal(ex);
		} catch (InterruptedException ex) {
			fatal(ex);
		} catch (Exception ex) {
			fatal(ex);
		} finally {

			job.destroy();
		}

		info("Worker stop.");
		working = false;
	}

	/**
	 * ワーカーが稼働中か判断する。
	 * 
	 * @return 稼働中の場合、<code>true</code>を返す。
	 */
	public boolean isWorking() {
		return working;
	}
}
