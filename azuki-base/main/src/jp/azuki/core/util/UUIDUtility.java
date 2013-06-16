package jp.azuki.core.util;

import java.util.UUID;

/**
 * このクラスは、UUID操作を行うユーティリティクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/06/14
 * @author Kawakicchi
 */
public class UUIDUtility {

	/**
	 * コンストラクタ
	 * <p>
	 * インスタンス生成を禁止する。
	 * </p>
	 */
	private UUIDUtility() {

	}

	/**
	 * UUIDを生成する。
	 * 
	 * @return UUID
	 */
	public static String generate() {
		String s = UUID.randomUUID().toString();
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24, 36);
	}
}
