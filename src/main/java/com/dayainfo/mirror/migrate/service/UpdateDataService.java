package com.dayainfo.mirror.migrate.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import com.dayainfo.mirror.migrate.service.essearch.ESIndexService;
import com.dayainfo.mirror.migrate.service.lucene.LuceneIndex;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.springframework.stereotype.Service;

@Service
public class UpdateDataService {
	
	private static final int PAGE_SIZE = 100;
	
	@Resource
	private ESIndexService esIndexService;
	
	public void update() throws Exception {
		LuceneIndex index = new LuceneIndex();
		MatchAllDocsQuery query = new MatchAllDocsQuery();
		while (true) {
			List<Document> docs = index.search(query, PAGE_SIZE);
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
			source.put("fileName", doc.get("fileName"));
			source.put("contents", doc.get("content"));
			return source;
		}).collect(Collectors.toList());
		esIndexService.insertBook(sources);
	}
}