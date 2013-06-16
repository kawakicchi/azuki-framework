package jp.azuki.web.purser;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * このクラスは、HTTPサーブレットパラメーターをそのままパラメーター化するパーサークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/29
 * @author Kawakicchi
 */
public final class DefaultHttpServletPurser extends AbstractHttpServletPurser {

	private String charset = "UTF-8";

	/**
	 * コンストラクタ
	 */
	public DefaultHttpServletPurser() {
		super(DefaultHttpServletPurser.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected Map<String, Object> doPurse(final HttpServletRequest aReq, final HttpServletResponse aRes) {
		try {
			aReq.setCharacterEncoding(charset);
		} catch (UnsupportedEncodingException ex) {
			error(ex);
		}

		Map<String, Object> m = new HashMap<String, Object>();
		for (String key : (Set<String>) aReq.getParameterMap().keySet()) {
			String[] values = aReq.getParameterValues(key);
			if (1 == values.length) {
				m.put(key, values[0]);
			} else {
				m.put(key, values);
			}
		}
		return m;
	}

}
