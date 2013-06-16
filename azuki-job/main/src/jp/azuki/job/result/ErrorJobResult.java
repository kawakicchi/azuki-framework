package jp.azuki.job.result;

/**
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/20
 * @author Kawakicchi
 */
public final class ErrorJobResult implements JobResult {

	public ErrorJobResult() {
	}

	@Override
	public boolean isResult() {
		return false;
	}

	@Override
	public boolean isContinue() {
		return false;
	}

	@Override
	public long getWait() {
		return 0;
	}

}
