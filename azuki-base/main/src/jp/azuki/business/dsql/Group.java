package jp.azuki.business.dsql;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * このクラスは、グループ情報を保持するクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/14
 * @author Kawakicchi
 */
public final class Group {

	/**
	 * group set
	 */
	private Set<String> groups;

	/**
	 * コンストラクタ
	 */
	public Group() {
		groups = new HashSet<String>();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aGroups グループ
	 */
	public Group(final String... aGroups) {
		groups = new HashSet<>();
		for (String group : aGroups) {
			groups.add(group);
		}
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aGroups グループ
	 */
	public Group(final List<String> aGroups) {
		groups = new HashSet<>();
		for (String group : aGroups) {
			groups.add(group);
		}
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aGroup グループ
	 */
	public Group(final Group aGroup) {
		for (String group : aGroup.groups) {
			groups.add(group);
		}
	}

	/**
	 * グループを追加する。
	 * 
	 * @param aGroup グループ
	 */
	public void add(final String aGroup) {
		groups.add(aGroup);
	}

	/**
	 * グループが存在するか判断する。
	 * 
	 * @param aGroup グループ
	 * @return グループが存在する場合、<code>true</code>を返す。
	 */
	public boolean is(final String aGroup) {
		return groups.contains(aGroup);
	}
}
