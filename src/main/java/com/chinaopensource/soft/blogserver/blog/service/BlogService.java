package com.chinaopensource.soft.blogserver.blog.service;

import com.chinaopensource.soft.blogserver.blog.model.Blog;

import java.util.List;

public interface BlogService {
    int save(Blog blog);
    List<Blog> findNewBlogs();
}
