package com.zs.blog.resources;

import com.codahale.metrics.annotation.Timed;
import com.zs.blog.core.Blog;
import com.zs.blog.db.BlogDao;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/v1/blog")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BlogResources {

    private BlogDao blogDao;

    public BlogResources(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    @GET
    @UnitOfWork
    public List<Blog> listBlogs() {
        return blogDao.findAll();
    }

    @GET
    @Path("/{id}")
    @Timed
    @UnitOfWork
    public Blog findBlogById(@PathParam("id") LongParam id) {
        if (!blogDao.findById(id.get()).isPresent()) {
            throw new NotFoundException("No such blog");
        }
        return blogDao.findById(id.get()).get();
    }

    @POST
    @Path("/{id}")
    @UnitOfWork
    public Blog createOrUpdate(@PathParam("id") LongParam id, Blog blog) {
        blog.setId(id.get());
        if(id.get() < 1) blog.setId(null);
        return blogDao.createAndUpdate(blog);
    }

}
