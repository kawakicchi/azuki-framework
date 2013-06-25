package jp.azuki.persistence.database;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import jp.azuki.core.util.StringUtility;
import jp.azuki.persistence.exception.PersistenceServiceException;

/**
 * このクラスは、データベースの管理を行うマネージャークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 12/06/09
 * @author Kawakicchi
 * 
 */
public final class DatabaseConnectionManager {

	/**
	 * Instance
	 */
	private static final DatabaseConnectionManager INSTANCE = new DatabaseConnectionManager();

	/**
	 * Connection map
	 */
	private final Map<String, DatabaseSource> connections = new HashMap<String, DatabaseSource>();

	/**
	 * コンストラクタ
	 */
	private DatabaseConnectionManager() {
		;
	}

	/**
	 * 初期化処理を行う。
	 */
	public static void initialize() {

	}

	/**
	 * 解放処理を行う。
	 */
	public static void destroy() {

	}

	public static void load(final String uri, final String driver, final String user, final String password) throws PersistenceServiceException,
			ClassNotFoundException {
		load(StringUtility.EMPTY, driver, uri, user, password);
	}

	public static void load(final Properties p) throws PersistenceServiceException, ClassNotFoundException {
		load(StringUtility.EMPTY, p);
	}

	public static void load(final String name, final String driver, final String uri, final String user, final String password)
			throws PersistenceServiceException, ClassNotFoundException {
		INSTANCE.doLoad(name, driver, uri, user, password);
	}

	public static void load(final String name, final Properties p) throws PersistenceServiceException, ClassNotFoundException {
		INSTANCE.doLoad(name, p);
	}

	public static DatabaseSource getSource() throws SQLException {
		return getSource(StringUtility.EMPTY);
	}

	public static DatabaseSource getSource(final String name) throws SQLException {
		return INSTANCE.doGetSource(name);
	}

	private void doLoad(final String name, final Properties p) throws PersistenceServiceException, ClassNotFoundException {
		if (connections.containsKey(name)) {
			throw new PersistenceServiceException("Duplicate database connection name.[" + name + "]");
		}

		DatabaseSource connection = new DatabaseSource();
		connection.load(p);

		connections.put(name, connection);
	}

	private void doLoad(final String name, final String driver, final String uri, final String user, final String password)
			throws PersistenceServiceException, ClassNotFoundException {
		if (connections.containsKey(name)) {
			throw new PersistenceServiceException("Duplicate database connection name.[" + name + "]");
		}

		DatabaseSource connection = new DatabaseSource();
		connection.load(driver, uri, user, password);

		connections.put(name, connection);
	}

	private DatabaseSource doGetSource(final String name) throws SQLException {
		DatabaseSource connection = null;
		if (connections.containsKey(name)) {
			connection = connections.get(name);
		}
		return connection;
	}

}
