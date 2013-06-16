package jp.azuki.business.logic;

import jp.azuki.business.dao.DataAccessObject;
import jp.azuki.business.dao.DynamicSQLAccessObject;
import jp.azuki.business.dsql.DynamicSQL;
import jp.azuki.business.dsql.Group;
import jp.azuki.business.dsql.Parameter;
import jp.azuki.persistence.database.DatabaseSupport;

/**
 * このクラスは、ダイナミックSQL機能を実装したロジッククラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/15
 * @author Kawakicchi
 */
public abstract class AbstractDynamicSQLLogic extends AbstractDatabaseLogic {

	/**
	 * コンストラクタ
	 */
	public AbstractDynamicSQLLogic() {
		super();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractDynamicSQLLogic(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractDynamicSQLLogic(final Class<?> aClass) {
		super(aClass);
	}

	/**
	 * データアクセスオブジェクトを取得します。
	 * 
	 * @param aName ダイナミックSQL名
	 * @return データアクセスオブジェクト
	 */
	protected final DataAccessObject getDao(final String aName) {
		return getDao(aName, null, null);
	}

	/**
	 * データアクセスオブジェクトを取得します。
	 * 
	 * @param aName ダイナミックSQL名
	 * @param aGroup グループ
	 * @return データアクセスオブジェクト
	 */
	protected final DataAccessObject getDao(final String aName, final Group aGroup) {
		return getDao(aName, aGroup, null);
	}

	/**
	 * データアクセスオブジェクトを取得します。
	 * 
	 * @param aName ダイナミックSQL名
	 * @param aParameter パラメータ
	 * @return データアクセスオブジェクト
	 */
	protected final DataAccessObject getDao(final String aName, final Parameter aParameter) {
		return getDao(aName, null, aParameter);
	}

	/**
	 * データアクセスオブジェクトを取得します。
	 * 
	 * @param aName ダイナミックSQL名
	 * @param aGroup グループ
	 * @param aParameter パラメータ
	 * @return データアクセスオブジェクト
	 */
	protected final DataAccessObject getDao(final String aName, final Group aGroup, final Parameter aParameter) {
		DataAccessObject dao = null;
		DynamicSQL dsql = DynamicSQL.generate(aName, aGroup, aParameter);
		if (null != dsql) {
			dao = new DynamicSQLAccessObject(dsql);
			if (dao instanceof DatabaseSupport) {
				((DatabaseSupport) dao).setConnection(getConnection());
			}
		}
		return dao;
	}
}
