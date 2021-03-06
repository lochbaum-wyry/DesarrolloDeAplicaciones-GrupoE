package domain;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class Chat  extends Entity{
    private List<User> users;
    private String name;
    private List<Message> messages;

    public Chat() {}
    public Chat(String name,User user1,User user2){
        this.name = name;
        this.messages = new ArrayList<Message>();
        this.users = new ArrayList<User>();
        this.setUser(user1);
        this.setUser(user2);
    }

    public List<User> getUsers() {
        return users;
    }

    public String getName() {
        return name;
    }

    public void setUser(User user) {
        this.users.add(user);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(User user,String content){
        Message message = new Message(user,content,new DateTime());
        this.messages.add(message);
    }


    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}