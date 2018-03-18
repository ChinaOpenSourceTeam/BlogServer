package com.liqiwei.soft.adminserver.common.user.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinaopensource.soft.blogserver.user.dao.UserMapper;
import com.liqiwei.soft.adminserver.BaseTest;

public class UserMapperTest extends BaseTest{

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testSelectByUserId() {
		this.userMapper.selectByUserId(3);
	}


}
