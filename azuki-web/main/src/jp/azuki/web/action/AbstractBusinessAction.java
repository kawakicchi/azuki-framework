package jp.azuki.web.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import jp.azuki.business.exception.BusinessServiceException;
import jp.azuki.business.logic.Logic;
import jp.azuki.business.logic.manager.LogicManager;
import jp.azuki.core.util.StringUtility;
import jp.azuki.persistence.context.ContextSupport;
import jp.azuki.persistence.database.DatabaseConnectionSupport;
import jp.azuki.persistence.exception.PersistenceServiceException;
import jp.azuki.persistence.proterty.Property;
import jp.azuki.persistence.proterty.PropertyManager;
import jp.azuki.persistence.proterty.PropertySupport;
import jp.azuki.web.constant.WebServiceException;
import jp.azuki.web.view.View;

/**
 * このクラスは、ビジネス機能を実装するアクションクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/12/31
 * @author Kawakicchi
 */
public abstract class AbstractBusinessAction extends AbstractDatabaseAction {

	/**
	 * Logics
	 */
	private Map<String, Map<String, Logic>> logics;

	/**
	 * コンストラクタ
	 */
	public AbstractBusinessAction() {
		super();
		logics = new HashMap<String, Map<String, Logic>>();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName Name
	 */
	public AbstractBusinessAction(final String aName) {
		super(aName);
		logics = new HashMap<String, Map<String, Logic>>();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass Class
	 */
	public AbstractBusinessAction(final Class<?> aClass) {
		super(aClass);
		logics = new HashMap<String, Map<String, Logic>>();
	}

	@Override
	protected final View doDatabaseAction(final Map<String, Object> aParameter) throws WebServiceException, PersistenceServiceException, SQLException {
		View view = null;
		try {
			view = doBusinessAction(aParameter);
			for (String namespace : logics.keySet()) {
				Map<String, Logic> ls = logics.get(namespace);
				for (String name : ls.keySet()) {
					ls.get(name).destroy();
				}
			}
			logics = new HashMap<String, Map<String, Logic>>();
		} catch (BusinessServiceException ex) {
			throw new WebServiceException(ex);
		}
		return view;
	}

	/**
	 * アクションを実行する。
	 * 
	 * @param aParameter パラメーター
	 * @return ビュー
	 * @throws WebServiceException ウェブサービス層に起因する問題が発生した場合
	 * @throws PersistenceServiceException 永続化層に起因する問題が発生した場合
	 * @throws BusinessServiceException ビジネスサービス層に起因する問題が発生した場合
	 * @throws SQLException SQL操作時に問題が発生した場合
	 */
	protected abstract View doBusinessAction(final Map<String, Object> aParameter) throws WebServiceException, PersistenceServiceException,
			BusinessServiceException, SQLException;

	/**
	 * ロジックを取得する。
	 * 
	 * @param aName ロジック名
	 * @return ロジック
	 * @throws BusinessServiceException ビジネスサービス層に起因する問題が発生した場合
	 */
	protected final Logic getLogic(final String aName) throws BusinessServiceException {
		return getLogic(StringUtility.EMPTY, aName);
	}

	/**
	 * ロジックを取得する。
	 * 
	 * @param aNamespace プラグイン名
	 * @param aName ロジック名
	 * @return ロジック
	 * @throws BusinessServiceException ビジネスサービス層に起因する問題が発生した場合
	 */
	protected final Logic getLogic(final String aNamespace, final String aName) throws BusinessServiceException {
		Logic logic = null;
		try {

			Map<String, Logic> ls = null;
			if (logics.containsKey(aNamespace)) {
				ls = logics.get(aNamespace);
			} else {
				ls = new HashMap<String, Logic>();
				logics.put(aNamespace, ls);
			}

			if (ls.containsKey(aName)) {
				logic = ls.get(aName);
			} else {
				logic = LogicManager.create(aNamespace, aName);
				if (null != logic) {
					if (logic instanceof ContextSupport) {
						((ContextSupport) logic).setContext(getContext());
					}
					if (logic instanceof PropertySupport) {
						Property property = PropertyManager.get(logic.getClass());
						if (null == property) {
							property = PropertyManager.load(logic.getClass(), getContext());
						}
						((PropertySupport) logic).setProperty(property);
					}
					if (logic instanceof DatabaseConnectionSupport) {
						((DatabaseConnectionSupport) logic).setConnection(getConnection());
					}

					logic.initialize();
				}
				ls.put(aName, logic);
			}

		} catch (SQLException ex) {
			throw new BusinessServiceException(ex);
		}
		return logic;
	}
}
