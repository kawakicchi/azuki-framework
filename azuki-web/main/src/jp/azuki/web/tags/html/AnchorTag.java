package jp.azuki.web.tags.html;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

import jp.azuki.web.constant.WebConstant;

/**
 * このクラスは、アンカーを実装するタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/15
 * @author Kawakicchi
 */
public class AnchorTag extends AbstractHtmlTag {

	/**
	 * Href
	 */
	private String href;

	/**
	 * Absolute
	 */
	private boolean absolute = false;

	public final void setHref(final String aHref) {
		href = aHref;
	}

	public final void setAbsolute(final boolean aAbsolute) {
		absolute = aAbsolute;
	}

	protected final String getHref() {
		return href;
	}

	protected final boolean isAbsolute() {
		return absolute;
	}

	protected String getUrl() {
		String url = WebConstant.getUrl(getHref(), isAbsolute());
		return url;
	}

	@Override
	public int doStartTag() throws JspException {
		StringBuffer s = new StringBuffer();
		s.append("<a");
		appendAttribute("href", WebConstant.getUrl(getHref(), isAbsolute()), s);
		s.append(">");
		
		write(s.toString());
		
		return (Tag.EVAL_BODY_INCLUDE);
	}

	@Override
	public int doEndTag() throws JspException {
		write("</a>");
		return (Tag.EVAL_PAGE);
	}
}
