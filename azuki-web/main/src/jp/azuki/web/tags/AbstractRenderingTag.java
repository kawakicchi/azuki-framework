package jp.azuki.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;

/**
 * このクラスは、レンダリング機能を実装したタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public abstract class AbstractRenderingTag extends AbstractTag {

	/**
	 * コンストラクタ
	 */
	public AbstractRenderingTag() {
		super();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractRenderingTag(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractRenderingTag(final Class<?> aClass) {
		super(aClass);
	}

	@Override
	public int doStartTag() throws JspException {
		return (Tag.SKIP_BODY);
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter writer = getPageContext().getOut();
			StringBuffer s = new StringBuffer();
			doRendering(s);
			writer.print(s.toString());
		} catch (IOException ex) {
			throw new JspException(ex);
		}
		return (Tag.EVAL_PAGE);
	}

	/**
	 * レンダリングを行います。
	 * 
	 * @param aRender レンダリング文字列
	 * @throws JspException JSP操作に起因する問題が発生した場合
	 */
	protected abstract void doRendering(final StringBuffer aRender) throws JspException;

	protected static final String toEscapeString(final String aString) {
		String s = aString;
		return s;
	}
}
