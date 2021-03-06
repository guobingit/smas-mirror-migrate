package com.dayainfo.mirror.migrate.service.essearch;

import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dayainfo.modules.utils.FileUtils;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ESIndexService extends ESBaseSearch {
	@Value("${es.index.number_of_shards}")
	private String INDEX_SHARDS; // 数据分片数，默认为5，有时候设置为3
	@Value("${es.index.number_of_replicas}")
	private String INDEX_REPLICAS; // 数据备份数，如果只有一台机器，设置为0
	
	/**
	 * 删除索引
	 */
	public void delIndex(String index) {
		IndicesExistsRequest inExistsRequest = new IndicesExistsRequest(index);
		IndicesExistsResponse inExistsResponse = getClient().admin().indices().exists(inExistsRequest).actionGet();
		if (inExistsResponse.isExists()) {
			getClient().admin().indices().prepareDelete(index).get();
		}
	}
	
	public void createIndex(String name, String type, String jsonName) {
		String mapping = FileUtils.readFile(jsonName);
		JSONObject jsonObject = JSON.parseObject(mapping);
		getClient().admin()
				.indices()
				.prepareCreate(name)
				.setSettings(Settings.builder()
						.put("index.number_of_replicas", INDEX_REPLICAS)
						.put("index.number_of_shards", INDEX_SHARDS)
						.build())
				.addMapping(type, jsonObject.getJSONObject("indexMapping"))
				.get();
	}
	
	public void insertBook(List<Map<String, Object>> sources, String name, String type) {
		BulkRequestBuilder bulkRequestBuilder = getClient().prepareBulk();
		sources.forEach(f -> bulkRequestBuilder.add(getClient().prepareIndex(name, type, f.get("docid").toString()).setSource(f)));
		bulkRequestBuilder.get();
	}
}
