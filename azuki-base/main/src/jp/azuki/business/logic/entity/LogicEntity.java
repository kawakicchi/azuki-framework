package jp.azuki.business.logic.entity;

/**
 * このクラスは、ロジック情報を保持するエンティティクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 12/09/21
 * @author Kawakicchi
 * 
 */
public class LogicEntity {

	/**
	 * ロジック名
	 */
	private String name;

	/**
	 * ロジッククラス
	 */
	private String clazz;

	/**
	 * ロジックインターフェース
	 */
	private String inter;

	/**
	 * ロジック名を設定する。
	 * 
	 * @param aName ロジック名
	 */
	public void setName(final String aName) {
		name = aName;
	}

	/**
	 * ロジッククラスを設定する。
	 * 
	 * @param aClass ロジッククラス
	 */
	public void setLogic(final String aClass) {
		clazz = aClass;
	}

	/**
	 * ロジックインターフェースを設定する。
	 * 
	 * @param aInterface ロジックインターフェース
	 */
	public void setInterface(final String aInterface) {
		inter = aInterface;
	}

	/**
	 * ロジック名を取得する。
	 * 
	 * @return ロジック名
	 */
	public String getName() {
		return name;
	}

	/**
	 * プラグインクラスを取得する。
	 * 
	 * @return プラグインクラス
	 */
	public String getLogic() {
		return clazz;
	}

	/**
	 * インターフェースを取得する。
	 * 
	 * @return インターフェース
	 */
	public String getInterface() {
		return inter;
	}
}
