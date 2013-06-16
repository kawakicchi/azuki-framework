package jp.azuki.web.tags.html.ex;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

import jp.azuki.web.constant.WebConstant;
import jp.azuki.web.tags.ParameterSupportTag;
import jp.azuki.web.tags.html.AnchorTag;

/**
 * このクラスは、アンカーを実装するタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/15
 * @author Kawakicchi
 */
public class AnchorExTag extends AnchorTag implements BodyTag, ParameterSupportTag {

	/**
	 * Parameter
	 */
	private Map<String, Object> parameter = new HashMap<String, Object>();

	@Override
	public void setParameter(final String aKey, final Object aValue) {
		parameter.put(aKey, aValue);
	}

	protected String getUrl() {
		String url = WebConstant.getUrl(getHref(), isAbsolute());
		for (String key : parameter.keySet()) {
			String value = s(parameter.get(key));
			url = url.replaceAll("\\{" + key + "\\}", value);
		}
		return url;
	}

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
		s.append("<a");
		appendAttribute("href", getUrl(), s);
		s.append(">");
		s.append(bodyContent.getString());
		s.append("</a>");
		
		write(s.toString());
		
		return (Tag.EVAL_PAGE);
	}
}
