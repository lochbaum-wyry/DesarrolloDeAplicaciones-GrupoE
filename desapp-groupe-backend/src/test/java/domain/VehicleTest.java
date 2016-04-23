package domain;

import domain.Repositories.VehicleRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;


public class VehicleTest
{

    @Test
    public void test_getOilUsePerKmInLts_returnsOilUsePerKmsInLtsSet()
    {
        Float oilWastePerKm = 0.05f;
        Vehicle vehicle = new Vehicle(1, oilWastePerKm);

        Assert.assertEquals(oilWastePerKm,vehicle.getOilUsePerKmInLts());

    }

    @Test
    public void test_save()
    {

       ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-persistence-context.xml");
        BeanFactory factory = context;
        VehicleRepository vehicleRepository = (VehicleRepository) factory.getBean("persistence.vehiclerepository");

        Float oilWastePerKm = 0.05f;
        Vehicle vehicle = new Vehicle(1, oilWastePerKm);
        vehicleRepository.save(vehicle);
//        Integer id =  vehicle.getId();
//
//        Vehicle vehicle1 = vehicleRepository.findById(id);
//
//        Assert.assertEquals(vehicle,vehicle1);
    }
}