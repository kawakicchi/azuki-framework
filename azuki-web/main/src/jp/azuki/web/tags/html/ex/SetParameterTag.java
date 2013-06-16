package jp.azuki.web.tags.html.ex;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

import jp.azuki.core.util.StringUtility;
import jp.azuki.web.tags.AbstractTag;
import jp.azuki.web.tags.ParameterSupportTag;

/**
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/31
 * @author Kawakicchi
 */
public class SetParameterTag extends AbstractTag {

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

	private String put;

	public final void setPut(final String aPut) {
		put = aPut;
	}

	protected final String getPut() {
		return put;
	}

	@Override
	public final int doStartTag() throws JspException {
		Object value = null;
		if (StringUtility.isNotEmpty(getKey())) {
			value = getAttribute(getName(), getKey());
		} else {
			value = getAttribute(getName());
		}
		Tag parent = getParent();
		if (null != parent) {
			if (parent instanceof ParameterSupportTag) {
				ParameterSupportTag t = (ParameterSupportTag) parent;
				t.setParameter(getPut(), value);
			}
		}
		return (Tag.SKIP_BODY);
	}

	@Override
	public final int doEndTag() throws JspException {
		return (Tag.EVAL_PAGE);
	}
}
