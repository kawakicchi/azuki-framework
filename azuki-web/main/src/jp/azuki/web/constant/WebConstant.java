package jp.azuki.web.constant;

import java.util.Properties;

import jp.azuki.core.util.StringUtility;

/**
 * このクラスは、Web定数を保持するクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public final class WebConstant {

	/** host name */
	private static String hostName;

	/** host port */
	private static String hostPort;

	/** protocol */
	private static String protocol;

	/** web application */
	private static String webApp;

	/**
	 * コンストラクタ
	 * <p>
	 * インスタンス生成を禁止する。
	 * </p>
	 */
	private WebConstant() {
		;
	}

	/**
	 * ロードします。
	 * 
	 * @param p プロパティ
	 */
	public static void load(final Properties p) {
		protocol = p.getProperty("host.protocol", "http");
		hostName = p.getProperty("host.name", StringUtility.EMPTY);
		hostPort = p.getProperty("host.port", StringUtility.EMPTY);
		webApp = p.getProperty("webApp", StringUtility.EMPTY);
	}

	/**
	 * ホスト名を取得する。
	 * 
	 * @return ホスト名
	 */
	public static String getHostName() {
		return hostName;
	}

	/**
	 * ポート番号を取得する。
	 * 
	 * @return ポート番号
	 */
	public static String getHostPort() {
		return hostPort;
	}

	/**
	 * Webアプリケーション名を取得する。
	 * 
	 * @return Webアプリケーション名
	 */
	public static String getWebApp() {
		return webApp;
	}

	/**
	 * プロトコルを取得する。
	 * 
	 * @return プロトコル
	 */
	public static String getProtocol() {
		return protocol;
	}

	/**
	 * URLを取得する。
	 * 
	 * @param aAlias エイリアス
	 * @return URL
	 */
	public static String getUrl(final String aAlias) {
		return getUrl(aAlias, false);
	}

	/**
	 * URLを取得する。
	 * 
	 * @param aAlias エイリアス
	 * @param aAbsolute 絶対URL
	 * @return　URL
	 */
	public static String getUrl(final String aAlias, final boolean aAbsolute) {
		StringBuffer s = new StringBuffer();
		if (aAbsolute) {
			s.append(getProtocol()).append("://").append(getHostName());
			if (StringUtility.isNotEmpty(getHostPort())) {
				s.append(":").append(getHostPort());
			}
		}
		if (StringUtility.isNotEmpty(getWebApp())) {
			s.append("/").append(getWebApp());
		}
		if (StringUtility.isNotEmpty(aAlias)) {
			if (!aAlias.startsWith("/")) {
				s.append("/");
			}
			s.append(aAlias);
		} else {
			s.append("/");
		}
		return s.toString();
	}
}
