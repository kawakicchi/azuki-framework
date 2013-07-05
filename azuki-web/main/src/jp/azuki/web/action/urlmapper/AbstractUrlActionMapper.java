package jp.azuki.web.action.urlmapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.azuki.core.lang.LoggingObject;
import jp.azuki.web.action.Action;

/**
 * このクラスは、URLとアクションのマッピング機能の実装を行うための基底クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/17
 * @author Kawakicchi
 */
public abstract class AbstractUrlActionMapper extends LoggingObject implements UrlActionMapper {

	/**
	 * コンストラクタ
	 */
	public AbstractUrlActionMapper() {
		super(UrlActionMapper.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractUrlActionMapper(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractUrlActionMapper(final Class<?> aClass) {
		super(aClass);
	}

	@Override
	public final Action mapping(final HttpServletRequest aReq, final HttpServletResponse aRes) {
		return doMapping(aReq, aRes);
	}

	/**
	 * URLマッピングを行う。
	 * 
	 * @param aReq リクエスト情報
	 * @param aRes レスポンス情報
	 * @return アクション。マッピング出来ない場合、<code>null</null>を返す。
	 */
	protected abstract Action doMapping(final HttpServletRequest aReq, final HttpServletResponse aRes);

}
