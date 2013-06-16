package jp.azuki.persistence.exception;

import jp.azuki.core.exception.PrimitiveException;

/**
 * このクラスは、永続化層機能の例外を表現する例外クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 12/06/07
 * @author Kawakicchi
 */
public class PersistenceServiceException extends PrimitiveException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2661768390147559735L;

	/**
	 * コンストラクタ
	 * 
	 * @param message Message
	 */
	public PersistenceServiceException(final String message) {
		super(message);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param throwable Throwable
	 */
	public PersistenceServiceException(final Throwable throwable) {
		super(throwable);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param message Message
	 * @param throwable Throwable
	 */
	public PersistenceServiceException(final String message, final Throwable throwable) {
		super(message, throwable);
	}
}
