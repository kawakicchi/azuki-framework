package jp.azuki.web.tags.logic;

import java.util.List;
import java.util.Map;

/**
 * このクラスは、値が<code>null</code>または空の場合に実装するタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/23
 * @author Kawakicchi
 */
public final class IfEmptyTag extends AbstractValueConditionTag {

	/**
	 * コンストラクタ
	 */
	public IfEmptyTag() {
		super(IfEmptyTag.class);
	}

	@Override
	protected boolean isCondition(final Object aValue) {
		if (null == aValue) {
			return true;
		} else if (aValue instanceof String) {
			return ((String) aValue).isEmpty();
		} else if (aValue instanceof List<?>) {
			return ((List<?>) aValue).isEmpty();
		} else if (aValue instanceof Map<?, ?>) {
			return ((Map<?, ?>) aValue).isEmpty();
		} else {
			return false;
		}
	}
}
