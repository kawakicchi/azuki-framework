package jp.azuki.plugin;

import java.io.IOException;
import java.io.InputStream;

import jp.azuki.persistence.ConfigurationFormatException;

/**
 * このインターフェースは、プラグイン機能を表現するインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/06/07
 * @author Kawakicchi
 */
public interface Plugin {

	/**
	 * 初期化処理を行う。
	 * 
	 * @throws PluginServiceException プラグイン機能に起因する問題が発生した場合
	 */
	public void initialize() throws PluginServiceException;

	/**
	 * 解放処理を行う。
	 * 
	 * @throws PluginServiceException プラグイン機能に起因する問題が発生した場合
	 */
	public void destroy() throws PluginServiceException;

	/**
	 * 設定情報をロードする。
	 * 
	 * @param aStream 設定ストリーム
	 * @throws PluginServiceException プラグイン機能に起因する問題が発生した場合
	 * @throws ConfigurationFormatException 設定ファイルに問題がある場合
	 * @throws IOException IO操作時に問題が発生した場合
	 */
	public void load(final InputStream aStream) throws PluginServiceException, ConfigurationFormatException, IOException;
}
