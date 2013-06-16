package jp.azuki.web.tags.base;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

import jp.azuki.core.util.StringUtility;
import jp.azuki.web.tags.AbstractTag;

/**
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/31
 * @author Kawakicchi
 */
public abstract class AbstractPrintTag extends AbstractTag {

	@Override
	public final int doStartTag() throws JspException {
		StringBuffer buffer = new StringBuffer();
		doReadering(buffer);
		if (0 < buffer.length()) {
			try {
				getPageContext().getOut().print(convertPlainText(buffer.toString()));
			} catch (IOException ex) {
				throw new JspException(ex);
			}
		}
		return (Tag.SKIP_BODY);
	}

	@Override
	public final int doEndTag() throws JspException {
		return (Tag.EVAL_PAGE);
	}

	/**
	 * プリント処理を行う。
	 * 
	 * @throws JspException JSP操作に起因する問題が発生した場合
	 */
	protected abstract void doReadering(final StringBuffer aBuffer) throws JspException;

	private final String convertPlainText(final String aValue) {
		String value = "";
		if (StringUtility.isNotEmpty(aValue)) {
			value = aValue;
			value = value.replace("¥r¥n", "<br />");
		}
		return value;
	}
}
