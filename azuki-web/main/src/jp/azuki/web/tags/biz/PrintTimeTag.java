package jp.azuki.web.tags.biz;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

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
public final class PrintTimeTag extends AbstractPrintTag {

	/**
	 * コンストラクタ
	 */
	public PrintTimeTag() {
		super(PrintTimeTag.class);
	}

	@Override
	protected String doPrint(Object aValue) throws JspException {
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
			String str = String.format("%02d時%02d分%02d秒", cln.get(Calendar.HOUR_OF_DAY), cln.get(Calendar.MINUTE), cln.get(Calendar.SECOND));
			return str;
		} else {
			return StringUtility.EMPTY;
		}
	}
}
