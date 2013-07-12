package jp.azuki.web.tags.logic;

/**
 * このクラスは、値が<code>null</code>でない場合に実装するタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/23
 * @author Kawakicchi
 */
public final class IfNotNullTag extends AbstractValueConditionTag {

	/**
	 * コンストラクタ
	 */
	public IfNotNullTag() {
		super(IfNotNullTag.class);
	}

	@Override
	protected boolean isCondition(final Object aValue) {
		return (null != aValue);
	}

}
