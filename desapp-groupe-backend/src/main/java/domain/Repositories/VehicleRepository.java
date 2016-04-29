package domain.Repositories;

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
        String hql = "SELECT v FROM " + persistentClass.getName() +  " as v " +
                " INNER JOIN v.rides r " +
                " WHERE month(r.date) = :month and year(r.date) = :year" +
                " ORDER BY (v.goodRateCount - v.badRateCount) DESC";

        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("month",month);
        query.setParameter("year",year);
        query.setFirstResult(0);
        query.setMaxResults(cant);

        return query.list();
    }

    public List<Vehicle> getWorstVehiclesInMonthYear(Integer month, Integer year,Integer cant)
    {
        String hql = "SELECT v FROM " + persistentClass.getName() +  " as v " +
                " INNER JOIN v.rides r " +
                " WHERE month(r.date) = :month and year(r.date) = :year" +
                " ORDER BY (v.goodRateCount - v.badRateCount) ASC";

        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("month",month);
        query.setParameter("year",year);
        query.setFirstResult(0);
        query.setMaxResults(cant);

        return query.list();
    }
}
