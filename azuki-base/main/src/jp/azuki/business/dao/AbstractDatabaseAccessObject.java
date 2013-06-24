package jp.azuki.business.dao;

import java.sql.Connection;

import jp.azuki.persistence.database.DatabaseConnectionSupport;

/**
 * このクラスは、データベース機能を備えたデータアクセスオブジェクトクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/14
 * @author Kawakicchi
 */
public abstract class AbstractDatabaseAccessObject extends AbstractDataAccessObject implements DatabaseConnectionSupport {

	/**
	 * コネクション
	 */
	private Connection connection;

	/**
	 * コンストラクタ
	 */
	public AbstractDatabaseAccessObject() {
		super();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName Name
	 */
	public AbstractDatabaseAccessObject(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass Class
	 */
	public AbstractDatabaseAccessObject(final Class<?> aClass) {
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

}
