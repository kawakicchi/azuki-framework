package jp.azuki.plugin.exception;

import jp.azuki.core.exception.PrimitiveException;

/**
 * このクラスは、プラグイン機能の例外を表現する例外クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 12/06/07
 * @author Kawakicchi
 */
public class PluginServiceException extends PrimitiveException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8783828565642263411L;

	/**
	 * コンストラクタ
	 * 
	 * @param message Message
	 */
	public PluginServiceException(final String message) {
		super(message);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param throwable Throwable
	 */
	public PluginServiceException(final Throwable throwable) {
		super(throwable);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param message Message
	 * @param throwable Throwable
	 */
	public PluginServiceException(final String message, final Throwable throwable) {
		super(message, throwable);
	}
}
