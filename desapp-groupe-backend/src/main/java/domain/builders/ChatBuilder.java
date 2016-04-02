package domain.builders;

import domain.Chat;
import domain.User;

public class ChatBuilder {
    private String name;
    private User withUser;
    private User withUser2;


    public static ChatBuilder aChat(){
        return new ChatBuilder();
    }

    public ChatBuilder(){
        this.name = "";
        this.withUser = null;
    }

    public Chat build(){
        return new Chat(name,withUser,withUser2);
    }

    public ChatBuilder withName(String name){
        this.name = name;
        return this;
    }

    public ChatBuilder withUser1(User user){
        this.withUser = user;
        return this;
    }

    public ChatBuilder withUser2(User user){
        this.withUser2 = user;
        return this;
    }
}