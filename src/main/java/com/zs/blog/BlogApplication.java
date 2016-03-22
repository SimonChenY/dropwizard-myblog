package com.zs.blog;

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
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;
import java.util.EnumSet;

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
        environment.jersey().getResourceConfig().register(MultiPartFeature.class);

        // CORS enable
        Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        filter.setInitParameter("allowCredentials", "true");

    }

    public static void main(String[] args) throws Exception{
        new BlogApplication().run(args);
    }
}

