package jp.azuki.web.action.filter;

import java.util.Map;

import jp.azuki.core.lang.LoggingObject;
import jp.azuki.web.constant.WebServiceException;
import jp.azuki.web.view.View;

/**
 * このクラスは、フィルター機能の実装を行うための基底クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/16
 * @author Kawakicchi
 */
public abstract class AbstractFilter extends LoggingObject implements Filter {

	/**
	 * ビュー
	 */
	private View view;

	/**
	 * コンストラクタ
	 */
	public AbstractFilter() {
		super(Filter.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractFilter(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractFilter(final Class<?> aClass) {
		super(aClass);
	}

	@Override
	public final void initialize() {
		doInitialize();
	}

	@Override
	public final void destroy() {
		doDestroy();
	}

	@Override
	public final void filter(final Map<String, Object> aParameter) throws WebServiceException {
		doFilter(aParameter);
	}

	/**
	 * 初期か処理を行う。
	 */
	protected abstract void doInitialize();

	/**
	 * 解放処理を行う。
	 */
	protected abstract void doDestroy();

	/**
	 * フィルター処理を行う。
	 * 
	 * @param aParameter パラメーター
	 * @throws WebServiceException ウェブサービス層に起因する問題が発生した場合
	 */
	protected abstract void doFilter(final Map<String, Object> aParameter) throws WebServiceException;

	@Override
	public final View getView() {
		return view;
	}

	/**
	 * ビューを設定する。
	 * 
	 * @param aView ビュー
	 */
	protected final void setView(final View aView) {
		view = aView;
	}
}
