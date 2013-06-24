package jp.azuki.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import jp.azuki.persistence.database.DatabaseConnectionManager;
import jp.azuki.persistence.database.DatabaseConnectionSupport;
import jp.azuki.persistence.exception.PersistenceServiceException;
import jp.azuki.web.constant.WebServiceException;
import jp.azuki.web.view.View;

/**
 * このクラスは、データベース機能を実装するアクションクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/13
 * @author Kawakicchi
 */
public abstract class AbstractDatabaseAction extends AbstractPersistenceAction implements DatabaseConnectionSupport {

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
	public AbstractDatabaseAction() {
		super();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName Name
	 */
	public AbstractDatabaseAction(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass Class
	 */
	public AbstractDatabaseAction(final Class<?> aClass) {
		super(aClass);
	}

	@Override
	protected final View doPersistenceAction(final Map<String, Object> aParameter) throws WebServiceException, PersistenceServiceException {
		View result = null;
		try {
			result = doDatabaseAction(aParameter);
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
		return result;
	}

	/**
	 * アクションを実行する。
	 * 
	 * @param aParameter パラメーター
	 * @return ビュー
	 * @throws WebServiceException ウェブサービス層に起因する問題が発生した場合
	 * @throws PersistenceServiceException 永続化層に起因する問題が発生した場合
	 * @throws SQLException SQL操作時に問題が発生した場合
	 */
	protected abstract View doDatabaseAction(final Map<String, Object> aParameter) throws WebServiceException, PersistenceServiceException,
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
