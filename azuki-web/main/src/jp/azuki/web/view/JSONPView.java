package jp.azuki.web.view;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.arnx.jsonic.JSON;

/**
 * このクラスは、JSONP表示機能を備えたビュークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/06/26
 * @author Kawakicchi
 */
public final class JSONPView extends AbstractView {

	/**
	 * callback function
	 */
	private String callback;

	/**
	 * Data
	 */
	private Object data;

	/**
	 * Constructor
	 * 
	 * @param aCallback callback
	 * @param aData data
	 */
	public JSONPView(final String aCallback, final Object aData) {
		callback = aCallback;
		data = aData;
	}

	@Override
	protected final void doView(final HttpServletRequest aReq, final HttpServletResponse aRes) throws ServletException, IOException {
		aRes.setContentType("text/javascript; charset=UTF-8");
		aRes.setCharacterEncoding("UTF-8");

		Writer writer = aRes.getWriter();
		writer.write(callback);
		writer.write("(");
		if (null != data) {
			writer.write(JSON.escapeScript(data));
		} else {
			writer.write("{}");
		}
		writer.write(");");
	}

}
