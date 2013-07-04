package jp.azuki.web.tags;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;

import jp.azuki.core.util.StringUtility;
import jp.azuki.web.constant.WebConstant;

/**
 * このクラスは、URLをレンダリングするタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public class UrlTag extends AbstractBodyRenderingTag implements ParameterTagSupport {

	/**
	 * エイリアス
	 */
	private String alias = "";

	/**
	 * 絶対パスフラグ
	 */
	private boolean absolute = false;

	/**
	 * パラメータ情報
	 */
	private Map<String, Object> parameter;

	/**
	 * コンストラクタ
	 */
	public UrlTag() {
		super(UrlTag.class);
		parameter = new HashMap<String, Object>();
	}

	/**
	 * エイリアスを設定する。
	 * 
	 * @param aAlias エイリアス
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

	@Override
	public void setParameter(final String aKey, final Object aValue) {
		parameter.put(aKey, aValue);
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
	protected final void doRendering(final StringBuffer aRender, final String aBody) throws JspException {
		String str = WebConstant.getUrl(getAlias(), isAbsolute());
		for (String key : parameter.keySet()) {
			String word = "\\$\\{" + key + "\\}";
			str = str.replaceAll(word, StringUtility.toStringEmpty(parameter.get(key)));
		}

		aRender.append(str);
	}
}
