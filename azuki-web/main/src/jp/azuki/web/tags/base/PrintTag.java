package jp.azuki.web.tags.base;

/**
 * このクラスは、URLをレンダリングするタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public class PrintTag extends AbstractValuePrintTag {

	@Override
	protected final void doReadering(final Object aValue, final StringBuffer aBuffer) {
		aBuffer.append(s(aValue));
	}
}
