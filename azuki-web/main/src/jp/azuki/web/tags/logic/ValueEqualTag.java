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
public abstract class ValueEqualTag extends AbstractTag {

	/**
	 * 名前
	 */
	private String srcName;

	/**
	 * 名前
	 */
	private String dstName;

	/**
	 * キー
	 */
	private String srcKey;

	/**
	 * キー
	 */
	private String dstKey;

	/**
	 * コンストラクタ
	 */
	public ValueEqualTag() {
		super(ValueEqualTag.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public ValueEqualTag(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public ValueEqualTag(final Class<?> aClass) {
		super(aClass);
	}

	/**
	 * 名前を設定する。
	 * 
	 * @param aName 名前
	 */
	public final void setSrcName(final String aName) {
		srcName = aName;
	}

	/**
	 * 名前を設定する。
	 * 
	 * @param aName 名前
	 */
	public final void setDstName(final String aName) {
		dstName = aName;
	}

	/**
	 * 名前を取得する。
	 * 
	 * @return 名前
	 */
	protected final String getSrcName() {
		return srcName;
	}

	/**
	 * 名前を取得する。
	 * 
	 * @return 名前
	 */
	protected final String getDstName() {
		return dstName;
	}

	/**
	 * キーを設定する。
	 * 
	 * @param aKey キー
	 */
	public final void setSrcKey(final String aKey) {
		srcKey = aKey;
	}

	/**
	 * キーを設定する。
	 * 
	 * @param aKey キー
	 */
	public final void setDstKey(final String aKey) {
		dstKey = aKey;
	}

	/**
	 * キーを取得する。
	 * 
	 * @return キー
	 */
	protected final String getSrcKey() {
		return srcKey;
	}

	/**
	 * キーを取得する。
	 * 
	 * @return キー
	 */
	protected final String getDstKey() {
		return dstKey;
	}

	/**
	 * 値に対する条件結果を取得する。
	 * 
	 * @param aSrc 値
	 * @param aDst 値
	 * @return 結果
	 */
	protected abstract boolean isEqual(final Object aSrc, final Object aDst);

	@Override
	public int doStartTag() throws JspException {
		Object src = null;
		if (StringUtility.isNotEmpty(getSrcKey())) {
			src = getAttribute(getSrcName(), getSrcKey());
		} else {
			src = getAttribute(getSrcName());
		}
		Object dst = null;
		if (StringUtility.isNotEmpty(getDstKey())) {
			dst = getAttribute(getDstName(), getDstKey());
		} else {
			dst = getAttribute(getDstName());
		}

		if (isEqual(src, dst)) {
			return (Tag.EVAL_BODY_INCLUDE);
		} else {
			return (Tag.SKIP_BODY);
		}
	}

	@Override
	public int doEndTag() throws JspException {
		return (Tag.EVAL_PAGE);
	}
}
