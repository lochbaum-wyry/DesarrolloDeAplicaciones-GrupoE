package domain.service_tests;
import domain.User;
import domain.Vehicle;
import domain.exceptions.SubiQueTeLlevoException;
import domain.repositories.UserRepository;
import domain.repositories.VehicleRepository;
import domain.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/spring-persistence-context.xml")
@Transactional
public class UserServiceTest {

    @Test
    public void test_singUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-persistence-context.xml");
        BeanFactory factory = context;

        UserRepository userRepository = (UserRepository) factory.getBean("persistence.userrepository");

        UserService userService= new UserService(userRepository);

        try {
            userService.singUp("federico","lochbaum","trimegisto","federico.lochbaum@gmail.com");

        } catch (SubiQueTeLlevoException e) {
            Assert.fail("Usuario o Email Existente");
        }

    }

    @Test
    public void test_addVehicleForUser(){
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-persistence-context.xml");
        BeanFactory factory = context;

        UserRepository userRepository = (UserRepository) factory.getBean("persistence.userrepository");
        VehicleRepository vehicleRepository = (VehicleRepository) factory.getBean("persistence.vehiclerepository");

        UserService userService= new UserService(userRepository);

        User user = new User("federico","lochbaum","trimegisto","federico.lochbaum@gmail.com");

        userRepository.save(user);

        Vehicle vehicle = new Vehicle(2,4f);
        vehicleRepository.save(vehicle);

        userService.addVehicleForUser(user,vehicle);

        //Vehicle vehicleReceived = userRepository.findById(user.getId()).getVehicle();

        //Assert.assertEquals(vehicleReceived,vehicle);

    }

}
