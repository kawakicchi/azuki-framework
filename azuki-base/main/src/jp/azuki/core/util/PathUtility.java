package jp.azuki.core.util;

/**
 * このクラスは、パス操作をまとめたユーティリティクラスです。
 * 
 * @author N.Kawakita
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/06/26
 * @author Kawakicchi
 */
public class PathUtility {

	/**
	 * コンストラクタ
	 * <p>
	 * インスタンス生成を禁止する。
	 * </p>
	 */
	private PathUtility() {

	}

	/**
	 * 文字配列を結合してパスを作成する。
	 * 
	 * @param strs 文字配列
	 * @return パス
	 */
	public static String cat(final String... strs) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < strs.length; i++) {
			if (0 < i) {
				s.append("\\");
			}
			if (0 == i) {
				s.append(trimR(replace(strs[i]), "\\"));
			} else {
				s.append(trim(replace(strs[i]), "\\"));
			}
		}
		return s.toString();
	}

	private static String replace(final String aString) {
		String buf = aString;
		buf = buf.replaceAll("\\\\/", "\\");
		return buf;
	}

	private static String trim(final String aString, final String aTrimword) {
		String buf = trimL(aString, aTrimword);
		buf = trimR(buf, aTrimword);
		return buf;
	}

	private static String trimL(final String aString, final String aTrimword) {
		String buf = aString;
		while (buf.startsWith(aTrimword)) {
			buf = buf.substring(aTrimword.length());
		}
		return buf;
	}

	private static String trimR(final String aString, final String aTrimword) {
		String buf = aString;
		while (buf.endsWith(aTrimword)) {
			buf = buf.substring(0, buf.length() - aTrimword.length());
		}
		return buf;
	}
}
