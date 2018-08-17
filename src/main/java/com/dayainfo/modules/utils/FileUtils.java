package com.dayainfo.modules.utils;

import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils extends org.apache.commons.io.FileUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);
	
	/**
	 * 文件上传
	 *
	 * @param file
	 * @return 上传之后的文件路径
	 */
	public static String uploadFile(MultipartFile file) {
		String path = PropertiesUtils.getUploadFilePath();
		String filename = file.getOriginalFilename();
		File filepath = new File(path, filename);
		if (!filepath.getParentFile().exists()) {
			filepath.getParentFile().mkdirs();
		}
		String filePath = path + File.separator + filename;
		try {
			file.transferTo(new File(filePath));
		} catch (IOException e) {
			LOGGER.error("FileUtils->uploadFile() save file fail!", e);
		}
		return filePath;
	}
	
}
