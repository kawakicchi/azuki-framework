package jp.azuki.job.result;

/**
 * このクラスは、ジョブ再実行を指示するジョブ結果クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/15
 * @author Kawakicchi
 */
public final class ContinueJobResult implements JobResult {

	/**
	 * 待ち時間(ms)
	 */
	private long wait;

	/**
	 * コンストラクタ
	 * <p>
	 * 待ち時間なしで再実行する。
	 * </p>
	 */
	public ContinueJobResult() {
		wait = 0;
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aWait 待ち時間(ms)
	 */
	public ContinueJobResult(long aWait) {
		wait = aWait;
	}

	@Override
	public boolean isResult() {
		return true;
	}

	@Override
	public boolean isContinue() {
		return true;
	}

	@Override
	public long getWait() {
		return wait;
	}

}
