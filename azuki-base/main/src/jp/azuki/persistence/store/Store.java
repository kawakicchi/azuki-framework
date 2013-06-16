package jp.azuki.persistence.store;

import java.util.Map;

/**
 * このクラスは、ストア機能を表現するインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/01
 * @author Kawakicchi
 */
public interface Store<K, V> {

	/**
	 * 値を格納する。
	 * 
	 * @param aKey キー
	 * @param aValue 値
	 */
	public void put(final K aKey, final V aValue);

	/**
	 * 値をすべて格納する。
	 * 
	 * @param aMap マップ
	 */
	public void putAll(final Map<K, V> aMap);

	/**
	 * 値を取得する。
	 * 
	 * @param aKey キー
	 * @return 値
	 */
	public V get(final K aKey);

	/**
	 * 値を取得する。
	 * 
	 * @param aKey キー
	 * @param aDefault デフォルト値
	 * @return 値
	 */
	public V get(final K aKey, final V aDefault);

	/**
	 * キーに値が存在するか判断する。
	 * 
	 * @param aKey キー
	 * @return 判断結果
	 */
	public boolean has(final K aKey);

	/**
	 * 値を削除する。
	 * 
	 * @param aKey キー
	 */
	public void remove(final K aKey);
}
