package jp.azuki.core.log;

/**
 * このインターフェースは、ログ機能のサポートを行うためのインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/09
 * @author Kawakicchi
 */
public interface LoggerSupport {

	/**
	 * ロガーを設定する。
	 * 
	 * @param aLogger ロガー
	 */
	public void setLogger(final Logger aLogger);
}
