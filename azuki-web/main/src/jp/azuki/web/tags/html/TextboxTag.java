package jp.azuki.web.tags.html;

/**
 * このクラスは、URLをレンダリングするタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public class TextboxTag extends AbstractInputTag {

	/**
	 * コンストラクタ
	 */
	public TextboxTag() {
		super(TextboxTag.class);
	}

	@Override
	protected final String getType() {
		return "text";
	}

	@Override
	protected void doAppendAttributes() {
		super.doAppendAttributes();
	}

}
