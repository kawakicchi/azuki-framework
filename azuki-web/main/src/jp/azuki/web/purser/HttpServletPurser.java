package jp.azuki.web.purser;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * このインターフェースは、HTTPサーブレットの解析機能を表現したインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/29
 * @author Kawakicchi
 */
public interface HttpServletPurser {

	/**
	 * HTTPサーブレットを解析する。
	 * 
	 * @param aReq リクエスト情報
	 * @param aRes レスポンス情報
	 * @return パラメーター
	 */
	public Map<String, Object> purse(final HttpServletRequest aReq, final HttpServletResponse aRes);
}
