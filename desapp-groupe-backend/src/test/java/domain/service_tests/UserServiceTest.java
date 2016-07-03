package domain.service_tests;
import domain.User;
import domain.Vehicle;
import domain.exceptions.SingUpException;
import domain.exceptions.SubiQueTeLlevoException;
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
            userService.signUp("federico","lochbaum","trimegisto","federico.lochbaum@gmail.com","unaImagen");

        } catch (SingUpException e) {
            Assert.fail("Usuario o Email Existente");
        }

    }

    @Test
    public void test_addVehicleForUser() throws SubiQueTeLlevoException {

        User user = new User("federico","lochbaum","trimegisto","federico.lochbaum@gmail.com");

        userRepository.save(user);

        Vehicle vehicle = new Vehicle(2,4f);
        vehicleRepository.save(vehicle);

        try {
        userService.addVehicleForUser(user,vehicle);
        } catch (SubiQueTeLlevoException e) { }

        Vehicle vehicleReceived = userRepository.findById(user.getId()).getVehicle();

        Assert.assertEquals(vehicleReceived,vehicle);

    }

}
