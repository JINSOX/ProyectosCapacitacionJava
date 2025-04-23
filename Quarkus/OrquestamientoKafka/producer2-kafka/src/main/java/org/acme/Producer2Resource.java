package org.acme;


import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/kafka")
@Produces(MediaType.APPLICATION_JSON)
public class Producer2Resource{
    @Inject
    Producer2 producer;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response send(Post post) {
        producer.sendPost(post);
        return Response.accepted().build();
    }
}