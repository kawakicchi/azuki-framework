package jp.azuki.business.logic;

import java.io.IOException;

import jp.azuki.business.BusinessServiceException;
import jp.azuki.plugin.AbstractPlugin;
import jp.azuki.plugin.PluginServiceException;

/**
 * このクラスは、ロジック機能をサポートするためのプラグインクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/09/21
 * @author Kawakicchi
 */
public final class LogicPlugin extends AbstractPlugin {

	/**
	 * コンストラクタ
	 */
	public LogicPlugin() {

	}

	@Override
	protected void doInitialize() throws PluginServiceException {
		LogicManager.initialize();
	}

	@Override
	protected void doDestroy() throws PluginServiceException {
		LogicManager.destroy();
	}

	@Override
	protected void doLoad() throws PluginServiceException, IOException {
		try {
			LogicManager.load(getConfiguration().getResourceAsStream(), getContext());
		} catch (BusinessServiceException ex) {
			throw new PluginServiceException(ex);
		}
	}
}
