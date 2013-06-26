package jp.azuki.business.message;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import jp.azuki.business.BusinessServiceException;
import jp.azuki.business.manager.AbstractManager;
import jp.azuki.core.util.StringUtility;
import jp.azuki.persistence.context.Context;

/**
 * このクラスは、メッセージの管理を行うマネージャークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/11
 * @author Kawakicchi
 */
public final class MessageManager extends AbstractManager {

	/**
	 * Instance
	 */
	private static final MessageManager INSTANCE = new MessageManager();

	private Map<String, Map<String, Message>> messages;

	/**
	 * コンストラクタ
	 * <p>
	 * インスタンス生成を禁止する。
	 * </p>
	 */
	private MessageManager() {
		super(MessageManager.class);
		messages = new HashMap<String, Map<String, Message>>();
	}

	public static void initialize() {
		INSTANCE.doInitialize();
	}

	public static void destroy() {
		INSTANCE.doDestroy();
	}

	/**
	 * メッセージ情報をロードします。
	 * 
	 * @param stream メッセージ情報
	 * @param context コンテキスト
	 * @throws BusinessServiceException ビジネスサービスに起因する問題が発生した場合
	 * @throws IOException 入出力操作に起因する問題が発生した場合
	 */
	public static void load(final InputStream stream, final Context context) throws BusinessServiceException, IOException {
		INSTANCE.doLoad(StringUtility.EMPTY, stream, context);
	}

	/**
	 * メッセージを取得する。
	 * 
	 * @param id メッセージID
	 * @return メッセージ
	 */
	public static Message getMessage(final String id) {
		return getMessage(StringUtility.EMPTY, id);
	}

	/**
	 * メッセージを取得する。
	 * 
	 * @param plugin プラグイン名
	 * @param id メッセージID
	 * @return　メッセージ
	 */
	public static Message getMessage(final String plugin, final String id) {
		return INSTANCE.doGetMessage(plugin, id);
	}

	private void doInitialize() {
		messages.clear();
	}

	private void doDestroy() {
		messages.clear();
	}

	/**
	 * メッセージ情報をロードする。
	 * 
	 * @param plugin プラグイン名
	 * @param stream メッセージ情報
	 * @param context コンテキスト
	 */
	private void doLoad(final String plugin, final InputStream stream, final Context context) {
		// TODO
	}

	/**
	 * メッセージを取得する。
	 * 
	 * @param plugin プラグイン名
	 * @param id メッセージID
	 * @return メッセージ
	 */
	private Message doGetMessage(final String plugin, final String id) {
		Message msg = null;
		if (messages.containsKey(plugin)) {
			Map<String, Message> ms = messages.get(plugin);
			if (ms.containsKey(id)) {
				msg = ms.get(id);
			}
		}
		return msg;
	}
}
