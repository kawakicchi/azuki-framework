package jp.azuki.web.action.entity;

import jp.azuki.core.util.StringUtility;
import jp.azuki.persistence.entity.Entity;

/**
 * このクラスは、アクション情報を保持するエンティティクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/18
 * @author Kawakicchi
 */
public class ActionEntity implements Entity {

	/**
	 * Action name
	 */
	private String name;

	/**
	 * Action class
	 */
	private String action;

	/**
	 * コンストラクタ
	 */
	public ActionEntity() {

	}

	/**
	 * アクション名を設定する。
	 * 
	 * @param aName アクション名
	 */
	public void setName(final String aName) {
		name = aName;
	}

	/**
	 * アクション名を取得する。
	 * 
	 * @return アクション名
	 */
	public String getName() {
		return name;
	}

	/**
	 * アクションクラスを設定する。
	 * 
	 * @param aAction アクションクラス
	 */
	public void setAction(final String aAction) {
		action = aAction;
	}

	/**
	 * アクションクラスを取得する。
	 * 
	 * @return アクションクラス
	 */
	public String getAction() {
		return action;
	}

	@Override
	public boolean isEmpty() {
		if (StringUtility.isNotEmpty(name)) {
			return false;
		}
		if (StringUtility.isNotEmpty(action)) {
			return false;
		}
		return true;
	}
}
