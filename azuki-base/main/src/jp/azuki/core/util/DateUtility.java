package jp.azuki.core.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * このクラスは、日付操作を行うユーティリティクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/04
 * @author Kawakicchi
 */
public final class DateUtility {

	/**
	 * コンストラクタ
	 * <p>
	 * インスタンス生成を禁止する。
	 * </p>
	 */
	private DateUtility() {

	}

	/**
	 * 日付を作成する。
	 * 
	 * @param aYear 年
	 * @param aMonth 月
	 * @param aDate 日
	 * @param aHour 時
	 * @param aMinute 分
	 * @param aSecond 秒
	 * @return 日付
	 */
	public static Date createDate(final int aYear, final int aMonth, final int aDate, final int aHour, final int aMinute, final int aSecond) {
		Calendar c = Calendar.getInstance();
		c.set(aYear, aMonth, aDate, aHour, aMinute, aSecond);
		return c.getTime();
	}

	/**
	 * 日付を作成する。
	 * 
	 * @param aYear 年
	 * @param aMonth 月
	 * @param aDate 日
	 * @param aHour 時
	 * @param aMinute 分
	 * @param aSecond 秒
	 * @return 日付
	 */
	public static Timestamp createTimestamp(final int aYear, final int aMonth, final int aDate, final int aHour, final int aMinute, final int aSecond) {
		Calendar c = Calendar.getInstance();
		c.set(aYear, aMonth, aDate, aHour, aMinute, aSecond);
		return new Timestamp(c.getTimeInMillis());
	}

	/**
	 * カレンダー型として取得する。
	 * 
	 * @param aDate 日付
	 * @return カレンダー型
	 */
	public static Calendar toCalender(final Date aDate) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(aDate.getTime());
		return c;
	}

	/**
	 * カレンダー型として取得する。
	 * 
	 * @param aDate 日付
	 * @return カレンダー型
	 */
	public static Calendar toCalender(final java.sql.Date aDate) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(aDate.getTime());
		return c;
	}

	/**
	 * カレンダー型として取得する。
	 * 
	 * @param aTimestamp 日付
	 * @return カレンダー型
	 */
	public static Calendar toCalender(final Timestamp aTimestamp) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(aTimestamp.getTime());
		return c;
	}
}
