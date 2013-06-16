package jp.azuki.job.context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import jp.azuki.core.util.StringUtility;
import jp.azuki.persistence.context.AbstractContext;

/**
 * このクラスは、Job用のコンテキスト機能を実装するクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 12/06/07
 * @author Kawakicchi
 * 
 */
public final class JobContext extends AbstractContext {

	/**
	 * ベースディレクトリ
	 */
	private String baseDir;

	/**
	 * コンストラクタ
	 */
	public JobContext() {
		super(JobContext.class);
		baseDir = ".";
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aBaseDir ベースディレクトリ
	 */
	public JobContext(final String aBaseDir) {
		super(JobContext.class);
		baseDir = aBaseDir;
	}

	@Override
	public String getAbstractPath(final String aName) {
		return getFullPath(aName);
	}

	@Override
	@SuppressWarnings("resource")
	public InputStream getResourceAsStream(final String aName) {
		InputStream stream = null;
		try {
			stream = new FileInputStream(getFullPath(aName));
		} catch (FileNotFoundException ex) {
			;
		}
		if (null == stream) {
			stream = this.getClass().getResourceAsStream(aName);
		}
		if (null == stream) {
			stream = Class.class.getResourceAsStream(aName);
		}
		return stream;
	}

	/**
	 * フルパスを取得する。
	 * 
	 * @param aName 名前
	 * @return パス
	 */
	private String getFullPath(final String aName) {
		StringBuilder path = new StringBuilder();
		if (StringUtility.isNotEmpty(baseDir)) {
			path.append(baseDir);
		}
		if (!aName.startsWith("/")) {
			path.append("/");
		}
		path.append(aName);
		return path.toString();
	}
}
