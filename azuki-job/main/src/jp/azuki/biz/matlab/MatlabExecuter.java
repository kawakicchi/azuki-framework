package jp.azuki.biz.matlab;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.azuki.core.util.PathUtility;
import jp.azuki.core.util.StringUtility;

/**
 * このクラスは、MATLABのスクリプトを実行するクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/06/26
 * @author Kawakicchi
 */
public class MatlabExecuter {

	public static enum Result {
		/**
		 * 実行結果 - 成功
		 */
		success,
		/**
		 * 実行結果 - キャンセル
		 */
		cancel,
		/**
		 * 実行結果 - 失敗
		 */
		error;
	}

	/**
	 * ログファイル
	 */
	private String logFile;

	/**
	 * 実行スクリプトファイル
	 */
	private String runScriptFileName = "run.m";

	/**
	 * 成功判定ファイル
	 */
	private String successFileName = "success";

	/**
	 * 失敗判定ファイル
	 */
	private String errorFileName = "error";

	/**
	 * コンストラクタ
	 */
	public MatlabExecuter() {

	}

	/**
	 * ログファイルパスを設定する。
	 * 
	 * @param aPath ログファイルパス
	 */
	public void setLogFile(final String aPath) {
		logFile = aPath;
	}

	/**
	 * 実行スクリプトファイル名を設定する。
	 * 
	 * @param aName ファイル名
	 */
	public void setRunScriptFileName(final String aName) {
		runScriptFileName = aName;
	}

	/**
	 * 成功判定ファイル名を設定する。
	 * 
	 * @param aName ファイル名
	 */
	public void setSuccessFileName(final String aName) {
		successFileName = aName;
	}

	/**
	 * 失敗判定ファイル名を設定する。
	 * 
	 * @param aName ファイル名
	 */
	public void setErrorFileName(final String aName) {
		errorFileName = aName;
	}

	/**
	 * MATLABスクリプトファイルを実行する。
	 * 
	 * @param aTargetDir 実行ディレクトリ
	 * @param aMfileName MATLABスクリプトファイル名
	 * @return 実行結果
	 * @throws InterruptedException {@link InterruptedException}
	 * @throws IOException {@link IOException}
	 */
	public Result execute(final String aTargetDir, final String aMfileName) throws InterruptedException, IOException {
		Result result = Result.cancel;

		String runFilePath = createRunScriptFile(aTargetDir, aMfileName);

		List<String> list = new ArrayList<String>();
		list.add("matlab");
		list.add("-nosplash");
		list.add("-minimize");
		list.add("-wait");
		list.add("-r");
		list.add("run('" + runFilePath + "');");
		if (StringUtility.isNotEmpty(logFile)) {
			list.add("-logfile");
			list.add(logFile);
		}

		ProcessBuilder pb = new ProcessBuilder(list);
		Process p = pb.start();
		p.waitFor();

		File successFile = new File(PathUtility.cat(aTargetDir, successFileName));
		if (successFile.isFile()) {
			result = Result.success;
		} else {
			File errorFile = new File(PathUtility.cat(aTargetDir, errorFileName));
			if (errorFile.isFile()) {
				result = Result.error;
			}
		}
		return result;
	}

	/**
	 * スクリプトファイル実行用スクリプトファイルを生成する。
	 * <p>
	 * </p>
	 * 
	 * @param aTargetDir 対象ディレクトリ
	 * @param aScriptName 実行スクリプトファイル
	 * @return 実行用スクリプトファイルパス。
	 * @throws IOException IO操作時に問題が発生した場合。
	 */
	private String createRunScriptFile(final String aTargetDir, final String aScriptName) throws IOException {
		String runFilePath = PathUtility.cat(aTargetDir, runScriptFileName);
		String scriptName = aScriptName;
		int index = aScriptName.lastIndexOf(".");
		if (-1 != index) {
			scriptName = aScriptName.substring(0, index);
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(runFilePath));
		writer.write("try\r\n");
		writer.write("cd('" + aTargetDir + "');\r\n");
		writer.write(scriptName + "\r\n");
		writer.write("successFileId = fopen('" + successFileName + "','W');\r\n");
		writer.write("fclose(successFileId);\r\n");
		writer.write("catch ME\r\n");
		writer.write("error=ME.message\r\n");
		writer.write("errorFileId = fopen('" + errorFileName + "','W');\r\n");
		writer.write("fclose(errorFileId);\r\n");
		writer.write("end\r\n");
		writer.write("exit;");
		writer.close();
		return runFilePath;
	}
}
