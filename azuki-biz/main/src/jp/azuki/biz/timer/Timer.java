package jp.azuki.biz.timer;

/**
 * このクラスは、タイマークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/07/16
 * @author Kawakicchi
 */
public final class Timer {

	/**
	 * 開始時間
	 */
	private long start;

	/**
	 * 終了時間
	 */
	private long stop;

	/**
	 * コンストラクタ
	 */
	public Timer() {
		start = 0;
		stop = 0;
	}

	/**
	 * 計測を開始します。
	 */
	public void start() {
		start = System.currentTimeMillis();
		stop = start;
	}

	/**
	 * 計測を終了します。
	 */
	public void stop() {
		stop = System.currentTimeMillis();
	}

	/**
	 * 計測時間を取得します。
	 * 
	 * @return 時間(ms)
	 */
	public long getTime() {
		return stop - start;
	}
}
