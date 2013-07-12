package jp.azuki.web.tags.logic;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import jp.azuki.core.util.DateUtility;

/**
 * このクラスは、値が今日の場合に実装するタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/23
 * @author Kawakicchi
 */
public final class IfTodayTag extends AbstractValueConditionTag {

	/**
	 * コンストラクタ
	 */
	public IfTodayTag() {
		super(IfTodayTag.class);
	}

	@Override
	protected boolean isCondition(final Object aValue) {
		Calendar now = DateUtility.toCalender(new Date());
		Calendar c = null;
		if (aValue instanceof Date) {
			Date date = (Date) aValue;
			c = DateUtility.toCalender(date);
		} else if (aValue instanceof java.sql.Date) {
			java.sql.Date date = (java.sql.Date) aValue;
			c = DateUtility.toCalender(date);
		} else if (aValue instanceof Timestamp) {
			Timestamp timestamp = (Timestamp) aValue;
			c = DateUtility.toCalender(timestamp);
		}
		if (null != c) {
			if (now.get(Calendar.YEAR) == c.get(Calendar.YEAR) && now.get(Calendar.MONTH) == c.get(Calendar.MONTH)
					&& now.get(Calendar.DAY_OF_MONTH) == c.get(Calendar.DAY_OF_MONTH)) {
				return true;
			}
		}
		return false;
	}

}
