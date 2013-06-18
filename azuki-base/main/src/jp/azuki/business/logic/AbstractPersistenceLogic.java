package jp.azuki.business.logic;

import jp.azuki.persistence.context.Context;
import jp.azuki.persistence.context.ContextSupport;
import jp.azuki.persistence.proterty.Property;
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
	private Property property;

	/**
	 * コンストラクタ
	 */
	public AbstractPersistenceLogic() {
		super();
		property = new Property();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractPersistenceLogic(final String aName) {
		super(aName);
		property = new Property();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractPersistenceLogic(final Class<?> aClass) {
		super(aClass);
		property = new Property();
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
	public final void setProperty(final Property aProperty) {
		property = aProperty;
	}

	/**
	 * プロパティ情報を取得する。
	 * 
	 * @return プロパティ情報
	 */
	protected final Property getProperty() {
		return property;
	}
}
