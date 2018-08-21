package com.dayainfo.mirror.migrate.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import com.dayainfo.mirror.migrate.service.essearch.ESIndexService;
import com.dayainfo.mirror.migrate.service.lucene.LuceneIndexService;
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
	
	public void update() throws Exception {
		MatchAllDocsQuery query = new MatchAllDocsQuery();
		while (true) {
			List<Document> docs = luceneIndexService.search(query, PAGE_SIZE);
			if(docs.size() == 0) {
				break;
			}
			updateData(docs);
			if (docs.size() < PAGE_SIZE) {
				break;
			}
		}
	}
	
	private void updateData(List<Document> docs) {
		List<Map<String, Object>> sources = docs.stream().map(doc-> {
			Map<String, Object> source = new LinkedHashMap<>();
			source.put("docid", doc.get("zjid"));
			source.put("title", doc.get("bookname"));
			source.put("dxid", doc.get("dxid"));
			source.put("ssid", doc.get("ssid"));
			source.put("author", doc.get("author"));
			source.put("publishDate", doc.get("publishDate"));
			source.put("zhangjie", doc.get("zhangjie"));
			source.put("qwpos", doc.get("qwpos"));
			source.put("feilei", doc.get("feilei"));
			source.put("fulltext", doc.get("mulu"));
			return source;
		}).collect(Collectors.toList());
		esIndexService.insertBook(sources);
	}
}
