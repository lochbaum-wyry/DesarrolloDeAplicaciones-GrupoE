package domain.repositories;

import domain.services.MonthlyRanking;
import org.hibernate.Query;

public class RankingRepository extends HibernateGenericDao<MonthlyRanking> implements
        GenericRepository<MonthlyRanking> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<MonthlyRanking> getDomainClass() {
        return MonthlyRanking.class;
    }

    public MonthlyRanking getRankingIn(Integer month,Integer year){
        String hql = " SELECT r FROM " +  MonthlyRanking.class.getName() +
                " AS r WHERE r.month = :month AND r.year = :year";

        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("month",month);
        query.setParameter("year",year);

        MonthlyRanking ranking = (MonthlyRanking) query.uniqueResult();
        return ranking;
    }
}
