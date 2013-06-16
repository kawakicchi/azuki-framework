package jp.azuki.persistence.session;

import jp.azuki.persistence.store.Store;

/**
 * このインターフェースは、セッション機能のサポートを行うためのインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/06
 * @author Kawakicchi
 */
public interface SessionSupport {

	/**
	 * セッションを設定する。
	 * 
	 * @param aStore セッション
	 */
	public void setSession(final Store<String, Object> aStore);
}
