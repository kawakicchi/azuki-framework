package jp.azuki.web.tags.html;

import jp.azuki.web.constant.WebConstant;

/**
 * このクラスは、アンカーを実装するタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/15
 * @author Kawakicchi
 */
public class AnchorTag extends AbstractBodyHtmlTag {

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

	@Override
	protected void doCreate() {
		setTagName("a");
		addAttribute("href", WebConstant.getUrl(getHref(), isAbsolute()));
	}
}
