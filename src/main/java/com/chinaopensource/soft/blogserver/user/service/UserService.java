package com.chinaopensource.soft.blogserver.user.service;

import com.chinaopensource.soft.blogserver.user.model.User;

public interface UserService {

	User selectByUserId (Integer id);

	User selectByLoginname(String loginname);

	int insert(User user);

	int updateByUserId(User user);
}
