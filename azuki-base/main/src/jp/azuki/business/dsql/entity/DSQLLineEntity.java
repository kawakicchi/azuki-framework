package jp.azuki.business.dsql.entity;

import jp.azuki.core.util.StringUtility;
import jp.azuki.persistence.entity.Entity;

/**
 * このクラスは、ダイナミックSQL行情報を保持するエンティティクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/14
 * @author Kawakicchi
 */
public final class DSQLLineEntity implements Entity {

	/**
	 * 行文字列
	 */
	private String string;

	/**
	 * コメント
	 */
	private boolean comment;

	/**
	 * SQL文字列
	 */
	private String sql;

	/**
	 * グループ名
	 */
	private String group;

	/**
	 * パラメータ名
	 */
	private String parameter;

	/**
	 * コンストラクタ
	 */
	public DSQLLineEntity() {

	}

	public void setString(final String aString) {
		string = aString;
	}

	public void setComment(final boolean aComment) {
		comment = aComment;
	}

	public void setSql(final String aSql) {
		sql = aSql;
	}

	public void setGroup(final String aGroup) {
		group = aGroup;
	}

	public void setParameter(final String aParameter) {
		parameter = aParameter;
	}

	/**
	 * 行の文字列を取得する。
	 * 
	 * @return 文字列
	 */
	public String getString() {
		return string;
	}

	/**
	 * コメント行か判断する。
	 * 
	 * @return 判断結果
	 */
	public boolean isComment() {
		return comment;
	}

	/**
	 * SQL部分の文字列を取得する。
	 * 
	 * @return 文字列
	 */
	public String getSQL() {
		return sql;
	}

	/**
	 * グループが有効か判断する。
	 * 
	 * @return 判断結果
	 */
	public boolean isGroup() {
		return StringUtility.isNotEmpty(group);
	}

	/**
	 * パラメータが有効か判断する。
	 * 
	 * @return 判断結果
	 */
	public boolean isParameter() {
		return StringUtility.isNotEmpty(parameter);
	}

	public String getGroup() {
		return group;
	}

	public String getParameter() {
		return parameter;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
