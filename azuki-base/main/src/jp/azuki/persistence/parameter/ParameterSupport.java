package jp.azuki.persistence.parameter;

/**
 * このインターフェースは、パラメータ機能のサポートを行うためのインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/07/04
 * @author Kawakicchi
 */
public interface ParameterSupport {

	/**
	 * パラメータ情報を設定する。
	 * 
	 * @param aParameter パラメータ情報
	 */
	public void setParameter(final Parameter aParameter);

}
