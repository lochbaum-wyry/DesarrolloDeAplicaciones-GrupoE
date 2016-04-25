package domain.Repositories;

import domain.gaming_service.ranking.MonthlyRanking;

public class RankingRepository extends HibernateGenericDao<MonthlyRanking> implements
        GenericRepository<MonthlyRanking> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<MonthlyRanking> getDomainClass() {
        return MonthlyRanking.class;
    }
}
