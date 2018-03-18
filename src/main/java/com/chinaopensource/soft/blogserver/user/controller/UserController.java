package com.chinaopensource.soft.blogserver.user.controller;

import com.chinaopensource.soft.blogserver.user.model.User;
import com.chinaopensource.soft.blogserver.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinaopensource.soft.blogserver.common.controller.BaseResponse;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 注册页面
	 * @return
	 */
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String addUserPage(){
		return "user/register";
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addUser", method = RequestMethod.POST)
	public String addUser(User user){
		this.userService.insert(user);
		return BaseResponse.successJson();
	}
	
	/**
	 * 跳转到修改页面
	 * @return
	 */
	@RequestMapping(value="/updateUserPage", method = RequestMethod.GET)
	public String updateUserPage(Integer id, Model model){
		User user = this.userService.selectByUserId(id);
		model.addAttribute("user",user);
		return "common/user/updateUser";
	}
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateUser", method = RequestMethod.POST)
	public String updateUser(User user){
		this.userService.updateByUserId(user);
		return BaseResponse.successJson();
	}
	
	
}
