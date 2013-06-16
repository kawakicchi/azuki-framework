package jp.azuki.web.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.azuki.web.constant.WebConstant;

/**
 * このクラスは、リダイレクト機能を備えたビュークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/01
 * @author Kawakicchi
 */
public class RedirectView extends AbstractView {

	/**
	 * url
	 */
	private String url;

	/**
	 * direct
	 */
	private boolean direct;

	/**
	 * コンストラクタ
	 * 
	 * @param aUrl URL
	 */
	public RedirectView(final String aUrl) {
		url = aUrl;
		direct = false;
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aUrl URL
	 * @param aDirect Direct
	 */
	public RedirectView(final String aUrl, final boolean aDirect) {
		url = aUrl;
		direct = aDirect;
	}

	@Override
	protected final void doView(final HttpServletRequest aReq, final HttpServletResponse aRes) throws ServletException, IOException {
		String s = null;
		if (direct) {
			s = url;
		} else {
			s = WebConstant.getUrl(url);
		}
		aRes.sendRedirect(s);
	}
}
