package com.dayainfo.mirror.migrate.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MigrateAction {

	@RequestMapping("")
	@ResponseBody
	public String hello() {
		return "hello world!";
	}
}
