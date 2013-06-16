package jp.azuki.persistence.database;

import java.sql.Connection;
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
	private final Map<String, DatabaseConnection> connections = new HashMap<String, DatabaseConnection>();

	/**
	 * コンストラクタ
	 */
	private DatabaseConnectionManager() {
		;
	}

	public static void initialize() {

	}

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

	public static Connection getConnection() throws SQLException {
		return getConnection(StringUtility.EMPTY);
	}

	public static Connection getConnection(final String name) throws SQLException {
		return INSTANCE.doGetConnection(name);
	}

	public static void returnConnection(final Connection connection) throws SQLException {
		returnConnection(StringUtility.EMPTY, connection);
	}

	public static void returnConnection(final String name, final Connection connection) throws SQLException {
		INSTANCE.doReturnConnection(name, connection);
	}

	private void doLoad(final String name, final Properties p) throws PersistenceServiceException, ClassNotFoundException {
		if (connections.containsKey(name)) {
			throw new PersistenceServiceException("Duplicate database connection name.[" + name + "]");
		}

		DatabaseConnection connection = new DatabaseConnection();
		connection.load(p);

		connections.put(name, connection);
	}

	private void doLoad(final String name, final String driver, final String uri, final String user, final String password)
			throws PersistenceServiceException, ClassNotFoundException {
		if (connections.containsKey(name)) {
			throw new PersistenceServiceException("Duplicate database connection name.[" + name + "]");
		}

		DatabaseConnection connection = new DatabaseConnection();
		connection.load(driver, uri, user, password);

		connections.put(name, connection);
	}

	private Connection doGetConnection(final String name) throws SQLException {
		Connection connection = null;
		if (connections.containsKey(name)) {
			connection = connections.get(name).getConnection();
		}
		return connection;
	}

	private void doReturnConnection(final String name, final Connection connection) throws SQLException {
		if (connections.containsKey(name)) {
			connections.get(name).returnConnection(connection);
		}
	}
}
