package jp.azuki.business.dsql.plugin;

import java.io.IOException;
import java.io.InputStream;

import jp.azuki.business.dsql.manager.DynamicSQLManager;
import jp.azuki.plugin.AbstractPlugin;
import jp.azuki.plugin.exception.PluginServiceException;

/**
 * このクラスは、ダイナミックSQL機能をサポートするためのプラグインクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/05
 * @author Kawakicchi
 */
public final class DynamicSQLPlugin extends AbstractPlugin {

	/**
	 * コンストラクタ
	 */
	public DynamicSQLPlugin() {

	}

	@Override
	protected void doInitialize() throws PluginServiceException {
		DynamicSQLManager.initialize();
	}

	@Override
	protected void doDestroy() throws PluginServiceException {
		DynamicSQLManager.destroy();
	}

	@Override
	protected void doLoad(final InputStream aStream) throws PluginServiceException, IOException {
		DynamicSQLManager.load(aStream, getContext());
	}

}
