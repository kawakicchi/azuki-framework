package jp.azuki.job.result;

/**
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/20
 * @author Kawakicchi
 */
public final class SuccessJobResult implements JobResult {

	private boolean continueFlg;
	private long wait;

	public SuccessJobResult() {
		continueFlg = false;
	}

	public SuccessJobResult(long aWait) {
		continueFlg = true;
		wait = aWait;
	}

	@Override
	public boolean isResult() {
		return true;
	}

	@Override
	public boolean isContinue() {
		return continueFlg;
	}

	@Override
	public long getWait() {
		return wait;
	}

}
