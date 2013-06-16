package jp.azuki.job.store;

import java.util.HashMap;
import java.util.Map;

import jp.azuki.persistence.store.AbstractStore;

/**
 * このクラスは、HTTPセッション用のストアクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/05
 * @author Kawakicchi
 */
public final class JobSessionStore extends AbstractStore<String, Object> {

	private Map<String, Object> map;

	/**
	 * コンストラクタ
	 * 
	 */
	public JobSessionStore() {
		map = new HashMap<String, Object>();
	}

	@Override
	public void put(final String aKey, final Object aValue) {
		map.put(aKey, aValue);
	}

	@Override
	public void putAll(final Map<String, Object> aMap) {
		for (String key : aMap.keySet()) {
			map.put(key, aMap.get(key));
		}
	}

	@Override
	public Object get(String aKey) {
		Object result = null;
		result = map.get(aKey);
		return result;
	}

	@Override
	public Object get(final String aKey, final Object aDefault) {
		Object result = aDefault;
		if (null != map.get(aKey)) {
			result = map.get(aKey);
		}
		return result;
	}

	@Override
	public boolean has(final String aKey) {
		Object obj = map.get(aKey);
		return (null != obj);
	}

	@Override
	public void remove(final String aKey) {
		map.remove(aKey);
	}

}
