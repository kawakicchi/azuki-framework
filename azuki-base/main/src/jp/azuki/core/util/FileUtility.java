package jp.azuki.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * このクラスは、ファイル操作をまとめたユーティリティクラスです。
 * 
 * @author N.Kawakita
 * 
 */
public final class FileUtility {

	/**
	 * コンストラクタ
	 * <p>
	 * インスタンス生成を禁止する。
	 * </p>
	 */
	private FileUtility() {

	}

	/**
	 * ファイルをコピーする。
	 * <p>
	 * コピー処理には
	 * {@link FileChannel#transferTo(long, long, java.nio.channels.WritableByteChannel)}
	 * メソッドを利用します。
	 * </p>
	 * 
	 * @param aSrcPath コピー元のパス
	 * @param aDestPath コピー先のパス
	 * @throws IOException 何らかの入出力処理例外が発生した場合
	 */
	public static void copy(final String aSrcPath, final String aDestPath) throws IOException {
		copy(new File(aSrcPath), new File(aDestPath));
	}

	/**
	 * ファイルをコピーする。
	 * <p>
	 * コピー処理には
	 * {@link FileChannel#transferTo(long, long, java.nio.channels.WritableByteChannel)}
	 * メソッドを利用します。
	 * </p>
	 * 
	 * @param aSrcFile コピー元のファイル
	 * @param aDestFile コピー先のファイル
	 * @throws IOException 何らかの入出力処理例外が発生した場合
	 */
	public static void copy(final File aSrcFile, final File aDestFile) throws IOException {
		if (aSrcFile.isDirectory()) {
			aDestFile.mkdirs();
			File[] files = aSrcFile.listFiles();
			for (File file : files) {
				String srcFile = aSrcFile.getAbsolutePath() + "\\" + file.getName();
				String destFile = aDestFile.getAbsolutePath() + "\\" + file.getName();
				copy(new File(srcFile), new File(destFile));
			}
		} else if (aSrcFile.isFile()) {
			FileChannel srcChannel = null;
			FileChannel destChannel = null;
			try {
				srcChannel = new FileInputStream(aSrcFile).getChannel();
				destChannel = new FileOutputStream(aDestFile).getChannel();
				srcChannel.transferTo(0, srcChannel.size(), destChannel);
			} finally {
				if (null != srcChannel) {
					srcChannel.close();
				}
				if (null != destChannel) {
					destChannel.close();
				}
			}
		}
	}

	/**
	 * ファイル/ディレクトリを削除する。
	 * <p>
	 * このメソッドは、対象配下を再帰的に削除します。
	 * </p>
	 * 
	 * @param aRoot 削除対象
	 */
	public static final void remove(final String aRoot) {
		remove(new File(aRoot));
	}

	/**
	 * ファイル/ディレクトリを削除する。
	 * <p>
	 * このメソッドは、対象配下を再帰的に削除します。
	 * </p>
	 * 
	 * @param aRoot 削除対象
	 */
	public static final void remove(final File aRoot) {
		if (aRoot == null || !aRoot.exists()) {
			return;
		}
		if (aRoot.isFile()) {
			if (aRoot.exists() && !aRoot.delete()) {
				aRoot.deleteOnExit();
			}
		} else {
			File[] list = aRoot.listFiles();
			for (int i = 0; i < list.length; i++) {
				remove(list[i]);
			}
			if (aRoot.exists() && !aRoot.delete()) {
				aRoot.deleteOnExit();
			}
		}
	}
}
