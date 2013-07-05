package jp.azuki.web.action.urlmapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.azuki.web.action.Action;

/**
 * このインターフェースは、URLとアクションのマッピング機能を表現するインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/14
 * @author Kawakicchi
 */
public interface UrlActionMapper {

	/**
	 * URLマッピングを行う。
	 * 
	 * @param aReq リクエスト情報
	 * @param aRes レスポンス情報
	 * @return アクション。マッピング出来ない場合、<code>null</null>を返す。
	 */
	public Action mapping(final HttpServletRequest aReq, final HttpServletResponse aRes);
}
