package jp.azuki.web.tags;

import jp.azuki.web.constant.WebConstant;

/**
 * このクラスは、URLをレンダリングするタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public class UrlTag extends ReaderingTag {

	/**
	 * alias
	 */
	private String alias = "";

	/**
	 * absolute
	 */
	private boolean absolute = false;

	/**
	 * エイリアスを設定する。
	 * 
	 * @param aAlias
	 */
	public final void setAlias(final String aAlias) {
		alias = aAlias;
	}

	/**
	 * 絶対URLかどうかを設定する。
	 * 
	 * @param aAbsolute 絶対URLの場合、<code>true</code>を設定する。
	 */
	public final void setAbsolute(final boolean aAbsolute) {
		absolute = aAbsolute;
	}

	/**
	 * エイリアスを取得する。
	 * 
	 * @return エイリアス
	 */
	protected final String getAlias() {
		return alias;
	}

	/**
	 * 絶対URLか判断する。
	 * 
	 * @return 絶対URLの場合、<code>true</code>を返す。
	 */
	protected final boolean isAbsolute() {
		return absolute;
	}

	@Override
	protected final void doReadering(final StringBuffer reader) {
		reader.append(WebConstant.getUrl(getAlias(), isAbsolute()));
	}
}
