package jp.azuki.web.tags.base;


/**
 * このクラスは、URLをレンダリングするタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public class PrintMoneyTag extends AbstractValuePrintTag {

	@Override
	protected final void doReadering(final Object aValue, final StringBuffer aBuffer) {
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
			aBuffer.append(str);
		}
	}
}
