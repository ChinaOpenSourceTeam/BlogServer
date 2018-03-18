package com.chinaopensource.soft.blogserver.api.controller;

import com.chinaopensource.soft.blogserver.common.Config;
import com.chinaopensource.soft.blogserver.common.controller.BaseResponse;
import com.chinaopensource.soft.blogserver.common.util.DateUtil;
import com.chinaopensource.soft.blogserver.user.model.User;
import com.chinaopensource.soft.blogserver.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 页面获取用户信息
 * @author: liqiwei
 * @create: 2018-03-08 14:40
 **/
@Controller
@RequestMapping(value="/api/user")
public class UserApiController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value="/bloguser", method = RequestMethod.GET)
    public String findUserByUserId(Integer userId){
        Map<String,String> userMap = new HashMap<String,String>();
        User user = this.userService.selectByUserId(userId);
        userMap.put("name",user.getNickname());
        userMap.put("photo",user.getPhoto());
        return BaseResponse.successJson(userMap);
    }

    // 单文件上传
    @PostMapping(value = "/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {

        try {
            if (file.isEmpty()) {
                return "{\"success\":\"" + false + "\"}";
            }
            //获得文件后缀名
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String  path  = "img"+File.separator+ DateUtil.imgPath()+suffix;
            // 设置文件存储路径
            File dest = new File(Config.WEB_SITE +path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入

            String urlPath = path.replace("\\","/");
            return "{\"success\":\"" + true + "\",\"file_path\":\"" + Config.WEB_URL+"/"+urlPath + "\"}";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "{\"success\":\"" + false + "\"}";
    }

}
