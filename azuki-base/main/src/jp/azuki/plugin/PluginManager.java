package jp.azuki.plugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jp.azuki.business.manager.AbstractManager;
import jp.azuki.core.util.StringUtility;
import jp.azuki.persistence.ConfigurationFormatException;
import jp.azuki.persistence.configuration.Configuration;
import jp.azuki.persistence.configuration.ConfigurationSupport;
import jp.azuki.persistence.configuration.InputStreamConfiguration;
import jp.azuki.persistence.context.Context;
import jp.azuki.persistence.context.ContextSupport;
import jp.azuki.persistence.entity.Entity;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

/**
 * このクラスは、プラグインの管理を行うマネージャークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 12/06/07
 * @author Kawakicchi
 */
public final class PluginManager extends AbstractManager {

	/**
	 * Instance
	 */
	private static final PluginManager INSTANCE = new PluginManager();

	/**
	 * plugin list
	 */
	private List<PluginEntity> plugins;

	/**
	 * コンストラクタ
	 * <p>
	 * インスタンス生成を禁止
	 * </p>
	 */
	private PluginManager() {
		super(PluginManager.class);
		plugins = new ArrayList<PluginEntity>();
	}

	/**
	 * 初期か処理を行います。
	 */
	public static void initialize() {
		INSTANCE.doInitialize();
	}

	/**
	 * 解放処理を行います。
	 */
	public static void destroy() {
		INSTANCE.doDestory();
	}

	/**
	 * プラグイン情報をロードします。
	 * 
	 * @param file プラグイン情報
	 * @param context コンテキスト
	 * @throws PluginServiceException プラグイン機能に起因する問題が発生した場合
	 * @throws ConfigurationFormatException 設定ファイルに問題がある場合
	 * @throws IOException 入出力操作に起因する問題が発生した場合
	 */
	public static void load(final String file, final Context context) throws PluginServiceException, ConfigurationFormatException, IOException {
		INSTANCE.doLoad(context.getResourceAsStream(file), context);
	}

	/**
	 * プラグイン情報をロードします。
	 * 
	 * @param stream プラグイン情報
	 * @param context コンテキスト
	 * @throws PluginServiceException プラグイン機能に起因する問題が発生した場合
	 * @throws ConfigurationFormatException 設定ファイルに問題がある場合
	 * @throws IOException 入出力操作に起因する問題が発生した場合
	 */
	public static void load(final InputStream stream, final Context context) throws PluginServiceException, ConfigurationFormatException, IOException {
		INSTANCE.doLoad(stream, context);
	}

	/**
	 * プラグイン情報リストを取得する。
	 * 
	 * @return プラグイン情報リスト
	 */
	public static List<PluginEntity> getPluginList() {
		return INSTANCE.doGetPluginList();
	}

	/**
	 * 初期か処理を行います。
	 */
	private void doInitialize() {
		synchronized (plugins) {

		}
	}

	/**
	 * 解放処理を行います。
	 */
	private void doDestory() {
		synchronized (plugins) {
			for (PluginEntity plugin : plugins) {
				try {
					plugin.getPlugin().destroy();
				} catch (PluginServiceException ex) {
					error(ex);
				}
			}
			plugins.clear();
		}
	}

