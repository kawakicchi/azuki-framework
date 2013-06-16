package jp.azuki.web.action.filter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import jp.azuki.persistence.database.DatabaseConnectionManager;
import jp.azuki.persistence.database.DatabaseSupport;
import jp.azuki.persistence.exception.PersistenceServiceException;
import jp.azuki.web.constant.WebServiceException;

/**
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/18
 * @author Kawakicchi
 */
public abstract class AbstractDatabaseFilter extends AbstractPersistenceFilter implements DatabaseSupport {

	/**
	 * コネクション
	 */
	private Connection myConnection;

	/**
	 * チェーンコネクション
	 */
	private Connection chainConnection;

	/**
	 * コンストラクタ
	 */
	public AbstractDatabaseFilter() {
		super();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName Name
	 */
	public AbstractDatabaseFilter(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass Class
	 */
	public AbstractDatabaseFilter(final Class<?> aClass) {
		super(aClass);
	}

	@Override
	protected final void doPersistenceFilter(final Map<String, Object> aParameter) throws WebServiceException, PersistenceServiceException {
		try {
			doDatabaseFilter(aParameter);
			if (null != myConnection) {
				myConnection.commit();
			}
		} catch (SQLException ex) {
			throw new WebServiceException(ex);
		} finally {
			if (null != myConnection) {
				try {
					DatabaseConnectionManager.returnConnection(myConnection);
				} catch (SQLException ex) {
					warn(ex);
				}
				myConnection = null;
			}
		}
	}

	/**
	 * フィルター処理を行う。
	 * 
	 * @param aParameter パラメーター
	 * @throws WebServiceException ウェブサービス層に起因する問題が発生した場合
	 * @throws PersistenceServiceException 永続化層に起因する問題が発生した場合
	 * @throws SQLException SQL操作時に問題が発生した場合
	 */
	protected abstract void doDatabaseFilter(final Map<String, Object> aParameter) throws WebServiceException, PersistenceServiceException,
			SQLException;

	@Override
	public final void setConnection(final Connection aConnection) {
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
	protected final Connection getConnection() throws SQLException {
		if (null != chainConnection) {
			return chainConnection;
		}
		if (null == myConnection) {
			info("Create my connection.");
			myConnection = DatabaseConnectionManager.getConnection();
			myConnection.setAutoCommit(false);
		}
		return myConnection;
	}

	/**
	 * コミット処理を行う。
	 * 
	 * @throws SQLException SQL実行中に問題が発生した場合
	 */
	protected final void commit() throws SQLException {
		getConnection().commit();
	}

	/**
	 * ロールバック処理を行う。
	 * 
	 * @throws SQLException SQL実行中に問題が発生した場合
	 */
	protected final void rollback() throws SQLException {
		getConnection().rollback();
	}
}
