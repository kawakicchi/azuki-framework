package jp.azuki.web.purser;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.azuki.core.lang.LoggingObject;

/**
 * このクラスは、HTTPサーブレットの解析を行うための基底クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/29
 * @author Kawakicchi
 */
public abstract class AbstractHttpServletPurser extends LoggingObject implements HttpServletPurser {

	/**
	 * コンストラクタ
	 */
	public AbstractHttpServletPurser() {
		super(HttpServletPurser.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractHttpServletPurser(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractHttpServletPurser(final Class<?> aClass) {
		super(aClass);
	}

	@Override
	public Map<String, Object> purse(final HttpServletRequest aReq, final HttpServletResponse aRes) {
		return doPurse(aReq, aRes);
	}

	/**
	 * HTTPサーブレットを解析する。
	 * 
	 * @param aReq リクエスト情報
	 * @param aRes レスポンス情報
	 * @return パラメーター
	 */
	protected abstract Map<String, Object> doPurse(final HttpServletRequest aReq, final HttpServletResponse aRes);

}
