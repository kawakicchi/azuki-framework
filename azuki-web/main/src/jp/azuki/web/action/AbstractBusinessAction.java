package jp.azuki.web.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import jp.azuki.business.BusinessServiceException;
import jp.azuki.business.logic.Logic;
import jp.azuki.business.logic.LogicManager;
import jp.azuki.core.util.StringUtility;
import jp.azuki.persistence.context.ContextSupport;
import jp.azuki.persistence.database.DatabaseConnectionSupport;
import jp.azuki.persistence.proterty.Property;
import jp.azuki.persistence.proterty.PropertyManager;
import jp.azuki.persistence.proterty.PropertySupport;

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
	 * @param aName 名前
	 */
	public AbstractBusinessAction(final String aName) {
		super(aName);
		logics = new HashMap<String, Map<String, Logic>>();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractBusinessAction(final Class<?> aClass) {
		super(aClass);
		logics = new HashMap<String, Map<String, Logic>>();
	}

	@Override
	protected void doBeforeAction() {
		super.doBeforeAction();
		// TODO Write doBeforeExecute code.

	}

	@Override
	protected void doAfterAction() {
		for (String namespace : logics.keySet()) {
			Map<String, Logic> ls = logics.get(namespace);
			for (String name : ls.keySet()) {
				ls.get(name).destroy();
			}
		}
		logics.clear();

		super.doAfterAction();
	}

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
					doLogicSupport(logic);
					logic.initialize();
				}
				ls.put(aName, logic);
			}

		} catch (SQLException ex) {
			throw new BusinessServiceException(ex);
		}
		return logic;
	}

	/**
	 * ロジックにサポートを行う。
	 * <p>
	 * ロジックに新機能を追加したい場合、このメソッドをオーバーライドしスーパークラスの同メソッドを呼び出した後に追加機能をサポートすること。
	 * </p>
	 * 
	 * @param aLogic ロジック
	 * @throws SQLException SQL操作時に問題が発生した場合
	 */
	protected void doLogicSupport(final Logic aLogic) throws SQLException {
		if (aLogic instanceof ContextSupport) {
			((ContextSupport) aLogic).setContext(getContext());
		}
		if (aLogic instanceof PropertySupport) {
			Property property = PropertyManager.get(aLogic.getClass());
			if (null == property) {
				property = PropertyManager.load(aLogic.getClass(), getContext());
			}
			((PropertySupport) aLogic).setProperty(property);
		}
		if (aLogic instanceof DatabaseConnectionSupport) {
			((DatabaseConnectionSupport) aLogic).setConnection(getConnection());
		}
	}
}
