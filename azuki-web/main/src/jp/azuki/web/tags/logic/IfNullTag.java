package jp.azuki.web.tags.logic;

/**
 * このクラスは、値が<code>null</code>の場合に実装するタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/23
 * @author Kawakicchi
 */
public final class IfNullTag extends AbstractValueConditionTag {

	/**
	 * コンストラクタ
	 */
	public IfNullTag() {
		super(IfNullTag.class);
	}

	@Override
	protected boolean isCondition(final Object aValue) {
		return (null == aValue);
	}

}
