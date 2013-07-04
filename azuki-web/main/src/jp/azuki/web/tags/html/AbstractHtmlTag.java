package jp.azuki.web.tags.html;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;

import jp.azuki.core.util.StringUtility;
import jp.azuki.web.tags.AbstractTag;

/**
 * このクラスは、HTMLタグの拡張を行うためのタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/23
 * @author Kawakicchi
 */
public abstract class AbstractHtmlTag extends AbstractTag {

	private String tagName;
	private Map<String, String> attributes;

	/**
	 * コンストラクタ
	 * 
	 */
	public AbstractHtmlTag() {
		super(AbstractHtmlTag.class);
		attributes = new HashMap<String, String>();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractHtmlTag(final String aName) {
		super(aName);
		attributes = new HashMap<String, String>();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractHtmlTag(final Class<?> aClass) {
		super(aClass);
		attributes = new HashMap<String, String>();
	}

	protected final void setTagName(final String aName) {
		tagName = aName;
	}

	protected final void addAttribute(final String aKey, final String aValue) {
		attributes.put(aKey, aValue);
	}

	@Override
	public int doStartTag() throws JspException {
		return (Tag.SKIP_BODY);
	}

	@Override
	public int doEndTag() throws JspException {
		doCreate();
		StringBuffer s = new StringBuffer();
		s.append("<");
		writeTagName(s);
		writeAttributes(s);
		s.append("/>");
		try {
			JspWriter writer = getPageContext().getOut();
			writer.print(s.toString());
		} catch (IOException ex) {
			throw new JspException(ex);
		}
		return (Tag.EVAL_PAGE);
	}

	protected abstract void doCreate();

	protected void writeTagName(final StringBuffer s) {
		s.append(tagName);
	}

	protected void writeAttributes(final StringBuffer s) {
		for (String key : attributes.keySet()) {
			String value = attributes.get(key);
			appendAttribute(key, value, s);
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
		aString.append(convertAttributeValue(StringUtility.toStringEmpty(aValue)));
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
