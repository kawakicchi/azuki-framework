package jp.azuki.persistence.proterty;

import java.util.Map;

/**
 * このインターフェースは、プロパティ機能のサポートを行うためのインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/01
 * @author Kawakicchi
 */
public interface PropertySupport {

	/**
	 * プロパティを設定する。
	 * 
	 * @param aKey キー
	 * @param aValue 値
	 */
	public void setProperty(final String aKey, final Object aValue);

	/**
	 * プロパティを設定する。
	 * <p>
	 * マップの情報を設定する。
	 * </p>
	 * 
	 * @param aValues マップ
	 */
	public void setProperties(final Map<String, Object> aValues);
}
