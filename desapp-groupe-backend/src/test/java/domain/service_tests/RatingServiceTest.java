package domain.service_tests;
import domain.*;
import domain.builders.RateBuilder;
import domain.builders.RideBuilder;
import domain.builders.UserBuilder;
import domain.builders.VehicleBuilder;
import domain.exceptions.RatingException;
import domain.repositories.RateRepository;
import domain.repositories.RideRepository;
import domain.repositories.UserRepository;
import domain.services.RatingService;
import org.junit.Assert;
import org.junit.Test;

import org.junit.validator.ValidateWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class RatingServiceTest extends AbstractServiceTest{

    @Autowired
    public RatingService ratingService;

    @Autowired
    public RateRepository rateRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public RideRepository rideRepository;



    @Test
    public void test_rateDriverOfRide() throws RatingException {
        User rater = UserBuilder.aUser().build();
        User driver = UserBuilder.aUser().build();
        userRepository.save(rater);
        userRepository.save(driver);


        Ride ride = RideBuilder.aRide().withDriver(driver).build();
        rideRepository.save(ride);

        ratingService.rateDriverOfRide(rater,ride, RateValue.GOOD,"un capo este pibe");

        List<Rate> ratesDriving = rateRepository.findAll().stream().filter(rate -> rate.getRateType().equals(RateType.Driving)).collect(Collectors.toList());

        Assert.assertTrue(ratesDriving.stream().anyMatch(rate -> rate.getRatedUser().equals(driver)));
    }

    @Test
    public void test_rateVehicleOfRide() throws RatingException {
        User rater = UserBuilder.aUser().build();
        User driver = UserBuilder.aUser().build();
        userRepository.save(rater);
        userRepository.save(driver);

        Vehicle vehicle = VehicleBuilder.aVehicle().build();
        vehicle.setOwner(driver);

        Ride ride = RideBuilder.aRide().withDriver(driver).withVehicle(vehicle).build();
        rideRepository.save(ride);

        ratingService.rateVehicleOfRide(rater,ride, RateValue.GOOD,"un capo este pibe");

        List<Rate> ratesCarState = rateRepository.findAll().stream().filter(rate -> rate.getRateType().equals(RateType.CarState)).collect(Collectors.toList());

        Assert.assertTrue(ratesCarState.stream().anyMatch(rate -> rate.getVehicle().equals(vehicle)));
    }

    @Test
    public void test_ratePassengerOfRide() throws RatingException {
        User rater = UserBuilder.aUser().build();
        User rateado = UserBuilder.aUser().build();
        userRepository.save(rater);
        userRepository.save(rateado);


        User driver = UserBuilder.aUser().build();

        Ride ride = RideBuilder.aRide().withDriver(driver).build();
        rideRepository.save(ride);

        ratingService.ratePassengerOfRide(rater,rateado,ride,RateValue.BAD,"apesta");

        List<Rate> ratesAccompany = rateRepository.findAll().stream().filter(rate -> rate.getRateType().equals(RateType.Accompany)).collect(Collectors.toList());

        Assert.assertTrue(ratesAccompany.stream().anyMatch(rate -> rate.getRatedUser().equals(rateado)));
    }

    @Test
    public void test_addRate(){

        User rateado = UserBuilder.aUser().build();

        Rate rate = RateBuilder.aRate().withRateValue(RateValue.GOOD).withUser(rateado).build();

        ratingService.addRate(rateado,rate);

        Assert.assertEquals(rateado.getGoodRateCount().intValue(),1);
        Assert.assertEquals(rateRepository.count(),1);
    }

}
