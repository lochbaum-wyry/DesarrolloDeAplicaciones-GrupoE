package domain.services;


import domain.*;
import domain.exceptions.RatingException;
import domain.repositories.RateRepository;
import domain.repositories.UserRepository;
import domain.Rate;
import domain.RateType;
import domain.RateValue;
import org.springframework.transaction.annotation.Transactional;


public class RatingService
{
    RateRepository rateRepository ;
    UserRepository userRepository ;

    public RatingService(){}

    public RatingService(final UserRepository userRepository, final RateRepository rateRepository)
    {
        this.userRepository = userRepository;
        this.rateRepository = rateRepository;
    }

    @Transactional
    public void rateDriverOfRide(User rater, Ride ride, RateValue qualification, String comment) throws RatingException {
        User raterUser = userRepository.findById(rater.getId());
        User ratedUser = userRepository.findById(ride.getDriver().getId());
        validateRateUser(raterUser,ratedUser,ride);
        Rate rate = new Rate(raterUser, ratedUser, ride, RateType.Driving, qualification, comment);
        addRate(raterUser,rate);
    }

    private void validateRateUser(User rater,User user,Ride ride) throws RatingException {
        validateNoSelfRate(rater,user);
        validateNoRateTwiceUser(rater,user,ride);
    }
    @Transactional
    private void validateNoRateTwiceUser(User rater, User ratedUser, Ride ride) throws RatingException {
        Rate rate = rateRepository.findRateUserByRaterInRide(rater,ratedUser,ride);
        if(rate !=null){
            throw new RatingException();
        }
    }

    private void validateNoSelfRate(User rater, User driver) throws RatingException {
        if(rater.equals(driver)){
            throw new RatingException();
        }
    }

    @Transactional
    public void rateVehicleOfRide(User rater, Ride ride, RateValue qualification, String comment) throws RatingException {
        User ratedUser = ride.getDriver();
        validateRateVehicle(rater,ride.getVehicle(),ride);
        Rate rate = new Rate(rater, ratedUser, ride, RateType.CarState, qualification, comment);
        rate.setVehicle(ride.getVehicle());

        rateRepository.save(rate);
        ride.getVehicle().updateRateCounters(rate);
    }

    private void validateRateVehicle(User rater, Vehicle ratedVehicle, Ride ride) throws RatingException {
        validateNoMyVehicle(rater,ratedVehicle);
        validateNoRateTwiceVehicle(rater,ratedVehicle,ride);
    }
    @Transactional
    private void validateNoRateTwiceVehicle(User rater, Vehicle ratedVehicle, Ride ride) throws RatingException {
        Rate rate = rateRepository.findRateVehicleByRaterInRide(rater,ratedVehicle,ride);
        if(rate !=null){
            throw new RatingException();
        }
    }

    private void validateNoMyVehicle(User rater, Vehicle ratedVehicle) throws RatingException {
        if(rater.getVehicle()==(ratedVehicle)){
            throw new RatingException();
        }
    }
    @Transactional
    public void ratePassengerOfRide(User rater, User ratedUser, Ride ride, RateValue qualification, String comment) throws RatingException {
        validateRateUser(rater,ratedUser,ride);
        Rate rate = new Rate(rater, ratedUser, ride, RateType.Accompany, qualification, comment);

        addRate(rater,rate);
    }

    @Transactional
    public void addRate(User rater, Rate rate)
    {
        rateRepository.save(rate);
        rater.updateRateCounters(rate) ;
        userRepository.update(rater);
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
