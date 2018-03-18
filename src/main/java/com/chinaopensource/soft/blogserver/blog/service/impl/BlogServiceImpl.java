package com.chinaopensource.soft.blogserver.blog.service.impl;

import com.chinaopensource.soft.blogserver.blog.dao.BlogMapper;
import com.chinaopensource.soft.blogserver.blog.model.Blog;
import com.chinaopensource.soft.blogserver.blog.service.BlogService;
import com.chinaopensource.soft.blogserver.common.Constant;
import com.chinaopensource.soft.blogserver.common.freemarker.FreemakerHelper;
import com.chinaopensource.soft.blogserver.common.shiro.ShiroUser;
import com.chinaopensource.soft.blogserver.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value="blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public int save(Blog blog) {
        blog.setStatus(Constant.BLOGSTATUS.SAVE);
        blog.setDeleteflag(false);
        blog.setVersion(1);
        blog.setCreateuser(ShiroUser.getUserId());
        blog.setCreatetime(new Date());
        blog.setUpdatetime(new Date());
        String urlPath = createPage(blog);
        blog.setLocation(urlPath.replace("\\","/"));
        return blogMapper.save(blog);
    }

    @Override
    public List<Blog> findNewBlogs() {
        return blogMapper.findNewBlogs();
    }

    /**
     * 生成页面
     * @param blog
     * @return
     */
    private String createPage(Blog blog){
        String location ="blog" +File.separator+ DateUtil.htmlPath()+ File.separator+blog.getUuid()+".html";
        //生成页面
        Map<String, Object> dataSource = new HashMap<String, Object>();
        dataSource.put("blog", blog);
        FreemakerHelper.createHtml("detail", dataSource, location);
        return location;
    }
}
