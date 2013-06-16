package jp.azuki.job.commandline;

/**
 * このインターフェースは、コマンドライン引数の解析機能を表現したインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/16
 * @author Kawakicchi
 */
public interface CommandLineArgumentPurser {

	/**
	 * コマンドライン引数を解析する。
	 * 
	 * @param aArguments 引数
	 * @return コマンドライン引数情報
	 */
	public CommandLineArgument purse(final String[] aArguments);
}
