package jp.azuki.web.action.context;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * このクラスは、アクション用のコンテキストクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/24
 * @author Kawakicchi
 */
public final class ActionContext {

	private Map<String, Object> attributes;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public ActionContext(final HttpServletRequest aReq, final HttpServletResponse aRes) {
		request = aReq;
		response = aRes;
		attributes = new HashMap<String, Object>();
	}

	public void setAttribute(final String aKey, final Object aValue) {
		attributes.put(aKey, aValue);
	}

	public Object getAttribute(final String aKey) {
		return attributes.get(aKey);
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}
}
