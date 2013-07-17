package jp.azuki.web.action;

import java.sql.SQLException;

import jp.azuki.persistence.database.DatabaseConnection;
import jp.azuki.persistence.database.DatabaseConnectionManager;
import jp.azuki.persistence.database.DatabaseSource;
<<<<<<< HEAD
=======
import jp.azuki.web.WebServiceException;
import jp.azuki.web.view.View;
>>>>>>> branch 'master' of https://github.com/kawakicchi/azuki-framework.git

/**
 * このクラスは、データベース機能を実装するアクションクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/13
 * @author Kawakicchi
 */
public abstract class AbstractDatabaseAction extends AbstractPersistenceAction {

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
	public AbstractDatabaseAction() {
		super();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractDatabaseAction(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractDatabaseAction(final Class<?> aClass) {
		super(aClass);
	}

	@Override
	protected void doBeforeAction() {
		super.doBeforeAction();
		// TODO Write doBeforeExecute code.

	}

	@Override
	protected void doAfterAction() {
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

		super.doAfterAction();
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
