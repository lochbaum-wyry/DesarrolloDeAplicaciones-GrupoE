package domain;

import domain.Message;
import domain.User;
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
}
