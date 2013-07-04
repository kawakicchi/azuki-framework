package jp.azuki.web.tags.logic;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.IterationTag;
import javax.servlet.jsp.tagext.Tag;

import jp.azuki.core.util.StringUtility;
import jp.azuki.web.tags.AbstractTag;

/**
 * このクラスは、繰り返し処理を行うタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/19
 * @author Kawakicchi
 */
public class IteratorTag extends AbstractTag implements IterationTag {

	/**
	 * name
	 */
	private String name;

	/**
	 * pub
	 */
	private String put;

	/**
	 * Iterator
	 */
	private Iterator<?> iterator;

	public final void setName(final String aName) {
		name = aName;
	}

	protected final String getName() {
		return name;
	}

	public final void setPut(final String aPut) {
		put = aPut;
	}

	protected final String getPut() {
		return put;
	}

	@Override
	public int doEndTag() throws JspException {
		return (0);
	}

	@Override
	public int doStartTag() throws JspException {
		Object obj = getPageContext().getRequest().getAttribute(getName());
		if (null == obj) {
			return (Tag.SKIP_BODY);
		}

		if (obj instanceof Object[]) {
			Object[] array = (Object[]) obj;
			iterator = Arrays.asList(array).iterator();
		} else if (obj instanceof Collection<?>) {
			Collection<?> collection = (Collection<?>) obj;
			iterator = collection.iterator();
		} else if (obj instanceof Iterator<?>) {
			iterator = (Iterator<?>) obj;
		} else {
			throw new JspException("Invalid type[" + obj.getClass().getName() + "].");
		}

		if (iterator.hasNext()) {
			Object o = iterator.next();
			if (StringUtility.isNotEmpty(getPut())) {
				getPageContext().getRequest().setAttribute(getPut(), o);
			}
			return (Tag.EVAL_BODY_INCLUDE);
		} else {
			return (Tag.SKIP_BODY);
		}
	}

	@Override
	public int doAfterBody() throws JspException {
		if (null == iterator) {
			return (Tag.SKIP_BODY);
		}

		if (iterator.hasNext()) {
			Object o = iterator.next();
			if (StringUtility.isNotEmpty(getPut())) {
				getPageContext().getRequest().setAttribute(getPut(), o);
			}
			return (IterationTag.EVAL_BODY_AGAIN);
		} else {
			return (Tag.SKIP_BODY);
		}
	}
}
