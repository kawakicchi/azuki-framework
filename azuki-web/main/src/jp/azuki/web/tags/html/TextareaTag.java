package jp.azuki.web.tags.html;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

import jp.azuki.core.util.StringUtility;

/**
 * このクラスは、URLをレンダリングするタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public class TextareaTag extends AbstractHtmlTag {

	/**
	 * id
	 */
	private String id = null;

	public final void setId(final String aId) {
		id = aId;
	}

	protected final String getId() {
		return id;
	}

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
	 * css
	 */
	private String css = null;

	public final void setCss(final String aCss) {
		css = aCss;
	}

	protected final String getCss() {
		return css;
	}

	/**
	 * style
	 */
	private String style = null;

	public final void setStyle(final String aStyle) {
		style = aStyle;
	}

	protected final String getStyle() {
		return style;
	}

	@Override
	public int doStartTag() throws JspException {
		Object value = null;
		if (StringUtility.isNotEmpty(getName())) {
			value = getAttribute(getName());
		}

		StringBuffer s = new StringBuffer();
		s.append("<textarea");
		//
		appendAttribute("id", getId(), s);
		appendAttribute("name", getName(), s);
		appendAttribute("css", getCss(), s);
		appendAttribute("style", getStyle(), s);
		//
		s.append(">");
		s.append(convertTextareaValue(s(value)));
		s.append("</textarea>");

		write(s.toString());
		return (Tag.SKIP_BODY);
	}

	@Override
	public int doEndTag() throws JspException {
		return (Tag.EVAL_PAGE);
	}
}
