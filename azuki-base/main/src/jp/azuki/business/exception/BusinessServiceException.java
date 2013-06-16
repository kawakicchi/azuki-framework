package jp.azuki.business.exception;

import jp.azuki.core.exception.PrimitiveException;

/**
 * このクラスは、ビジネスサービス層に起因する例外クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/12/30
 * @author Kawakicchi
 */
public class BusinessServiceException extends PrimitiveException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3052128492508534650L;

	/**
	 * コンストラクタ
	 */
	public BusinessServiceException() {
		super();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param message Message
	 */
	public BusinessServiceException(final String message) {
		super(message);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param throwable Throwable
	 */
	public BusinessServiceException(final Throwable throwable) {
		super(throwable);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param message Message
	 * @param throwable Throwable
	 */
	public BusinessServiceException(final String message, final Throwable throwable) {
		super(message, throwable);
	}
}
