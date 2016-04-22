package domain.Repositories;

import domain.gaming_service.ranking.Ranking;

public class RankingRepository extends HibernateGenericDao<Ranking> implements
        GenericRepository<Ranking> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Ranking> getDomainClass() {
        return Ranking.class;
    }
}
