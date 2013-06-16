package jp.azuki.business.logic;

import jp.azuki.core.lang.LoggingObject;

/**
 * このクラスは、ロジック機能を実装する基底クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/12/30
 * @author Kawakicchi
 */
public abstract class AbstractLogic extends LoggingObject implements Logic {

	/**
	 * コンストラクタ
	 */
	public AbstractLogic() {
		super(Logic.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractLogic(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractLogic(final Class<?> aClass) {
		super(aClass);
	}

	/**
	 * 初期化処理を行う。
	 */
	public final void initialize() {
		doInitialize();
	}

	/**
	 * 解放処理を行う。
	 */
	public final void destroy() {
		doDestroy();
	}

	/**
	 * 初期化処理を行う。
	 */
	protected abstract void doInitialize();

	/**
	 * 解放処理を行う。
	 */
	protected abstract void doDestroy();

}
