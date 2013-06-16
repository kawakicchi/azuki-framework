package jp.azuki.web.util;

import javax.servlet.http.HttpServletRequest;

import jp.azuki.core.util.StringUtility;
import jp.azuki.web.constant.WebConstant;

/**
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/21
 * @author Kawakicchi
 */
public class RequestUtility {

	public static String getUrlPath(final HttpServletRequest aReq) {
		String path = aReq.getRequestURI();
		if (StringUtility.isNotEmpty(WebConstant.getWebApp())) {
			path = path.substring(WebConstant.getWebApp().length() + 1);
		} else {
			// TODO ??ß
		}
		return path;
	}
}
