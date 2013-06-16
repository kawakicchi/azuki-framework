package jp.azuki.web.tags.html.ex;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

import jp.azuki.web.constant.WebConstant;
import jp.azuki.web.tags.ParameterSupportTag;
import jp.azuki.web.tags.html.ImageTag;

/**
 * このクラスは、URLをレンダリングするタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public class ImageExTag extends ImageTag implements BodyTag, ParameterSupportTag {

	/**
	 * Parameter
	 */
	private Map<String, Object> parameter = new HashMap<String, Object>();

	@Override
	public void setParameter(final String aKey, final Object aValue) {
		parameter.put(aKey, aValue);
	}

	protected String getSource() {
		String source = WebConstant.getUrl(getSrc(), isAbsolute());
		for (String key : parameter.keySet()) {
			String value = s(parameter.get(key));
			source = source.replaceAll("\\{" + key + "\\}", value);
		}
		return source;
	}

	@SuppressWarnings("unused")
	private BodyContent bodyContent = null;

	@Override
	public void setBodyContent(final BodyContent aContent) {
		bodyContent = aContent;
	}

	@Override
	public void doInitBody() throws JspException {

	}

	@Override
	public int doStartTag() throws JspException {
		return (BodyTag.EVAL_BODY_BUFFERED);
	}

	@Override
	public int doAfterBody() throws JspException {
		return (Tag.SKIP_BODY);
	}

	@Override
	public int doEndTag() throws JspException {
		StringBuffer s = new StringBuffer();
		s.append("<img");
		appendAttribute("class", getCss(), s);
		appendAttribute("style", getStyle(), s);
		appendAttribute("src", getSource(), s);
		s.append("/>");

		write(s.toString());

		return (Tag.EVAL_PAGE);
	}

}
