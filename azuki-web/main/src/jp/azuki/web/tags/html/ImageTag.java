package jp.azuki.web.tags.html;

import jp.azuki.web.constant.WebConstant;

/**
 * このクラスは、URLをレンダリングするタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public class ImageTag extends AbstractHtmlTag {

	/**
	 * css
	 */
	private String css = null;

	/**
	 * style
	 */
	private String style = null;

	/**
	 * src
	 */
	private String src = null;

	/**
	 * absolute
	 */
	private boolean absolute = false;

	public final void setCss(final String aCss) {
		css = aCss;
	}

	protected final String getCss() {
		return css;
	}

	public final void setStyle(final String aStyle) {
		style = aStyle;
	}

	protected final String getStyle() {
		return style;
	}

	/**
	 * ソースを設定する。
	 * 
	 * @param aSrc ソース
	 */
	public final void setSrc(final String aSrc) {
		src = aSrc;
	}

	/**
	 * ソースを取得する。
	 * 
	 * @return ソース
	 */
	protected final String getSrc() {
		return src;
	}

	/**
	 * 絶対URLかどうかを設定する。
	 * 
	 * @param aAbsolute 絶対URLの場合、<code>true</code>を設定する。
	 */
	public final void setAbsolute(final boolean aAbsolute) {
		absolute = aAbsolute;
	}

	/**
	 * 絶対URLか判断する。
	 * 
	 * @return 絶対URLの場合、<code>true</code>を返す。
	 */
	protected final boolean isAbsolute() {
		return absolute;
	}

	protected String getSource() {
		String source = WebConstant.getUrl(getSrc(), isAbsolute());
		return source;
	}

	protected void doCreate() {
		setTagName("img");
		addAttribute("class", getCss());
		addAttribute("style", getStyle());
		addAttribute("src", getSource());
	}
}
