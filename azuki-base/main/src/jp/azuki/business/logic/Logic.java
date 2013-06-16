package jp.azuki.business.logic;

/**
 * このインターフェースは、ロジック機能を表現するインターフェスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/12/30
 * @author Kawakicchi
 */
public interface Logic {

	/**
	 * 初期化処理を行う。
	 */
	public void initialize();

	/**
	 * 解放処理を行う。
	 */
	public void destroy();
}
