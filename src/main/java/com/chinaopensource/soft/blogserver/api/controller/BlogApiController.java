package com.chinaopensource.soft.blogserver.api.controller;

import com.chinaopensource.soft.blogserver.blog.service.BlogService;
import com.chinaopensource.soft.blogserver.common.controller.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/api/blog")
public class BlogApiController {


    @Autowired
    private BlogService blogService;

    @ResponseBody
    @RequestMapping(value="/newblog", method = RequestMethod.GET)
    public String newblog(){
        return BaseResponse.successJson(blogService.findNewBlogs());
    }

}
