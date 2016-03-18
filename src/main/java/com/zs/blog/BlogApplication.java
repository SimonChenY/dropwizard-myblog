package com.zs.blog;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.zs.blog.core.Blog;
import com.zs.blog.db.BlogDao;
import com.zs.blog.resources.BlogResources;
import com.zs.blog.resources.UploadResources;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

public class BlogApplication extends Application<BlogConfiguration>{

    private final HibernateBundle<BlogConfiguration> hibernate = new HibernateBundle<BlogConfiguration>(
            Blog.class
    ) {
        @Override
        public DataSourceFactory getDataSourceFactory(BlogConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<BlogConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
        bootstrap.addBundle(new MigrationsBundle<BlogConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(BlogConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(BlogConfiguration blogConfiguration, Environment environment) throws Exception {
        final BlogDao blogDao = new BlogDao(hibernate.getSessionFactory());

        // Register all resources here.
        environment.jersey().register(new BlogResources(blogDao));
        environment.jersey().register(new UploadResources());

        // Register MultiPartFeature.class to support mime type "multipart/form-data."
        environment.jersey().getResourceConfig().register(JacksonJaxbJsonProvider.class);
        environment.jersey().getResourceConfig().register(MultiPartFeature.class);
    }

    public static void main(String[] args) throws Exception{
        new BlogApplication().run(args);
    }
}

