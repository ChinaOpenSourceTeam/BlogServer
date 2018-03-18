package com.chinaopensource.soft.blogserver.page.controller;

import com.chinaopensource.soft.blogserver.blog.service.BlogService;
import com.chinaopensource.soft.blogserver.common.freemarker.FreemakerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 页面生成接口
 * @author: liqiwei
 * @create: 2018-03-08 14:23
 **/
@Controller
@RequestMapping(value="/page")
public class PageController {

    @Autowired
    private BlogService blogService;

    /**
     *生成首页
     * @return
     */
    @RequestMapping(value="/index")
    public String index(Model model) {

        //生成页面
        Map<String, Object> dataSource = new HashMap<String, Object>();

        FreemakerHelper.createHtml("index", dataSource, "index.html");

        return "index";
    }



}
