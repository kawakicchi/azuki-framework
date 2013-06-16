package jp.azuki.business.logic.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * このクラスは、ロジック情報のリストを保持するエンティティクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 12/09/21
 * @author Kawakicchi
 * 
 */
public final class LogicEntityList {

	/**
	 * プラグイン情報リスト
	 */
	private List<LogicEntity> list;

	/**
	 * コンストラクタ
	 */
	public LogicEntityList() {
		list = new ArrayList<LogicEntity>();
	}

	/**
	 * ロジック情報を追加する。
	 * 
	 * @param logic ロジック情報
	 */
	public void add(final LogicEntity logic) {
		list.add(logic);
	}

	/**
	 * ロジック数を取得する。
	 * 
	 * @return ロジック数
	 */
	public int size() {
		return list.size();
	}

	/**
	 * ロジック情報を取得する。
	 * 
	 * @param index インデックス
	 * @return ロジック情報
	 */
	public LogicEntity get(final int index) {
		return list.get(index);
	}

	/**
	 * ロジック情報をクリアする。
	 */
	public void clear() {
		list.clear();
	}
}
