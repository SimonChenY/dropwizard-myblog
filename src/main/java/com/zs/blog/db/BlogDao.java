package com.zs.blog.db;

import com.zs.blog.core.Blog;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

import java.util.List;

public class BlogDao extends BaseDao<Blog> {

    public BlogDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Blog> findAllByCriteria(Criteria criteria) {
        return super.findAllByCriteria(criteria);
    }
}
