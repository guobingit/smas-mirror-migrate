package com.dayainfo.mirror.migrate.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import com.dayainfo.mirror.migrate.constants.Constants;
import com.dayainfo.mirror.migrate.service.essearch.ESIndexService;
import com.dayainfo.mirror.migrate.service.lucene.LuceneIndexService;
import com.dayainfo.modules.utils.StringUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.springframework.stereotype.Service;

@Service
public class UpdateDataService {
	
	private static final int PAGE_SIZE = 100;
	
	@Resource
	private ESIndexService esIndexService;
	@Resource
	private LuceneIndexService luceneIndexService;
	
	public void update() {
		MatchAllDocsQuery query = new MatchAllDocsQuery();
		int cpage = 1;
		while (true) {
			System.out.println("页面大小："+ PAGE_SIZE +"，当前第" + cpage + "页");
			List<Document> docs = luceneIndexService.search(query, cpage, PAGE_SIZE);
			if(docs == null || docs.size() == 0) {
				break;
			}
			updateData(docs);
			if (docs.size() < PAGE_SIZE) {
				break;
			}
			cpage++;
		}
	}
	
	private void updateData(List<Document> docs) {
		List<Map<String, Object>> sources = docs.stream().map(doc-> {
			Map<String, Object> source = new LinkedHashMap<>();
			String zjid = doc.get("zjid");
			String dxid = doc.get("dxid");
			if(StringUtils.isBlank(zjid)) {
				if(StringUtils.isNotBlank(dxid)) {
					zjid = dxid + "_" + System.currentTimeMillis();
				} else {
					zjid = System.currentTimeMillis() + "";
				}
			}
			source.put("docid", zjid);
			source.put("title", doc.get("bookname"));
			source.put("dxid", dxid);
			source.put("ssid", doc.get("ssid"));
			source.put("author", doc.get("author"));
			source.put("publishDate", doc.get("publishDate"));
			source.put("zhangjie", doc.get("zhangjie"));
			source.put("qwpos", doc.get("qwpos"));
			source.put("fenlei", doc.get("fenlei"));
			source.put("fulltext", doc.get("mulu"));
			return source;
		}).collect(Collectors.toList());
		esIndexService.insertBook(sources, Constants.INDEX_NAME, Constants.INDEX_TYPE);
	}
}
