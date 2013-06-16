package jp.azuki.persistence.proterty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * このアノテーションは、プロパティファイル情報を付与する場合に定義するアノテーションです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/16
 * @author Kawakicchi
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertyFile {

	/**
	 * プロパティファイルを取得する。
	 * 
	 * @return プロパティファイル
	 */
	public String value();
}
