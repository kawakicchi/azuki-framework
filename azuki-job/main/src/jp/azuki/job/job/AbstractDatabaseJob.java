package jp.azuki.job.job;

import java.sql.SQLException;

import jp.azuki.persistence.database.DatabaseConnection;
import jp.azuki.persistence.database.DatabaseConnectionManager;
import jp.azuki.persistence.database.DatabaseSource;

/**
 * このクラスは、データベース機能を実装するジョブクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/13
 * @author Kawakicchi
 */
public abstract class AbstractDatabaseJob extends AbstractPersistenceJob {

	/**
	 * データベースソース
	 */
	private DatabaseSource source;

	/**
	 * コネクション
	 */
	private DatabaseConnection myConnection;

	/**
	 * コンストラクタ
	 */
	public AbstractDatabaseJob() {
		super();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName Name
	 */
	public AbstractDatabaseJob(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass Class
	 */
	public AbstractDatabaseJob(final Class<?> aClass) {
		super(aClass);
	}

	@Override
	protected void doBeforeExecute() {
		super.doBeforeExecute();

	}

	@Override
	protected void doAfterExecute() {
		try {
			if (null != myConnection) {
				myConnection.getConnection().commit();
			}
		} catch (SQLException ex) {
			fatal(ex);
		}
		if (null != source && null != myConnection) {
			try {
				source.returnConnection(myConnection);
			} catch (SQLException ex) {
				warn(ex);
			}
			myConnection = null;
			source = null;
		}

		super.doAfterExecute();
	}

	/**
	 * コネクションを取得する。
	 * 
	 * @return コネクション
	 * @throws SQL実行時に問題が発生した場合
	 */
	protected final DatabaseConnection getConnection() throws SQLException {
		if (null == myConnection) {
			info("Create my connection.");
			source = DatabaseConnectionManager.getSource();
			myConnection = source.getConnection();
			if (null != myConnection) {
				myConnection.getConnection().setAutoCommit(false);
			}
		}
		return myConnection;
	}

	/**
	 * コミット処理を行う。
	 * 
	 * @throws SQLException SQL実行中に問題が発生した場合
	 */
	protected final void commit() throws SQLException {
		DatabaseConnection connection = getConnection();
		if (null != connection) {
			connection.getConnection().commit();
		}
	}

	/**
	 * ロールバック処理を行う。
	 * 
	 * @throws SQLException SQL実行中に問題が発生した場合
	 */
	protected final void rollback() throws SQLException {
		DatabaseConnection connection = getConnection();
		if (null != connection) {
			connection.getConnection().rollback();
		}
	}
}
