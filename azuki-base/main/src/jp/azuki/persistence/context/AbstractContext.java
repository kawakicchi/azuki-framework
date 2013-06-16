package jp.azuki.persistence.context;

import jp.azuki.core.lang.LoggingObject;

/**
 * このクラスは、コンテキスト機能の実装を行うための基底クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/08/12
 * @author Kawakicchi
 */
public abstract class AbstractContext extends LoggingObject implements Context {

	/**
	 * コンストラクタ
	 */
	public AbstractContext() {
		super(Context.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractContext(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractContext(final Class<?> aClass) {
		super(aClass);
	}

}
