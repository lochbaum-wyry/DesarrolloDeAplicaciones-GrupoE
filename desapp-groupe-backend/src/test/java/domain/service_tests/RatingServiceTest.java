package domain.service_tests;
import domain.*;
import domain.builders.RateBuilder;
import domain.builders.RideBuilder;
import domain.builders.UserBuilder;
import domain.builders.VehicleBuilder;
import domain.exceptions.RatingException;
import domain.exceptions.SubiQueTeLlevoException;
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

        Rate rate = new Rate(rater, driver, ride, RateType.Driving, RateValue.GOOD, "un capo el pibe");

        ratingService.rate(rate);

        List<Rate> ratesDriving = rateRepository.findAll().stream().filter(r -> r.getRateType().equals(RateType.Driving)).collect(Collectors.toList());

        Assert.assertTrue(ratesDriving.stream().anyMatch(r -> r.getRatedUser().equals(driver)));
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

        Rate rate = new Rate(rater, driver, ride, RateType.CarState, RateValue.GOOD, "un capo el pibe");
        rate.setVehicle(vehicle);
        ratingService.rate(rate);

        List<Rate> ratesCarState = rateRepository.findAll().stream().filter(r -> rate.getRateType().equals(RateType.CarState)).collect(Collectors.toList());

        Assert.assertTrue(ratesCarState.stream().anyMatch(r -> r.getVehicle().equals(vehicle)));
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

        Rate rate = new Rate(rater, rateado, ride, RateType.Accompany, RateValue.GOOD, "un capo el pibe");
        rate.setVehicle(driver.getVehicle());

        ratingService.rate(rate);


        List<Rate> ratesAccompany = rateRepository.findAll().stream().filter(r -> r.getRateType().equals(RateType.Accompany)).collect(Collectors.toList());

        Assert.assertTrue(ratesAccompany.stream().anyMatch(r -> r.getRatedUser().equals(rateado)));
    }

}
