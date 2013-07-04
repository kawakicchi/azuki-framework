package jp.azuki.web.action;

import jp.azuki.web.constant.WebServiceException;
import jp.azuki.web.view.View;

/**
 * このインターフェースは、アクション機能を表現するインターフェスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/12/30
 * @author Kawakicchi
 */
public interface Action {

	/**
	 * 初期化処理を行う。
	 */
	public void initialize();

	/**
	 * 解放処理を行う。
	 */
	public void destroy();

	/**
	 * アクションを実行する。
	 * 
	 * @return ビュー
	 * @throws WebServiceException ウェブサービス層に起因する問題が発生した場合
	 */
	public View action() throws WebServiceException;

}
