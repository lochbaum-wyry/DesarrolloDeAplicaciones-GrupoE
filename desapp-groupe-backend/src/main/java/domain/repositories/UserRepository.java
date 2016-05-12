package domain.repositories;

import domain.Ride;
import domain.TakenSeat;
import domain.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import java.util.List;

public class UserRepository extends HibernateGenericDao<User> implements
        GenericRepository<User> {

    private static final long serialVersionUID = -8543996946304099003L;

    @Override
    protected Class<User> getDomainClass() {
        return User.class;
    }

    public List<User> getBestDriversInMonthYear(Integer month, Integer year,Integer cant)
    {
        String hql = " SELECT r.driver FROM " +  Ride.class.getName() +
                " AS r INNER JOIN r.driver u " +
                " WHERE   month(r.date) = :month AND year(r.date) = :year" +
                " ORDER BY (u.goodRateCount-u.badRateCount) DESC ";

        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("month",month);
        query.setParameter("year",year);
//        query.setFirstResult(0);
//        query.setMaxResults(cant);

        return query.list();
    }

    public List<User> getWorstDriversInMonthYear(Integer month, Integer year,Integer cant)
    {
        String hql = " SELECT r.driver FROM " +  Ride.class.getName() +
                " AS r INNER JOIN r.driver u " +
                " WHERE   month(r.date) = :month AND year(r.date) = :year" +
                " ORDER BY (u.goodRateCount-u.badRateCount) ASC ";

        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("month",month);
        query.setParameter("year",year);
//        query.setFirstResult(0);
//        query.setMaxResults(cant);

        return query.list();
    }

    public List<User> getBestPassengersInMonthYear(Integer month, Integer year,Integer cant)
    {
        String hql = "SELECT distinct u FROM " + TakenSeat.class.getName() + " ts " +
                " INNER JOIN ts.passenger u " +
                " INNER JOIN ts.ride r  " +
                " WHERE month(r.date) = :month and year(r.date) = :year" +
                " ORDER BY (u.goodRateCount - u.badRateCount) DESC";

        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("month",month);
        query.setParameter("year",year);
//        query.setFirstResult(0);
//        query.setMaxResults(cant);

        return query.list();
    }

    public User getUserByEmail(String email){
        String hql = "SELECT u FROM " + User.class.getName() + " u " +
                "WHERE u.email = :email";
        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("email",email);

        User user = (User) query.uniqueResult();

        return user;
    }

    public User getUserByUserName(String userName){
        String hql = "SELECT u FROM " + User.class.getName() + " u " +
                "WHERE u.userName = :userName";
        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("userName",userName);

        User user = (User) query.uniqueResult();

        return user;
    }

    public List<User> getWorstPassengersInMonthYear(Integer month, Integer year,Integer cant)
    {
        String hql = "SELECT distinct u FROM " + TakenSeat.class.getName() + " ts " +
                " INNER JOIN ts.passenger u " +
                " INNER JOIN ts.ride r  " +
                " WHERE month(r.date) = :month and year(r.date) = :year" +
                " ORDER BY (u.goodRateCount - u.badRateCount) ASC";

        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("month",month);
        query.setParameter("year",year);
//        query.setFirstResult(0);
//        query.setMaxResults(cant);

        return query.list();
    }

    public List<User> getMostEfficientDriversInMonthYear(Integer month, Integer year, Integer cant)
    {
        String hql =    "SELECT r.driver.id " +
                ", AVG(r.efficiencyPercentage) as percentage " +
                        "FROM Ride r "+
                        "WHERE month(r.date) = :month and year(r.date) = :year " +
                        "GROUP BY r.driver.id " +
                        "ORDER BY percentage DESC";

        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);

        query.setParameter("month",month);
        query.setParameter("year",year);

        return query.list();


    }

}
