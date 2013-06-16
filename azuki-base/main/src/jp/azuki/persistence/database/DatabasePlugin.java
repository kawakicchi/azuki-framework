package jp.azuki.persistence.database;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import jp.azuki.persistence.exception.PersistenceServiceException;
import jp.azuki.plugin.AbstractPlugin;
import jp.azuki.plugin.exception.PluginServiceException;

/**
 * このクラスは、データベース機能をサポートするためのプラグインクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/15
 * @author Kawakicchi
 */
public class DatabasePlugin extends AbstractPlugin {

	@Override
	protected void doInitialize() throws PluginServiceException {
		DatabaseConnectionManager.initialize();
	}

	@Override
	protected void doDestroy() throws PluginServiceException {
		DatabaseConnectionManager.destroy();
	}

	@Override
	protected void doLoad(final InputStream aStream) throws PluginServiceException, IOException {
		try {
			Properties p = new Properties();
			p.load(aStream);
			DatabaseConnectionManager.load(p);
		} catch (ClassNotFoundException ex) {
			throw new PluginServiceException(ex);
		} catch (PersistenceServiceException ex) {
			throw new PluginServiceException(ex);
		}
	}

}
