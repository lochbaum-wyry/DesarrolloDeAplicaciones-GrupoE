package domain.repositories;

import domain.Rate;
import domain.Ride;
import domain.User;
import domain.Vehicle;
import org.hibernate.Query;

public class RateRepository extends HibernateGenericDao<Rate> implements
        GenericRepository<Rate> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Rate> getDomainClass() {
        return Rate.class;
    }

    public Rate findRateUserByRaterInRide(User rater, User ratedUser, Ride ride){

        String hql = "SELECT r FROM " + Rate.class.getName() + " r " +
                "WHERE r.rater = :rater AND r.ride =:ride AND r.ratedUser =:ratedUser";

        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setEntity("rater",rater);
        query.setEntity("ride",ride);
        query.setEntity("ratedUser",ratedUser);

        Rate rate = (Rate) query.uniqueResult();

        return rate;
    }

    public Rate findRateVehicleByRaterInRide(User rater, Vehicle ratedVehicle, Ride ride){
        String hql = "SELECT r FROM " + Rate.class.getName() + " r " +
                "WHERE r.rater = :rater AND r.ride =:ride AND r.vehicle =:vehicle";

        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setEntity("rater",rater);
        query.setEntity("ride",ride);
        query.setEntity("vehicle",ratedVehicle);

        Rate rate = (Rate) query.uniqueResult();

        return rate;
    }
}
