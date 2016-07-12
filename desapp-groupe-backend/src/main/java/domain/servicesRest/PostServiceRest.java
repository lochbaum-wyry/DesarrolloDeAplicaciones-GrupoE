package domain.servicesRest;


import domain.Post;
import domain.notifications.WallPostNotification;
import domain.services.NotificationService;
import domain.services.PostService;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("posts")
@Service
public class PostServiceRest{

    private NotificationService notificationService;
    private PostService postService;

    public PostServiceRest(){}

    public PostServiceRest(PostService postService, NotificationService notificationService) {
        this.postService = postService;
        this.notificationService = notificationService;
    }

    @POST
    @Path("post")
    @Produces("application/json")
    @Consumes("application/json")
    public Response createPost(Post post){
        Response response;
        try{
            postService.createPost(post);
            notificationService.create(new WallPostNotification(post.getWallOwner(), post.getPublisher()));
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
    public List posts(@PathParam("id") final Integer id){
        return postService.posts(id);
    }



}
