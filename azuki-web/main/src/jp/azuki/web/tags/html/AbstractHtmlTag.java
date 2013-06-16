package jp.azuki.web.tags.html;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import jp.azuki.core.util.StringUtility;
import jp.azuki.web.tags.AbstractTag;

/**
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/23
 * @author Kawakicchi
 */
public abstract class AbstractHtmlTag extends AbstractTag {

	protected final void write(final String string) throws JspException {
		try {
			JspWriter writer = getPageContext().getOut();
			writer.print(string);
		} catch (IOException ex) {
			throw new JspException(ex);
		}
	}

	protected final boolean appendAttribute(final String aKey, final Object aValue, final StringBuffer aString) {
		if (StringUtility.isEmpty(aKey))
			return false;
		if (null == aValue)
			return false;

		aString.append(" ");
		aString.append(aKey);
		aString.append("=\"");
		aString.append(convertAttributeValue(s(aValue)));
		aString.append("\"");
		return true;
	}

	/**
	 * 文字列をタグの属性値用に変換します。
	 * 
	 * @param aValue 文字列
	 * @return 変換後文字列
	 */
	protected final String convertAttributeValue(final String aValue) {
		String value = "";
		if (StringUtility.isNotEmpty(aValue)) {
			value = aValue;
		}
		return value;
	}

	/**
	 * 文字列をテキストエリア用に変換します。
	 * 
	 * @param aValue 文字列
	 * @return 変換後文字列
	 */
	protected final String convertTextareaValue(final String aValue) {
		String value = "";
		if (StringUtility.isNotEmpty(aValue)) {
			value = aValue;
		}
		return value;
	}
}
