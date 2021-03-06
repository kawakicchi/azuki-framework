package jp.azuki.persistence.database;

import java.io.IOException;
import java.util.Properties;

import jp.azuki.persistence.PersistenceServiceException;
import jp.azuki.plugin.AbstractPlugin;
import jp.azuki.plugin.PluginServiceException;

/**
 * このクラスは、データベース機能をサポートするためのプラグインクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/15
 * @author Kawakicchi
 */
public final class DatabasePlugin extends AbstractPlugin {

	/**
	 * コンストラクタ
	 */
	public DatabasePlugin() {

	}

	@Override
	protected void doInitialize() throws PluginServiceException {
		DatabaseConnectionManager.initialize();
	}

	@Override
	protected void doDestroy() throws PluginServiceException {
		DatabaseConnectionManager.destroy();
	}

	@Override
	protected void doLoad() throws PluginServiceException, IOException {
		try {
			Properties p = new Properties();
			p.load(getConfiguration().getResourceAsStream());
			DatabaseConnectionManager.load(p);
		} catch (ClassNotFoundException ex) {
			throw new PluginServiceException(ex);
		} catch (PersistenceServiceException ex) {
			throw new PluginServiceException(ex);
		}
	}

}
