package domain;

import domain.builders.MessageBuilder;
import domain.builders.UserBuilder;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MessageTest {

    @Test
    public void testmarkSeenBy(){
        User user = UserBuilder.aUser().withName("Mario").build();
        User user2 = UserBuilder.aUser().withName("Fede").build();

        Message message = MessageBuilder.aMessage().withSender(user).withContent("holaa").withDate(new DateTime()).build();

        message.markSeenBy(user2);

        Assert.assertTrue(message.getSeenBy().contains(user2));
    }

    @Test
    public void test_isSeenBy_aUserNoSeeMessage(){
        //aca el nombre podria cambiarse
        User fede = UserBuilder.aUser().withName("fede").build();
        User dan = UserBuilder.aUser().withName("dan").build();
        User sender = UserBuilder.aUser().withName("sender").build();
        User antonio = UserBuilder.aUser().withName("antonio").build();

        Message message = MessageBuilder.aMessage().withSender(sender).withContent("holaaa").withDate(new DateTime()).build();
        message.markSeenBy(fede);
        message.markSeenBy(dan);

        Assert.assertTrue(message.isSeenBy(fede));

        Assert.assertFalse(message.isSeenBy(antonio));
    }

    @Test
    public void test_getContent(){
        User sender = UserBuilder.aUser().withName("sender").build();
        Message message = MessageBuilder.aMessage().withSender(sender).withContent("holaaa").withDate(new DateTime()).build();
        Assert.assertEquals(message.getContent(),"holaaa");
    }

    @Test
    public void test_getSender(){
        User sender = UserBuilder.aUser().withName("sender").build();
        Message message = MessageBuilder.aMessage().withSender(sender).withContent("holaaa").withDate(new DateTime()).build();
        Assert.assertEquals(message.getSender(),sender);
    }

    @Test
    public void test_getTimestamp(){
        DateTime time = new DateTime();
        User sender = UserBuilder.aUser().withName("sender").build();
        Message message = MessageBuilder.aMessage().withSender(sender).withContent("holaaa").withDate(time).build();
        Assert.assertEquals(message.getTimestamp(),time);
    }

    @Test
    public void test_setAndGetContent(){
        Message message = MessageBuilder.aMessage().withContent("").build();

        Assert.assertEquals(message.getContent(),"");

        message.setContent("fede");

        Assert.assertEquals(message.getContent(),"fede");
    }

    @Test
    public void test_setAndGetSeenBy(){
        Message message = MessageBuilder.aMessage().withContent("").build();

        Assert.assertEquals(message.getSeenBy().size(),0);

        List<User> userList = new ArrayList<User>();
        userList.add(UserBuilder.aUser().build());
        message.setSeenBy(userList);

        Assert.assertEquals(message.getSeenBy().size(),1);
    }

    @Test
    public void test_setAndGetSender(){
        Message message = MessageBuilder.aMessage().withContent("").build();

        Assert.assertEquals(message.getSender(),null);

        User user = UserBuilder.aUser().build();
        message.setSender(user);

        Assert.assertEquals(message.getSender(),user);
    }

    @Test
    public void test_setAndGetTimesTamp(){
        DateTime dateTime = new DateTime(2,3,4,5,6);
        Message message = MessageBuilder.aMessage().withDate(dateTime).build();

        Assert.assertEquals(message.getTimestamp(),dateTime);

        DateTime dateTime2 = new DateTime(2,5,4,3,2);

        message.setTimestamp(dateTime2);

        Assert.assertEquals(message.getTimestamp(),dateTime2);
    }
}