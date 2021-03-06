package com.chinaopensource.soft.blogserver.user.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinaopensource.soft.blogserver.common.controller.BaseResponse;
import com.chinaopensource.soft.blogserver.common.shiro.ShiroUser;
import com.chinaopensource.soft.blogserver.user.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chinaopensource.soft.blogserver.common.util.ValidateCode;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("Welcome home!");
		model.addAttribute("user",ShiroUser.getUser());
		return "user/home";
	}

	@ResponseBody
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String userInfo(){
		User user = ShiroUser.getUser();
		Map<String,String> userinfo = new HashMap<String, String>();
		userinfo.put("name",user.getNickname());
		userinfo.put("photo",user.getPhoto());
		return BaseResponse.successJson(userinfo);
	}

	@RequestMapping(value = "/unauth", method = RequestMethod.GET)
	public String unauth() {
		return "user/unauth";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value = "/*", method = RequestMethod.GET)
	public String patternUrl() {
		Subject user = SecurityUtils.getSubject();
		if(user.isAuthenticated()){
			return "redirect:/user/";
		}else{
			return "redirect:/user/login";
		}
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String logindata(HttpServletRequest request,String loginname ,String password ,String validateCode , Model model) {
		String sessionValidateCode = (String) request.getSession().getAttribute("validateCode");
		if(!validateCode.equals(sessionValidateCode)) {
			return "redirect:/user/login";
		}
		
		Subject user = SecurityUtils.getSubject();
	    UsernamePasswordToken token = new UsernamePasswordToken(loginname,password);
        try
        {
            // 会调用 shiroDbRealm 的认证方法
            user.login(token);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            return "redirect:/user/login";
        }
		return "redirect:/user/";
	}
	
	/** 
    * user logout
    */  
    @RequestMapping(value="/logout",method=RequestMethod.GET)  
    public String logout(){  
         SecurityUtils.getSubject().logout();  
         return "redirect:/user/login";  
    }  

    
    /**
     * 生成验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/validateCode",method=RequestMethod.GET)
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_NUM_ONLY, 4, null);
        request.getSession().setAttribute("validateCode", verifyCode);
        response.setContentType("image/jpeg");
        BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
        ImageIO.write(bim, "JPEG", response.getOutputStream());
    }
}
