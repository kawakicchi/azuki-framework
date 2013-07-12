package jp.azuki.web.tags.logic;

/**
 * このクラスは、値が異なる場合にボディが実行されるタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/23
 * @author Kawakicchi
 */
public final class IfNotEqualTag extends AbstractValueEqualTag {

	/**
	 * コンストラクタ
	 */
	public IfNotEqualTag() {
		super(IfNotEqualTag.class);
	}

	@Override
	protected boolean isEqual(final Object aSrc, final Object aDst) {
		if (null == aSrc && null == aDst) {
			return false;
		} else if (null != aSrc && null != aDst) {
			return !aSrc.equals(aDst);
		} else {
			return true;
		}
	}
}
