package jp.azuki.business.dsql;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * このクラスは、パラメータ情報を保持するクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/14
 * @author Kawakicchi
 */
public final class Parameter {

	/**
	 * parameter map
	 */
	private Map<String, Object> params;

	/**
	 * コンストラクタ
	 */
	public Parameter() {
		params = new HashMap<String, Object>();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aParameter パラメータ
	 */
	public Parameter(final Map<String, Object> aParameter) {
		params = new HashMap<String, Object>(aParameter);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aParameter パラメータ
	 */
	public Parameter(final Parameter aParameter) {
		params = new HashMap<String, Object>(aParameter.params);
	}

	/**
	 * パラメータが存在するか判断する。
	 * 
	 * @param aKey キー
	 * @return パラメータが存在する場合、<code>true</code>を返す。
	 */
	public boolean isKey(final String aKey) {
		return params.containsKey(aKey);
	}

	/**
	 * パラメータを設定する。
	 * 
	 * @param aKey キー
	 * @param aValue 値
	 */
	public void put(final String aKey, final Object aValue) {
		params.put(aKey, aValue);
	}

	/**
	 * パラメータを取得する。
	 * 
	 * @param aKey キー
	 * @return 値
	 */
	public Object get(final String aKey) {
		return params.get(aKey);
	}

	/**
	 * パラメータのキーセットを取得する。
	 * 
	 * @return キーセット
	 */
	public Set<String> keySet() {
		return params.keySet();
	}
}
