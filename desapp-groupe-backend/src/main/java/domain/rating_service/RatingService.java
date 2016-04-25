package domain.rating_service;


import domain.*;
import domain.Repositories.RateRepository;
import domain.Repositories.UserRepository;


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
        addRate(rate);
    }

    public void rateVehicleOfRide(User rater, Ride ride, RateValue qualification, String comment)
    {
        User ratedUser = ride.getDriver();
        Rate rate = new Rate(rater, ratedUser, ride, RateType.CarState, qualification, comment);
        rate.setVehicle(ride.getVehicle());

        addRate(rate);
    }

    public void ratePassengerOfRide(User rater, User ratedUser, Ride ride, RateValue qualification, String comment)
    {
        Rate rate = new Rate(rater, ratedUser, ride, RateType.Accompany, qualification, comment);
        addRate(rate);
    }

    public void addRate(Rate rate)
    {
        rateRepository.save(rate);
        rate.getRatedUser().updateRateCounters(rate) ;
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
