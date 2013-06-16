package jp.azuki.web.tags.base;

import javax.servlet.jsp.JspException;

import jp.azuki.core.util.StringUtility;

/**
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/31
 * @author Kawakicchi
 */
public abstract class AbstractValuePrintTag extends AbstractPrintTag {

	/**
	 * name
	 */
	private String name = null;

	public final void setName(final String aName) {
		name = aName;
	}

	protected final String getName() {
		return name;
	}

	/**
	 * key
	 */
	private String key;

	public final void setKey(final String aKey) {
		key = aKey;
	}

	protected final String getKey() {
		return key;
	}

	protected final void doReadering(final StringBuffer aBuffer) throws JspException {
		Object value = null;
		if (StringUtility.isNotEmpty(getKey())) {
			value = getAttribute(getName(), getKey());
		} else {
			value = getAttribute(getName());
		}
		doReadering(value, aBuffer);
	}

	/**
	 * プリント処理を行う。
	 * 
	 * @param aValue 値
	 * @throws JspException JSP操作に起因する問題が発生した場合
	 */
	protected abstract void doReadering(final Object aValue, final StringBuffer aBuffer) throws JspException;

}
