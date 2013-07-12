package jp.azuki.web.tags.html;

/**
 * このクラスは、URLをレンダリングするタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public class PasswordTag extends AbstractInputTag {

	/**
	 * コンストラクタ
	 */
	public PasswordTag() {
		super(PasswordTag.class);
	}

	@Override
	protected final String getType() {
		return "password";
	}

	@Override
	protected void doAppendAttributes() {
		super.doAppendAttributes();
	}
}
