package jp.azuki.business.dsql.entity;

import jp.azuki.core.util.StringUtility;
import jp.azuki.persistence.entity.Entity;

/**
 * このクラスは、ダイナミックSQL情報を保持するエンティティクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/15
 * @author Kawakicchi
 */
public class DynamicSQLEntity implements Entity {

	private String name;

	private String file;

	public DynamicSQLEntity() {

	}

	public void setName(final String aName) {
		name = aName;
	}

	public String getName() {
		return name;
	}

	public void setFile(final String aFile) {
		file = aFile;
	}

	public String getFile() {
		return file;
	}

	public boolean isEmpty() {
		if (StringUtility.isNotEmpty(name)) {
			return false;
		}
		if (StringUtility.isNotEmpty(file)) {
			return false;
		}
		return true;
	}
}
