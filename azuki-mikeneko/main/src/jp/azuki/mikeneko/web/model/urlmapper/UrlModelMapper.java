package jp.azuki.mikeneko.web.model.urlmapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.azuki.mikeneko.web.model.BusinessModel;

/**
 * このインターフェースは、URLとモデルのマッピング機能を表現するインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/07/09
 * @author Kawakicchi
 */
public interface UrlModelMapper {

	/**
	 * URLマッピングを行う。
	 * 
	 * @param aReq リクエスト情報
	 * @param aRes レスポンス情報
	 * @return モデル。マッピング出来ない場合、<code>null</null>を返す。
	 */
	public BusinessModel mapping(final HttpServletRequest aReq, final HttpServletResponse aRes);
}
