package jp.azuki.plugin.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * このクラスは、プラグイン情報のリストを保持するエンティティクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 12/06/14
 * @author Kawakicchi
 */
public final class PluginEntityList {

	/**
	 * プラグイン情報リスト
	 */
	private List<PluginEntity> list;

	/**
	 * コンストラクタ
	 */
	public PluginEntityList() {
		list = new ArrayList<PluginEntity>();
	}

	/**
	 * プラグイン情報を追加する。
	 * 
	 * @param plugin プラグイン情報
	 */
	public void add(final PluginEntity plugin) {
		list.add(plugin);
	}

	/**
	 * プラグイン数を取得する。
	 * 
	 * @return プラグイン数
	 */
	public int size() {
		return list.size();
	}

	/**
	 * プラグイン情報を取得する。
	 * 
	 * @param index インデックス
	 * @return プラグイン情報
	 */
	public PluginEntity get(final int index) {
		return list.get(index);
	}

	/**
	 * プラグイン情報をクリアする。
	 */
	public void clear() {
		list.clear();
	}
}
