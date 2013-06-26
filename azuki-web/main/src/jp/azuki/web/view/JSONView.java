package jp.azuki.web.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.arnx.jsonic.JSON;

/**
 * このクラスは、JSON表示機能を備えたビュークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/06/26
 * @author Kawakicchi
 */
public final class JSONView extends AbstractView {

	/**
	 * Data
	 */
	private Object data;

	/**
	 * Constructor
	 * 
	 * @param aData data
	 */
	public JSONView(final Object aData) {
		data = aData;
	}

	@Override
	protected final void doView(final HttpServletRequest aReq, final HttpServletResponse aRes) throws ServletException, IOException {
		aRes.setContentType("application/json; charset=UTF-8");
		aRes.setCharacterEncoding("UTF-8");

		JSON.encode(data, aRes.getOutputStream());
	}

}
