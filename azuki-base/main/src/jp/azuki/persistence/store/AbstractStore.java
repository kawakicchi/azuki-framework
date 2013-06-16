package jp.azuki.persistence.store;

import jp.azuki.core.lang.LoggingObject;

/**
 * このクラスは、ストア機能の実装を行うための基底クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/01
 * @author Kawakicchi
 */
public abstract class AbstractStore<K, V> extends LoggingObject implements Store<K, V> {

	/**
	 * コンストラクタ
	 */
	public AbstractStore() {
		super(Store.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractStore(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractStore(final Class<?> aClass) {
		super(aClass);
	}
}
