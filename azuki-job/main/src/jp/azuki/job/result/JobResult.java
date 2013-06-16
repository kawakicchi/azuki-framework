package jp.azuki.job.result;

/**
 * このインターフェースは、ジョブの結果情報を表現するインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/01
 * @author Kawakicchi
 */
public interface JobResult {

	/**
	 * 結果を取得する。
	 * 
	 * @return 結果
	 */
	public boolean isResult();

	/**
	 * ジョブを再実行するか判断する。
	 * 
	 * @return 判断結果
	 */
	public boolean isContinue();

	/**
	 * ジョブ再実行までの待ち時間(ms)を取得する。
	 * 
	 * @return 待ち時間(ms)
	 */
	public long getWait();
}
