package jp.azuki.web.action.filter;

import jp.azuki.web.constant.WebServiceException;
import jp.azuki.web.view.View;

/**
 * このインターフェースは、アクション対するフィルター機能を表現するインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/16
 * @author Kawakicchi
 */
public interface Filter {

	/**
	 * 初期化処理を行う。
	 */
	public void initialize();

	/**
	 * 解放処理を行う。
	 */
	public void destroy();

	/**
	 * ビューを取得する。
	 * 
	 * @return ビュー
	 */
	public View getView();

	/**
	 * フィルター処理を行う。
	 * 
	 * @throws WebServiceException ウェブサービス層に起因する問題が発生した場合
	 */
	void filter() throws WebServiceException;

}
