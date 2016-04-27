package domain.Repositories;

import domain.User;
import domain.gaming_service.ranking.MonthlyRanking;
import org.hibernate.Query;

import java.util.List;

public class UserRepository extends HibernateGenericDao<User> implements
        GenericRepository<User> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<User> getDomainClass() {
        return User.class;
    }

    public List<User> getBestDriversInMonthYear(Integer month, Integer year,Integer cant)
    {
        String hql = "SELECT u FROM " + persistentClass.getName() +  " as u " +
                " INNER JOIN u.rides r " +
                " WHERE month(r.date) = :month and year(r.date) = :year" +
                " ORDER BY (u.goodRateCount - u.badRateCount) DESC";

        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("month",month);
        query.setParameter("year",year);
        query.setFirstResult(0);
        query.setMaxResults(cant);

        return query.list();
    }

    public List<User> getWorstDriversInMonthYear(Integer month, Integer year,Integer cant)
    {
        String hql = "SELECT u FROM " + persistentClass.getName() +  " as u " +
                " INNER JOIN u.rides r " +
                " WHERE month(r.date) = :month and year(r.date) = :year" +
                " ORDER BY (u.goodRateCount - u.badRateCount) ASC";

        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("month",month);
        query.setParameter("year",year);
        query.setFirstResult(0);
        query.setMaxResults(cant);

        return query.list();
    }

    public List<User> getBestPassengersInMonthYear(Integer month, Integer year,Integer cant)
    {
        String hql = "SELECT distinct u FROM TakenSeat ts " +
                " INNER JOIN ts.passenger u " +
                " INNER JOIN ts.ride r  " +
                " WHERE month(r.date) = :month and year(r.date) = :year" +
                " ORDER BY (u.goodRateCount - u.badRateCount) DESC";

        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("month",month);
        query.setParameter("year",year);
        query.setFirstResult(0);
        query.setMaxResults(cant);

        return query.list();

    }

    public List<User> getWorstPassengersInMonthYear(Integer month, Integer year,Integer cant)
    {
        return null;
    }

    public List<User> getMostEfficientDriversInMonthYear(Integer month, Integer year)
    {
        return null;
    }


    /* ALGORITMOS DE COMPARACIÓN :

    RANKING RATE COUNT : {
        int totalO1 = o1.getGoodRateCount() - o1.getBadRateCount();
        int totalO2 = o2.getGoodRateCount() - o2.getBadRateCount();
        int valor = totalO1 > totalO2 ? -1 : 1;
        return Math.multiplyExact(valor,getOrder().getValue());
    }

    RANKING RATE COUNT(User user, User t1) :
    {
        int totalO1 = o1.getGoodRateCount() - o1.getBadRateCount();
        int totalO2 = o2.getGoodRateCount() - o2.getBadRateCount();
        int valor = totalO1 > totalO2 ? -1 : 1;
        return Math.multiplyExact(valor,getOrder().getValue());
    }

    RANKING EFFICIENCY(User user, User t1)
    {
        Integer valor = (getEfficiencyAvgForUser(user) > getEfficiencyAvgForUser(t1)) ? 1 : -1;
        return valor * this.getOrder().getValue();
    }

    public Float getEfficiencyAvgForUser(User user)
    {
        if (user.getRidesCount() == 0 )
            return 0f ;
        Float effSumUser = user.getRidesAsDriver().stream()
                .map(ride -> ride.getEfficiencyPercentage())
                .reduce(0f, Float::sum);

        return effSumUser / user.getRidesCount();
    }
    */

}
