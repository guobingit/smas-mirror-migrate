package com.dayainfo.mirror.migrate.entity.vo;

import java.util.Map;
import java.util.Objects;

public class ESResult {
	
	private String docid;
	private String title;
	private String dxid;
	private String ssid;
	private String author;
	private String publishDate;
	private String zhangjie;
	private String qwpos;
	private String fenlei;
	private String fulltext;
	
	/**
	 * 实例化
	 *
	 * @param map
	 */
	public void parse(Map<String, Object> map) {
		this.docid = Objects.toString(map.get("docid"));
		this.title = Objects.toString(map.get("title"));
		this.dxid = Objects.toString(map.get("dxid"));
		this.ssid = Objects.toString(map.get("ssid"));
		this.author = Objects.toString(map.get("author"));
		this.publishDate = Objects.toString(map.get("publishDate"));
		this.zhangjie = Objects.toString(map.get("zhangjie"));
		this.qwpos = Objects.toString(map.get("qwpos"));
		this.fenlei = Objects.toString(map.get("fenlei"));
		this.fulltext = Objects.toString(map.get("fulltext"));
	}
	
	public String getDocid() {
		return docid;
	}
	
	public void setDocid(String docid) {
		this.docid = docid;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDxid() {
		return dxid;
	}
	
	public void setDxid(String dxid) {
		this.dxid = dxid;
	}
	
	public String getSsid() {
		return ssid;
	}
	
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getPublishDate() {
		return publishDate;
	}
	
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	
	public String getZhangjie() {
		return zhangjie;
	}
	
	public void setZhangjie(String zhangjie) {
		this.zhangjie = zhangjie;
	}
	
	public String getQwpos() {
		return qwpos;
	}
	
	public void setQwpos(String qwpos) {
		this.qwpos = qwpos;
	}
	
	public String getFenlei() {
		return fenlei;
	}
	
	public void setFenlei(String fenlei) {
		this.fenlei = fenlei;
	}
	
	public String getFulltext() {
		return fulltext;
	}
	
	public void setFulltext(String fulltext) {
		this.fulltext = fulltext;
	}
}

