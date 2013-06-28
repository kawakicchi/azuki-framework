package jp.azuki.business.dsql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import jp.azuki.business.dsql.entity.DSQLEntity;
import jp.azuki.business.dsql.entity.DSQLLineEntity;
import jp.azuki.business.manager.AbstractManager;
import jp.azuki.core.util.StringUtility;
import jp.azuki.persistence.context.Context;
import jp.azuki.persistence.entity.Entity;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

/**
 * このクラスは、ダイナミックSQLの管理を行うマネージャークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/14
 * @author Kawakicchi
 */
public final class DynamicSQLManager extends AbstractManager {

	/**
	 * Dyanamic pattern
	 */
	private static Pattern PATTERN = Pattern.compile("^\\$\\{.*\\}.*$");

	/**
	 * Instance
	 */
	private static final DynamicSQLManager INSTANCE = new DynamicSQLManager();

	/**
	 * ダイナミックSQL情報
	 */
	private Map<String, DSQLEntity> dynamicSQLs = new HashMap<String, DSQLEntity>();

	/**
	 * コンストラクタ
	 * <p>
	 * インスタンス生成を禁止する。
	 * </p>
	 */
	private DynamicSQLManager() {
		super(DynamicSQLManager.class);
	}

	/**
	 * 初期化処理を行なう。
	 */
	public static void initialize() {

	}

	/**
	 * 解放処理を行なう。
	 */
	public static void destroy() {

	}

	/**
	 * 設定をロードする。
	 * 
	 * @param aConfig 設定ファイル
	 * @param aContext コンテキスト
	 * @throws IOException IO操作時に問題が発生した場合
	 */
	public static void load(final String aConfig, final Context aContext) throws IOException {
		INSTANCE.doLoad(aConfig, aContext);
	}

	/**
	 * 設定をロードする。
	 * 
	 * @param aStream ストリーム
	 * @param aContext コンテキスト
	 * @throws IOException IO操作時に問題が発生した場合
	 */
	public static void load(final InputStream aStream, final Context aContext) throws IOException {
		INSTANCE.doLoad(aStream, aContext);
	}

	/**
	 * ダイナミックSQLを生成する。
	 * 
	 * @param aName 名前
	 * @return ダイナミックSQL
	 */
	public static DSQLEntity get(final String aName) {
		return INSTANCE.doGet(aName);
	}

	/**
	 * 設定をロードする。
	 * 
	 * @param aConfig 設定ファイル
	 * @param aContext コンテキスト
	 * @throws IOException IO操作時に問題が発生した場合
	 */
	private void doLoad(final String aConfig, final Context aContext) throws IOException {
		InputStream stream = aContext.getResourceAsStream(aConfig);
		if (null == stream) {
			error("Not found dynamicSQL file.[" + aConfig + "]");
			throw new IOException("Not found dynamicSQL file.[" + aConfig + "]");
		}
		doLoad(stream, aContext);
	}

	/**
	 * 設定をロードする。
	 * 
	 * @param aStream 設定ファイル
	 * @param aContext コンテキスト
	 * @throws IOException IO操作時に問題が発生した場合
	 */
	private void doLoad(final InputStream aStream, final Context aContext) throws IOException {
		List<DynamicSQLEntity> dsqls = null;
		try {
			Digester digester = new Digester();
			digester.addObjectCreate("azuki/dynamicSQL-list", ArrayList.class);
			digester.addObjectCreate("azuki/dynamicSQL-list/dynamicSQL", DynamicSQLEntity.class);
			digester.addSetProperties("azuki/dynamicSQL-list/dynamicSQL");
			digester.addSetNext("azuki/dynamicSQL-list/dynamicSQL", "add");
			dsqls = digester.parse(aStream);
		} catch (SAXException ex) {
			error(ex);
			throw new IOException(ex);
		} catch (IOException ex) {
			error(ex);
			throw ex;
		}

		for (DynamicSQLEntity entity : dsqls) {
			if (dynamicSQLs.containsKey(entity.getName())) {
				error("Duplicate dynamicSQL name.[" + entity.getName() + "]");
				continue;
			}
			InputStream is = aContext.getResourceAsStream(entity.getFile());
			if (null != is) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				String line = null;
				DSQLEntity dsql = new DSQLEntity();
				while (null != (line = reader.readLine())) {
					String buf = line.trim();
					DSQLLineEntity dsqll = new DSQLLineEntity();
					if (buf.startsWith("#")) {
						dsqll.setComment(true);
						dsqll.setString(line);
					} else {
						if (PATTERN.matcher(buf).matches()) {
							int index = buf.indexOf("}");
							String sql = buf.substring(index + 1).trim();
							String cnt = buf.substring(2, index).trim();
							index = cnt.indexOf(":");
							if (0 == index) {
								String param = cnt.substring(1).trim();
								dsqll.setComment(false);
								dsqll.setParameter(param);
								dsqll.setSql(sql);
								dsqll.setString(line);
							} else if (cnt.length() - 1 == index) {
								String group = cnt.substring(0, cnt.length() - 1);
								dsqll.setComment(false);
								dsqll.setGroup(group);
								dsqll.setSql(sql);
								dsqll.setString(line);
							} else if (-1 == index) {
								dsqll.setComment(false);
								dsqll.setParameter(cnt);
								dsqll.setSql(sql);
								dsqll.setString(line);
							} else {
								String[] splt = cnt.split(":");
								String group = splt[0];
								String param = splt[1];
								dsqll.setComment(false);
								dsqll.setGroup(group);
								dsqll.setParameter(param);
								dsqll.setSql(sql);
								dsqll.setString(line);
							}
						} else {
							dsqll.setComment(false);
							dsqll.setSql(buf);
							dsqll.setString(line);
						}
					}
					dsql.add(dsqll);
				}
				dynamicSQLs.put(entity.getName(), dsql);
				reader.close();
			} else {
				error("Not found dynamicSQL file.[" + entity.getFile() + "]");
				throw new IOException("Not found dynamicSQL file.[" + entity.getFile() + "]");
			}
		}
	}

	/**
	 * ダイナミックSQLを生成する。
	 * 
	 * @param aName 名前
	 * @param aGroup グループ
	 * @param aParameter パラメーター
	 * @return ダイナミックSQL
	 */
	private DSQLEntity doGet(final String aName) {
		return dynamicSQLs.get(aName);
	}

	/**
	 * このクラスは、ダイナミックSQL情報を保持するエンティティクラスです。
	 * 
	 * @since 1.0.0
	 * @version 1.0.0 2013/02/15
	 * @author Kawakicchi
	 */
	public static class DynamicSQLEntity implements Entity {

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

}
