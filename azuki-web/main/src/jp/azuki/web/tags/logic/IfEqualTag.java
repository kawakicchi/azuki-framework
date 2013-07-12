package jp.azuki.web.tags.logic;

/**
 * このクラスは、値が同じ場合にボディが実行されるタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/23
 * @author Kawakicchi
 */
public final class IfEqualTag extends AbstractValueEqualTag {

	/**
	 * コンストラクタ
	 */
	public IfEqualTag() {
		super(IfEqualTag.class);
	}

	@Override
	protected boolean isEqual(final Object aSrc, final Object aDst) {
		if (null == aSrc && null == aDst) {
			return true;
		} else if (null != aSrc && null != aDst) {
			return aSrc.equals(aDst);
		} else {
			return false;
		}
	}
}
