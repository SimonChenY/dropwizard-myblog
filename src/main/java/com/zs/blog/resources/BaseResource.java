package com.zs.blog.resources;


import com.zs.blog.db.BaseDao;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BaseResource<T> {
    private BaseDao<T> baseDao;

    public BaseResource(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }


}
