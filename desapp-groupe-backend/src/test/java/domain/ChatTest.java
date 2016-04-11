package domain;

import domain.builders.ChatBuilder;
import domain.builders.UserBuilder;
import org.junit.Assert;
import org.junit.Test;

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



}