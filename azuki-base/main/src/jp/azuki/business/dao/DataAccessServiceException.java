package jp.azuki.business.dao;

import jp.azuki.persistence.PersistenceServiceException;

/**
 * このクラスは、データアクセス機能の例外を表現する例外クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/14
 * @author Kawakicchi
 */
public class DataAccessServiceException extends PersistenceServiceException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2094034570966908024L;

	/**
	 * コンストラクタ
	 * 
	 * @param message Message
	 */
	public DataAccessServiceException(final String message) {
		super(message);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param throwable Throwable
	 */
	public DataAccessServiceException(final Throwable throwable) {
		super(throwable);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param message Message
	 * @param throwable Throwable
	 */
	public DataAccessServiceException(final String message, final Throwable throwable) {
		super(message, throwable);
	}
}
