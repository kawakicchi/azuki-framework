package jp.azuki.business.logic.plugin;

import java.io.IOException;
import java.io.InputStream;

import jp.azuki.business.exception.BusinessServiceException;
import jp.azuki.business.logic.manager.LogicManager;
import jp.azuki.plugin.AbstractPlugin;
import jp.azuki.plugin.exception.PluginServiceException;

/**
 * このクラスは、ロジック機能をサポートするためのプラグインクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/09/21
 * @author Kawakicchi
 */
public final class LogicPlugin extends AbstractPlugin {

	@Override
	protected void doInitialize() throws PluginServiceException {
		LogicManager.initialize();
	}

	@Override
	protected void doDestroy() throws PluginServiceException {
		LogicManager.destroy();
	}

	@Override
	protected void doLoad(final InputStream stream) throws PluginServiceException, IOException {
		try {
			LogicManager.load(stream, getContext());
		} catch (BusinessServiceException ex) {
			throw new PluginServiceException(ex);
		}
	}
}
