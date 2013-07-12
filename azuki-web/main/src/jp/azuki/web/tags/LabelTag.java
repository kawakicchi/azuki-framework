package jp.azuki.web.tags;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;

import jp.azuki.business.label.Label;
import jp.azuki.business.label.LabelFactory;
import jp.azuki.core.util.StringUtility;

/**
 * このクラスは、ラベル表示を行うタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/07/02
 * @author Kawakicchi
 */
public class LabelTag extends AbstractBodyRenderingTag implements ParameterTagSupport {

	/**
	 * 名前空間
	 */
	private String namespace;

	/**
	 * メッセージ名
	 */
	private String name;

	/**
	 * パラメータ情報
	 */
	private Map<String, Object> parameter;

	/**
	 * コンストラクタ
	 */
	public LabelTag() {
		super(LabelTag.class);
		parameter = new HashMap<String, Object>();
	}

	/**
	 * 名前空間を設定する。
	 * 
	 * @param aNamespace 名前空間
	 */
	public final void setNamespace(final String aNamespace) {
		namespace = aNamespace;
	}

	/**
	 * ラベル名を設定する。
	 * 
	 * @param aName ラベル名
	 */
	public final void setName(final String aName) {
		name = aName;
	}

	@Override
	public final void setParameter(final String aKey, final Object aValue) {
		parameter.put(aKey, aValue);
	}

	@Override
	public final void setParameters(final Map<String, Object> aParams) {
		parameter.putAll(aParams);
	}

	/**
	 * 名前空間を取得する。
	 * 
	 * @return 名前空間
	 */
	protected final String getNamespace() {
		return namespace;
	}

	/**
	 * ラベル名を取得する。
	 * 
	 * @return ラベル名
	 */
	protected final String getName() {
		return name;
	}

	@Override
	protected void doRendering(final StringBuffer aRender) throws JspException {
		String ns = getNamespace();
		String nm = getName();

		Label lbl = null;
		if (StringUtility.isNotEmpty(ns)) {
			lbl = LabelFactory.create(ns, nm);
		} else {
			lbl = LabelFactory.create(nm);
		}

		if (null != lbl) {
			String str = lbl.generate(parameter);
			aRender.append(toStringEscapeHTML(str));
		}
	}

}
