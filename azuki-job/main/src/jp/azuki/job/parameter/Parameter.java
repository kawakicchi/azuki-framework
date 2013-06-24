package jp.azuki.job.parameter;

import java.util.HashMap;
import java.util.Map;

/**
 * このクラスは、パラメータ情報を保持するクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/06/19
 * @author Kawakicchi
 */
public final class Parameter {

	/**
	 * プロパティ
	 */
	private Map<String, Object> parameter;

	/**
	 * コンストラクタ
	 */
	public Parameter() {
		parameter = new HashMap<String, Object>();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aProperties プロパティ情報
	 */
	public Parameter(final Map<String, Object> aProperties) {
		parameter = new HashMap<String, Object>(aProperties);
	}

	/**
	 * プロパティを取得する。
	 * <p>
	 * 値が存在しない場合、<code>null</code>を返す。
	 * </p>
	 * 
	 * @param aKey キー
	 * @return 値
	 */
	public Object get(final String aKey) {
		return get(aKey, null);
	}

	/**
	 * プロパティを取得する。
	 * <p>
	 * 値が存在しない、または<code>null</code>の場合、デフォルト値を返す。
	 * </p>
	 * 
	 * @param aKey キー
	 * @param aDefault デフォルト値
	 * @return 値
	 */
	public Object get(final String aKey, final Object aDefault) {
		Object value = aDefault;
		if (parameter.containsKey(aKey)) {
			if (null != parameter.get(aKey)) {
				value = parameter.get(aKey);
			}
		}
		return value;
	}

	/**
	 * プロパティを取得する。
	 * <p>
	 * 値が存在しない場合、<code>null</code>を返す。
	 * </p>
	 * 
	 * @param aKey キー
	 * @return 値
	 */
	public String getString(final String aKey) {
		return getString(aKey, null);
	}

	/**
	 * プロパティを取得する。
	 * <p>
	 * 値が存在しない、または<code>null</code>の場合、デフォルト値を返す。
	 * </p>
	 * 
	 * @param aKey キー
	 * @param aDefault デフォルト値
	 * @return 値
	 */
	public String getString(final String aKey, final String aDefault) {
		String value = aDefault;
		if (parameter.containsKey(aKey)) {
			if (null != parameter.get(aKey)) {
				value = parameter.get(aKey).toString();
			}
		}
		return value;
	}

	/**
	 * プロパティを取得する。
	 * <p>
	 * 値が存在しない場合、<code>null</code>を返す。
	 * </p>
	 * 
	 * @param aKey キー
	 * @return 値
	 */
	public Integer getInteger(final String aKey) {
		return getInteger(aKey, null);
	}

	/**
	 * プロパティを取得する。
	 * <p>
	 * 値が存在しない、または<code>null</code>の場合、デフォルト値を返す。
	 * </p>
	 * 
	 * @param aKey キー
	 * @param aDefault デフォルト値
	 * @return 値
	 */
	public Integer getInteger(final String aKey, final Integer aDefault) {
		Integer value = aDefault;
		if (parameter.containsKey(aKey)) {
			Object o = parameter.get(aKey);
			if (null != o) {
				if (o instanceof Integer) {
					value = (Integer) o;
				} else if (o instanceof String) {
					value = Integer.parseInt((String) o);
				}
			}
		}
		return value;
	}

	/**
	 * プロパティを取得する。
	 * <p>
	 * 値が存在しない場合、<code>null</code>を返す。
	 * </p>
	 * 
	 * @param aKey キー
	 * @return 値
	 */
	public Long getLong(final String aKey) {
		return getLong(aKey, null);
	}

	/**
	 * プロパティを取得する。
	 * <p>
	 * 値が存在しない、または<code>null</code>の場合、デフォルト値を返す。
	 * </p>
	 * 
	 * @param aKey キー
	 * @param aDefault デフォルト値
	 * @return 値
	 */
	public Long getLong(final String aKey, final Long aDefault) {
		Long value = aDefault;
		if (parameter.containsKey(aKey)) {
			Object o = parameter.get(aKey);
			if (null != o) {
				if (o instanceof Long) {
					value = (Long) o;
				} else if (o instanceof String) {
					value = Long.parseLong((String) o);
				}
			}
		}
		return value;
	}

	/**
	 * プロパティを取得する。
	 * <p>
	 * 値が存在しない場合、<code>null</code>を返す。
	 * </p>
	 * 
	 * @param aKey キー
	 * @return 値
	 */
	public Boolean getBoolean(final String aKey) {
		return getBoolean(aKey, null);
	}

	/**
	 * プロパティを取得する。
	 * <p>
	 * 値が存在しない、または<code>null</code>の場合、デフォルト値を返す。
	 * </p>
	 * 
	 * @param aKey キー
	 * @param aDefault デフォルト値
	 * @return 値
	 */
	public Boolean getBoolean(final String aKey, final Boolean aDefault) {
		Boolean value = aDefault;
		if (parameter.containsKey(aKey)) {
			Object o = parameter.get(aKey);
			if (null != o) {
				if (o instanceof Long) {
					value = (Boolean) o;
				} else if (o instanceof String) {
					value = Boolean.parseBoolean((String) o);
				}
			}
		}
		return value;
	}
}
