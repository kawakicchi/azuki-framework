package jp.azuki.persistence.database.entity;

/**
 * このクラスは、データベース接続情報を保持するエンティティクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 12/06/09
 * @author Kawakicchi
 * 
 */
public class DatabaseConnectionEntity {

	/**
	 * Driver
	 */
	private String driver;

	/**
	 * URI
	 */
	private String uri;

	/**
	 * User
	 */
	private String user;

	/**
	 * Password
	 */
	private String password;

	/**
	 * ドライバ名を取得します。
	 * 
	 * @return ドライバ名
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * 接続文字列を取得します。
	 * 
	 * @return 接続文字列
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * ユーザ名を取得します。
	 * 
	 * @return ユーザ名
	 */
	public String getUser() {
		return user;
	}

	/**
	 * パスワードを取得します。
	 * 
	 * @return パスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * ドライバ名を取得します。
	 * 
	 * @param aDriver ドライバ名
	 */
	public void setDriver(final String aDriver) {
		driver = aDriver;
	}

	/**
	 * 接続文字列を取得します。
	 * 
	 * @param aUri 接続文字列
	 */
	public void setUri(final String aUri) {
		uri = aUri;
	}

	/**
	 * ユーザ名を取得します。
	 * 
	 * @param aUser ユーザ名
	 */
	public void setUser(final String aUser) {
		user = aUser;
	}

	/**
	 * パスワードを取得します。
	 * 
	 * @param aPassword パスワード
	 */
	public void setPassword(final String aPassword) {
		password = aPassword;
	}
}
