package com.dayainfo.mirror.migrate.action;

import javax.annotation.Resource;
import com.dayainfo.mirror.migrate.service.UpdateDataService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MigrateAction {
	
	@Resource
	UpdateDataService updateDataService;

	@RequestMapping("")
	@ResponseBody
	public String hello() {
		return "hello world!";
	}
	
	@RequestMapping("")
	@ResponseBody
	public String migrate() {
		try {
			updateDataService.update();
			return "success";
		}catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
