package jp.azuki.job.job;

import java.sql.SQLException;

import jp.azuki.job.exception.JobServiceException;
import jp.azuki.job.parameter.Parameter;
import jp.azuki.job.result.JobResult;
import jp.azuki.persistence.database.DatabaseConnection;
import jp.azuki.persistence.database.DatabaseConnectionManager;
import jp.azuki.persistence.database.DatabaseConnectionSupport;
import jp.azuki.persistence.database.DatabaseSource;
import jp.azuki.persistence.exception.PersistenceServiceException;

/**
 * このクラスは、データベース機能を実装するジョブクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/13
 * @author Kawakicchi
 */
public abstract class AbstractDatabaseJob extends AbstractPersistenceJob implements DatabaseConnectionSupport {

	/**
	 * データベースソース
	 */
	private DatabaseSource source;

	/**
	 * コネクション
	 */
	private DatabaseConnection myConnection;

	/**
	 * チェーンコネクション
	 */
	private DatabaseConnection chainConnection;

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
	protected final JobResult doPersistenceExecute(final Parameter aParameter) throws JobServiceException, PersistenceServiceException {
		JobResult result = null;
		try {
			result = doDatabaseExecute(aParameter);
			if (null != myConnection) {
				myConnection.getConnection().commit();
			}
		} catch (SQLException ex) {
			throw new JobServiceException(ex);
		} finally {
			if (null != source && null != myConnection) {
				try {
					source.returnConnection(myConnection);
				} catch (SQLException ex) {
					warn(ex);
				}
				myConnection = null;
				source = null;
			}
		}
		return result;
	}

	/**
	 * ジョブを実行する。
	 * 
	 * @param aParameter パラメータ情報
	 * @return 結果
	 * @throws JobServiceException ジョブ機能に起因する問題が発生した場合
	 * @throws PersistenceServiceException 永続化層に起因する問題が発生した場合
	 * @throws SQLException SQL操作時に問題が発生した場合
	 */
	protected abstract JobResult doDatabaseExecute(final Parameter aParameter) throws JobServiceException, PersistenceServiceException, SQLException;

	@Override
	public final void setConnection(final DatabaseConnection aConnection) {
		if (null == chainConnection) {
			chainConnection = aConnection;
		} else {
			warn("exist connection.");
		}
	}

	/**
	 * コネクションを取得する。
	 * 
	 * @return コネクション
	 * @throws SQL実行時に問題が発生した場合
	 */
	protected final DatabaseConnection getConnection() throws SQLException {
		if (null != chainConnection) {
			return chainConnection;
		}
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