	/**
	 * プラグイン情報をロードします。
	 * 
	 * @param aStream プラグイン情報
	 * @param aContext コンテキスト
	 * @throws PluginServiceException プラグイン機能に起因する問題が発生した場合
	 * @throws ConfigurationFormatException 設定ファイルに問題がある場合
	 * @throws IOException 入出力操作に起因する問題が発生した場合
	 */
	@SuppressWarnings("unchecked")
	private void doLoad(final InputStream aStream, final Context aContext) throws PluginServiceException, ConfigurationFormatException, IOException {
		synchronized (plugins) {
			// Load plugin xml file.
			List<PluginXmlEntity> pluginList;
			try {
				Digester digester = new Digester();
				digester.addObjectCreate("azuki/plugin-list", ArrayList.class);
				digester.addObjectCreate("azuki/plugin-list/plugin", PluginXmlEntity.class);
				digester.addSetProperties("azuki/plugin-list/plugin");
				digester.addSetNext("azuki/plugin-list/plugin", "add");
				pluginList = digester.parse(aStream);
			} catch (SAXException ex) {
				error(ex);
				throw new ConfigurationFormatException(ex);
			} catch (IOException ex) {
				error(ex);
				throw ex;
			}

			try {
				for (int i = 0; i < pluginList.size(); i++) {
					PluginXmlEntity p = pluginList.get(i);
					Class<Plugin> clazz = (Class<Plugin>) Class.forName(p.getPlugin());
					Plugin plugin = clazz.newInstance();

					PluginEntity pe = new PluginEntity();
					pe.name = p.getName();
					pe.config = p.getConfig();
					pe.plugin = plugin;
					plugins.add(pe);
				}
			} catch (ClassNotFoundException ex) {
				error(ex);
				throw new PluginServiceException(ex);
			} catch (IllegalAccessException ex) {
				error(ex);
				throw new PluginServiceException(ex);
			} catch (InstantiationException ex) {
				error(ex);
				throw new PluginServiceException(ex);
			}

			// Support
			for (int i = 0; i < plugins.size(); i++) {
				Plugin plugin = plugins.get(i).getPlugin();
				// Support context
				if (plugin instanceof ContextSupport) {
					((ContextSupport) plugin).setContext(aContext);
				}
				// Support configuration
				if (plugin instanceof ConfigurationSupport) {
					String config = plugins.get(i).config;
					if (StringUtility.isNotEmpty(config)) {
						Configuration configuration = new InputStreamConfiguration(aContext.getResourceAsStream(config));
						((ConfigurationSupport) plugin).setConfiguration(configuration);
					} else {
						warn("Not setting config file.[" + plugins.get(i).getName() + "]");
					}
				}
			}

			// initialize
			for (int i = 0; i < plugins.size(); i++) {
				Plugin plugin = plugins.get(i).getPlugin();
				plugin.initialize();
			}

			// load
			for (int i = 0; i < plugins.size(); i++) {
				Plugin plugin = plugins.get(i).getPlugin();
				plugin.load();
			}
		}
	}

	/**
	 * プラグイン情報リストを取得する。
	 * 
	 * @return プラグイン情報リスト
	 */
	private List<PluginEntity> doGetPluginList() {
		return plugins;
	}

	/**
	 * このクラスは、プラグイン情報を保持するエンティティクラスです。
	 * 
	 * @since 1.0.0
	 * @version 1.0.0 12/07/16
	 * @author Kawakicchi
	 */
	public static class PluginEntity implements Entity {

		/**
		 * プラグイン名
		 */
		private String name;

		/**
		 * プラグイン
		 */
		private Plugin plugin;

		/**
		 * config
		 */
		private String config;

		/**
		 * コンストラクタ
		 * 
		 */
		private PluginEntity() {
		}

		/**
		 * プラグイン名を取得する。
		 * 
		 * @return
		 */
		public String getName() {
			return name;
		}

		/**
		 * プラグインを取得する。
		 * 
		 * @return プラグイン
		 */
		public Plugin getPlugin() {
			return plugin;
		}

		@Override
		public boolean isEmpty() {
			return false;
		}
	}

	/**
	 * このクラスは、XMLプラグイン情報を保持するエンティティクラスです。
	 * 
	 * @since 1.0.0
	 * @version 1.0.0 12/06/14
	 * @author Kawakicchi
	 */
	public static class PluginXmlEntity implements Entity {

		/**
		 * プラグイン名
		 */
		private String name;

		/**
		 * プラグインクラス
		 */
		private String clazz;

		/**
		 * 設定ファイル
		 */
		private String config;

		/**
		 * プラグイン名を設定する。
		 * 
		 * @param aName プラグイン名
		 */
		public void setName(final String aName) {
			name = aName;
		}

		/**
		 * プラグインクラスを設定する。
		 * 
		 * @param aClass プラグインクラス
		 */
		public void setPlugin(final String aClass) {
			clazz = aClass;
		}

		/**
		 * 設定ファイルを設定する。
		 * 
		 * @param aConfig 設定ファイル
		 */
		public void setConfig(final String aConfig) {
			config = aConfig;
		}

		/**
		 * プラグイン名を取得する。
		 * 
		 * @return プラグイン名
		 */
		public String getName() {
			return name;
		}

		/**
		 * プラグインクラスを取得する。
		 * 
		 * @return プラグインクラス
		 */
		public String getPlugin() {
			return clazz;
		}

		/**
		 * 設定ファイルを取得する。
		 * 
		 * @return 設定ファイル
		 */
		public String getConfig() {
			return config;
		}

		@Override
		public boolean isEmpty() {
			if (StringUtility.isEmpty(name)) {
				return true;
			}
			if (StringUtility.isEmpty(clazz)) {
				return true;
			}
			if (StringUtility.isEmpty(config)) {
				return true;
			}
			return false;
		}
	}
}
