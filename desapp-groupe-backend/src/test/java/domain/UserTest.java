package domain;

import domain.builders.*;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class UserTest extends AbstractDomainTest
{

    @Test
    public void test_sendMessage_newChatIsCreatedWhenUsersMessageForTheFirstTime()
    {
        User user = UserBuilder.aUser().build();
        User otherUser = UserBuilder.aUser().build();

        Assert.assertTrue(user.getChats().isEmpty());

        user.sendMessage(otherUser, "como estas cabezon???");

        Assert.assertFalse(user.getChats().isEmpty());
    }

    @Test
    public void test_sendMessage_newChatIsAddedToTheOtherUserWhenUsersMessageForTheFirstTime()
    {
        User user = UserBuilder.aUser().build();
        User otherUser = UserBuilder.aUser().build();

        Assert.assertTrue(otherUser.getChats().isEmpty());

        user.sendMessage(otherUser, "como estas cabezon???");

        Assert.assertFalse(otherUser.getChats().isEmpty());
    }

    @Test
    public void test_sendMessage_onlyOneChatPerUserIsCreatedOnEachUser()
    {
        User user = UserBuilder.aUser().build();
        User otherUser = UserBuilder.aUser().build();

        user.sendMessage(otherUser, "como estas cabezon???");
        user.sendMessage(otherUser, "todo bien???");

        Assert.assertEquals(1, this.numberOfChatsWithUser(user,otherUser));
    }


    @Test
    public void test_isDriver_ReturnTrueIfaUserHaveAvehicle(){

        Vehicle vehicle = VehicleBuilder.aVehicle().withCapacity(23).withOilWasterPerHour(23.0f).build();

        User user = UserBuilder.aUser()
                .withName("fede")
                .withUserName("trimegisto")
                .withLastName("lochbaum")
                .withEmail("EmailBuilder.aEmail.build()")
                .withVehicle(vehicle)
                .build();

        Assert.assertTrue(user.hasVehicle());
    }

    @Test
    public void test_isPassenger_ReturnTrueIfaUserNotHaveAvehicle(){

        User user = UserBuilder.aUser()
                .withName("fede")
                .withUserName("trimegisto")
                .withLastName("lochbaum")
                .withEmail("EmailBuilder.aEmail.build()")
                .build();

        Assert.assertTrue(user.isPassenger());
    }

    public long numberOfChatsWithUser(User user, User searchedUser)
    {
        return user.getChats().stream()
                .filter(p -> p.getUsers().contains(searchedUser))
                .count();
    }

}