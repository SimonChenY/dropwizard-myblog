package com.zs.blog.db;

import com.zs.blog.core.Blog;
import org.hibernate.SessionFactory;

import java.util.List;

public class BlogDao extends BaseDao<Blog> {

    public BlogDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Blog> findAll() {
        return list(namedQuery("com.zs.blog.core.Blog.findAll"));
    }
}
