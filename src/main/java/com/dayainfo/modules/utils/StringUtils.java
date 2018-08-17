package com.dayainfo.modules.utils;

public class StringUtils extends org.apache.commons.lang3.StringUtils {
	
	/**
	 * 截取部分字符串，这里一个汉字的长度认为是2
	 *
	 * @param str
	 * @param len
	 * @return
	 */
	public static String getPartString(String str, int len) {
		try {
			byte b[];
			int counterOfDoubleByte = 0;
			b = str.getBytes("GBK");
			if (b.length <= len) {
				return str;
			}
			for (int i = 0; i < len; i++) {
				if (b[i] < 0) {
					counterOfDoubleByte++;
				}
			}
			if (counterOfDoubleByte % 2 == 0) {
				return new String(b, 0, len, "GBK") + "...";
			} else {
				return new String(b, 0, len - 1, "GBK") + "...";
			}
		} catch (Exception e) {
			return str;
		}
	}
}
