package jp.azuki.business.label;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import jp.azuki.business.BusinessServiceException;
import jp.azuki.business.manager.AbstractManager;
import jp.azuki.core.util.StringUtility;
import jp.azuki.persistence.ConfigurationFormatException;
import jp.azuki.persistence.context.Context;

/**
 * このクラスは、ラベルの管理を行うマネージャークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/07/02
 * @author Kawakicchi
 */
public final class LabelManager extends AbstractManager {

	/**
	 * Instance
	 */
	private static final LabelManager INSTANCE = new LabelManager();

	/**
	 * デフォルト名前空間
	 */
	private static final String NAMESPACE_DEFAULT = StringUtility.EMPTY;

	/**
	 * 初期化処理を行う。
	 */
	public static void initialize() {
		INSTANCE.doInitialize();
	}

	/**
	 * 解放処理を行う。
	 */
	public static void destroy() {
		INSTANCE.doDestroy();
	}

	/**
	 * ラベル情報をロードします。
	 * 
	 * @param aNamespace 名前空間
	 * @param aStream ラベル「ファイルストリーム
	 * @param aContext コンテキスト情報
	 * @throws BusinessServiceException ビジネスサービスに起因する問題が発生した場合
	 * @throws ConfigurationFormatException 設定ファイルに問題がある場合
	 * @throws IOException 入出力操作に起因する問題が発生した場合
	 */
	public static void load(final String aNamespace, final InputStream aStream, final Context aContext) throws BusinessServiceException,
			ConfigurationFormatException, IOException {
		INSTANCE.doLoad(aNamespace, aStream, aContext);
	}

	/**
	 * ラベル情報をロードします。
	 * 
	 * @param aStream ラベルファイルストリーム
	 * @param aContext コンテキスト情報
	 * @throws BusinessServiceException ビジネスサービスに起因する問題が発生した場合
	 * @throws ConfigurationFormatException 設定ファイルに問題がある場合
	 * @throws IOException 入出力操作に起因する問題が発生した場合
	 */
	public static void load(final InputStream aStream, final Context aContext) throws BusinessServiceException, ConfigurationFormatException,
			IOException {
		INSTANCE.doLoad(LabelManager.NAMESPACE_DEFAULT, aStream, aContext);
	}

	/**
	 * ラベルを取得する。
	 * 
	 * @param aId ラベルID
	 * @return ラベル情報。ラベル情報が存在しない場合、<code>null</code>を返す。
	 */
	public static Label getLabel(final String aId) {
		return INSTANCE.doGetLabel(LabelManager.NAMESPACE_DEFAULT, aId);
	}

	/**
	 * ラベルを取得する。
	 * 
	 * @param aNamespace 名前空間
	 * @param aId ラベルID
	 * @return ラベル情報。ラベル情報が存在しない場合、<code>null</code>を返す。
	 */
	public static Label getLabel(final String aNamespace, final String aId) {
		return INSTANCE.doGetLabel(aNamespace, aId);
	}

	/**
	 * ラベル情報マップ
	 */
	private Map<String, Map<String, Label>> labels;

	/**
	 * コンストラクタ
	 * <p>
	 * インスタンス生成を禁止する。
	 * </p>
	 */
	private LabelManager() {
		super(LabelManager.class);
		labels = new HashMap<String, Map<String, Label>>();
	}

	/**
	 * 処理化処理を行う。
	 */
	private void doInitialize() {
		synchronized (labels) {
			labels.clear();
		}
	}

	/**
	 * 解放処理を行う。
	 */
	private void doDestroy() {
		synchronized (labels) {
			labels.clear();
		}
	}

	/**
	 * ラベル情報をロードする。
	 * 
	 * @param aNamespace 名前空間
	 * @param aStream ラベルファイルストリーム
	 * @param aContext コンテキスト情報
	 * @throws ConfigurationFormatException 設定ファイルに問題がある場合
	 * @throws IOException IO操作時に問題が発生した場合
	 */
	private void doLoad(final String aNamespace, final InputStream aStream, final Context aContext) throws ConfigurationFormatException, IOException {
		synchronized (labels) {
			Properties p = new Properties();
			p.load(aStream);
			for (Object key : p.keySet()) {
				Object value = p.get(key);

				String k, v;
				if (key instanceof String) {
					k = (String) key;
				} else {
					k = key.toString();
				}
				if (value instanceof String) {
					v = (String) value;
				} else {
					v = value.toString();
				}

				Map<String, Label> lbls = null;
				if (labels.containsKey(aNamespace)) {
					lbls = labels.get(aNamespace);
				} else {
					lbls = new HashMap<String, Label>();
					labels.put(aNamespace, lbls);
				}

				if (lbls.containsKey(k)) {
					throw new ConfigurationFormatException("Duplicate　label key.[namespace=" + aNamespace + ", key=" + k + "]");
				}

				Label lbl = new Label(k, v);
				lbls.put(k, lbl);
			}
		}
	}

	/**
	 * ラベルを取得する。
	 * 
	 * @param aNamespace 名前空間
	 * @param aId ラベルID
	 * @return ラベル情報。ラベル情報が存在しない場合、<code>null</code>を返す。
	 */
	private Label doGetLabel(final String aNamespace, final String aId) {
		Label lbl = null;
		if (labels.containsKey(aNamespace)) {
			Map<String, Label> ms = labels.get(aNamespace);
			if (ms.containsKey(aId)) {
				lbl = ms.get(aId);
			}
		}
		return lbl;
	}
}
