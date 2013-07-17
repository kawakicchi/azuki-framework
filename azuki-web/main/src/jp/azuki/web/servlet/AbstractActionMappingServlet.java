package jp.azuki.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.azuki.persistence.context.ContextSupport;
import jp.azuki.persistence.parameter.Parameter;
import jp.azuki.persistence.parameter.ParameterSupport;
import jp.azuki.persistence.proterty.Property;
import jp.azuki.persistence.proterty.PropertyManager;
import jp.azuki.persistence.proterty.PropertySupport;
import jp.azuki.persistence.session.SessionSupport;
import jp.azuki.persistence.store.Store;
import jp.azuki.web.WebServiceException;
import jp.azuki.web.action.Action;
<<<<<<< HEAD
import jp.azuki.web.action.purser.ActionHttpServletPurser;
=======
import jp.azuki.web.purser.ActionHttpServletPurser;
>>>>>>> branch 'master' of https://github.com/kawakicchi/azuki-framework.git
import jp.azuki.web.purser.DefaultHttpServletPurser;
import jp.azuki.web.purser.HttpServletPurser;
import jp.azuki.web.store.HttpSessionStore;
import jp.azuki.web.view.View;

/**
 * このクラスは、アクションのマッピングコントロールを行うサーブレットクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/19
 * @author Kawakicchi
 */
public abstract class AbstractActionMappingServlet extends AbstractServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2088382830721888186L;

	/**
	 * コンストラクタ
	 */
	public AbstractActionMappingServlet() {
		super();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractActionMappingServlet(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractActionMappingServlet(final Class<?> aClass) {
		super(aClass);
	}

	/**
	 * アクションを生成する。
	 * 
	 * @param aReq リクエスト情報
	 * @param aRes レスポンス情報
	 * @return アクション。アクションにマッピング出来ない場合、<code>null</code>を返す。
	 */
	protected abstract Action createAction(final HttpServletRequest aReq, final HttpServletResponse aRes);

	/**
	 * 処理を実行する。
	 * 
	 * @param aReq リクエスト情報
	 * @param aRes レスポンス情報
	 * @throws ServletException サーブレット機能に起因する問題が発生した場合
	 * @throws IOException IO操作時に問題が発生した場合
	 */
	protected final void doTask(final HttpServletRequest aReq, final HttpServletResponse aRes) throws ServletException, IOException {
		Action action = createAction(aReq, aRes);
		if (null == action) {
			aRes.sendError(404);
			return;
		}

		try {
<<<<<<< HEAD
			appendActionSupport(action, aReq, aRes);
			View view = action.action();
			if (null != view) {
				view.view(aReq, aRes);
			} else {
				fatal("No return view.[" + action.getClass().getName() + "]");
				throw new WebServiceException("No View.");
			}

		} catch (WebServiceException ex) {
			fatal(ex);
			aRes.sendError(500);
		}
	}

	protected void appendActionSupport(final Action aAction, final HttpServletRequest aReq, final HttpServletResponse aRes)
			throws WebServiceException {
		try {
			// Session support
			if (aAction instanceof SessionSupport) {
				Store<String, Object> session = new HttpSessionStore(aReq.getSession(true));
				((SessionSupport) aAction).setSession(session);
			}

			// Context support
			if (aAction instanceof ContextSupport) {
				((ContextSupport) aAction).setContext(getContext());
			}

			// Property support
			if (aAction instanceof PropertySupport) {
				Property property = PropertyManager.get(aAction.getClass());
				if (null == property) {
					property = PropertyManager.load(aAction.getClass(), getContext());
				}
				((PropertySupport) aAction).setProperty(property);
			}

			// Parameter support (request parameter)
			if (aAction instanceof ParameterSupport) {
=======
			// Create parameter
			Parameter parameter;
			{
>>>>>>> branch 'master' of https://github.com/kawakicchi/azuki-framework.git
				Map<String, Object> params = new HashMap<String, Object>();
				ActionHttpServletPurser aPurser = aAction.getClass().getAnnotation(ActionHttpServletPurser.class);
				if (null != aPurser) {
					Class<? extends HttpServletPurser>[] classes = aPurser.value();
					for (Class<? extends HttpServletPurser> clazz : classes) {
						HttpServletPurser purser = clazz.newInstance();
						Map<String, Object> m = purser.purse(aReq, aRes);
						params.putAll(m);
					}
				} else {
					DefaultHttpServletPurser purser = new DefaultHttpServletPurser();
					Map<String, Object> m = purser.purse(aReq, aRes);
					params.putAll(m);
				}
				Parameter parameter = new Parameter(params);
				((ParameterSupport) aAction).setParameter(parameter);
			}
<<<<<<< HEAD
=======
			// Create Session
			Store<String, Object> session = new HttpSessionStore(aReq.getSession(true));
>>>>>>> branch 'master' of https://github.com/kawakicchi/azuki-framework.git

<<<<<<< HEAD
=======
			// ##### Action Support start #####
			// Session support
			if (action instanceof SessionSupport) {
				((SessionSupport) action).setSession(session);
			}
			// Context support
			if (action instanceof ContextSupport) {
				((ContextSupport) action).setContext(getContext());
			}
			// Property support
			if (action instanceof PropertySupport) {
				Property property = PropertyManager.get(action.getClass());
				if (null == property) {
					property = PropertyManager.load(action.getClass(), getContext());
				}
				((PropertySupport) action).setProperty(property);
			}
			// Parameter support (request parameter)
			if (action instanceof ParameterSupport) {
				((ParameterSupport) action).setParameter(parameter);
			}
			// ##### Action Support end #####

			View view = action.action();

			if (null != view) {
				view.view(aReq, aRes);
			} else {
				fatal("No return view.[" + action.getClass().getName() + "]");
				throw new WebServiceException("No View.");
			}

		} catch (WebServiceException ex) {
			fatal(ex);
			aRes.sendError(500);
>>>>>>> branch 'master' of https://github.com/kawakicchi/azuki-framework.git
		} catch (InstantiationException ex) {
			throw new WebServiceException(ex);
		} catch (IllegalAccessException ex) {
			throw new WebServiceException(ex);
		}
	}
}
