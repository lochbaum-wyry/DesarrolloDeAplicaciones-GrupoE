package domain;

import domain.repositories.RideRepository;
import domain.repositories.UserRepository;
import domain.repositories.VehicleRepository;
import domain.builders.RideBuilder;
import domain.builders.RouteBuilder;
import domain.builders.UserBuilder;
import domain.builders.VehicleBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.lang.*;
import java.lang.System;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/spring-persistence-context.xml")
@Transactional
public class VehicleTest
{
    @Autowired
    private SessionFactory sessionFactory;
    private Session currentSession;
    @Before
    public void openSession() {
        currentSession = sessionFactory.getCurrentSession();
    }

    @Test
    public void test_getOilUsePerKmInLts_returnsOilUsePerKmsInLtsSet()
    {
        Float oilWastePerKm = 0.05f;
        Vehicle vehicle = new Vehicle(1, oilWastePerKm);

        Assert.assertEquals(oilWastePerKm,vehicle.getOilUsePerKmInLts());

    }

    @Test
    public void test_saveAndFindById()
    {

       ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-persistence-context.xml");
        BeanFactory factory = context;
        VehicleRepository vehicleRepository = (VehicleRepository) factory.getBean("persistence.vehiclerepository");

        Float oilWastePerKm = 0.05f;
        Vehicle vehicle = new Vehicle(1, oilWastePerKm);
        vehicleRepository.save(vehicle);

        Integer id =  vehicle.getId();

        Vehicle vehicle1 = vehicleRepository.findById(id);

        Assert.assertEquals(vehicle,vehicle1);
    }

    @Test
    public void test_update()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-persistence-context.xml");
        BeanFactory factory = context;
        VehicleRepository vehicleRepository = (VehicleRepository) factory.getBean("persistence.vehiclerepository");

        Float oilWastePerKm = 0.05f;
        Vehicle vehicle = new Vehicle(1, oilWastePerKm);
        vehicleRepository.save(vehicle);

        Integer id =  vehicle.getId();
        vehicle.setId(3);
        vehicleRepository.update(vehicle);

        Vehicle vehicle1 = vehicleRepository.findById(id);

        Assert.assertEquals(vehicle1.getId(),3);
    }

    @Test
    public void test_deleteById()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-persistence-context.xml");
        BeanFactory factory = context;
        VehicleRepository vehicleRepository = (VehicleRepository) factory.getBean("persistence.vehiclerepository");

        Float oilWastePerKm = 0.05f;
        Vehicle vehicle = new Vehicle(1, oilWastePerKm);
        vehicleRepository.save(vehicle);

        vehicleRepository.deleteById(vehicle.getId());


        Assert.assertEquals(vehicleRepository.count(),0);
    }

    @Test
    public void test_findAll()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-persistence-context.xml");
        BeanFactory factory = context;
        VehicleRepository vehicleRepository = (VehicleRepository) factory.getBean("persistence.vehiclerepository");

        Float oilWastePerKm = 0.05f;
        Vehicle vehicle = new Vehicle(1, oilWastePerKm);
        vehicleRepository.save(vehicle);
        Vehicle vehicle1 = new Vehicle(2, oilWastePerKm);
        vehicleRepository.save(vehicle1);
        Vehicle vehicle2 = new Vehicle(3, oilWastePerKm);
        vehicleRepository.save(vehicle2);

        Assert.assertEquals(vehicleRepository.findAll().size(),3);
    }


    @Test
    public void test_prueba_userRepository()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-persistence-context.xml");
        BeanFactory factory = context;
        UserRepository userRepository = (UserRepository) factory.getBean("persistence.userrepository");
        RideRepository rideRepository = (RideRepository) factory.getBean("persistence.riderepository");

        Vehicle vehicle = VehicleBuilder.aVehicle().withCapacity(2).withOilWasterPerHour(2f).build();
        User fede = UserBuilder.aUser().withName("fede").withVehicle(vehicle).build();
        User jorge = UserBuilder.aUser().withName("jorge").withVehicle(vehicle).build();
        User nadie = UserBuilder.aUser().withName("nadie").build();

        Route route = RouteBuilder.aRoute().build();

        fede.setGoodRateCount(20);
        jorge.setGoodRateCount(10);
        Ride ride = RideBuilder.aRide().withDriver(fede).withDate(new DateTime(2016,2,10,23,4)).withOilPrice(2f).withVehicle(vehicle).withRoute(route).build();

        rideRepository.save(ride);


        userRepository.save(fede);
        userRepository.save(jorge);
        userRepository.save(nadie);

        System.out.print(userRepository.getMostEfficientDriversInMonthYear(2,2016));
    }

}