package jp.azuki.business.dsql;

import java.util.ArrayList;
import java.util.List;

import jp.azuki.business.dsql.entity.DSQLEntity;
import jp.azuki.business.dsql.entity.DSQLLineEntity;
import jp.azuki.business.dsql.manager.DynamicSQLManager;

/**
 * このクラスは、ダイナミックSQL情報を保持するクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/14
 * @author Kawakicchi
 */
public class DynamicSQL {

	private String name;
	private String sql;
	private List<Object> parameters;

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 * @param aSql SQL文
	 * @param aParameters パラメータ
	 */
	private DynamicSQL(final String aName, final String aSql, final List<Object> aParameters) {
		name = aName;
		sql = aSql;
		parameters = new ArrayList<Object>(aParameters);
	}

	/**
	 * 名前を取得する。
	 * 
	 * @return 名前
	 */
	public final String getName() {
		return name;
	}

	/**
	 * SQL文を取得する。
	 * 
	 * @return SQL文
	 */
	public final String getSQL() {
		return sql;
	}

	/**
	 * パラメータを取得する。
	 * 
	 * @return パラメータ
	 */
	public List<Object> getParameters() {
		return parameters;
	}

	/**
	 * ダイナミックSQLを生成する。
	 * 
	 * @param aName 名前
	 * @param aGroup グループ
	 * @param aParameter パラメータ
	 * @return ダイナミックSQL。ダイナミックSQLの生成に失敗した場合、<code>null</code>を返す。
	 */
	public static DynamicSQL generate(final String aName, final Group aGroup, final Parameter aParameter) {
		DynamicSQL dsql = null;
		DSQLEntity entity = DynamicSQLManager.get(aName);
		if (null != entity) {

			StringBuilder sql = new StringBuilder();
			List<Object> params = new ArrayList<Object>();
			for (DSQLLineEntity line : entity) {
				if (line.isComment()) {
					continue;
				}
				if (line.isGroup()) {
					if (null == aGroup) {
						continue;
					}
					if (!aGroup.is(line.getGroup())) {
						continue;
					}
				}
				if (line.isParameter()) {
					if (null == aParameter) {
						continue;
					}
					if (!aParameter.isKey(line.getParameter())) {
						continue;
					}
					params.add(aParameter.get(line.getParameter()));
				}
				sql.append(line.getSQL());
				sql.append(" ");
			}

			dsql = new DynamicSQL(aName, sql.toString(), params);
		}
		return dsql;
	}
}
