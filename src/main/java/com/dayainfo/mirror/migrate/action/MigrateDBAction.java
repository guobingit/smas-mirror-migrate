package com.dayainfo.mirror.migrate.action;

import javax.annotation.Resource;
import com.dayainfo.mirror.migrate.service.UpdateDBDataService;
import com.dayainfo.mirror.migrate.service.essearch.ESIndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("db")
public class MigrateDBAction {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MigrateAction.class);
	
	@Resource
	ESIndexService esIndexService;
	@Resource
	UpdateDBDataService updateDBDataService;
	
	@RequestMapping("migrate")
	@ResponseBody
	public String migrate() {
		try {
			Long begin = System.currentTimeMillis();
			updateDBDataService.update();
			Long end = System.currentTimeMillis();
			LOGGER.warn("转换成功，共用时{}ms", (end - begin));
			return "success";
		} catch (Exception e) {
			LOGGER.error("migrate error", e);
			return "error";
		}
	}
}
