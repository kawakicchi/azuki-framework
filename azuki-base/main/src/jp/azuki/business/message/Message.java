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
	 * id
	 */
	private String id;

	/**
	 * message
	 */
	private String message;

	/**
	 * コンストラクタ
	 * 
	 * @param aId Id
	 * @param aMessage Message
	 */
	public Message(final String aId, final String aMessage) {
		id = aId;
		message = aMessage;
	}

	/**
	 * IDを取得する。
	 * 
	 * @return ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * メッセージを生成する。
	 * 
	 * @param args パラメーター
	 * @return メッセージ
	 */
	public String getMessage(final String... args) {
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
	public String getMessage(final Map<String, String> args) {
		String msg = message;
		for (String key : args.keySet()) {
			String word = "¥¥$¥¥{" + key + "¥¥}";
			msg = msg.replaceAll(word, args.get(key));
		}
		return msg;
	}

}
