package jp.azuki.core.log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import jp.azuki.plugin.AbstractPlugin;
import jp.azuki.plugin.exception.PluginServiceException;

/**
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/22
 * @author Kawakicchi
 */
public class LoggerPlugin extends AbstractPlugin {

	@Override
	protected void doInitialize() throws PluginServiceException {
	}

	@Override
	protected void doDestroy() throws PluginServiceException {
	}

	@Override
	protected void doLoad(final InputStream aStream) throws PluginServiceException, IOException {
		Properties p = new Properties();
		p.load(aStream);
		// String loggerClass = p.getProperty("logger.class");
		String loggerConfig = p.getProperty("logger.config");

		LoggerFactory.load(loggerConfig, getContext());
	}

}
