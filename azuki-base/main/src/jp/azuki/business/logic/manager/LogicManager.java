package jp.azuki.business.logic.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import jp.azuki.business.exception.BusinessServiceException;
import jp.azuki.business.logic.Logic;
import jp.azuki.business.logic.entity.LogicEntity;
import jp.azuki.business.logic.entity.LogicEntityList;
import jp.azuki.core.lang.LoggingObject;
import jp.azuki.core.util.StringUtility;
import jp.azuki.persistence.context.Context;
import jp.azuki.persistence.proterty.Property;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

/**
 * このクラスは、ロジックの管理を行うマネージャークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/09/21
 * @author Kawakicchi
 */
public final class LogicManager extends LoggingObject {

	/**
	 * Instance
	 */
	private static final LogicManager INSTANCE = new LogicManager();

	/**
	 * logics
	 */
	private Map<String, Map<String, LogicData>> logics = new HashMap<String, Map<String, LogicData>>();

	/**
	 * コンストラクタ
	 * <p>
	 * インスタンス生成を禁止する。
	 * </p>
	 */
	private LogicManager() {
		super(LogicManager.class);
	}

	/**
	 * 初期か処理を行う。
	 */
	public synchronized static void initialize() {
		INSTANCE.doInitialize();
	}

	/**
	 * 解放処理を行う。
	 */
	public synchronized static void destroy() {
		INSTANCE.doDestroy();
	}

	/**
	 * ロジック情報をロードする。
	 * 
	 * @param aStream ロジック情報
	 * @param aContext コンテキスト
	 * @throws BusinessServiceException ビジネスサービスに起因する問題が発生した場合
	 * @throws IOException 入出力操作に起因する問題が発生した場合
	 */
	public synchronized static void load(final InputStream aStream, final Context aContext) throws BusinessServiceException, IOException {
		INSTANCE.doLoad(StringUtility.EMPTY, aStream, aContext);
	}

	/**
	 * ロジック情報をロードする。
	 * 
	 * @param aNamespace 名前空間
	 * @param aStream ロジック情報
	 * @param aContext コンテキスト
	 * @throws BusinessServiceException ビジネスサービスに起因する問題が発生した場合
	 * @throws IOException 入出力操作に起因する問題が発生した場合
	 */
	public synchronized static void load(final String aNamespace, final InputStream aStream, final Context aContext) throws BusinessServiceException,
			IOException {
		INSTANCE.doLoad(StringUtility.EMPTY, aStream, aContext);
	}

	/**
	 * ロジックを生成する。
	 * 
	 * @param aNamespace 名前空間
	 * @param aName ロジック名
	 * @return ロジック。ロジックが存在しない場合、<code>null</code>を返す。
	 * @throws BusinessServiceException ビジネスサービス層に起因する問題が発生した場合
	 */
	public static Logic create(final String aNamespace, final String aName) throws BusinessServiceException {
		return INSTANCE.doCreate(aNamespace, aName);
	}

	/**
	 * ロジックを生成する。
	 * 
	 * @param aName ロジック名
	 * @return ロジック。ロジックが存在しない場合、<code>null</code>を返す。
	 * @throws BusinessServiceException ビジネスサービス層に起因する問題が発生した場合
	 */
	public static Logic create(final String aName) throws BusinessServiceException {
		return INSTANCE.doCreate(StringUtility.EMPTY, aName);
	}

	/**
	 * 初期化処理を行う。
	 */
	private void doInitialize() {

	}

	/**
	 * 解放処理を行う。
	 */
	private void doDestroy() {

	}

	/**
	 * ロジック情報をロードする。
	 * 
	 * @param aNamespace 名前空間
	 * @param aStream ロジック情報
	 * @param aContext コンテキスト
	 * @throws BusinessServiceException ビジネスサービスに起因する問題が発生した場合
	 * @throws IOException 入出力操作に起因する問題が発生した場合
	 */
	@SuppressWarnings("unchecked")
	private void doLoad(final String aNamespace, final InputStream aStream, final Context aContext) throws BusinessServiceException, IOException {

		LogicEntityList logicList = null;
		try {
			Digester digester = new Digester();
			digester.addObjectCreate("azuki/logic-list", LogicEntityList.class);
			digester.addObjectCreate("azuki/logic-list/logic", LogicEntity.class);
			digester.addSetProperties("azuki/logic-list/logic");
			digester.addSetNext("azuki/logic-list/logic", "add");
			logicList = digester.parse(aStream);
		} catch (SAXException ex) {
			error(ex);
			throw new IOException(ex);
		} catch (IOException ex) {
			error(ex);
			throw new IOException(ex);
		}

		Map<String, LogicData> m = null;
		if (logics.containsKey(aNamespace)) {
			m = logics.get(aNamespace);
		} else {
			m = new HashMap<String, LogicData>();
		}

		for (int i = 0; i < logicList.size(); i++) {
			LogicEntity logic = logicList.get(i);
			if (m.containsKey(logic.getName())) {
				throw new BusinessServiceException("Duplicate logic name.[" + logic.getName() + "]");
			} else {
				try {
					LogicData data = new LogicData();

					Class<Logic> clazz = (Class<Logic>) Class.forName(logic.getLogic());

					Map<String, Object> properties = new HashMap<String, Object>();
					Property anProperty = clazz.getAnnotation(Property.class);
					if (null != anProperty) {
						String property = anProperty.value();
						if (StringUtility.isNotEmpty(property)) {
							InputStream is = aContext.getResourceAsStream(property);
							if (null != is) {
								Properties p = new Properties();
								p.load(is);
								for (String key : p.stringPropertyNames()) {
									properties.put(key, p.getProperty(key));
								}
							} else {
								throw new BusinessServiceException("Not found logic property file.[" + property + "]");
							}
						}
					}
					data.setLogic(clazz);
					data.setProperties(properties);
					data.setEntity(logic);
					m.put(logic.getName(), data);
				} catch (ClassNotFoundException ex) {
					error(ex);
					throw new BusinessServiceException(ex);
				}
			}
		}
		logics.put(aNamespace, m);
	}

	/**
	 * ロジックを生成する。
	 * 
	 * @param aNamespace 名前空間
	 * @param aName ロジック名
	 * @return ロジック。ロジックが存在しない場合、<code>null</code>を返す。
	 * @throws BusinessServiceException ビジネスサービス層に起因する問題が発生した場合
	 */
	private Logic doCreate(final String aNamespace, final String aName) throws BusinessServiceException {
		Logic logic = null;
		try {
			if (logics.containsKey(aNamespace)) {
				Map<String, LogicData> ls = logics.get(aNamespace);
				if (ls.containsKey(aName)) {
					LogicData d = ls.get(aName);
					logic = d.getLogic().newInstance();
				}
			}
		} catch (InstantiationException ex) {
			throw new BusinessServiceException(ex);
		} catch (IllegalAccessException ex) {
			throw new BusinessServiceException(ex);
		}
		return logic;
	}

	private static class LogicData {

		private Class<Logic> logic;

		private Map<String, Object> properties;

		@SuppressWarnings("unused")
		private LogicEntity entity;

		public void setLogic(final Class<Logic> aLogic) {
			logic = aLogic;
		}

		public void setProperties(final Map<String, Object> aProperties) {
			properties = new HashMap<String, Object>(aProperties);
		}

		public void setEntity(final LogicEntity aEntity) {
			entity = aEntity;
		}

		public Class<Logic> getLogic() {
			return logic;
		}

		@SuppressWarnings("unused")
		public Map<String, Object> getProperties() {
			return properties;
		}
	}
}
