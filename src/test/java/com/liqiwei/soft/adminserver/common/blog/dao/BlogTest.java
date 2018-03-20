package com.liqiwei.soft.adminserver.common.blog.dao;

import com.chinaopensource.soft.blogserver.blog.dao.BlogMapper;
import com.liqiwei.soft.adminserver.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BlogTest extends BaseTest {

    @Autowired
    private BlogMapper blogMapper;

    @Test
    public void testFind(){
        System.out.println(blogMapper.findMyBlogs(6));
    }
}
