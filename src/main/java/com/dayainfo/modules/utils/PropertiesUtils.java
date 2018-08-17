package com.dayainfo.modules.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author simone (wangxinsh55@126.com)
 */
public class PropertiesUtils {
	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
	private final static Properties properties = new Properties();
	
	static {
		try {
			FileInputStream fileInputStream = new FileInputStream(new File(PropertiesUtils.class.getResource("/config.properties").toURI()));
			InputStreamReader inputStrReader = new InputStreamReader(fileInputStream, "UTF-8");
			properties.load(inputStrReader);
			inputStrReader.close();
			fileInputStream.close();
		} catch (Exception e) {
			logger.error("PropertiesUtils init error", e);
			throw new IllegalArgumentException(e);
		}
	}
	
	/**
	 * 获取后台索引上传文件的保存路径
	 * 
	 * @return
	 */
	public static String getIndexFilePath() {
		return properties.getProperty("index.file.path");
	}
	
	/**
	 * 获取前台上传文件的保存路径
	 *
	 * @return
	 */
	public static String getUploadFilePath() {
		return properties.getProperty("upload.file.path");
	}
}
