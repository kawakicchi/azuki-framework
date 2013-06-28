package jp.azuki.web.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import jp.azuki.persistence.context.Context;
import jp.azuki.web.context.WebContext;

/**
 * このクラスは、サーブレット機能の実装を行うための基底クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/19
 * @author Kawakicchi
 */
public abstract class AbstractServlet extends PrimitiveServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4013523062769751635L;

	/**
	 * コンテキスト
	 */
	private Context context;

	/**
	 * コンストラクタ
	 */
	public AbstractServlet() {
		super();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractServlet(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractServlet(final Class<?> aClass) {
		super(aClass);
	}

	@Override
	public final void init(final ServletConfig aConfig) throws ServletException {
		super.init(aConfig);
		context = new WebContext(aConfig.getServletContext());
		doInitialize(aConfig);
	}

	@Override
	public final void destroy() {
		doDestroy();
	}

	/**
	 * 初期化処理を行う。
	 * 
	 * @param aConfig コンフィグ情報
	 * @throws ServletException サーブレット機能に起因する問題が発生した場合
	 */
	protected abstract void doInitialize(final ServletConfig aConfig) throws ServletException;

	/**
	 * 解放処理を行う。
	 */
	protected abstract void doDestroy();

	/**
	 * コンテキストを取得する。
	 * 
	 * @return コンテキスト
	 */
	protected final Context getContext() {
		return context;
	}

}
