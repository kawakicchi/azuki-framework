package jp.azuki.persistence.proterty;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import jp.azuki.core.lang.LoggingObject;
import jp.azuki.core.util.StringUtility;
import jp.azuki.persistence.context.Context;

/**
 * このクラスは、プロパティ管理を行うマネージャークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/13
 * @author Kawakicchi
 */
public final class PropertyManager extends LoggingObject {

	/**
	 * Instance
	 */
	private static final PropertyManager INSTANCE = new PropertyManager();

	/**
	 * Properties
	 */
	private Map<Class<?>, Map<String, Object>> properties;

	/**
	 * コンストラクタ
	 * <p>
	 * インスタンス生成を禁止する。
	 * </p>
	 */
	private PropertyManager() {
		super(PropertyManager.class);
		properties = new HashMap<Class<?>, Map<String, Object>>();
	}

	/**
	 * プロパティを取得する。
	 * 
	 * @param aClass クラス
	 * @return プロパティ。未読み込みの場合、<code>null</code>を返す。
	 */
	public static Map<String, Object> get(final Class<?> aClass) {
		return INSTANCE.doGet(aClass);
	}

	/**
	 * プロパティをロードする。
	 * 
	 * @param aClass クラス
	 * @param aContext コンテキスト
	 * @return プロパティ
	 */
	public synchronized static Map<String, Object> load(final Class<?> aClass, final Context aContext) {
		return INSTANCE.doLoad(aClass, aContext);
	}

	private Map<String, Object> doGet(final Class<?> aClass) {
		return properties.get(aClass);
	}

	private Map<String, Object> doLoad(final Class<?> aClass, final Context aContext) {
		Map<String, Object> m = doGet(aClass);
		if (null == m) {
			m = new HashMap<String, Object>();
			Property aProperty = aClass.getAnnotation(Property.class);
			if (null != aProperty) {
				String value = aProperty.value();
				if (StringUtility.isNotEmpty(value)) {
					InputStream stream = aContext.getResourceAsStream(value);
					if (null != stream) {
						try {
							Properties p = new Properties();
							p.load(stream);
							for (Object key : p.keySet()) {
								m.put(key.toString(), p.get(key));
							}
						} catch (IOException ex) {
							error(ex);
						}
					} else {
						error("Not found property file.[" + value + "]");
					}
				} else {

				}
			} else {

			}
			properties.put(aClass, m);
		}
		return m;
	}
}
