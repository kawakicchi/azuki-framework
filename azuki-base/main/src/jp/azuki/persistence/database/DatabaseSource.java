package jp.azuki.persistence.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import jp.azuki.persistence.database.entity.DatabaseConnectionEntity;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.StackObjectPool;

/**
 * このクラスは、データベース接続機能を実装するクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 12/06/09
 * @author Kawakicchi
 * 
 */
public final class DatabaseSource {

	/**
	 * Connection entity
	 */
	private DatabaseConnectionEntity entity;

	/**
	 * Datasource
	 */
	private DataSource ds;

	/**
	 * コンストラクタ
	 */
	public DatabaseSource() {
		;
	}

	/**
	 * データベース接続設定をロードします。
	 * 
	 * @param driver ドライバ名
	 * @param uri 接続情報
	 * @param user ユーザ名
	 * @param password パスワード
	 * @throws ClassNotFoundException データベース接続ドライバが見つからない場合
	 */
	public void load(final String driver, final String uri, final String user, final String password) throws ClassNotFoundException {
		DatabaseConnectionEntity e = new DatabaseConnectionEntity();
		e.setDriver(driver);
		e.setUri(uri);
		e.setUser(user);
		e.setPassword(password);
		load(e);
	}

	/**
	 * データベース接続設定をロードします。
	 * 
	 * @param p 接続設定プロパティ
	 * @throws ClassNotFoundException データベース接続ドライバが見つからない場合
	 */
	public void load(final Properties p) throws ClassNotFoundException {
		DatabaseConnectionEntity e = new DatabaseConnectionEntity();
		e.setDriver(p.getProperty("database.dirver"));
		e.setUri(p.getProperty("database.uri"));
		e.setUser(p.getProperty("database.user"));
		e.setPassword(p.getProperty("database.password"));
		load(e);
	}

	/**
	 * データベース接続設定をロードします。
	 * 
	 * @param aEntity 接続設定
	 * @throws ClassNotFoundException データベース接続ドライバが見つからない場合
	 */
	private void load(final DatabaseConnectionEntity aEntity) throws ClassNotFoundException {
		entity = aEntity;
		pooling();
	}

	/**
	 * データベース接続をプールします。
	 * 
	 * @throws ClassNotFoundException データベース接続ドライバが見つからない場合
	 */
	private void pooling() throws ClassNotFoundException {
		Class.forName(entity.getDriver());

		@SuppressWarnings("deprecation")
		ObjectPool pool = new StackObjectPool();

		ConnectionFactory conFactory = new DriverManagerConnectionFactory(entity.getUri(), entity.getUser(), entity.getPassword());
		new PoolableConnectionFactory(conFactory, pool, null, null, false, true);
		ds = new PoolingDataSource(pool);
	}

	/**
	 * コネクションを取得します。
	 * 
	 * @return コネクション
	 * @throws SQLException SQL例外が発生した場合
	 */
	public DatabaseConnection getConnection() throws SQLException {
		return getConnection(true);
	}

	/**
	 * コネクションを取得します。
	 * 
	 * @param pool プールフラグ
	 * @return コネクション
	 * @throws SQLException SQL例外が発生した場合
	 */
	public DatabaseConnection getConnection(final boolean pool) throws SQLException {
		Connection con = null;
		if (pool) {
			con = ds.getConnection();
		} else {
			con = DriverManager.getConnection(entity.getUri(), entity.getUser(), entity.getPassword());
		}
		return new DatabaseConnection(con);
	}

	/**
	 * コネクションを返却します。
	 * 
	 * @param connection コネクション
	 * @throws SQLException SQL例外が発生した場合
	 */
	public void returnConnection(final DatabaseConnection connection) throws SQLException {
		returnConnection(connection, true);
	}

	/**
	 * コネクションを返却します。
	 * 
	 * @param connection コネクション
	 * @param pool プールフラグ
	 * @throws SQLException SQL例外が発生した場合
	 */
	public void returnConnection(final DatabaseConnection connection, final boolean pool) throws SQLException {
		Connection con = connection.getConnection();
		if (null != con) {
			if (pool) {
				con.close();
			} else {
				con.close();
			}
		}
	}
}
