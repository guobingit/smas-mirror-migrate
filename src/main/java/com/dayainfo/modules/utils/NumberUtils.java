package com.dayainfo.modules.utils;

public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils {
	
	/**
	 * 将Integer转换成字符串<br />
	 * toString(null) = "0" <br />
	 * toString(0) = "0" <br />
	 * toString(1) = "1" <br />
	 *
	 * @param i
	 * @return
	 */
	public static String toString(Integer i) {
		return toString(i, "0");
	}
	
	/**
	 * 将Integer转换成字符串<br />
	 * toString(null,null) = null <br />
	 * toString(null,"0") = "0" <br />
	 * toString(null,"2") = "2" <br />
	 * toString(0,"1") = "0" <br />
	 * toString(1,"0") = "1" <br />
	 *
	 * @param i
	 * @param defaultString
	 * @return
	 */
	public static String toString(Integer i, String defaultString) {
		return i == null ? defaultString : String.valueOf(i);
	}
}
