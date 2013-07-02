package jp.azuki.business.message;

import java.util.Map;

/**
 * このクラスは、メッセージ情報を保持するクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/11
 * @author Kawakicchi
 */
public final class Message {

	/**
	 * メッセージＩＤ
	 */
	private String id;

	/**
	 * メッセージ
	 */
	private String message;

	/**
	 * コンストラクタ
	 * 
	 * @param aId メッセージＩＤ
	 * @param aMessage メッセージ
	 */
	public Message(final String aId, final String aMessage) {
		id = aId;
		message = aMessage;
	}

	/**
	 * メッセージIDを取得する。
	 * 
	 * @return メッセージID
	 */
	public String getId() {
		return id;
	}

	/**
	 * メッセージを取得する。
	 * 
	 * @return メッセージ
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * メッセージを生成する。
	 * 
	 * @param args パラメーター
	 * @return メッセージ
	 */
	public String generate(final String... args) {
		String msg = message;
		for (int i = 0; i < args.length; i++) {
			String word = "¥¥$¥¥{" + (i + 1) + "¥¥}";
			msg = msg.replaceAll(word, args[i]);
		}
		return msg;
	}

	/**
	 * メッセージを生成する。
	 * 
	 * @param args パラメーター
	 * @return メッセージ
	 */
	public String generate(final Map<String, String> args) {
		String msg = message;
		for (String key : args.keySet()) {
			String word = "¥¥$¥¥{" + key + "¥¥}";
			msg = msg.replaceAll(word, args.get(key));
		}
		return msg;
	}

}
