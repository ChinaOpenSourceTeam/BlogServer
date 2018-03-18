package com.chinaopensource.soft.blogserver.common.shiro;

import com.chinaopensource.soft.blogserver.user.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class ShiroUser {
	
	public static User getUser(){
        Subject subject= SecurityUtils.getSubject();
        if(subject==null)
            return null;
        return (User) subject.getPrincipal();
    }
	
	public static Integer getUserId(){
		return getUser().getId();
	}
}
