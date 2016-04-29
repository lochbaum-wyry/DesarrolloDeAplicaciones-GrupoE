package domain.repositories;

import domain.Ride;
import domain.Vehicle;
import org.hibernate.Query;

import java.util.List;

public class VehicleRepository extends HibernateGenericDao<Vehicle> implements
        GenericRepository<Vehicle> {

private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Vehicle> getDomainClass() {
        return Vehicle.class;
    }

    public List<Vehicle> getBestVehiclesInMonthYear(Integer month, Integer year,Integer cant)
    {
        String hql = " SELECT r.vehicle FROM " +  Ride.class.getName() +
                " AS r INNER JOIN r.vehicle v " +
                " WHERE   month(r.date) = :month AND year(r.date) = :year" +
                " ORDER BY (v.goodRateCount-v.badRateCount) DESC ";

        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("month",month);
        query.setParameter("year",year);
        query.setFirstResult(0);
        query.setMaxResults(cant);

        return query.list();
    }

    public List<Vehicle> getWorstVehiclesInMonthYear(Integer month, Integer year,Integer cant)
    {
        String hql = " SELECT r.vehicle FROM " +  Ride.class.getName() +
                " AS r INNER JOIN r.vehicle v " +
                " WHERE   month(r.date) = :month AND year(r.date) = :year" +
                " ORDER BY (v.goodRateCount-v.badRateCount) ASC ";

        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("month",month);
        query.setParameter("year",year);
        query.setFirstResult(0);
        query.setMaxResults(cant);

        return query.list();
    }
}
