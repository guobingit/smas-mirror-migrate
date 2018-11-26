package com.dayainfo.mirror.migrate;

import javax.annotation.Resource;
import com.dayainfo.mirror.migrate.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DBTest {
	
	@Resource
	private UserMapper userMapper;
	
	@Test
	public void test() {
//		List<User> users = userMapper.getAll();
//		System.out.println(users.size());
	}
}
