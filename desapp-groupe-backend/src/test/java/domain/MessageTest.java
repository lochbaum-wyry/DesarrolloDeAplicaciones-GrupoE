package domain;

import domain.builders.MessageBuilder;
import domain.builders.UserBuilder;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

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
}
