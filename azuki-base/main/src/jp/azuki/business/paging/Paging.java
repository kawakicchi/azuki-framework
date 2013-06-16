package jp.azuki.business.paging;

/**
 * このインターフェースは、ページング機能を表現したインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/21
 * @author Kawakicchi
 */
public interface Paging {

	/**
	 * ページサイズを取得する。
	 * 
	 * @return サイズ
	 */
	public long getSize();

	/**
	 * ページ数を取得する。
	 * 
	 * @return ページ数(0始まり)
	 */
	public long getPage();

	/**
	 * 開始IDを取得する。
	 * 
	 * @return ID
	 */
	public String getSinceId();

	/**
	 * 完了IDを取得する。
	 * 
	 * @return ID
	 */
	public String getMaxId();

	/**
	 * IDのキーを取得する。
	 * 
	 * @return キー
	 */
	public String getKey();
}
