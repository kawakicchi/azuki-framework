package jp.azuki.plugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jp.azuki.core.lang.LoggingObject;
import jp.azuki.core.util.StringUtility;
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
public final class PluginManager extends LoggingObject {

	/**
	 * Instance
	 */
	private static final PluginManager INSTANCE = new PluginManager();

	/**
	 * plugin entity list
	 */
	private List<PluginEntity> pluginList;

	/**
	 * plugin list
	 */
	private List<Plugin> plugins;

	/**
	 * コンストラクタ
	 * <p>
	 * インスタンス生成を禁止
	 * </p>
	 */
	private PluginManager() {
		super(PluginManager.class);
		plugins = new ArrayList<Plugin>();
	}

	/**
	 * 初期か処理を行います。
	 */
	public synchronized static void initialize() {
		INSTANCE.doInitialize();
	}

	/**
	 * プラグイン情報をロードします。
	 * 
	 * @param file プラグイン情報
	 * @param context コンテキスト
	 * @throws PluginServiceException プラグイン機能に起因する問題が発生した場合
	 * @throws IOException 入出力操作に起因する問題が発生した場合
	 */
	public synchronized static void load(final String file, final Context context) throws PluginServiceException, IOException {
		INSTANCE.doLoad(context.getResourceAsStream(file), context);
	}

	/**
	 * プラグイン情報をロードします。
	 * 
	 * @param stream プラグイン情報
	 * @param context コンテキスト
	 * @throws PluginServiceException プラグイン機能に起因する問題が発生した場合
	 * @throws IOException 入出力操作に起因する問題が発生した場合
	 */
	public synchronized static void load(final InputStream stream, final Context context) throws PluginServiceException, IOException {
		INSTANCE.doLoad(stream, context);
	}

	/**
	 * 解放処理を行います。
	 */
	public synchronized static void destroy() {
		INSTANCE.doDestory();
	}

	/**
	 * 初期か処理を行います。
	 */
	private void doInitialize() {
	}

	/**
	 * プラグイン情報をロードします。
	 * 
	 * @param aStream プラグイン情報
	 * @param aContext コンテキスト
	 * @throws PluginServiceException プラグイン機能に起因する問題が発生した場合
	 * @throws IOException 入出力操作に起因する問題が発生した場合
	 */
	@SuppressWarnings("unchecked")
	private void doLoad(final InputStream aStream, final Context aContext) throws PluginServiceException, IOException {

		try {
			Digester digester = new Digester();
			digester.addObjectCreate("azuki/plugin-list", ArrayList.class);
			digester.addObjectCreate("azuki/plugin-list/plugin", PluginEntity.class);
			digester.addSetProperties("azuki/plugin-list/plugin");
			digester.addSetNext("azuki/plugin-list/plugin", "add");
			pluginList = digester.parse(aStream);
		} catch (SAXException ex) {
			error(ex);
			throw new PluginServiceException(ex);
		} catch (IOException ex) {
			error(ex);
			throw ex;
		}

		try {
			for (int i = 0; i < pluginList.size(); i++) {
				PluginEntity p = pluginList.get(i);
				Class<Plugin> clazz = (Class<Plugin>) Class.forName(p.getPlugin());
				Plugin plugin = clazz.newInstance();
				plugins.add(plugin);
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

		for (int i = 0; i < plugins.size(); i++) {
			Plugin plugin = plugins.get(i);
			if (plugin instanceof ContextSupport) {
				((ContextSupport) plugin).setContext(aContext);
			}
		}
		for (int i = 0; i < plugins.size(); i++) {
			Plugin plugin = plugins.get(i);
			plugin.initialize();
		}
		for (int i = 0; i < plugins.size(); i++) {
			Plugin plugin = plugins.get(i);
			String config = pluginList.get(i).getConfig();
			if (StringUtility.isNotEmpty(config)) {
				InputStream stream = aContext.getResourceAsStream(config);
				if (null != stream) {
					plugin.load(stream);
				} else {
					error("Not found config file.[" + config + "]");
				}
			}
		}
	}

	/**
	 * 解放処理を行います。
	 */
	private void doDestory() {
		for (Plugin plugin : plugins) {
			try {
				plugin.destroy();
			} catch (PluginServiceException ex) {
				error(ex);
			}
		}
	}

	/**
	 * このクラスは、プラグイン情報を保持するエンティティクラスです。
	 * 
	 * @since 1.0.0
	 * @version 1.0.0 12/06/14
	 * @author Kawakicchi
	 */
	public static class PluginEntity implements Entity {

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
