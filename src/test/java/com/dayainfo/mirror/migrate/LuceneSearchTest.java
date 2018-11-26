package com.dayainfo.mirror.migrate;

import java.util.List;
import javax.annotation.Resource;
import com.dayainfo.mirror.migrate.service.lucene.LuceneIndexService;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LuceneSearchTest {
	
	@Resource
	private LuceneIndexService luceneIndexService;
	
	@Test
	public void search() {
		MatchAllDocsQuery query = new MatchAllDocsQuery();
		List<Document> docs = luceneIndexService.search(query, 1, 100);
		System.out.println(123);
	}
}
