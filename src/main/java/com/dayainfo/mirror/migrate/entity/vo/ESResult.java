package com.dayainfo.mirror.migrate.entity.vo;

import java.util.Map;
import java.util.Objects;

public class ESResult {
	
	private String docid;
	private String author;
	private String fulltext;
	
	/**
	 * 实例化
	 *
	 * @param map
	 */
	public void parse(Map<String, Object> map) {
		this.docid =Objects.toString(map.get("docid"));
		this.author = Objects.toString(map.get("author"));
		this.fulltext = Objects.toString(map.get("fulltext"));
	}
	
	public String getDocid() {
		return docid;
	}
	
	public void setDocid(String docid) {
		this.docid = docid;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getFulltext() {
		return fulltext;
	}
	
	public void setFulltext(String fulltext) {
		this.fulltext = fulltext;
	}
}
