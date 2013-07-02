package jp.azuki.business.message;

import jp.azuki.business.BusinessServiceException;
import jp.azuki.core.util.StringUtility;

/**
 * このクラスは、メッセージのインスタンス生成を行うファクトリークラスです。
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
	 * @param aId メッセージID
	 * @return メッセージ情報。メッセージ情報が存在しない場合、<code>null</code>を返す。
	 * @throws BusinessServiceException ビジネスサービス層に起因する問題が発生した場合
	 */
	public static Message create(final String aId) throws BusinessServiceException {
		return create(StringUtility.EMPTY, aId);
	}

	/**
	 * メッセージを生成する。
	 * 
	 * @param aNamespace 名前空間
	 * @param aId メッセージID
	 * @return メッセージ情報。メッセージ情報が存在しない場合、<code>null</code>を返す。
	 * @throws BusinessServiceException ビジネスサービス層に起因する問題が発生した場合
	 */
	public static Message create(final String aNamespace, final String aId) throws BusinessServiceException {
		return MessageManager.getMessage(aNamespace, aId);
	}
}
