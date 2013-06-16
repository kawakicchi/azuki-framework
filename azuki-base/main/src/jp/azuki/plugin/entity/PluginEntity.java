package jp.azuki.plugin.entity;

import jp.azuki.core.util.StringUtility;
import jp.azuki.persistence.entity.Entity;

/**
 * このクラスは、プラグイン情報を保持するエンティティクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 12/06/14
 * @author Kawakicchi
 */
public class PluginEntity implements Entity {

	/**
	 * プラグイン名
	 */
	private String name;

	/**
	 * プラグインクラス
	 */
	private String clazz;

	/**
	 * 設定ファイル
	 */
	private String config;

	/**
	 * プラグイン名を設定する。
	 * 
	 * @param aName プラグイン名
	 */
	public void setName(final String aName) {
		name = aName;
	}

	/**
	 * プラグインクラスを設定する。
	 * 
	 * @param aClass プラグインクラス
	 */
	public void setPlugin(final String aClass) {
		clazz = aClass;
	}

	/**
	 * 設定ファイルを設定する。
	 * 
	 * @param aConfig 設定ファイル
	 */
	public void setConfig(final String aConfig) {
		config = aConfig;
	}

	/**
	 * プラグイン名を取得する。
	 * 
	 * @return プラグイン名
	 */
	public String getName() {
		return name;
	}

	/**
	 * プラグインクラスを取得する。
	 * 
	 * @return プラグインクラス
	 */
	public String getPlugin() {
		return clazz;
	}

	/**
	 * 設定ファイルを取得する。
	 * 
	 * @return 設定ファイル
	 */
	public String getConfig() {
		return config;
	}

	@Override
	public boolean isEmpty() {
		if (StringUtility.isEmpty(name)) {
			return true;
		}
		if (StringUtility.isEmpty(clazz)) {
			return true;
		}
		if (StringUtility.isEmpty(config)) {
			return true;
		}
		return false;
	}
}
