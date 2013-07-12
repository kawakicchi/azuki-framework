package jp.azuki.web.tags.html;

import jp.azuki.core.util.StringUtility;

/**
 * このクラスは、URLをレンダリングするタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public class TextareaTag extends AbstractBodyHtmlTag {

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

	@Override
	protected final String getTagName() {
		return "textarea";
	}

	@Override
	protected void doAppendAttributes() {
		super.doAppendAttributes();

		Object value = null;
		if (StringUtility.isNotEmpty(getName())) {
			value = getRequestAttribute(getName());
		}

		addAttribute("name", getName());

		setBodyString(StringUtility.toStringEmpty(value));
	}
}
