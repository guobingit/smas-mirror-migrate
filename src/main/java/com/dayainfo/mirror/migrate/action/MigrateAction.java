package com.dayainfo.mirror.migrate.action;

import java.io.IOException;
import java.net.URISyntaxException;
import javax.annotation.Resource;
import com.dayainfo.mirror.migrate.constants.Constants;
import com.dayainfo.mirror.migrate.service.UpdateDataService;
import com.dayainfo.mirror.migrate.service.essearch.ESIndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MigrateAction {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MigrateAction.class);
	
	@Resource
	UpdateDataService updateDataService;
	@Resource
	ESIndexService esIndexService;
	
	@RequestMapping("")
	@ResponseBody
	public String hello() {
		return "hello world!";
	}
	
	@RequestMapping("migrate")
	@ResponseBody
	public String migrate() {
		try {
			Long begin = System.currentTimeMillis();
			updateDataService.update();
			Long end = System.currentTimeMillis();
			LOGGER.warn("转换成功，共用时{}ms", (end - begin));
			return "success";
		} catch (Exception e) {
			LOGGER.error("migrate error", e);
			return "error";
		}
	}
	
	@RequestMapping("create")
	@ResponseBody
	public String create() {
		try {
			esIndexService.createIndex(Constants.INDEX_NAME);
			return "success";
		} catch (URISyntaxException | IOException e) {
			LOGGER.error("create es index error", e);
			return "false";
		}
	}
}
