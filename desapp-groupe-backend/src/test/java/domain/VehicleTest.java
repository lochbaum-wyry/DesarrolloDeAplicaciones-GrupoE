package domain;

import domain.builders.*;
import domain.repositories.RideRepository;
import domain.repositories.UserRepository;
import domain.repositories.VehicleRepository;
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
import java.util.ArrayList;
import java.util.List;


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
    public void test_setAndGetTotalRateCount(){
        Vehicle vehicle = VehicleBuilder.aVehicle().build();

        vehicle.setTotalRateCount(200);

        Assert.assertEquals(vehicle.getTotalRateCount().intValue(),200);
    }

    @Test
    public void test_setAndGetTotalBadRateCount(){
        Vehicle vehicle = VehicleBuilder.aVehicle().build();

        vehicle.setBadRateCount(200);

        Assert.assertEquals(vehicle.getBadRateCount().intValue(),200);
    }

    @Test
    public void test_setAndGetTotalGoodRateCount(){
        Vehicle vehicle = VehicleBuilder.aVehicle().build();

        vehicle.setGoodRateCount(200);

        Assert.assertEquals(vehicle.getGoodRateCount().intValue(),200);
    }

    @Test
    public void test_updateRateCounters(){
        Vehicle vehicle = VehicleBuilder.aVehicle().build();

        vehicle.updateRateCounters(RateBuilder.aRate().withRateValue(RateValue.GOOD).build());

        Assert.assertEquals(vehicle.getGoodRateCount().intValue(),1);
        Assert.assertEquals(vehicle.getBadRateCount().intValue(),0);

        vehicle.updateRateCounters(RateBuilder.aRate().withRateValue(RateValue.BAD).build());

        Assert.assertEquals(vehicle.getBadRateCount().intValue(),1);

    }

    @Test
    public void test_setAndGetRates(){
        Vehicle vehicle = VehicleBuilder.aVehicle().build();

        List<Rate> rates = new ArrayList<Rate>();
        rates.add(RateBuilder.aRate().build());
        vehicle.setRates(rates);

        Assert.assertEquals(vehicle.getRates(),rates);
    }

    @Test
    public void test_setAndGetOwner(){
        Vehicle vehicle = VehicleBuilder.aVehicle().build();

        User user = UserBuilder.aUser().build();
        vehicle.setOwner(user);

        Assert.assertEquals(vehicle.getOwner(),user);
    }

    @Test
    public void test_setAndGetCapacity(){
        Vehicle vehicle = VehicleBuilder.aVehicle().build();

        vehicle.setCapacity(20);

        Assert.assertEquals(vehicle.getCapacity().intValue(),20);
    }

    @Test
    public void test_setAndGetOilWastePerKm(){
        Vehicle vehicle = VehicleBuilder.aVehicle().build();

        vehicle.setOilWastePerKm(24f);

        Assert.assertEquals(vehicle.getOilWastePerKm().intValue(),24);
    }
}