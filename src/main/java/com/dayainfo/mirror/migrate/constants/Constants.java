package com.dayainfo.mirror.migrate.constants;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.dayainfo.modules.utils.StringUtils;

/**
 * 全局常量
 */
public class Constants {
	
	/**
	 * es索引名字(必须小写)
	 */
	public static final String INDEX_NAME = "index";
	public static final String INDEX_TYPE = "book";
	
	public enum UploadTypeEnum {
		
		doc, docx, pdf, txt, rtf, wps;
		
		private final static List<String> types = Stream.of(values()).map(Enum::name).collect(Collectors.toList());
		
		public static boolean containsType(String type) {
			return types.contains(StringUtils.lowerCase(type));
		}
	}
	
	/**
	 * 检测结果
	 */
	public enum AnalysisStatusEnum {
		/** 未检测 */
		NONE(0),
		/** 检测完成（有结果） */
		FINISH(1),
		/** 检测完成（没有结果） */
		FINISH_NULL(2),
		/** 检测失败 */
		ERROR(-1);
		private int status;
		
		AnalysisStatusEnum(int status) {
			this.status = status;
		}
		
		public int getStatus() {
			return this.status;
		}
	}
}
