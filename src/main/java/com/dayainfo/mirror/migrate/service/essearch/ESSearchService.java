package com.dayainfo.mirror.migrate.service.essearch;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import com.dayainfo.mirror.migrate.constants.Constants;
import com.dayainfo.mirror.migrate.entity.vo.ESResult;
import com.dayainfo.modules.utils.StringUtils;
import com.google.common.collect.Lists;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.SpanNearQueryBuilder;
import org.elasticsearch.index.query.SpanQueryBuilder;
import org.elasticsearch.index.query.SpanTermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

@Service
public class ESSearchService extends ESBaseSearch {
	
	public List<ESResult> search(String sentence) throws Exception {
		SearchRequestBuilder searchRequestBuilder = getClient().prepareSearch(Constants.INDEX_NAME);
		searchRequestBuilder.setTypes(Constants.INDEX_TYPE);
		SpanQueryBuilder spanQueryBuilder = getSearchQuery("fulltext", sentence);
		if (Objects.isNull(spanQueryBuilder)) {
			return null;
		}
		searchRequestBuilder.setQuery(spanQueryBuilder);
		SearchResponse response = searchRequestBuilder.execute().actionGet();
		SearchHits hits = response.getHits();
		return operateData(hits);
	}
	
	/**
	 * 处理检索的结果集
	 *
	 * @param hits 命中结果
	 * @return
	 */
	private List<ESResult> operateData(SearchHits hits) {
		
		List<ESResult> resultList = Lists.newArrayList();
		
		if (Objects.isNull(hits) || hits.getHits().length == 0) {
			return null;
		}
		
		for (SearchHit h : hits.getHits()) {
			Map<String, Object> centerMap = h.getSourceAsMap();
			ESResult esResult = new ESResult();
			esResult.parse(centerMap);
			resultList.add(esResult);
		}
		return resultList;
	}
	
	/**
	 * 获取检索的span near query
	 *
	 * @param field 检索的字段
	 * @param content 检索内容
	 * @return
	 * @throws Exception
	 */
	public SpanNearQueryBuilder getSearchQuery(String field, String content) throws IOException {
		
		if (StringUtils.isBlank(content)) {
			return null;
		}
		
		SpanNearQueryBuilder spanNearQueryBuilder = null;
		StringReader reader = new StringReader(content);
		IKSegmenter ik = new IKSegmenter(reader, true);//当为true时，分词器进行最大词长切分
		Lexeme lexeme;
		while ((lexeme = ik.next()) != null) {
			String text = lexeme.getLexemeText();
			if (StringUtils.isBlank(text)) {
				continue;
			} else {
				SpanTermQueryBuilder spanTermQueryBuilder = QueryBuilders.spanTermQuery(field, text);
				if (Objects.isNull(spanNearQueryBuilder)) {
					spanNearQueryBuilder = QueryBuilders.spanNearQuery(spanTermQueryBuilder, 2).inOrder(true);
				} else {
					spanNearQueryBuilder.addClause(spanTermQueryBuilder);
				}
			}
		}
		reader.close();
		return spanNearQueryBuilder;
	}
}
