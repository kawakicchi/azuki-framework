package jp.azuki.business.dsql;

import java.io.IOException;

import jp.azuki.plugin.AbstractPlugin;
import jp.azuki.plugin.PluginServiceException;

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
	protected void doLoad() throws PluginServiceException, IOException {
		DynamicSQLManager.load(getConfiguration().getResourceAsStream(), getContext());
	}

}
