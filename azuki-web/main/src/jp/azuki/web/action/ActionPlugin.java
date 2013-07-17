package jp.azuki.web.action;

import java.io.IOException;
import java.io.InputStream;

import jp.azuki.plugin.AbstractPlugin;
import jp.azuki.plugin.PluginServiceException;

/**
 * このクラスは、アクション機能をサポートするためのプラグインクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/18
 * @author Kawakicchi
 */
public class ActionPlugin extends AbstractPlugin {

	@Override
	public String getName() {
		return null;
	}

	@Override
	protected void doInitialize() throws PluginServiceException {
		ActionManager.initialize();
	}

	@Override
	protected void doDestroy() throws PluginServiceException {
		ActionManager.destroy();
	}

	@Override
	protected void doLoad(final InputStream aStream) throws PluginServiceException, IOException {
		ActionManager.load(aStream, getContext());
	}

}
