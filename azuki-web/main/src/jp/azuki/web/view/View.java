package jp.azuki.web.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * このインターフェースは、ビュー機能を表現するインターフェスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/12/31
 * @author Kawakicchi
 */
public interface View {

	/**
	 * ビューを実行します。
	 * 
	 * @param aReq リクエスト情報
	 * @param aRes レスポンス情報
	 * @throws ServletException サーブレット機能に起因する問題が発生した場合
	 * @throws IOException IO操作時に問題が発生した場合
	 */
	void view(final HttpServletRequest aReq, final HttpServletResponse aRes) throws ServletException, IOException;
}
