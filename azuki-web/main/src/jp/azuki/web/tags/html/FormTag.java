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
	protected final String getTagName() {
		return "form";
	}

	@Override
	protected void doAppendAttributes() {
		super.doAppendAttributes();

		addAttribute("action", WebConstant.getUrl(getAction(), isAbsolute()));
		addAttribute("method", getMethod());
	}
}
