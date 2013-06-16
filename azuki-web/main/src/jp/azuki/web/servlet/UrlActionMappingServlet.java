package jp.azuki.web.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.azuki.web.action.Action;
import jp.azuki.web.action.urlmapper.UrlActionMapper;

/**
 * このクラスは、URL情報よりアクションのマッピングコントロールを行うサーブレットクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/01
 * @author Kawakicchi
 */
public final class UrlActionMappingServlet extends AbstractActionMappingServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2110980628087062116L;

	/**
	 * Url action mapper class
	 */
	private UrlActionMapper mapper;

	/**
	 * コンストラクタ
	 */
	public UrlActionMappingServlet() {
		super(UrlActionMappingServlet.class);
	}

	@Override
	protected void doInitialize(final ServletConfig aConfig) throws ServletException {
		try {
			String mapperClass = aConfig.getInitParameter("UrlActionMapper");
			Class<?> clazz = Class.forName(mapperClass);
			Object obj = clazz.newInstance();
			if (obj instanceof UrlActionMapper) {
				mapper = (UrlActionMapper) obj;
			} else {
				error("This class is unsupported UrlActionMappter.[" + mapperClass + "]");
			}
		} catch (ClassNotFoundException ex) {
			throw new ServletException(ex);
		} catch (IllegalAccessException ex) {
			throw new ServletException(ex);
		} catch (InstantiationException ex) {
			throw new ServletException(ex);
		}
	}

	@Override
	protected void doDestroy() {

	}

	@Override
	public void doGet(final HttpServletRequest aReq, final HttpServletResponse aRes) throws ServletException, IOException {
		doTask(aReq, aRes);
	}

	@Override
	public void doPost(final HttpServletRequest aReq, final HttpServletResponse aRes) throws ServletException, IOException {
		doTask(aReq, aRes);
	}

	@Override
	protected final Action createAction(final HttpServletRequest aReq, final HttpServletResponse aRes) {
		return mapper.mapping(aReq, aRes);
	}

}
