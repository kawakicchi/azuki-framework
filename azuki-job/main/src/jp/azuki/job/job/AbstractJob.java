package jp.azuki.job.job;

import jp.azuki.core.lang.LoggingObject;
import jp.azuki.job.exception.JobServiceException;
import jp.azuki.job.parameter.Parameter;
import jp.azuki.job.result.JobResult;

/**
 * このクラスは、ジョブ機能を実装する基底クラスです。
 * <p>
 * このクラスは、プロパティ機能をサポートします。
 * </p>
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/15
 * @author Kawakicchi
 */
public abstract class AbstractJob extends LoggingObject implements Job {

	/**
	 * コンストラクタ
	 */
	public AbstractJob() {
		super(Job.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName Name
	 */
	public AbstractJob(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass Class
	 */
	public AbstractJob(final Class<?> aClass) {
		super(aClass);
	}

	@Override
	public void initialize() {
		doInitialize();
	}

	@Override
	public void destroy() {
		doDestroy();
	}

	@Override
	public JobResult execute(final Parameter aParameter) throws JobServiceException {
		return doExecute(aParameter);
	}

	/**
	 * 初期化処理を行う。
	 */
	protected abstract void doInitialize();

	/**
	 * 解放処理を行う。
	 */
	protected abstract void doDestroy();

	/**
	 * ジョブを実行する。
	 * 
	 * @param aParameter パラメータ情報
	 * @return 結果
	 * @throws JobServiceException ジョブ機能に起因する問題が発生した場合
	 */
	protected abstract JobResult doExecute(final Parameter aParameter) throws JobServiceException;

}
