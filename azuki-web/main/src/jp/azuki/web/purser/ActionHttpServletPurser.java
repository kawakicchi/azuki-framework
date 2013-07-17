package jp.azuki.web.purser;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * このアノテーションは、アクションに引き渡すパラメータを解析するパーサーを設定するアノテーションです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/29
 * @author Kawakicchi
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionHttpServletPurser {

	/**
	 * <p>
	 * デフォルトのパーサーは、{@link DefaultHttpServletPurser}です。
	 * </p>
	 * 
	 * @return パーサー
	 */
	Class<? extends HttpServletPurser>[] value() default DefaultHttpServletPurser.class;
}
