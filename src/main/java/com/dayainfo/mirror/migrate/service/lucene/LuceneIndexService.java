package com.dayainfo.mirror.migrate.service.lucene;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LuceneIndexService {
	
	@Value("${index.file.path}")
	private String indexDir;
	
//	private IndexWriter writer; // 写索引实例
//
//	/**
//	 * 构造方法 实例化IndexWriter
//	 *
//	 * @throws Exception
//	 */
//	public LuceneIndex() throws IOException {
//		Directory dir = FSDirectory.open(Paths.get(indexDir));
//		Analyzer analyzer = new StandardAnalyzer(); // 标准分词器
//		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
//		writer = new IndexWriter(dir, iwc);
//	}
//
//
//	/**
//	 * 把指定的数据，更新到索引中
//	 *
//	 * @param datas
//	 */
//	public void insertIndex(List<Map<String, Object>> datas) throws Exception {
//		for (Map<String, Object> data : datas) {
//			writer.addDocument(getDocument(data));
//		}
//		writer.close();
//	}
//
//
//	/**
//	 * 获取文档，文档里再设置每个字段
//	 *
//	 * @param data
//	 */
//	private Document getDocument(Map<String, Object> data) {
//		Document doc = new Document();
//		doc.add(new TextField("docid", Objects.toString(data.get("zjid")), Field.Store.YES));
//		doc.add(new TextField("title", Objects.toString(data.get("bookname")), Field.Store.YES));
//		doc.add(new TextField("dxid", Objects.toString(data.get("dxid")), Field.Store.YES));
//		doc.add(new TextField("ssid", Objects.toString(data.get("ssid")), Field.Store.YES));
//		doc.add(new TextField("author", Objects.toString(data.get("author")), Field.Store.YES));
//		doc.add(new TextField("publishDate", Objects.toString(data.get("publishDate")), Field.Store.YES));
//		doc.add(new TextField("zhangjie", Objects.toString(data.get("zhangjie")), Field.Store.YES));
//		doc.add(new TextField("qwpos", Objects.toString(data.get("qwpos")), Field.Store.YES));
//		doc.add(new TextField("feilei", Objects.toString(data.get("feilei")), Field.Store.YES));
//		doc.add(new TextField("fulltext", Objects.toString(data.get("mulu")), Field.Store.YES));
//		return doc;
//	}
//
	/**
	 * 测试搜索的工具方法
	 *
	 * @param query
	 */
	public List<Document> search(Query query, int pageSize) throws Exception {
		IndexReader reader = null;
		List<Document> documents = new ArrayList<>(0);
		try {
			reader = DirectoryReader.open(FSDirectory.open(Paths.get(indexDir)));
			IndexSearcher indexSearcher = new IndexSearcher(reader);
			TopDocs topDocs = indexSearcher.search(query, pageSize);
			for (int i = 0; i < topDocs.scoreDocs.length; i++) {
				// 根据编号拿到Document数据
				int docId = topDocs.scoreDocs[i].doc; // Document的内部编号
				Document doc = indexSearcher.doc(docId);
				documents.add(doc);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			reader.close();
		}
		return documents;
	}
}
