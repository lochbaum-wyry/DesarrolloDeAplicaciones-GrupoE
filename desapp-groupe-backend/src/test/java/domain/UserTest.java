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


}