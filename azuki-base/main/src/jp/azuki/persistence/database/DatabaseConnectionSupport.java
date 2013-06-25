package jp.azuki.persistence.database;

/**
 * このインターフェースは、データベースコネクション機能のサポートを行うためのインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/13
 * @author Kawakicchi
 */
public interface DatabaseConnectionSupport {

	/**
	 * コネクション情報を設定する。
	 * 
	 * @param aConnection コネクション情報
	 */
	public void setConnection(final DatabaseConnection aConnection);
}
