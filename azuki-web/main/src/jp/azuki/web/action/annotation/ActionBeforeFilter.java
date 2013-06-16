package jp.azuki.web.action.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jp.azuki.web.action.filter.Filter;

/**
 * このアノテーションは、アクションにフィルター処理を設定するアノテーションです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/16
 * @author Kawakicchi
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionBeforeFilter {

	/**
	 * アクション実行前の実行されるフィルタークラス群
	 * 
	 * @return フィルタークラス群
	 */
	Class<? extends Filter>[] value();

}
