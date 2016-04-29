package domain.repositories;

import domain.TakenSeat;

public class TakenSeatRepository extends HibernateGenericDao<TakenSeat> implements
        GenericRepository<TakenSeat> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<TakenSeat> getDomainClass() {
        return TakenSeat.class;
    }
}
