package domain.repositories;

import domain.*;
import org.hibernate.Query;

import java.util.List;

public class RateRepository extends HibernateGenericDao<Rate> implements
        GenericRepository<Rate> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Rate> getDomainClass() {
        return Rate.class;
    }

    public Rate findRateUserByRaterInRide(User rater, User ratedUser, Ride ride,RateType rateType){

        String hql = "SELECT r FROM " + Rate.class.getName() + " r " +
                "WHERE r.rater = :rater AND r.ride =:ride AND r.ratedUser =:ratedUser AND r.rateType =:rateType";

        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setEntity("rater",rater);
        query.setEntity("ride",ride);
        query.setParameter("rateType",rateType);
        query.setEntity("ratedUser",ratedUser);

        Rate rate = (Rate) query.uniqueResult();

        return rate;
    }

    public Rate findRateVehicleByRaterInRide(User rater, Vehicle ratedVehicle, Ride ride){
        String hql = "SELECT r FROM " + Rate.class.getName() + " r " +
                "WHERE r.rater = :rater AND r.ride =:ride AND r.vehicle =:vehicle AND r.rateType =:carType";

        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setEntity("rater",rater);
        query.setEntity("ride",ride);
        query.setParameter("carType",RateType.CarState);
        query.setEntity("vehicle",ratedVehicle);

        Rate rate = (Rate) query.uniqueResult();

        return rate;
    }

    public boolean similarRateExists(Rate rate) {
        String hql =    "SELECT r " +
                        "FROM Rate r " +
                        "WHERE " +
                    "r.rater = :rater " +
                "AND r.ride =:ride " +
                "AND r.ratedUser = :ratedUser " +
                "AND r.rateType = :type ";

        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);

        query.setEntity("rater",rate.getRater());
        query.setEntity("ratedUser",rate.getRatedUser());
        query.setEntity("ride",rate.getRide());
        query.setParameter("type", rate.getRateType());

        return query.uniqueResult() != null ;
    }

    public List<Rate> getRatesForUser(User user) {
        String hql = "SELECT r FROM " + Rate.class.getName() + " r " +
                "WHERE r.ratedUser = :user";

        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setEntity("user",user);

        return query.list();
    }
}
