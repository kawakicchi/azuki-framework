package jp.azuki.web.tags.html;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

import jp.azuki.web.constant.WebConstant;

/**
 * このクラスは、フォームを実装するタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/15
 * @author Kawakicchi
 */
public class FormTag extends AbstractHtmlTag {

	private String action = null;

	private String method = "get";

	private boolean absolute = false;

	public final void setAction(final String aAction) {
		action = aAction;
	}
	protected final String getAction() {
		return action;
	}
	
	public final void setMethod(final String aMethod) {
		method = aMethod;
	}

	protected final String getMethod() {
		return method;
	}
	
	public final void setAbsolute(final boolean aAbsolute) {
		absolute = aAbsolute;
	}

	protected final boolean isAbsolute() {
		return absolute;
	}

	@Override
	public int doStartTag() throws JspException {
		StringBuffer s = new StringBuffer();
		s.append("<form");
		appendAttribute("action", WebConstant.getUrl(getAction(), isAbsolute()), s);
		appendAttribute("method", getMethod(), s);
		s.append(">");

		write(s.toString());
		return (Tag.EVAL_BODY_INCLUDE);
	}

	@Override
	public int doEndTag() throws JspException {
		write("</form>");
		return (Tag.EVAL_PAGE);
	}

}
