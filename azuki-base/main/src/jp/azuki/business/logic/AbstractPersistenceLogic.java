package jp.azuki.business.logic;

import java.util.HashMap;
import java.util.Map;

import jp.azuki.persistence.context.Context;
import jp.azuki.persistence.context.ContextSupport;
import jp.azuki.persistence.proterty.PropertySupport;

/**
 * このクラスは、ロジック機能を実装する基底クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/12/30
 * @author Kawakicchi
 */
public abstract class AbstractPersistenceLogic extends AbstractLogic implements ContextSupport, PropertySupport {

	/**
	 * コンテキスト
	 */
	private Context context;

	/**
	 * プロパティ
	 */
	private Map<String, Object> properties;

	/**
	 * コンストラクタ
	 */
	public AbstractPersistenceLogic() {
		super();
		properties = new HashMap<String, Object>();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractPersistenceLogic(final String aName) {
		super(aName);
		properties = new HashMap<String, Object>();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractPersistenceLogic(final Class<?> aClass) {
		super(aClass);
		properties = new HashMap<String, Object>();
	}

	@Override
	public final void setContext(final Context aContext) {
		context = aContext;
	}

	/**
	 * コンテキストを取得する。
	 * 
	 * @return コンテキスト
	 */
	protected final Context getContext() {
		return context;
	}

	@Override
	public final void setProperty(final String aKey, final Object aValue) {
		properties.put(aKey, aValue);
	}

	@Override
	public final void setProperties(final Map<String, Object> aValues) {
		properties.putAll(aValues);
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
	protected final Object getProperty(final String aKey) {
		return getProperty(aKey, null);
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
	protected final Object getProperty(final String aKey, final Object aDefault) {
		Object value = aDefault;
		if (properties.containsKey(aKey)) {
			if (null != properties.get(aKey)) {
				value = properties.get(aKey);
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
	protected final String getPropertyToString(final String aKey) {
		return getPropertyToString(aKey, null);
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
	protected final String getPropertyToString(final String aKey, final String aDefault) {
		String value = aDefault;
		if (properties.containsKey(aKey)) {
			if (null != properties.get(aKey)) {
				value = properties.get(aKey).toString();
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
	protected final Integer getPropertyToInteger(final String aKey) {
		return getPropertyToInteger(aKey, null);
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
	protected final Integer getPropertyToInteger(final String aKey, final Integer aDefault) {
		Integer value = aDefault;
		if (properties.containsKey(aKey)) {
			Object o = properties.get(aKey);
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
	protected final Long getPropertyToLong(final String aKey) {
		return getPropertyToLong(aKey, null);
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
	protected final Long getPropertyToLong(final String aKey, final Long aDefault) {
		Long value = aDefault;
		if (properties.containsKey(aKey)) {
			Object o = properties.get(aKey);
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
	protected final Boolean getPropertyToBoolean(final String aKey) {
		return getPropertyToBoolean(aKey, null);
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
	protected final Boolean getPropertyToBoolean(final String aKey, final Boolean aDefault) {
		Boolean value = aDefault;
		if (properties.containsKey(aKey)) {
			Object o = properties.get(aKey);
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
