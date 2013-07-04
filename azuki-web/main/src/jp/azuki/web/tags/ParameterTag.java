package jp.azuki.web.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

/**
 * このクラスは、パラメータの設定を行うタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/07/03
 * @author Kawakicchi
 */
public final class ParameterTag extends AbstractTag implements BodyTag {

	/**
	 * パラメータキー
	 */
	private String key;

	/**
	 * パラメータ値
	 */
	private Object value;

	/**
	 * ボディコンテンツ
	 */
	private BodyContent bodyContent;

	/**
	 * コンストラクタ
	 */
	public ParameterTag() {
		super(ParameterTag.class);
	}

	/**
	 * パラメータキーを設定する。
	 * 
	 * @param aKey キー
	 */
	public void setKey(final String aKey) {
		key = aKey;
	}

	/**
	 * パラメータキーを取得する。
	 * 
	 * @return キー
	 */
	protected String getKey() {
		return key;
	}

	/**
	 * パラメータ値を設定する。
	 * 
	 * @param aValue 値
	 */
	public void setValue(final String aValue) {
		value = aValue;
	}

	/**
	 * パラメータ値を取得する。
	 * 
	 * @return 値
	 */
	protected Object getValue() {
		return value;
	}

	@Override
	public final int doStartTag() throws JspException {
		if (null != getValue()) {
			return (Tag.SKIP_BODY);
		} else {
			return (BodyTag.EVAL_BODY_BUFFERED);
		}
	}

	@Override
	public final int doEndTag() throws JspException {
		Tag parent = getParent();
		if (null != parent) {
			if (parent instanceof ParameterTagSupport) {
				ParameterTagSupport t = (ParameterTagSupport) parent;
				t.setParameter(getKey(), getValue());
			}
		}
		return (Tag.EVAL_PAGE);
	}

	@Override
	public void setBodyContent(final BodyContent aBodyContent) {
		bodyContent = aBodyContent;
	}

	@Override
	public void doInitBody() throws JspException {
	}

	@Override
	public int doAfterBody() throws JspException {
		value = bodyContent.getString();
		return (Tag.SKIP_BODY);
	}

}
