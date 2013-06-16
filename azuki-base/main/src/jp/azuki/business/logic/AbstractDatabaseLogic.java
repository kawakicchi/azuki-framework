package jp.azuki.business.logic;

import java.sql.Connection;
import java.sql.SQLException;

import jp.azuki.persistence.database.DatabaseSupport;

/**
 * このクラスは、データベース機能を実装するロジッククラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/05
 * @author Kawakicchi
 */
public abstract class AbstractDatabaseLogic extends AbstractPersistenceLogic implements DatabaseSupport {

	/**
	 * コネクション
	 */
	private Connection connection;

	/**
	 * コンストラクタ
	 */
	public AbstractDatabaseLogic() {
		super();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractDatabaseLogic(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractDatabaseLogic(final Class<?> aClass) {
		super(aClass);
	}

	@Override
	public final void setConnection(final Connection aConnection) {
		connection = aConnection;
	}

	/**
	 * コネクションを取得する。
	 * 
	 * @return コネクション
	 */
	protected final Connection getConnection() {
		return connection;
	}

	/**
	 * コミット処理を行う。
	 * 
	 * @throws SQLException SQL実行中に問題が発生した場合
	 */
	protected final void commit() throws SQLException {
		connection.commit();
	}

	/**
	 * ロールバック処理を行う。
	 * 
	 * @throws SQLException SQL実行中に問題が発生した場合
	 */
	protected final void rollback() throws SQLException {
		connection.rollback();
	}
}
