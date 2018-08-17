package com.dayainfo.mirror.migrate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.dayainfo.mirror.migrate.service.lucene.LuceneIndex;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.junit.Test;

public class LuceneTest {
	
	@Test
	public void create() throws Exception {
		LuceneIndex index = new LuceneIndex();
		List<Map<String, Object>> datas = new ArrayList<>();
		Map<String, Object> data1 = new HashMap<>();
		data1.put("fileName", "test");
		data1.put("fullPath", "ssssss");
		data1.put("contents", "This is a command-line application demonstrating simple Lucene indexing.");
		datas.add(data1);
		index.insertIndex(datas);
	}
	
	@Test
	public void search() throws Exception {
		LuceneIndex index = new LuceneIndex();
		MatchAllDocsQuery query = new MatchAllDocsQuery();
		index.search(query);
	}
}
