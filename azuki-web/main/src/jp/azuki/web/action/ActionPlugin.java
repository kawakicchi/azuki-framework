package jp.azuki.web.action;

import java.io.IOException;

import jp.azuki.plugin.AbstractPlugin;
import jp.azuki.plugin.PluginServiceException;

/**
 * このクラスは、アクション機能をサポートするためのプラグインクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/18
 * @author Kawakicchi
 */
public final class ActionPlugin extends AbstractPlugin {

	/**
	 * コンストラクタ
	 */
	public ActionPlugin() {
		super(ActionPlugin.class);
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
	protected void doLoad() throws PluginServiceException, IOException {
		ActionManager.load(getConfiguration().getResourceAsStream(), getContext());
	}

}
