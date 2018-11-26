package com.dayainfo.mirror.migrate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dayainfo.mirror.migrate.mapper")
public class MigrateApplication {

	public static void main(String[] args) {
		SpringApplication.run(MigrateApplication.class, args);
	}
}
