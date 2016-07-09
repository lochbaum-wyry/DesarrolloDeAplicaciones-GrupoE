package domain.servicesRest;

import domain.Notification;
import domain.User;
import domain.services.NotificationService;
import domain.services.RideProposal;
import domain.services.RouteService;
import domain.services.UserService;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("notification")
@Service
public class NotificationServiceRest {

    private UserService userService;
    private NotificationService notificationService;


    public NotificationServiceRest(NotificationService notificationService, UserService userService)
    {
        this.userService = userService;
        this.notificationService = notificationService;
    }

    public NotificationServiceRest(){}


    @GET
    @Path("notificationsFor/{userId}")
    @Produces("application/json")
    public List<Notification> notificationsFor(@PathParam("userId") final Integer userId)
    {
        User user = userService.getUser(userId);
        return notificationService.notificationsFor(user);
    }


    @GET
    @Path("markSeen/{id}")
    @Produces("application/json")
    public Response markSeen(@PathParam("id") final Integer id)
    {

        Notification notification = notificationService.getNotification(id);
        notificationService.markSeen(notification);
        return Response.ok().tag("notification_seen").build();

    }

}
