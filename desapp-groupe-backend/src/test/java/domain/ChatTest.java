package domain;

import domain.builders.ChatBuilder;
import domain.builders.MessageBuilder;
import domain.builders.UserBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ChatTest {


    @Test
    public void testAddMessageInChat(){


        User user = UserBuilder.aUser().build();
        User user2 = UserBuilder.aUser().build();

        Chat chat = ChatBuilder.aChat().withName("Sara").withUser1(user).withUser2(user2).build();

        String content = "aMessage";

        chat.addMessage(user,content);

        Integer expected = 1;
        Integer received = chat.getMessages().size();

        Assert.assertEquals(received,expected);
    }

    @Test
    public void test_getName(){
        Chat chat = ChatBuilder.aChat().withName("Sara").build();
        Assert.assertEquals(chat.getName(),"Sara");
    }

    @Test
    public void test_setAndGetName(){
        Chat chat = ChatBuilder.aChat().build();

        Assert.assertEquals(chat.getName(),"");

        chat.setName("hola");

        Assert.assertEquals(chat.getName(),"hola");
    }

    @Test
    public void test_setAndGetUsers(){
        Chat chat = ChatBuilder.aChat().build();

        Assert.assertEquals(chat.getUsers().size(),2);

        List<User> userList = new ArrayList<User>();
        userList.add(UserBuilder.aUser().build());
        chat.setUsers(userList);

        Assert.assertEquals(chat.getUsers().size(),1);
    }

    @Test
    public void test_setAndGetMessages(){
        Chat chat = ChatBuilder.aChat().build();

        Assert.assertEquals(chat.getMessages().size(),0);

        List<Message> messageList = new ArrayList<Message>();
        messageList.add(MessageBuilder.aMessage().build());
        chat.setMessages(messageList);

        Assert.assertEquals(chat.getMessages().size(),1);
    }


}