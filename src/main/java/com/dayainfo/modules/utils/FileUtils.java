package com.dayainfo.modules.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils extends org.apache.commons.io.FileUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);
	
	public static String readFile(String path) {
		InputStream is = null;
		try {
			is = FileUtils.class.getClassLoader().getResourceAsStream(path);
		    return IOUtils.toString(is, Charsets.toCharset(Charset.defaultCharset()));
		}catch (Exception e) {
			LOGGER.error("读取文件 {} 错误", path);
			return "";
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				} finally {
					is = null;
				}
			}
		}
	}
}
