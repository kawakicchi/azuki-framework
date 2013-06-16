package jp.azuki.persistence.context;

import java.io.InputStream;

/**
 * このインターフェースは、コンテキスト機能を表現するインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/08/12
 * @author Kawakicchi
 */
public interface Context {

	/**
	 * リソースの絶対パスを取得する。
	 * 
	 * @param aName リソース名
	 * @return 絶対パス
	 */
	public String getAbstractPath(final String aName);

	/**
	 * リソースストリームを取得する。
	 * 
	 * @param aName リソース名
	 * @return ストリーム
	 */
	public InputStream getResourceAsStream(final String aName);
}
