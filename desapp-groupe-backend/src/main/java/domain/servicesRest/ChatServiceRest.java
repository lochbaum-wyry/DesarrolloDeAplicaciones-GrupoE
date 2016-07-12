package domain.servicesRest;

import domain.Chat;
import domain.Notification;
import domain.User;
import domain.notifications.InconmingMessageNotification;
import domain.services.ChatService;
import domain.services.NotificationService;
import domain.services.UserService;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("chat")
@Service
public class ChatServiceRest {
    private UserService userService;
    private ChatService chatService;
    private NotificationService notificationService ;

    public ChatServiceRest(){}

    public ChatServiceRest(ChatService chatService, UserService userService , NotificationService notificationService)
    {
        this.chatService = chatService;
        this.userService = userService;
        this.notificationService = notificationService ;
    }

    public ChatService getChatService() {
        return chatService;
    }

    public void setChatService(ChatService chatService) {
        this.chatService = chatService;
    }


    @GET
    @Path("sendMessage/{chatId}/{senderId}/{string}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response sendMessage(@PathParam("senderId") final Integer senderId,@PathParam("chatId") final Integer chatId,@PathParam("string") final String content) {
        Response response;
        try{
            chatService.sendMessage(senderId,chatId,content);

            User sender = userService.getUserRepository().findById(senderId);
            Chat chat= chatService.getChatRepository().findById(chatId);

            for (User receiver : chat.getUsers() ) {
                if (receiver.getId() != senderId)
                    notificationService.create(new InconmingMessageNotification(receiver, sender)) ;
            }
            response = Response.ok().build();
        }
        catch (Exception e ){
            e.getMessage();
            response = Response.serverError().build();
        }
        return response;
    }

    @GET
    @Path("getChatByUser/{userId}/{userChatId}")
    @Produces("application/json")
    @Consumes("application/json")
    public Chat getChatByUser(@PathParam("userId") final Integer userId,@PathParam("userChatId") final Integer userChatId)
    {
        return chatService.getChatByUser(userId,userChatId);
    }



}
