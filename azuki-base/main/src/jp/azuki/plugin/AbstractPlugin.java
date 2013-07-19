package jp.azuki.plugin;

import java.io.IOException;

import jp.azuki.core.lang.LoggingObject;
import jp.azuki.persistence.ConfigurationFormatException;
import jp.azuki.persistence.configuration.Configuration;
import jp.azuki.persistence.configuration.ConfigurationSupport;
import jp.azuki.persistence.context.Context;
import jp.azuki.persistence.context.ContextSupport;

/**
 * このクラスは、プラグイン機能の実装を行うための基底クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 12/06/07
 * @author Kawakicchi
 */
public abstract class AbstractPlugin extends LoggingObject implements Plugin, ContextSupport, ConfigurationSupport {

	/**
	 * コンテキスト情報
	 */
	private Context context;

	/**
	 * コンフィグレーション情報
	 */
	private Configuration configuration;

	/**
	 * コンストラクタ
	 */
	public AbstractPlugin() {
		super(Plugin.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractPlugin(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param clazz クラス
	 */
	public AbstractPlugin(final Class<?> aClass) {
		super(aClass);
	}

	@Override
	public final void setContext(final Context aContext) {
		context = aContext;
	}

	/**
	 * コンテキスト情報を取得する。
	 * 
	 * @return コンテキスト情報
	 */
	protected final Context getContext() {
		return context;
	}

	@Override
	public final void setConfiguration(final Configuration aConfiguration) {
		configuration = aConfiguration;
	}

	/**
	 * コンフィグレーション情報を取得する。
	 * 
	 * @return コンフィグレーション情報
	 */
	protected final Configuration getConfiguration() {
		return configuration;
	}

	@Override
	public final void initialize() throws PluginServiceException {
		debug(this.getClass().getSimpleName() + ".initialize()");
		doInitialize();
	}

	@Override
	public final void load() throws PluginServiceException, ConfigurationFormatException, IOException {
		debug(this.getClass().getSimpleName() + ".load()");
		doLoad();
	}

	@Override
	public final void destroy() throws PluginServiceException {
		debug(this.getClass().getSimpleName() + ".destroy()");
		doDestroy();
	}

	/**
	 * 初期化処理を行う。
	 * 
	 * @throws PluginServiceException プラグイン機能に起因する問題が発生した場合
	 */
	protected abstract void doInitialize() throws PluginServiceException;

	/**
	 * 設定情報をロードする。
	 * 
	 * @throws PluginServiceException プラグイン機能に起因する問題が発生した場合
	 * @throws ConfigurationFormatException 設定ファイルに問題がある場合
	 * @throws IOException IO操作時に問題が発生した場合
	 */
	protected abstract void doLoad() throws PluginServiceException, ConfigurationFormatException, IOException;

	/**
	 * 解放処理を行う。
	 * 
	 * @throws PluginServiceException プラグイン機能に起因する問題が発生した場合
	 */
	protected abstract void doDestroy() throws PluginServiceException;

}
