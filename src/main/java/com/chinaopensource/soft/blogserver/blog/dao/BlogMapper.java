package com.chinaopensource.soft.blogserver.blog.dao;

import com.chinaopensource.soft.blogserver.blog.model.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface BlogMapper {
    int save(Blog blog);

    List<Blog> findNewBlogs();
}
