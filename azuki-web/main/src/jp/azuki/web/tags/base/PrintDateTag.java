package jp.azuki.web.tags.base;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * このクラスは、URLをレンダリングするタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public class PrintDateTag extends AbstractValuePrintTag {

	@Override
	protected final void doReadering(final Object aValue, final StringBuffer aBuffer) {
		Calendar cln = null;
		if (null == aValue) {

		} else if (aValue instanceof Date) {
			Date dt = (Date) aValue;
			cln = Calendar.getInstance();
			cln.setTimeInMillis(dt.getTime());
		} else if (aValue instanceof java.sql.Date) {
			java.sql.Date dt = (java.sql.Date) aValue;
			cln = Calendar.getInstance();
			cln.setTimeInMillis(dt.getTime());
		} else if (aValue instanceof Timestamp) {
			Timestamp ts = (Timestamp) aValue;
			cln = Calendar.getInstance();
			cln.setTimeInMillis(ts.getTime());
		}

		if (null != cln) {
			String str = String.format("%04d年%02d月%02d日", cln.get(Calendar.YEAR), cln.get(Calendar.MONTH) + 1, cln.get(Calendar.DAY_OF_MONTH));
			aBuffer.append(str);
		}
	}
}
