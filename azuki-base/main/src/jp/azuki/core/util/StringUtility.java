package jp.azuki.core.util;


/**
 * このクラスは、文字列操作を行うユーティリティクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/04
 * @author Kawakicchi
 */
public final class StringUtility {

	/** Empty */
	public static final String EMPTY = "";

	/**
	 * コンストラクタ
	 * <p>
	 * インスタンス生成を禁止する。
	 * </p>
	 */
	private StringUtility() {

	}

	/**
	 * 文字列が、エンプティーか判断する。
	 * 
	 * @param aString 文字列
	 * @return 文字列が<code>null</code>かブランク以外の場合、<code>true</code>を返す。
	 */
	public static boolean isNotEmpty(final String aString) {
		if (null != aString) {
			if (!EMPTY.equals(aString)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 文字列が、エンプティーか判断する。
	 * 
	 * @param aString 文字列
	 * @return 文字列が<code>null</code>かブランクの場合、<code>true</code>を返す。
	 */
	public static boolean isEmpty(final String aString) {
		return !(isNotEmpty(aString));
	}

	/**
	 * 文字列をキャメル表記で取得する。
	 * 
	 * @param aString 文字列
	 * @return キャメル表記文字列
	 */
	public static String toCamel(final String aString) {
		StringBuilder s = new StringBuilder();
		boolean big = false;
		for (int i = 0; i < aString.length(); i++) {
			char c = aString.charAt(i);
			if ('_' == c) {
				big = true;
			} else {
				if (big) {
					big = false;
					s.append(Character.toUpperCase(c));
				} else {
					s.append(c);
				}
			}
		}
		return s.toString();
	}
}
