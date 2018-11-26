package com.dayainfo.mirror.migrate.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import com.dayainfo.mirror.migrate.constants.Constants;
import com.dayainfo.mirror.migrate.entity.po.User;
import com.dayainfo.mirror.migrate.mapper.UserMapper;
import com.dayainfo.mirror.migrate.service.essearch.ESIndexService;
import org.springframework.stereotype.Service;

@Service
public class UpdateDBDataService {
	
	private static final int PAGE_SIZE = 2;
	
	@Resource
	private UserMapper userMapper;
	@Resource
	private ESIndexService esIndexService;
	
	
	public void update() {
		int count = userMapper.getCount();
		int pages = count / PAGE_SIZE;
		if (count % PAGE_SIZE != 0) {
			pages++;
		}
		for (int i = 0; i < pages; i++) {
			System.out.println("页面大小：" + PAGE_SIZE + "，共" + pages + "页, 当前第" + (i + 1) + "页");
			List<User> users = userMapper.getAll(i * PAGE_SIZE, PAGE_SIZE);
			updateUsers(users);
		}
	}
	
	public void updateUsers(List<User> users) {
		List<Map<String, Object>> sources = users.stream().map(user -> {
			Map<String, Object> source = new LinkedHashMap<>();
			source.put("docid", user.getId());
			source.put("name", user.getName());
			source.put("age", user.getAge());
			return source;
		}).collect(Collectors.toList());
		esIndexService.insertBook(sources, Constants.DB_INDEX_NAME, Constants.DB_INDEX_TYPE);
		
	}
}
