package jp.azuki.web.tags.html;

import jp.azuki.web.constant.WebConstant;

/**
 * このクラスは、フォームを実装するタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/15
 * @author Kawakicchi
 */
public class FormTag extends AbstractBodyHtmlTag {

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
	protected void doCreate() {
		setTagName("form");

		addAttribute("id", getId());
		addAttribute("class", getCss());
		addAttribute("style", getStyle());

		addAttribute("action", WebConstant.getUrl(getAction(), isAbsolute()));
		addAttribute("method", getMethod());
	}
}
