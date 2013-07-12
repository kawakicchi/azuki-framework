package jp.azuki.mikeneko.web.model.urlmapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.azuki.core.lang.LoggingObject;
import jp.azuki.mikeneko.web.model.BusinessModel;

/**
 * このクラスは、URLとモデルのマッピング機能の実装を行うための基底クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/07/09
 * @author Kawakicchi
 */
public abstract class AbstractUrlModelMapper extends LoggingObject implements UrlModelMapper {

	/**
	 * コンストラクタ
	 */
	public AbstractUrlModelMapper() {
		super(UrlModelMapper.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractUrlModelMapper(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractUrlModelMapper(final Class<?> aClass) {
		super(aClass);
	}

	@Override
	public final BusinessModel mapping(final HttpServletRequest aReq, final HttpServletResponse aRes) {
		return doMapping(aReq, aRes);
	}

	/**
	 * URLマッピングを行う。
	 * 
	 * @param aReq リクエスト情報
	 * @param aRes レスポンス情報
	 * @return モデル。マッピング出来ない場合、<code>null</null>を返す。
	 */
	protected abstract BusinessModel doMapping(final HttpServletRequest aReq, final HttpServletResponse aRes);

}
