package jp.azuki.web.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * このクラスは、フォワード機能を備えたビュークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/21
 * @author Kawakicchi
 */
public class ForwardView extends AbstractView {

	/**
	 * url
	 */
	private String url;

	/**
	 * コンストラクタ
	 * 
	 * @param aUrl URL
	 */
	public ForwardView(final String aUrl) {
		url = aUrl;
	}

	@Override
	protected final void doView(final HttpServletRequest aReq, final HttpServletResponse aRes) throws ServletException, IOException {
		aReq.getRequestDispatcher(url).forward(aReq, aRes);
	}
}
