package jp.azuki.web.pattern;public abstract class AbstractBusinessPattern extends AbstractPattern {	/**	 * コンストラクタ	 */	public AbstractBusinessPattern() {		super();	}	/**	 * コンストラクタ	 * 	 * @param aName 名前	 */	public AbstractBusinessPattern(final String aName) {		super(aName);	}	/**	 * コンストラクタ	 * 	 * @param aClass クラス	 */	public AbstractBusinessPattern(final Class<?> aClass) {		super(aClass);	}}