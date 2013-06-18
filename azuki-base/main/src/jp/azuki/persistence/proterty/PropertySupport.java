package jp.azuki.persistence.proterty;


/**
 * このインターフェースは、プロパティ機能のサポートを行うためのインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/01
 * @author Kawakicchi
 */
public interface PropertySupport {

	/**
	 * プロパティ情報を設定する。
	 * 
	 * @param aProperty プロパティ情報
	 */
	public void setProperty(final Property aProperty);

}
