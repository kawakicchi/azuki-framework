package jp.azuki.core.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * このクラスは、マップ生成を行うビルダークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/07/16
 * @author Kawakicchi
 */
public class MapBuilder {

	private Map<String, Object> srcMap;

	private Map<String, Object> dstMap;

	/**
	 * コンストラクタ
	 */
	public MapBuilder() {
		srcMap = null;
		dstMap = new HashMap<>();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aSrcMap ソースマップ
	 */
	public MapBuilder(final Map<String, Object> aSrcMap) {
		srcMap = aSrcMap;
		dstMap = new HashMap<>();
	}

	/**
	 * 値を設定する。
	 * 
	 * @param aKey キー
	 * @param aValue 値
	 */
	public void put(final String aKey, final Object aValue) {
		dstMap.put(aKey, aValue);
	}

	/**
	 * ソースマップのキーの値を設定する。
	 * 
	 * @param aKey キー
	 * @return 結果
	 */
	public boolean copyToObj(final String aKey) {
		Object value = srcMap.get(aKey);
		dstMap.put(aKey, value);
		return true;
	}

	/**
	 * ソースマップのキーの値を設定する。
	 * <p>
	 * ソースマップの値が<code>null</code>の場合、設定しない。
	 * </p>
	 * 
	 * @param aKey キー
	 * @return 結果
	 */
	public boolean copyToObjNotNull(final String aKey) {
		Object value = srcMap.get(aKey);
		if (null != value) {
			dstMap.put(aKey, value);
		}
		return true;
	}

	/**
	 * ソースマップのキーの値を設定する。
	 * 
	 * @param aKey キー
	 * @return 結果
	 */
	public boolean copyToStr(final String aKey) {
		Object value = srcMap.get(aKey);
		if (null == value) {
			dstMap.put(aKey, value);
		} else if (value instanceof String) {
			dstMap.put(aKey, value);
		} else {
			dstMap.put(aKey, value.toString());
		}
		return true;
	}

	/**
	 * ソースマップのキーの値を設定する。
	 * <p>
	 * ソースマップの値が<code>null</code>の場合、設定しない。
	 * </p>
	 * 
	 * @param aKey キー
	 * @return 結果
	 */
	public boolean copyToStrNotNull(final String aKey) {
		Object value = srcMap.get(aKey);
		if (null == value) {
			// no setting
		} else if (value instanceof String) {
			dstMap.put(aKey, value);
		} else {
			dstMap.put(aKey, value.toString());
		}
		return true;
	}

	/**
	 * ソースマップのキーの値を設定する。
	 * <p>
	 * ソースマップの値が<code>null</code>またはブランクの場合、設定しない。
	 * </p>
	 * 
	 * @param aKey キー
	 * @return 結果
	 */
	public boolean copyToStrNotEmpty(final String aKey) {
		Object value = srcMap.get(aKey);
		if (null == value) {
			// no setting
		} else if (value instanceof String) {
			String str = (String) value;
			if (StringUtility.isNotEmpty(str)) {
				dstMap.put(aKey, str);
			}
		} else {
			String str = value.toString();
			if (StringUtility.isNotEmpty(str)) {
				dstMap.put(aKey, str);
			}
		}
		return true;
	}

	/**
	 * ソースマップのキーの値を設定する。
	 * <p>
	 * ソースマップの値が<code>null</code>またはブランクの場合、設定しない。
	 * </p>
	 * 
	 * @param aKey キー
	 * @return 結果
	 */
	public boolean copyToDblNotEmpty(final String aKey) {
		Object obj = srcMap.get(aKey);
		Double value = null;
		if (null == obj) {
			// no setting
		} else if (obj instanceof Double) {
			value = (Double) obj;
		} else if (obj instanceof String) {
			String str = (String) obj;
			if (StringUtility.isNotEmpty(str)) {
				value = Double.parseDouble(str);
			}
		} else {
			String str = obj.toString();
			if (StringUtility.isNotEmpty(str)) {
				value = Double.parseDouble(str);
			}
		}
		if (null != value) {
			dstMap.put(aKey, value);
		}
		return true;
	}

	/**
	 * ソースマップのキーの値を設定する。
	 * <p>
	 * ソースマップの値が<code>null</code>またはブランクの場合、設定しない。
	 * </p>
	 * 
	 * @param aKey キー
	 * @return 結果
	 */
	public boolean copyToLngNotEmpty(final String aKey) {
		Object obj = srcMap.get(aKey);
		Long value = null;
		if (null == obj) {
			// no setting
		} else if (obj instanceof Long) {
			value = (Long) obj;
		} else if (obj instanceof String) {
			String str = (String) obj;
			if (StringUtility.isNotEmpty(str)) {
				value = Long.parseLong(str);
			}
		} else {
			String str = obj.toString();
			if (StringUtility.isNotEmpty(str)) {
				value = Long.parseLong(str);
			}
		}
		if (null != value) {
			dstMap.put(aKey, value);
		}
		return true;
	}

	/**
	 * ソースマップのキーの値を設定する。
	 * <p>
	 * ソースマップの値が<code>null</code>またはブランクの場合、設定しない。
	 * </p>
	 * 
	 * @param aKey キー
	 * @return 結果
	 */
	public boolean copyToIntNotEmpty(final String aKey) {
		Object obj = srcMap.get(aKey);
		Integer value = null;
		if (null == obj) {
			// no setting
		} else if (obj instanceof Integer) {
			value = (Integer) obj;
		} else if (obj instanceof String) {
			String str = (String) obj;
			if (StringUtility.isNotEmpty(str)) {
				value = Integer.parseInt(str);
			}
		} else {
			String str = obj.toString();
			if (StringUtility.isNotEmpty(str)) {
				value = Integer.parseInt(str);
			}
		}
		if (null != value) {
			dstMap.put(aKey, value);
		}
		return true;
	}

	/**
	 * ソースマップのキーの値(Timestamp型)を設定する。
	 * <p>
	 * ソースマップの値が<code>null</code>またはブランクの場合、設定しない。
	 * </p>
	 * 
	 * @param aKey キー
	 * @return 結果
	 */
	public boolean copyToTmsNotEmpty(final String aKey) {
		Object obj = srcMap.get(aKey);
		Timestamp value = null;
		if (null == obj) {
			// no setting
		} else if (obj instanceof Timestamp) {
			value = (Timestamp) obj;
		} else if (obj instanceof Date) {
			value = cnvTimestamp((Date) obj);
		} else if (obj instanceof java.sql.Date) {
			value = cnvTimestamp((java.sql.Date) obj);
		} else {
		}
		if (null != value) {
			dstMap.put(aKey, value);
		}
		return true;
	}

	/**
	 * マップを生成する。
	 * 
	 * @return マップ
	 */
	public Map<String, Object> build() {
		return new HashMap<String, Object>(dstMap);
	}

	private Timestamp cnvTimestamp(final Date aDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(aDate);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		Timestamp ts = new Timestamp(c.getTimeInMillis());
		return ts;
	}

	private Timestamp cnvTimestamp(final java.sql.Date aDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(aDate);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		Timestamp ts = new Timestamp(c.getTimeInMillis());
		return ts;
	}
}
