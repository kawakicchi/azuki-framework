package jp.azuki.web.tags;

import java.util.Map;

/**
 * このインターフェースは、パラメーター機能のサポートを行うためのインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/31
 * @author Kawakicchi
 */
public interface ParameterTagSupport {

	/**
	 * パラメーターを設定する。
	 * 
	 * @param aKey キー
	 * @param aValue 値
	 */
	public void setParameter(final String aKey, final Object aValue);

	/**
	 * パラメータを設定する。
	 * 
	 * @param aParams パラメータ
	 */
	public void setParameters(final Map<String, Object> aParams);
}
