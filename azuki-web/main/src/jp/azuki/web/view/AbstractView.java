package jp.azuki.web.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.azuki.core.lang.LoggingObject;

/**
 * このクラスは、ビュー機能の実装を行うための基底クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/24
 * @author Kawakicchi
 */
public abstract class AbstractView extends LoggingObject implements View {

	/**
	 * コンストラクタ
	 */
	public AbstractView() {
		super(View.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractView(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractView(final Class<?> aClass) {
		super(aClass);
	}

	@Override
	public final void view(final HttpServletRequest aReq, final HttpServletResponse aRes) throws ServletException, IOException {
		doView(aReq, aRes);
	}

	/**
	 * ビューを実行します。
	 * 
	 * @param aReq リクエスト情報
	 * @param aRes レスポンス情報
	 * @throws ServletException サーブレット機能に起因する問題が発生した場合
	 * @throws IOException IO操作時に問題が発生した場合
	 */
	protected abstract void doView(final HttpServletRequest aReq, final HttpServletResponse aRes) throws ServletException, IOException;
}
