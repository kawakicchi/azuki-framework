package jp.azuki.core.log;

/**
 * このインターフェースは、ログ機能を表現するインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/08/12
 * @author Kawakicchi
 */
public interface Logger {

	/**
	 * ログ(DEBUG)を出力します。
	 * 
	 * @param message Message
	 */
	public void debug(final String message);

	/**
	 * ログ(DEBUG)を出力します。
	 * 
	 * @param throwable Throwable
	 */
	public void debug(final Throwable throwable);

	/**
	 * ログ(DEBUG)を出力します。
	 * 
	 * @param message Message
	 * @param throwable Throwable
	 */
	public void debug(final String message, final Throwable throwable);

	/**
	 * ログ(INFO)を出力します。
	 * 
	 * @param message Message
	 */
	public void info(final String message);

	/**
	 * ログ(INFO)を出力します。
	 * 
	 * @param throwable Throwable
	 */
	public void info(final Throwable throwable);

	/**
	 * ログ(INFO)を出力します。
	 * 
	 * @param message Message
	 * @param throwable Throwable
	 */
	public void info(final String message, final Throwable throwable);

	/**
	 * ログ(WARN)を出力します。
	 * 
	 * @param message Message
	 */
	public void warn(final String message);

	/**
	 * ログ(WARN)を出力します。
	 * 
	 * @param throwable Throwable
	 */
	public void warn(final Throwable throwable);

	/**
	 * ログ(WARN)を出力します。
	 * 
	 * @param message Message
	 * @param throwable Throwable
	 */
	public void warn(final String message, final Throwable throwable);

	/**
	 * ログ(ERROR)を出力します。
	 * 
	 * @param message Message
	 */
	public void error(final String message);

	/**
	 * ログ(ERROR)を出力します。
	 * 
	 * @param throwable Throwable
	 */
	public void error(final Throwable throwable);

	/**
	 * ログ(ERROR)を出力します。
	 * 
	 * @param message Message
	 * @param throwable Throwable
	 */
	public void error(final String message, final Throwable throwable);

	/**
	 * ログ(FATAL)を出力します。
	 * 
	 * @param message Message
	 */
	public void fatal(final String message);

	/**
	 * ログ(FATAL)を出力します。
	 * 
	 * @param throwable Throwable
	 */
	public void fatal(final Throwable throwable);

	/**
	 * ログ(FATAL)を出力します。
	 * 
	 * @param message Message
	 * @param throwable Throwable
	 */
	public void fatal(final String message, final Throwable throwable);
}
