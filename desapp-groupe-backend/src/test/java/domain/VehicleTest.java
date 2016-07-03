package domain;

import domain.builders.*;
import org.junit.Assert;
import org.junit.Test;

import java.lang.*;
import java.util.ArrayList;
import java.util.List;


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/META-INF/spring-persistence-context.xml")
//@Transactional
public class VehicleTest
{
//    @Autowired
//    private SessionFactory sessionFactory;
//    private Session currentSession;
//    @Before
//    public void openSession() {
//        currentSession = sessionFactory.getCurrentSession();
//    }

    @Test
    public void test_getOilUsePerKmInLts_returnsOilUsePerKmsInLtsSet()
    {
        Float oilWastePerKm = 0.05f;
        Vehicle vehicle = new Vehicle(1, oilWastePerKm);

        Assert.assertEquals(oilWastePerKm,vehicle.getOilWastePerKmInLts());

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

}