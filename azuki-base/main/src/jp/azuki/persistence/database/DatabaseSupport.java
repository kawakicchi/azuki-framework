package jp.azuki.persistence.database;

import java.sql.Connection;

/**
 * このインターフェースは、データベース機能のサポートを行うためのインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/13
 * @author Kawakicchi
 */
public interface DatabaseSupport {

	/**
	 * コネクションを設定する。
	 * 
	 * @param aConnection コネクション
	 */
	public void setConnection(final Connection aConnection);
}
