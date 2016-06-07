package domain.servicesRest;


import domain.Post;
import domain.services.PostService;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("posts")
@Service
public class PostServiceRest{

    private PostService postService;

    public PostServiceRest(){}

    public PostServiceRest(PostService postService){
        this.postService = postService;
    }

    @POST
    @Path("post")
    @Produces("application/json")
    @Consumes("application/json")
    public Response createPost(Post post){
        Response response;
        try{
            postService.createPost(post);
            response = Response.ok().build();
        }
        catch (Exception e ){
            e.getMessage();
            response = Response.serverError().build();

        }
        return response;
    }

    @GET
    @Path("posts/{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public List<Post> posts(@PathParam("id") final Integer id){
        return postService.posts(id);
    }



}
