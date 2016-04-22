package domain.Repositories;

import domain.Rate;

public class RateRepository extends HibernateGenericDao<Rate> implements
        GenericRepository<Rate> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Rate> getDomainClass() {
        return Rate.class;
    }
}
