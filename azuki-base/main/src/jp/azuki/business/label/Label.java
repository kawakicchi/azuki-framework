package jp.azuki.business.label;

import java.util.Map;

/**
 * このクラスは、ラベル情報を保持するクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/07/02
 * @author Kawakicchi
 */
public final class Label {

	/**
	 * ラベルＩＤ
	 */
	private String id;

	/**
	 * ラベル
	 */
	private String label;

	/**
	 * コンストラクタ
	 * 
	 * @param aId ラベルＩＤ
	 * @param aLabel ラベル
	 */
	public Label(final String aId, final String aLabel) {
		id = aId;
		label = aLabel;
	}

	/**
	 * ラベルIDを取得する。
	 * 
	 * @return ラベルID
	 */
	public String getId() {
		return id;
	}

	/**
	 * ラベルを取得する。
	 * 
	 * @return ラベル
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * ラベルを生成する。
	 * 
	 * @param args パラメーター
	 * @return ラベル
	 */
	public String generate(final String... args) {
		String lbl = label;
		for (int i = 0; i < args.length; i++) {
			String word = "¥¥$¥¥{" + (i + 1) + "¥¥}";
			lbl = lbl.replaceAll(word, args[i]);
		}
		return lbl;
	}

	/**
	 * ラベルを生成する。
	 * 
	 * @param args パラメーター
	 * @return ラベル
	 */
	public String generate(final Map<String, String> args) {
		String lbl = label;
		for (String key : args.keySet()) {
			String word = "¥¥$¥¥{" + key + "¥¥}";
			lbl = lbl.replaceAll(word, args.get(key));
		}
		return lbl;
	}

}
