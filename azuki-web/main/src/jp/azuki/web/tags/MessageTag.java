package jp.azuki.web.tags;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;

import jp.azuki.business.message.Message;
import jp.azuki.business.message.MessageFactory;
import jp.azuki.core.util.StringUtility;

/**
 * このクラスは、メッセージ表示を行うタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/16
 * @author Kawakicchi
 */
public class MessageTag extends AbstractBodyRenderingTag implements ParameterTagSupport {

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
	public MessageTag() {
		super(MessageTag.class);
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
	 * メッセージ名を設定する。
	 * 
	 * @param aName メッセージ名
	 */
	public final void setName(final String aName) {
		name = aName;
	}

	@Override
	public void setParameter(final String aKey, final Object aValue) {
		parameter.put(aKey, aValue);
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
	 * メッセージ名を取得する。
	 * 
	 * @return メッセージ名
	 */
	protected final String getName() {
		return name;
	}

	@Override
	protected void doRendering(final StringBuffer aReader, final String aBody) throws JspException {
		String ns = getNamespace();
		String nm = getName();

		Message msg = null;
		if (StringUtility.isNotEmpty(ns)) {
			msg = MessageFactory.create(ns, nm);
		} else {
			msg = MessageFactory.create(nm);
		}

		if (null != msg) {
			String str = msg.generate(parameter);
			aReader.append(toEscapeString(str));
		}
	}

}
