package jp.azuki.core.log;

import jp.azuki.persistence.context.Context;

/**
 * このクラスは、ログを生成するファクトリークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/08/12
 * @author Kawakicchi
 */
public abstract class LoggerFactory {

	private static LoggerFactory FACTORY;

	protected abstract void doLoad(final String aName, final Context aContext);

	protected abstract Logger doCreate();

	protected abstract Logger doCreate(final String aName);

	protected abstract Logger doCreate(final Class<?> aClass);

	protected LoggerFactory() {

	}

	public static final void load(final String aFactoryClass, final String aName, final Context aContext) {
		try {
			Class<?> clazz = Class.forName(aFactoryClass);
			Object obj = clazz.newInstance();
			if (obj instanceof LoggerFactory) {
				FACTORY = (LoggerFactory) obj;
				FACTORY.doLoad(aName, aContext);
			}
		} catch (ClassNotFoundException ex) {

		} catch (IllegalAccessException ex) {

		} catch (InstantiationException ex) {

		}
	}

	/**
	 * ログインスタンスを生成します。
	 * 
	 * @return ログインスタンス
	 */
	public static final Logger create() {
		return FACTORY.doCreate();
	}

	/**
	 * ログインスタンスを生成します。
	 * 
	 * @param aName 名前
	 * @return ログインスタンス
	 */
	public static final Logger create(final String aName) {
		return FACTORY.doCreate(aName);
	}

	/**
	 * ログインスタンスを生成します。
	 * 
	 * @param class クラス
	 * @return ログインスタンス
	 */
	public static final Logger create(final Class<?> aClass) {
		return FACTORY.doCreate(aClass);
	}
}
