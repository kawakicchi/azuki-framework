package jp.azuki.core.log;

import jp.azuki.persistence.context.Context;

import org.apache.log4j.xml.DOMConfigurator;

/**
 * このクラスは、ログを生成するファクトリークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/08/12
 * @author Kawakicchi
 */
public class LoggerFactory {

	public static final void load(final String aName, final Context aContext) {
		DOMConfigurator.configure(aContext.getAbstractPath(aName));
	}

	/**
	 * ログインスタンスを生成します。
	 * 
	 * @return Logger
	 */
	public static final Logger create() {
		return new Log4jLogger(LoggerFactory.class);
	}

	/**
	 * ログインスタンスを生成します。
	 * 
	 * @param name 名前
	 * @return Logger
	 */
	public static final Logger create(final String name) {
		return new Log4jLogger(name);
	}

	/**
	 * ログインスタンスを生成します。
	 * 
	 * @param clazz クラス
	 * @return Logger
	 */
	public static final Logger create(final Class<?> clazz) {
		return new Log4jLogger(clazz);
	}
}
