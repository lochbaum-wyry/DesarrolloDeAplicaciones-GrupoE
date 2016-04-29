package domain.services;


import domain.*;
import domain.repositories.RateRepository;
import domain.repositories.UserRepository;
import domain.Rate;
import domain.RateType;
import domain.RateValue;


public class RatingService
{
    RateRepository rateRepository ;
    UserRepository userRepository ;

    public RatingService(final UserRepository userRepository, final RateRepository rateRepository)
    {
        this.userRepository = userRepository;
        this.rateRepository = rateRepository;
    }

    public void rateDriverOfRide(User rater, Ride ride, RateValue qualification, String comment)
    {
        User ratedUser = ride.getDriver();
        Rate rate = new Rate(rater, ratedUser, ride, RateType.Driving, qualification, comment);
        addRate(rater,rate);
    }

    public void rateVehicleOfRide(User rater, Ride ride, RateValue qualification, String comment)
    {
        User ratedUser = ride.getDriver();
        Rate rate = new Rate(rater, ratedUser, ride, RateType.CarState, qualification, comment);
        rate.setVehicle(ride.getVehicle());

        rateRepository.save(rate);
        ride.getVehicle().updateRateCounters(rate);
    }

    public void ratePassengerOfRide(User rater, User ratedUser, Ride ride, RateValue qualification, String comment)
    {
        Rate rate = new Rate(rater, ratedUser, ride, RateType.Accompany, qualification, comment);

        addRate(rater,rate);
    }

    public void addRate(User rater, Rate rate)
    {
        rateRepository.save(rate);
        rater.updateRateCounters(rate) ;
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


}
