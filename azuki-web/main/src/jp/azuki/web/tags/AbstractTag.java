package jp.azuki.web.tags;

import java.util.Map;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import jp.azuki.core.lang.LoggingObject;
import jp.azuki.core.util.StringUtility;

/**
 * このクラスは、タグ機能を実装する基底クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public abstract class AbstractTag extends LoggingObject implements Tag {

	/**
	 * Parent tag
	 */
	private Tag parentTag;

	/**
	 * Page context
	 */
	private PageContext pageContext;

	/**
	 * コンストラクタ
	 */
	public AbstractTag() {
		super(AbstractTag.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractTag(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractTag(final Class<?> aClass) {
		super(aClass);
	}

	@Override
	public final void setPageContext(final PageContext aPageContext) {
		pageContext = aPageContext;
	}

	@Override
	public final Tag getParent() {
		return parentTag;
	}

	@Override
	public final void setParent(final Tag aParent) {
		parentTag = aParent;
	}

	@Override
	public void release() {
	}

	/**
	 * ページコンテキストを取得する。
	 * 
	 * @return ページコンテキスト
	 */
	protected final PageContext getPageContext() {
		return pageContext;
	}

	/**
	 * ページコンテキストに値を設定する。
	 * 
	 * @param aName 名前
	 * @param aValue 値
	 */
	protected final void setPageAttribute(final String aName, final Object aValue) {
		pageContext.setAttribute(aName, aValue);
	}

	/**
	 * ページコンテキストから値を取得する。
	 * 
	 * @param aName 名前
	 * @return 値
	 */
	protected final Object getPageAttribute(final String aName) {
		return pageContext.getAttribute(aName);
	}

	/**
	 * ページコンテキストから値を取得する。
	 * 
	 * @param aName 名前
	 * @param aKey キー
	 * @return 値
	 */
	@SuppressWarnings("unchecked")
	protected final Object getPageAttribute(final String aName, final String aKey) {
		Object value = null;
		Object o = getPageAttribute(aName);
		if (null != o) {
			if (o instanceof Map<?, ?>) {
				value = ((Map<String, Object>) o).get(aKey);
			}
		}
		return value;
	}

	/**
	 * リクエストコンテキストに値を設定する。
	 * 
	 * @param aName 名前
	 * @param aValue 値
	 */
	protected final void setRequestAttribute(final String aName, final Object aValue) {
		pageContext.getRequest().setAttribute(aName, aValue);
	}

	/**
	 * リクエストコンテキストから値を取得する。
	 * 
	 * @param aName 名前
	 * @return 値
	 */
	protected final Object getRequestAttribute(final String aName) {
		return pageContext.getRequest().getAttribute(aName);
	}

	/**
	 * リクエストコンテキストから値を取得する。
	 * 
	 * @param aName 名前
	 * @param aKey キー
	 * @return 値
	 */
	@SuppressWarnings("unchecked")
	protected final Object getRequestAttribute(final String aName, final String aKey) {
		Object value = null;
		Object o = getRequestAttribute(aName);
		if (null != o) {
			if (o instanceof Map<?, ?>) {
				value = ((Map<String, Object>) o).get(aKey);
			}
		}
		return value;
	}

	/**
	 * リクエストコンテキストに値を設定する。
	 * 
	 * @param aName 名前
	 * @param aValue 値
	 */
	protected final void setAttribute(final String aName, final Object aValue) {
		setAttribute(null, aName, aValue);
	}

	/**
	 * コンテキストに値を設定する。
	 * 
	 * @param aScope スコープ。<code>null</code>の場合リクエストコンテキストを参照する。
	 * @param aName 名前
	 * @param aValue 値
	 */
	protected final void setAttribute(final String aScope, final String aName, final Object aValue) {
		if ("page".equals(aScope.toLowerCase())) {
			setPageAttribute(aName, aValue);
		} else if (StringUtility.isEmpty(aScope) || "request".equals(aScope.toLowerCase())) {
			setRequestAttribute(aName, aValue);
		}
	}

	/**
	 * コンテキストから値を取得する。
	 * 
	 * @param aName 名前
	 * @return 値。値が見つからない場合は、<code>null</code>を返す。
	 */
	protected final Object getAttribute(final String aName) {
		return getAttribute(null, aName, null);
	}

	/**
	 * リクエストコンテキストから値を取得する。
	 * 
	 * @param aName 名前
	 * @param aKey キー
	 * @return 値。値が見つからない場合は、<code>null</code>を返す。
	 */
	protected final Object getAttribute(final String aName, final String aKey) {
		return getAttribute(null, aName, aKey);
	}

	/**
	 * コンテキストから値を取得する。
	 * 
	 * @param aScope スコープ。<code>null</code>の場合リクエストコンテキストを参照する。
	 * @param aName 名前
	 * @param aKey キー
	 * @return 値。値が見つからない場合は、<code>null</code>を返す。
	 */
	protected final Object getAttribute(final String aScope, final String aName, final String aKey) {
		Object value = null;
		if ("page".equals(aScope.toLowerCase())) {
			if (StringUtility.isNotEmpty(aKey)) {
				value = getPageAttribute(aName, aKey);
			} else {
				value = getPageAttribute(aName);
			}
		} else if (StringUtility.isEmpty(aScope) || "request".equals(aScope.toLowerCase())) {
			if (StringUtility.isNotEmpty(aKey)) {
				value = getRequestAttribute(aName, aKey);
			} else {
				value = getRequestAttribute(aName);
			}
		}
		return value;
	}
}
