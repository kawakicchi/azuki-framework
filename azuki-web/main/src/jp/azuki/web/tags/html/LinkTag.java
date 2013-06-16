package jp.azuki.web.tags.html;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

import jp.azuki.web.constant.WebConstant;

/**
 * このクラスは、URLをレンダリングするタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public class LinkTag extends AbstractHtmlTag {

	/**
	 * rel
	 */
	private String rel = null;

	/**
	 * href
	 */
	private String href = null;

	/**
	 * type
	 */
	private String type = null;

	private boolean absolute = false;

	public final void setRel(final String aRel) {
		rel = aRel;
	}

	public final void setHref(final String aHref) {
		href = aHref;
	}

	public final void setType(final String aType) {
		type = aType;
	}

	protected final String getRel() {
		return rel;
	}

	protected final String getHref() {
		return href;
	}

	protected final String getType() {
		return type;
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
		s.append("<link");
		appendAttribute("rel", getRel(), s);
		appendAttribute("href", WebConstant.getUrl(getHref(), isAbsolute()), s);
		appendAttribute("type", getType(), s);
		s.append("/>");

		write(s.toString());
		return (Tag.SKIP_BODY);
	}

	@Override
	public int doEndTag() throws JspException {
		return (Tag.EVAL_PAGE);
	}
}
