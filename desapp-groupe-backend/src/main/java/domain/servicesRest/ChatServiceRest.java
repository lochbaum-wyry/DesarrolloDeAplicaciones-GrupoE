package domain.servicesRest;

import domain.Chat;
import domain.Message;
import domain.Post;
import domain.User;
import domain.services.ChatService;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("chat")
@Service
public class ChatServiceRest {
    private ChatService chatService;

    public ChatServiceRest(){}

    public ChatServiceRest(ChatService chatService){
        this.chatService = chatService;
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
    public Chat getChatByUser(@PathParam("userId") final Integer userId,@PathParam("userChatId") final Integer userChatId){
        return chatService.getChatByUser(userId,userChatId);
    }



}
