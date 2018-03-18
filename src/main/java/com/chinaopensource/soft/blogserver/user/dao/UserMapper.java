package com.chinaopensource.soft.blogserver.user.dao;

import com.chinaopensource.soft.blogserver.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {

	User selectByUserId (Integer id);

	User selectByLoginname(String loginname);

	int deleteByUserId(Integer id);

	int insert(User user);
	
	int updateByUserId(User user);

}
