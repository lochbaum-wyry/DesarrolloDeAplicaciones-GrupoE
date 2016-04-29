package domain.service_tests;

import domain.User;
import domain.builders.UserBuilder;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by prospero on 4/29/16.
 */
public class ChatServiceTest {


//
//    @Test
//    public void test_sendMessage_newChatIsCreatedWhenUsersMessageForTheFirstTime()
//    {
//        User user = UserBuilder.aUser().build();
//        User otherUser = UserBuilder.aUser().build();
//
//        Assert.assertTrue(user.getChats().isEmpty());
//
//        user.sendMessage(otherUser, "como estas cabezon???");
//
//        Assert.assertFalse(user.getChats().isEmpty());
//    }
//
//    @Test
//    public void test_sendMessage_newChatIsAddedToTheOtherUserWhenUsersMessageForTheFirstTime()
//    {
//        User user = UserBuilder.aUser().build();
//        User otherUser = UserBuilder.aUser().build();
//
//        Assert.assertTrue(otherUser.getChats().isEmpty());
//
//        user.sendMessage(otherUser, "como estas cabezon???");
//
//        Assert.assertFalse(otherUser.getChats().isEmpty());
//    }
//
//    @Test
//    public void test_sendMessage_onlyOneChatPerUserIsCreatedOnEachUser()
//    {
//        User user = UserBuilder.aUser().build();
//        User otherUser = UserBuilder.aUser().build();
//
//        user.sendMessage(otherUser, "como estas cabezon???");
//        user.sendMessage(otherUser, "todo bien???");
//
//        Assert.assertEquals(1, this.numberOfChatsWithUser(user,otherUser));
//    }
//
//    public long numberOfChatsWithUser(User user, User searchedUser)
//    {
//        return user.getChats().stream()
//                .filter(p -> p.getUsers().contains(searchedUser))
//                .count();
//    }
}
