package jp.azuki.persistence.context;

/**
 * このインターフェースは、コンテキスト機能のサポートを行うためのインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/13
 * @author Kawakicchi
 */
public interface ContextSupport {

	/**
	 * コンテキストを設定する。
	 * 
	 * @param context コンテキスト
	 */
	public void setContext(final Context context);
}
