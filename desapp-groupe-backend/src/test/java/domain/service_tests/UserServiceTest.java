package domain.service_tests;
import domain.User;
import domain.Vehicle;
import domain.exceptions.SingUpException;
import domain.repositories.UserRepository;
import domain.repositories.VehicleRepository;
import domain.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class UserServiceTest extends AbstractServiceTest{

    @Autowired
    public UserService userService;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public VehicleRepository vehicleRepository;


    @Test
    public void test_singUp() {

        try {
            userService.singUp("federico","lochbaum","trimegisto","federico.lochbaum@gmail.com");

        } catch (SingUpException e) {
            Assert.fail("Usuario o Email Existente");
        }

    }

    @Test
    public void test_addVehicleForUser(){

        User user = new User("federico","lochbaum","trimegisto","federico.lochbaum@gmail.com");

        userRepository.save(user);

        Vehicle vehicle = new Vehicle(2,4f);
        vehicleRepository.save(vehicle);

        userService.addVehicleForUser(user,vehicle);

        Vehicle vehicleReceived = userRepository.findById(user.getId()).getVehicle();

        Assert.assertEquals(vehicleReceived,vehicle);

    }

}
