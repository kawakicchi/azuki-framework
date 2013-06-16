package jp.azuki.core.util;

import java.util.List;

/**
 * このクラスは、リスト操作を行うユーティリティクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/03/01
 * @author Kawakicchi
 */
public final class ListUtility {

	/**
	 * コンストラクタ
	 * <p>
	 * インスタンス生成を禁止する。
	 * </p>
	 */
	private ListUtility() {

	}

	/**
	 * リストが、エンプティーか判断する。
	 * 
	 * @param aList リスト
	 * @return リストが<code>null</code>かエンプティーの場合、<code>true</code>を返す。
	 */
	public static boolean isEmpty(final List<?> aList) {
		return !(isNotEmpty(aList));
	}

	/**
	 * リストが、エンプティーか判断する。
	 * 
	 * @param aList リスト
	 * @return リストが<code>null</code>かエンプテー以外の場合、<code>true</code>を返す。
	 */
	public static boolean isNotEmpty(final List<?> aList) {
		if (null != aList) {
			if (!aList.isEmpty()) {
				return true;
			}
		}
		return false;
	}
}
