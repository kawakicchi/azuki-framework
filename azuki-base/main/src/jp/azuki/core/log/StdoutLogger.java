package jp.azuki.core.log;

import java.util.Calendar;
import java.util.Date;

/**
 * このクラスは、標準出力を行うロガークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/04
 * @author Kawakicchi
 */
public final class StdoutLogger extends AbstractLogger {

	/**
	 * Debug
	 */
	private static String LEVEL_DEBUG = "DEBUG";

	/**
	 * Information
	 */
	private static String LEVEL_INFO = "INFO";

	/**
	 * Warning
	 */
	private static String LEVEL_WARN = "WARN";

	/**
	 * Error
	 */
	private static String LEVEL_ERROR = "ERROR";

	/**
	 * Fatal
	 */
	private static String LEVEL_FATAL = "FATAL";

	/**
	 * 名前
	 */
	private String name;

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public StdoutLogger(final String aName) {
		name = aName;
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public StdoutLogger(final Class<?> aClass) {
		name = aClass.getName();
	}

	@Override
	public void debug(final String message) {
		log(LEVEL_DEBUG, message);
	}

	@Override
	public void debug(final Throwable throwable) {
		log(LEVEL_DEBUG, throwable.toString());
	}

	@Override
	public void debug(final String message, final Throwable throwable) {
		log(LEVEL_DEBUG, message);
		log(LEVEL_DEBUG, throwable.toString());
	}

	@Override
	public void info(final String message) {
		log(LEVEL_INFO, message);
	}

	@Override
	public void info(final Throwable throwable) {
		log(LEVEL_INFO, throwable.toString());
	}

	@Override
	public void info(final String message, final Throwable throwable) {
		log(LEVEL_INFO, message);
		log(LEVEL_INFO, throwable.toString());
	}

	@Override
	public void warn(final String message) {
		log(LEVEL_WARN, message);
	}

	@Override
	public void warn(final Throwable throwable) {
		log(LEVEL_WARN, throwable.toString());
	}

	@Override
	public void warn(final String message, final Throwable throwable) {
		log(LEVEL_WARN, message);
		log(LEVEL_WARN, throwable.toString());
	}

	@Override
	public void error(final String message) {
		log(LEVEL_ERROR, message);
	}

	@Override
	public void error(final Throwable throwable) {
		log(LEVEL_ERROR, throwable.toString());
	}

	@Override
	public void error(final String message, final Throwable throwable) {
		log(LEVEL_ERROR, message);
		log(LEVEL_ERROR, throwable.toString());
	}

	@Override
	public void fatal(final String message) {
		log(LEVEL_FATAL, message);
	}

	@Override
	public void fatal(final Throwable throwable) {
		log(LEVEL_FATAL, throwable.toString());
	}

	@Override
	public void fatal(final String message, final Throwable throwable) {
		log(LEVEL_FATAL, message);
		log(LEVEL_FATAL, throwable.toString());
	}

	/**
	 * ログを出力する。
	 * 
	 * @param level レベル
	 * @param message メッセージ
	 */
	private void log(final String level, final String message) {
		Calendar cln = Calendar.getInstance();
		cln.setTime(new Date());
		String msg = String.format("%02d:%02d:%02d.%03d %5s %s %s", cln.get(Calendar.HOUR_OF_DAY), cln.get(Calendar.MINUTE),
				cln.get(Calendar.SECOND), cln.get(Calendar.MILLISECOND), level, name, message);
		System.out.println(msg);
	}
}
