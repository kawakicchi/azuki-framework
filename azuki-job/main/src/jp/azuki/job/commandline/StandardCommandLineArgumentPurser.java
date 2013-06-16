package jp.azuki.job.commandline;

/**
 * このクラスは、標準のコマンドライン引数解析クラスです。
 * <p>
 * Job [-option...] [-optionValue...] [argument...]
 * <p>
 * TODO JOB [-option...] [argument...]の場合、最後のoptionがoptionValueとして認識してしまう。
 * </p>
 * </p>
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/16
 * @author Kawakicchi
 */
public final class StandardCommandLineArgumentPurser implements CommandLineArgumentPurser {

	/**
	 * コンストラクタ
	 */
	public StandardCommandLineArgumentPurser() {

	}

	@Override
	public CommandLineArgument purse(final String[] aArguments) {
		CommandLineArgument arg = new CommandLineArgument();
		for (int i = 0; i < aArguments.length; i++) {
			String value = aArguments[i];
			if (value.startsWith("-")) {
				String key = value.substring(1);
				if (aArguments.length > i + 1) {
					String nextValue = aArguments[i + 1];
					if (nextValue.startsWith("-")) {
						arg.setOption(key);
					} else {
						arg.setOptionValue(key, nextValue);
					}
				} else {
					arg.setOption(key);
				}
			} else {
				arg.addArgument(value);
			}
		}
		return arg;
	}

}
