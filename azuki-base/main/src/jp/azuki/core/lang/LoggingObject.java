package jp.azuki.core.lang;

import jp.azuki.core.log.Logger;
import jp.azuki.core.log.LoggerFactory;
import jp.azuki.core.log.LoggerSupport;

/**
 * このクラスは、オブジェクトの基底となるクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/06/14
 * @author Kawakicchi
 */
public abstract class LoggingObject extends PrimitiveObject implements LoggerSupport {

	/**
	 * Logger
	 */
	private Logger logger;

	/**
	 * コンストラクタ
	 */
	public LoggingObject() {
		logger = LoggerFactory.create();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param name Name
	 */
	public LoggingObject(final String name) {
		logger = LoggerFactory.create(name);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param clazz Class
	 */
	public LoggingObject(final Class<?> clazz) {
		logger = LoggerFactory.create(clazz);
	}

	/**
	 * ロガーを設定します。
	 * 
	 * @param aLogger ロガー
	 */
	public void setLogger(final Logger aLogger) {
		logger = aLogger;
	}

	/**
	 * ログ(DEBUG)を出力します。
	 * 
	 * @param message Message
	 */
	protected final void debug(final String message) {
		logger.debug(message);
	}

	/**
	 * ログ(DEBUG)を出力します。
	 * 
	 * @param throwable Throwable
	 */
	protected final void debug(final Throwable throwable) {
		logger.debug(throwable);
	}

	/**
	 * ログ(DEBUG)を出力します。
	 * 
	 * @param message Message
	 * @param throwable Throwable
	 */
	protected final void debug(final String message, final Throwable throwable) {
		logger.debug(message, throwable);
	}

	/**
	 * ログ(INFO)を出力します。
	 * 
	 * @param message Message
	 */
	protected final void info(final String message) {
		logger.info(message);
	}

	/**
	 * ログ(INFO)を出力します。
	 * 
	 * @param throwable Throwable
	 */
	protected final void info(final Throwable throwable) {
		logger.info(throwable);
	}

	/**
	 * ログ(INFO)を出力します。
	 * 
	 * @param message Message
	 * @param throwable Throwable
	 */
	protected final void info(final String message, final Throwable throwable) {
		logger.info(message, throwable);
	}

	/**
	 * ログ(WARN)を出力します。
	 * 
	 * @param message Message
	 */
	protected final void warn(final String message) {
		logger.warn(message);
	}

	/**
	 * ログ(WARN)を出力します。
	 * 
	 * @param throwable Throwable
	 */
	protected final void warn(final Throwable throwable) {
		logger.warn(throwable);
	}

	/**
	 * ログ(WARN)を出力します。
	 * 
	 * @param message Message
	 * @param throwable Throwable
	 */
	protected final void warn(final String message, final Throwable throwable) {
		logger.warn(message, throwable);
	}

	/**
	 * ログ(ERROR)を出力します。
	 * 
	 * @param message Message
	 */
	protected final void error(final String message) {
		logger.error(message);
	}

	/**
	 * ログ(ERROR)を出力します。
	 * 
	 * @param throwable Throwable
	 */
	protected final void error(final Throwable throwable) {
		logger.error(throwable);
	}

	/**
	 * ログ(ERROR)を出力します。
	 * 
	 * @param message Message
	 * @param throwable Throwable
	 */
	protected final void error(final String message, final Throwable throwable) {
		logger.error(message, throwable);
	}

	/**
	 * ログ(FATAL)を出力します。
	 * 
	 * @param message Message
	 */
	protected final void fatal(final String message) {
		logger.fatal(message);
	}

	/**
	 * ログ(FATAL)を出力します。
	 * 
	 * @param throwable Throwable
	 */
	protected final void fatal(final Throwable throwable) {
		logger.fatal(throwable);
	}

	/**
	 * ログ(FATAL)を出力します。
	 * 
	 * @param message Message
	 * @param throwable Throwable
	 */
	protected final void fatal(final String message, final Throwable throwable) {
		logger.fatal(message, throwable);
	}
}
