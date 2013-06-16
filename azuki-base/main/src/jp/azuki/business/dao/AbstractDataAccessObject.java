package jp.azuki.business.dao;

import java.util.List;
import java.util.Map;

import jp.azuki.business.paging.Paging;
import jp.azuki.core.lang.LoggingObject;

/**
 * このクラスは、データアクセス機能の実装を行うための基底クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/14
 * @author Kawakicchi
 */
public abstract class AbstractDataAccessObject extends LoggingObject implements DataAccessObject {

	/**
	 * コンストラクタ
	 */
	public AbstractDataAccessObject() {
		super(DataAccessObject.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName Name
	 */
	public AbstractDataAccessObject(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass Class
	 */
	public AbstractDataAccessObject(final Class<?> aClass) {
		super(aClass);
	}

	@Override
	public final boolean execute() throws DataAccessServiceException {
		return doExecute();
	}

	@Override
	public final int update() throws DataAccessServiceException {
		return doUpdate();
	}

	@Override
	public final long count() throws DataAccessServiceException {
		return doCount();
	}

	@Override
	public final Map<String, Object> get() throws DataAccessServiceException {
		return doGet();
	}

	@Override
	public final List<Map<String, Object>> query() throws DataAccessServiceException {
		return doQuery();
	}

	@Override
	public final List<Map<String, Object>> query(final Paging aPaging) throws DataAccessServiceException {
		return doQuery(aPaging);
	}

	/**
	 * データに処理を実行する。
	 * 
	 * @return 実行結果
	 * @throws DataAccessServiceException データへのアクセス時に問題が発生した場合
	 */
	protected abstract boolean doExecute() throws DataAccessServiceException;

	/**
	 * データを更新する。
	 * 
	 * @return 更新件数
	 * @throws DataAccessServiceException データへのアクセス時に問題が発生した場合
	 */
	protected abstract int doUpdate() throws DataAccessServiceException;

	/**
	 * データの件数を取得する。
	 * 
	 * @return 件数
	 * @throws DataAccessServiceException データへのアクセス時に問題が発生した場合
	 */
	protected abstract long doCount() throws DataAccessServiceException;

	/**
	 * データを取得する。
	 * 
	 * @return データ
	 * @throws DataAccessServiceException データへのアクセス時に問題が発生した場合
	 */
	protected abstract Map<String, Object> doGet() throws DataAccessServiceException;

	/**
	 * データを取得する。
	 * 
	 * @return データ
	 * @throws DataAccessServiceException データへのアクセス時に問題が発生した場合
	 */
	protected abstract List<Map<String, Object>> doQuery() throws DataAccessServiceException;

	/**
	 * データを取得する。
	 * 
	 * @param aPaging ページ情報
	 * @return データ
	 * @throws DataAccessServiceException データへのアクセス時に問題が発生した場合
	 */
	protected abstract List<Map<String, Object>> doQuery(final Paging aPaging) throws DataAccessServiceException;
}
