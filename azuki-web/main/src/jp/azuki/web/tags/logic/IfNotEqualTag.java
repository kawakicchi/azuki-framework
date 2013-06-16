package jp.azuki.web.tags.logic;

/**
 * このクラスは、値が<code>null</code>または空の場合に実装するタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/23
 * @author Kawakicchi
 */
public class IfNotEqualTag extends ValueEqualTag {

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
