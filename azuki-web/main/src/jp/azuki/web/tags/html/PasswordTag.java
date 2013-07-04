package jp.azuki.web.tags.html;

import jp.azuki.core.util.StringUtility;

/**
 * このクラスは、URLをレンダリングするタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public class PasswordTag extends AbstractHtmlTag {

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
	protected void doCreate() {
		Object value = null;
		if (StringUtility.isNotEmpty(getName())) {
			value = getAttribute(getName());
		}

		setTagName("input");
		addAttribute("id", getId());
		addAttribute("name", getName());
		addAttribute("css", getCss());
		addAttribute("style", getStyle());

		addAttribute("type", "password");
		addAttribute("value", StringUtility.toStringEmpty(value));
	}
}
