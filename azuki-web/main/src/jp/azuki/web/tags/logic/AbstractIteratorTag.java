package jp.azuki.web.tags.logic;

import javax.servlet.jsp.tagext.IterationTag;

import jp.azuki.web.tags.AbstractTag;

/**
 * このクラスは、繰り返し処理を行うタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/07/12
 * @author Kawakicchi
 */
public abstract class AbstractIteratorTag extends AbstractTag implements IterationTag {

	/**
	 * コンストラクタ
	 */
	public AbstractIteratorTag() {
		super(AbstractIteratorTag.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractIteratorTag(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractIteratorTag(final Class<?> aClass) {
		super(aClass);
	}
}
