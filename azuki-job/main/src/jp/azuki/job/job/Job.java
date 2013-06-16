package jp.azuki.job.job;

import jp.azuki.job.exception.JobServiceException;
import jp.azuki.job.result.JobResult;

/**
 * このインターフェースは、ジョブ機能を表現したインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/15
 * @author Kawakicchi
 */
public interface Job {

	/**
	 * 初期化処理を行う。
	 */
	public void initialize();

	/**
	 * 解放処理を行う。
	 */
	public void destroy();

	/**
	 * ジョブを実行する。
	 * 
	 * @return 結果
	 * @throws ジョブ機能に起因する問題が発生した場合
	 */
	public JobResult execute() throws JobServiceException;
}
