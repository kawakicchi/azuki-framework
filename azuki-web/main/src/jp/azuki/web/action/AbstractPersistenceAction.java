package jp.azuki.web.action;

import java.util.Map;

import jp.azuki.persistence.context.Context;
import jp.azuki.persistence.context.ContextSupport;
import jp.azuki.persistence.PersistenceServiceException;
import jp.azuki.persistence.proterty.Property;
import jp.azuki.persistence.proterty.PropertySupport;
import jp.azuki.persistence.session.SessionSupport;
import jp.azuki.persistence.store.Store;
import jp.azuki.web.constant.WebServiceException;
import jp.azuki.web.view.View;

/**
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/21
 * @author Kawakicchi
 */
public abstract class AbstractPersistenceAction extends AbstractAction implements SessionSupport, ContextSupport, PropertySupport {

	/**
	 * Session store
	 */
	private Store<String, Object> session;

	/**
	 * コンテキスト
	 */
	private Context context;

	/**
	 * プロパティ情報
	 */
	private Property property;

	/**
	 * コンストラクタ
	 */
	public AbstractPersistenceAction() {
		super();
		property = new Property();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractPersistenceAction(final String aName) {
		super(aName);
		property = new Property();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractPersistenceAction(final Class<?> aClass) {
		super(aClass);
		property = new Property();
	}

	@Override
	protected final View doAction(final Map<String, Object> aParameter) throws WebServiceException {
		View view = null;
		try {
			view = doPersistenceAction(aParameter);
		} catch (PersistenceServiceException ex) {
			throw new WebServiceException(ex);
		}
		return view;
	}

	/**
	 * アクションを実行する。
	 * 
	 * @param aParameter パラメーター
	 * @return ビュー
	 * @throws WebServiceException ウェブサービス層に起因する問題が発生した場合
	 * @throws PersistenceServiceException 永続化層に起因する問題が発生した場合
	 */
	protected abstract View doPersistenceAction(final Map<String, Object> aParameter) throws WebServiceException, PersistenceServiceException;

	@Override
	public final void setSession(final Store<String, Object> aSession) {
		session = aSession;
	}

	/**
	 * セッション情報を取得する。
	 * 
	 * @return セッション情報
	 */
	protected final Store<String, Object> getSession() {
		return session;
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
