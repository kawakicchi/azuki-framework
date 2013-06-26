package jp.azuki.web.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * このクラスは、JSP表示機能を備えたビュークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/06/26
 * @author Kawakicchi
 */
public final class JSPView extends AbstractView {

	/**
	 * JSP path
	 */
	private String path;

	/**
	 * Attributes
	 */
	private Map<String, Object> attributes;

	/**
	 * Constructor
	 * 
	 * @param aJspPath JSP path
	 */
	public JSPView(final String aJspPath) {
		path = aJspPath;
		attributes = null;
	}

	/**
	 * Constructor
	 * 
	 * @param aJspPath JSP path
	 * @param aAttributes Attributes
	 */
	public JSPView(final String aJspPath, final Map<String, Object> aAttributes) {
		path = aJspPath;
		attributes = new HashMap<String, Object>(aAttributes);
	}

	@Override
	protected final void doView(final HttpServletRequest aReq, final HttpServletResponse aRes) throws ServletException, IOException {
		if (null != attributes) {
			for (String key : attributes.keySet()) {
				aReq.setAttribute(key, attributes.get(key));
			}
		}

		StringBuffer s = new StringBuffer("/WEB-INF/jsp");
		if (!path.startsWith("/")) {
			s.append("/");
		}
		s.append(path);

		RequestDispatcher rd = aReq.getRequestDispatcher(s.toString());
		rd.forward(aReq, aRes);
	}

}
