package jp.azuki.core.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import jp.azuki.core.util.StringUtility;

/**
 * このクラスは、CSVファイルを読み込むリーダークラスです。
 * 
 * @author N.Kawakita
 * 
 */
public class CsvBufferedReader extends BufferedReader {

	/**
	 * コンストラクタ
	 * 
	 * @param aReader リーダー
	 */
	public CsvBufferedReader(final Reader aReader) {
		super(aReader);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aFile ファイル
	 * @param aCharset 文字エンコーディング
	 * @throws FileNotFoundException {@link FileNotFoundException}
	 * @throws UnsupportedEncodingException {@link UnsupportedEncodingException}
	 */
	public CsvBufferedReader(final String aFile, final String aCharset) throws FileNotFoundException, UnsupportedEncodingException {
		super(new InputStreamReader(new FileInputStream(aFile), aCharset));
	}

	/**
	 * CSVとして１行読み取る。
	 * 
	 * @return CSV１行データ
	 * @throws IOException IO操作時に問題が発生した場合
	 */
	public List<String> readCsvLine() throws IOException {
		List<String> result = null;
		String line = readLine();
		if (null != line) {
			result = purseLine(line);
		}
		return result;
	}

	/**
	 * CSV行を解析しデータ単位にする。
	 * 
	 * @param aLine CSV行文字列
	 * @return データ
	 */
	private List<String> purseLine(final String aLine) {
		List<String> result = new ArrayList<String>();
		if (StringUtility.isNotEmpty(aLine)) {
			StringBuilder sb = new StringBuilder();
			boolean dblFlg = false;
			for (int i = 0; i < aLine.length(); i++) {
				char c = aLine.charAt(i);
				if (dblFlg) {
					if ('"' == c) {
						if (aLine.length() > i + 1) {
							if ('"' == aLine.charAt(i + 1)) {
								sb.append('"');
								i++;
							} else {
								dblFlg = false;
							}
						} else {
							// end
						}
					} else {
						sb.append(c);
					}
				} else {
					if ('"' == c) {
						dblFlg = true;
					} else if (',' == c) {
						result.add(sb.toString());
						sb = new StringBuilder();
					} else {
						sb.append(c);
					}
				}
			}
			result.add(sb.toString());
		}
		return result;
	}
}
