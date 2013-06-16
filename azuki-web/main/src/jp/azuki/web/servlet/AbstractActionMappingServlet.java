package jp.azuki.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.azuki.persistence.context.ContextSupport;
import jp.azuki.persistence.proterty.PropertyManager;
import jp.azuki.persistence.proterty.PropertySupport;
import jp.azuki.persistence.session.SessionSupport;
import jp.azuki.persistence.store.Store;
import jp.azuki.web.action.Action;
import jp.azuki.web.action.annotation.ActionAfterFilter;
import jp.azuki.web.action.annotation.ActionBeforeFilter;
import jp.azuki.web.action.annotation.ActionHttpServletPurser;
import jp.azuki.web.action.context.ActionContext;
import jp.azuki.web.action.filter.Filter;
import jp.azuki.web.constant.WebServiceException;
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
			ActionContext actionContext = new ActionContext(aReq, aRes);
			Map<String, Object> parameter = new HashMap<String, Object>();
			Store<String, Object> session = new HttpSessionStore(aReq.getSession(true));

			// purser
			ActionHttpServletPurser aPurser = action.getClass().getAnnotation(ActionHttpServletPurser.class);
			if (null != aPurser) {
				Class<? extends HttpServletPurser>[] classes = aPurser.value();
				for (Class<? extends HttpServletPurser> clazz : classes) {
					HttpServletPurser purser = clazz.newInstance();
					Map<String, Object> m = purser.purse(aReq, aRes);
					parameter.putAll(m);
				}
			}

			// setting
			if (action instanceof SessionSupport) {
				((SessionSupport) action).setSession(session);
			}
			if (action instanceof ContextSupport) {
				((ContextSupport) action).setContext(getContext());
			}
			if (action instanceof PropertySupport) {
				Map<String, Object> properties = PropertyManager.get(action.getClass());
				if (null == properties) {
					properties = PropertyManager.load(action.getClass(), getContext());
				}
				((PropertySupport) action).setProperties(properties);
			}

			View view = null;
			view = doBeforeFilter(action, parameter, session, actionContext);
			if (null == view) {
				view = action.action(parameter);
				if (null == view) {
					fatal("No return view.[" + action.getClass().getName() + "]");
					throw new WebServiceException("No View.");
				}
			}
			View bufView = doAfterFilter(action, parameter, session, actionContext);
			if (null != bufView) {
				view = bufView;
			}

			view.view(aReq, aRes);

		} catch (WebServiceException ex) {
			fatal(ex);
		} catch (InstantiationException ex) {
			fatal(ex);
		} catch (IllegalAccessException ex) {
			fatal(ex);
		} catch (ServletException ex) {
			fatal(ex);
		} catch (IOException ex) {
			fatal(ex);
		}
	}

	/**
	 * アクション実行後フィルターを実行する。
	 * 
	 * @param aAction アクション
	 * @param aParameter パラメーター情報
	 * @param aSession セッション情報
	 * @param aContext コンテキスト情報
	 * @return ビューまたは、<code>null</code>を返す。
	 * @throws IllegalAccessException {@link IllegalAccessException}
	 * @throws InstantiationException {@link InstantiationException}
	 * @throws WebServiceException {@link WebServiceException}
	 */
	private View doAfterFilter(final Action aAction, final Map<String, Object> aParameter, final Store<String, Object> aSession,
			final ActionContext aContext) throws IllegalAccessException, InstantiationException, WebServiceException {
		View view = null;
		ActionAfterFilter aFilter = aAction.getClass().getAnnotation(ActionAfterFilter.class);
		if (null != aFilter) {
			Class<? extends Filter>[] filterClasses = aFilter.value();
			if (null != filterClasses) {
				for (Class<? extends Filter> clazz : filterClasses) {
					Filter filter = clazz.newInstance();
					if (filter instanceof SessionSupport) {
						((SessionSupport) filter).setSession(aSession);
					}
					if (filter instanceof ContextSupport) {
						((ContextSupport) filter).setContext(getContext());
					}
					if (filter instanceof PropertySupport) {
						Map<String, Object> properties = PropertyManager.get(clazz);
						if (null == properties) {
							properties = PropertyManager.load(clazz, getContext());
						}
						((PropertySupport) filter).setProperties(properties);
					}
					filter.filter(aParameter);
					if (null != filter.getView()) {
						view = filter.getView();
						break;
					}
				}
			}
		}
		return view;
	}

	/**
	 * アクション実行前フィルターを実行する。
	 * 
	 * @param aAction アクション
	 * @param aParameter パラメーター情報
	 * @param aSession セッション情報
	 * @param aContext コンテキスト情報
	 * @return ビューまたは、<code>null</code>を返す。
	 * @throws IllegalAccessException {@link IllegalAccessException}
	 * @throws InstantiationException {@link InstantiationException}
	 * @throws WebServiceException {@link WebServiceException}
	 */
	private View doBeforeFilter(final Action aAction, final Map<String, Object> aParameter, final Store<String, Object> aSession,
			final ActionContext aContext) throws IllegalAccessException, InstantiationException, WebServiceException {
		View view = null;
		ActionBeforeFilter aFilter = aAction.getClass().getAnnotation(ActionBeforeFilter.class);
		if (null != aFilter) {
			Class<? extends Filter>[] filterClasses = aFilter.value();
			if (null != filterClasses) {
				for (Class<? extends Filter> clazz : filterClasses) {
					Filter filter = clazz.newInstance();
					if (filter instanceof SessionSupport) {
						((SessionSupport) filter).setSession(aSession);
					}
					if (filter instanceof ContextSupport) {
						((ContextSupport) filter).setContext(getContext());
					}
					if (filter instanceof PropertySupport) {
						Map<String, Object> properties = PropertyManager.get(clazz);
						if (null == properties) {
							properties = PropertyManager.load(clazz, getContext());
						}
						((PropertySupport) filter).setProperties(properties);
					}
					filter.filter(aParameter);
					if (null != filter.getView()) {
						view = filter.getView();
						break;
					}
				}
			}
		}
		return view;
	}
}
