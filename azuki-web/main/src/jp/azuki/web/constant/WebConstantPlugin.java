package jp.azuki.web.constant;

import java.io.IOException;
import java.util.Properties;

import jp.azuki.plugin.AbstractPlugin;
import jp.azuki.plugin.PluginServiceException;

/**
 * このクラスは、Web定数をサポートするためのプラグインクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public class WebConstantPlugin extends AbstractPlugin {

	@Override
	protected void doInitialize() throws PluginServiceException {

	}

	@Override
	protected void doDestroy() throws PluginServiceException {

	}

	@Override
	protected void doLoad() throws PluginServiceException, IOException {
		Properties properties = new Properties();
		properties.load(getConfiguration().getResourceAsStream());
		WebConstant.load(properties);
	}

}
