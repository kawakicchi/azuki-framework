package jp.azuki.business.label;

import jp.azuki.core.util.StringUtility;

/**
 * このクラスは、ラベルのインスタンス生成を行うファクトリークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/07/02
 * @author Kawakicchi
 */
public final class LabelFactory {

	/**
	 * コンストラクタ
	 * <p>
	 * インスタンス生成を禁止する。
	 * </p>
	 */
	private LabelFactory() {

	}

	/**
	 * ラベルを生成する。
	 * 
	 * @param aId ラベルID
	 * @return ラベル情報。ラベル情報が存在しない場合、<code>null</code>を返す。
	 */
	public static Label create(final String aId) {
		return create(StringUtility.EMPTY, aId);
	}

	/**
	 * ラベルを生成する。
	 * 
	 * @param aNamespace 名前空間
	 * @param aId ラベルID
	 * @return ラベル情報。ラベル情報が存在しない場合、<code>null</code>を返す。
	 */
	public static Label create(final String aNamespace, final String aId) {
		return LabelManager.getLabel(aNamespace, aId);
	}
}
