package domain.services;


import domain.*;
import domain.exceptions.RatingException;
import domain.exceptions.SubiQueTeLlevoException;
import domain.repositories.RateRepository;
import domain.repositories.RideRepository;
import domain.repositories.UserRepository;
import domain.Rate;
import domain.RateType;
import domain.RateValue;
import domain.repositories.VehicleRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class RatingService
{
    RateRepository rateRepository ;
    UserRepository userRepository ;
    RideRepository rideRepository;
    VehicleRepository vehicleRepository;

    public RatingService(){}

    public RatingService(final UserRepository userRepository, final RateRepository rateRepository,final VehicleRepository vehicleRepository,RideRepository rideRepository)
    {
        this.userRepository = userRepository;
        this.rateRepository = rateRepository;
        this.rideRepository = rideRepository;
        this.vehicleRepository = vehicleRepository;
    }

//    public void getRatesForUserBetweenDates(User user, DateTime from, DateTime to)
//    {
//        DetachedCriteria criteria = DetachedCriteria.forClass(Rate.class);
//        rateRepository.getHibernateTemplate().findByCriteria(criteria);
//    }
//
//    public void getRatesForUserOfTypeBetweenDates(User user, RateType rateType, DateTime from, DateTime to)
//    {
//        DetachedCriteria criteria = DetachedCriteria.forClass(Rate.class);
//        rateRepository.getHibernateTemplate().findByCriteria(criteria);
//    }

    @Transactional
    public void rate(Rate rate) throws RatingException {
        validateRate(rate);
        rateRepository.save(rate);
        rate = rateRepository.findById(rate.getId());

        updateRateCounters(rate);
    }

    @Transactional
    private void updateRateCounters(Rate rate) {
        switch (rate.getRateType()) {
            case CarState:
                Vehicle vehicle = rate.getVehicle();
                vehicle.updateRateCounters(rate);
                vehicleRepository.update(vehicle);
            break;
            default:
                User ratedUser = rate.getRatedUser();
                ratedUser.updateRateCounters(rate);
                userRepository.update(ratedUser);

                break;
        }
    }

    private void validateRate(Rate rate) throws RatingException {
        validateNoSelfRating(rate);
        validateNotRatingTwice(rate);
    }

    private void validateNoSelfRating(Rate rate) throws RatingException {
        if (rate.getRatedUser().getId() == rate.getRater().getId())
            throw new RatingException("cant_rate_yourself");
    }

    private void validateNotRatingTwice(Rate rate) throws RatingException {
        if (rateRepository.similarRateExists(rate))
            throw new RatingException("cant_rate_twice");
    }

    @Transactional
    public List<Rate> getRates(Integer userId) {
        User user = userRepository.findById(userId);
        return rateRepository.getRatesForUser(user);
    }
}
