package jp.azuki.web.context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.servlet.ServletContext;

import jp.azuki.persistence.context.AbstractContext;

/**
 * このクラスは、Web用のコンテキスト機能を実装するクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 12/06/07
 * @author Kawakicchi
 * 
 */
public final class WebContext extends AbstractContext {

	/**
	 * Servlet context
	 */
	private ServletContext context;

	/**
	 * コンストラクタ
	 * 
	 * @param aContext コンテキスト
	 */
	public WebContext(final ServletContext aContext) {
		context = aContext;
	}

	@Override
	public String getAbstractPath(final String aName) {
		return context.getRealPath(getFullPath(aName));
	}

	@Override
	public InputStream getResourceAsStream(final String aName) {
		InputStream stream = null;
		stream = context.getResourceAsStream(getFullPath(aName));
		if (null == stream) {
			try {
				stream = new FileInputStream(aName);
			} catch (FileNotFoundException ex) {
				;
			}
		}
		if (null == stream) {
			stream = this.getClass().getResourceAsStream(aName);
		}
		if (null == stream) {
			stream = Class.class.getResourceAsStream(aName);
		}
		return stream;
	}

	/**
	 * フルパスを取得する。
	 * 
	 * @param aName Name
	 * @return パス
	 */
	private String getFullPath(final String aName) {
		StringBuffer sb = new StringBuffer("/WEB-INF");
		if (!aName.startsWith("/")) {
			sb.append("/");
		}
		sb.append(aName);
		return sb.toString();
	}
}
