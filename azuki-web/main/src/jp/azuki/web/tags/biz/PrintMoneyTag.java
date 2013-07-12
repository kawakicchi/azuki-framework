package jp.azuki.web.tags.biz;

import javax.servlet.jsp.JspException;

import jp.azuki.core.util.StringUtility;
import jp.azuki.web.tags.AbstractPrintTag;

/**
 * このクラスは、URLをレンダリングするタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public final class PrintMoneyTag extends AbstractPrintTag {

	/**
	 * コンストラクタ
	 */
	public PrintMoneyTag() {
		super(PrintMoneyTag.class);
	}

	@Override
	protected String doPrint(Object aValue) throws JspException {
		Long value = null;
		if (null == aValue) {

		} else if (aValue instanceof Short) {
			value = ((Short) aValue).longValue();
		} else if (aValue instanceof Integer) {
			value = ((Integer) aValue).longValue();
		} else if (aValue instanceof Long) {
			value = (Long) aValue;
		}

		if (null != value) {
			String str = String.format("%,3d円", value);
			return str;
		} else {
			return StringUtility.EMPTY;
		}
	}
}
