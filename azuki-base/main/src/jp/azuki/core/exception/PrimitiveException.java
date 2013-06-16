package jp.azuki.core.exception;

/**
 * このクラスは、例外の基底となるクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 12/06/07
 * @author Kawakicchi
 */
public abstract class PrimitiveException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2889735279991264706L;

	/**
	 * コンストラクタ
	 */
	public PrimitiveException() {
		super();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param message Message
	 */
	public PrimitiveException(final String message) {
		super(message);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param throwable Throwable
	 */
	public PrimitiveException(final Throwable throwable) {
		super(throwable);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param message Message
	 * @param throwable Throwable
	 */
	public PrimitiveException(final String message, final Throwable throwable) {
		super(message, throwable);
	}
}
