package jp.azuki.business.message;

import jp.azuki.business.BusinessServiceException;
import jp.azuki.core.util.StringUtility;

/**
 * このクラスは、ロジックのインスタンス生成を行うファクトリークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/13
 * @author Kawakicchi
 */
public final class MessageFactory {

	/**
	 * コンストラクタ
	 * <p>
	 * インスタンス生成を禁止する。
	 * </p>
	 */
	private MessageFactory() {

	}

	/**
	 * メッセージを生成する。
	 * 
	 * @param id メッセージID
	 * @return メッセージ
	 * @throws BusinessServiceException ビジネスサービス層に起因する問題が発生した場合
	 */
	public static Message create(final String id) throws BusinessServiceException {
		return create(StringUtility.EMPTY, id);
	}

	/**
	 * メッセージを生成する。
	 * 
	 * @param plugin プラグイン
	 * @param id メッセージID
	 * @return メッセージ
	 * @throws BusinessServiceException ビジネスサービス層に起因する問題が発生した場合
	 */
	public static Message create(final String plugin, final String id) throws BusinessServiceException {
		Message message = null;
		message = MessageManager.getMessage(plugin, id);
		if (null == message) {
			throw new BusinessServiceException("Message not found.[plugin:" + plugin + "; id:" + id + ";]");
		}
		return message;
	}
}
