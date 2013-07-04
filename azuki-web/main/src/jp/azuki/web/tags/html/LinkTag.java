package jp.azuki.web.tags.html;

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
	protected void doCreate() {
		setTagName("link");
		addAttribute("rel", getRel());
		addAttribute("href", WebConstant.getUrl(getHref(), isAbsolute()));
		addAttribute("type", getType());
	}
}
