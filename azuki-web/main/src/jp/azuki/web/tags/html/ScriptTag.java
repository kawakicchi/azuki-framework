package jp.azuki.web.tags.html;


/**
 * このクラスは、URLをレンダリングするタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public class ScriptTag extends AbstractBodyHtmlTag {

	/**
	 * src
	 */
	private String src = null;

	private boolean absolute = false;

	public final void setSrc(final String aSrc) {
		src = aSrc;
	}

	protected final String getSrc() {
		return src;
	}

	public final void setAbsolute(final boolean aAbsolute) {
		absolute = aAbsolute;
	}

	protected final boolean isAbsolute() {
		return absolute;
	}

	@Override
	protected void doCreate() {
		setTagName("script");
		addAttribute("src", getSrc());
	}
}
