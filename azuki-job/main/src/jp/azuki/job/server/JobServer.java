package jp.azuki.job.server;

/**
 * このインターフェースは、ジョブサーバ機能を表現するインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/15
 * @author Kawakicchi
 */
public interface JobServer {

	/**
	 * ジョブサーバを実行する。
	 * 
	 * @return 結果
	 */
	public boolean run();
}
