package jp.azuki.business.dao;

import java.util.List;
import java.util.Map;

import jp.azuki.business.paging.Paging;

/**
 * このインターフェースは、データアクセス機能を表現したインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/14
 * @author Kawakicchi
 */
public interface DataAccessObject {

	/**
	 * データに処理を実行する。
	 * 
	 * @return 実行結果
	 * @throws DataAccessServiceException データへのアクセス時に問題が発生した場合
	 */
	public boolean execute() throws DataAccessServiceException;

	/**
	 * データを更新する。
	 * 
	 * @return 更新件数
	 * @throws DataAccessServiceException データへのアクセス時に問題が発生した場合
	 */
	public int update() throws DataAccessServiceException;

	/**
	 * データの件数を取得する。
	 * 
	 * @return 件数
	 * @throws DataAccessServiceException データへのアクセス時に問題が発生した場合
	 */
	public long count() throws DataAccessServiceException;

	/**
	 * データを取得する。
	 * 
	 * @return データ
	 * @throws DataAccessServiceException データへのアクセス時に問題が発生した場合
	 */
	public Map<String, Object> get() throws DataAccessServiceException;

	/**
	 * データを取得する。
	 * 
	 * @return データ
	 * @throws DataAccessServiceException データへのアクセス時に問題が発生した場合
	 */
	public List<Map<String, Object>> query() throws DataAccessServiceException;

	/**
	 * データを取得する。
	 * 
	 * @param aPaging ページング情報
	 * @return データ
	 * @throws DataAccessServiceException データへのアクセス時に問題が発生した場合
	 */
	public List<Map<String, Object>> query(final Paging aPaging) throws DataAccessServiceException;
}
