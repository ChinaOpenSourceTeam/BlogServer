package com.liqiwei.soft.adminserver.common.user.service;

import com.chinaopensource.soft.blogserver.user.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinaopensource.soft.blogserver.user.service.UserService;
import com.liqiwei.soft.adminserver.BaseTest;

import java.util.Date;

public class UserServiceTest extends BaseTest{

	@Autowired
	private UserService userService;
	
	@Test
	public void testSelectByUserId() {
		this.userService.selectByUserId(1);
	}
	@Test
	public void testSave(){
		User user = new User();
		user.setLoginname("123");
		user.setNickname("3123");
		user.setPassword("34123");
		user.setCreateTime(new Date());
		this.userService.insert(user);
	}

}
