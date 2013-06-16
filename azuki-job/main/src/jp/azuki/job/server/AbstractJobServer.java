package jp.azuki.job.server;

import jp.azuki.core.lang.LoggingObject;

/**
 * このクラスは、ジョブサーバ機能の実装を行うための基底クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/15
 * @author Kawakicchi
 */
public abstract class AbstractJobServer extends LoggingObject implements JobServer {

	/**
	 * コンストラクタ
	 */
	public AbstractJobServer() {
		super(JobServer.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractJobServer(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractJobServer(final Class<?> aClass) {
		super(aClass);
	}

	@Override
	public final boolean run() {
		return doRun();
	}

	/**
	 * ジョブサーバを実行する。
	 * 
	 * @return 結果
	 */
	protected abstract boolean doRun();
}
