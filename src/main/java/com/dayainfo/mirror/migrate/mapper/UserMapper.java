package com.dayainfo.mirror.migrate.mapper;

import java.util.List;
import com.dayainfo.mirror.migrate.entity.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
	@Select("SELECT * FROM user limit #{currIndex}, #{pageSize}")
	List<User> getAll(@Param("currIndex") Integer currIndex, @Param("pageSize") Integer pageSize);
	
	@Select("select count(*) from user")
	int getCount();
	
}
