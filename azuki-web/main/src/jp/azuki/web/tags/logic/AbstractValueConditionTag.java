package jp.azuki.web.tags.logic;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

import jp.azuki.core.util.StringUtility;
import jp.azuki.web.tags.AbstractTag;

/**
 * このクラスは、条件制御を行うためのタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/23
 * @author Kawakicchi
 */
public abstract class AbstractValueConditionTag extends AbstractTag {

	/**
	 * 名前
	 */
	private String name;

	/**
	 * キー
	 */
	private String key;

	/**
	 * コンストラクタ
	 */
	public AbstractValueConditionTag() {
		super(AbstractValueConditionTag.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractValueConditionTag(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractValueConditionTag(final Class<?> aClass) {
		super(aClass);
	}

	/**
	 * 名前を設定する。
	 * 
	 * @param aName 名前
	 */
	public final void setName(final String aName) {
		name = aName;
	}

	/**
	 * 名前を取得する。
	 * 
	 * @return 名前
	 */
	protected final String getName() {
		return name;
	}

	/**
	 * キーを設定する。
	 * 
	 * @param aKey キー
	 */
	public final void setKey(final String aKey) {
		key = aKey;
	}

	/**
	 * キーを取得する。
	 * 
	 * @return キー
	 */
	protected final String getKey() {
		return key;
	}

	@Override
	public int doStartTag() throws JspException {
		Object value = null;
		if (StringUtility.isNotEmpty(getKey())) {
			value = getRequestAttribute(getName(), getKey());
		} else {
			value = getRequestAttribute(getName());
		}

		if (isCondition(value)) {
			return (Tag.EVAL_BODY_INCLUDE);
		} else {
			return (Tag.SKIP_BODY);
		}
	}

	@Override
	public int doEndTag() throws JspException {
		return (Tag.EVAL_PAGE);
	}

	/**
	 * 値に対する条件結果を取得する。
	 * 
	 * @param aValue 値
	 * @return 結果
	 */
	protected abstract boolean isCondition(final Object aValue);
}
