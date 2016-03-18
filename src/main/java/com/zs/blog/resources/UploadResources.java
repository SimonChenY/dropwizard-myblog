package com.zs.blog.resources;

import com.codahale.metrics.annotation.Timed;
import com.zs.blog.core.Bean;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

@Path("/api/v1")
public class UploadResources {

    @Path("/upload")
    @POST
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(
            @FormDataParam("bean") final FormDataContentDisposition beanD,
            @FormDataParam("bean") final Bean bean,
            @FormDataParam("file") final InputStream fileInputStream,
            @FormDataParam("file") final FormDataContentDisposition contentDispositionHeader
    ) {
        System.err.println("-----------here-----------");
        return null;
    }

}
