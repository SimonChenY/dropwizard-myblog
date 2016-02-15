package com.zs.blog;

import com.zs.blog.core.Blog;
import com.zs.blog.db.BlogDao;
import com.zs.blog.resources.BlogResources;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        environment.jersey().register(new BlogResources(blogDao));
    }

    public static void main(String[] args) throws Exception{
        new BlogApplication().run(args);
    }
}

