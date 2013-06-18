package jp.azuki.job.job;

import jp.azuki.job.exception.JobServiceException;
import jp.azuki.job.result.JobResult;
import jp.azuki.persistence.context.Context;
import jp.azuki.persistence.context.ContextSupport;
import jp.azuki.persistence.exception.PersistenceServiceException;
import jp.azuki.persistence.proterty.Property;
import jp.azuki.persistence.proterty.PropertySupport;
import jp.azuki.persistence.session.SessionSupport;
import jp.azuki.persistence.store.Store;

/**
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/21
 * @author Kawakicchi
 */
public abstract class AbstractPersistenceJob extends AbstractJob implements SessionSupport, ContextSupport, PropertySupport {

	/**
	 * Session store
	 */
	private Store<String, Object> session;

	/**
	 * コンテキスト
	 */
	private Context context;

	/**
	 * プロパティ情報
	 */
	private Property property;

	/**
	 * コンストラクタ
	 */
	public AbstractPersistenceJob() {
		super();
		property = new Property();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName Name
	 */
	public AbstractPersistenceJob(final String aName) {
		super(aName);
		property = new Property();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass Class
	 */
	public AbstractPersistenceJob(final Class<?> aClass) {
		super(aClass);
		property = new Property();
	}

	@Override
	protected final JobResult doExecute() throws JobServiceException {
		JobResult result = null;
		try {
			result = doPersistenceExecute();
		} catch (PersistenceServiceException ex) {
			throw new JobServiceException(ex);
		}
		return result;
	}

	/**
	 * ジョブを実行する。
	 * 
	 * @return 結果
	 * @throws JobServiceException ジョブ機能に起因する問題が発生した場合
	 * @throws PersistenceServiceException 永続化層に起因する問題が発生した場合
	 */
	protected abstract JobResult doPersistenceExecute() throws JobServiceException, PersistenceServiceException;

	@Override
	public final void setSession(final Store<String, Object> aSession) {
		session = aSession;
	}

	/**
	 * セッション情報を取得する。
	 * 
	 * @return セッション情報
	 */
	protected final Store<String, Object> getSession() {
		return session;
	}

	@Override
	public final void setContext(final Context aContext) {
		context = aContext;
	}

	/**
	 * コンテキストを取得する。
	 * 
	 * @return コンテキスト
	 */
	protected final Context getContext() {
		return context;
	}

	@Override
	public final void setProperty(final Property aProperty) {
		property = aProperty;
	}

	/**
	 * プロパティ情報を取得する。
	 * 
	 * @return プロパティ情報
	 */
	protected final Property getProperty() {
		return property;
	}
}
