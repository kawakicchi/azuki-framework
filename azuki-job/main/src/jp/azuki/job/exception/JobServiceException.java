package jp.azuki.job.exception;

import jp.azuki.core.exception.PrimitiveException;

/**
 * このクラスは、ジョブサービス層に起因する例外クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/01
 * @author Kawakicchi
 */
public class JobServiceException extends PrimitiveException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7386966590550850521L;

	/**
	 * コンストラクタ
	 */
	public JobServiceException() {
		super();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param message Message
	 */
	public JobServiceException(final String message) {
		super(message);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param throwable Throwable
	 */
	public JobServiceException(final Throwable throwable) {
		super(throwable);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param message Message
	 * @param throwable Throwable
	 */
	public JobServiceException(final String message, final Throwable throwable) {
		super(message, throwable);
	}
}
