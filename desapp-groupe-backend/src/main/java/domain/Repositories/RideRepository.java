package domain.Repositories;

import domain.Ride;

public class RideRepository extends HibernateGenericDao<Ride> implements
        GenericRepository<Ride> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Ride> getDomainClass() {
        return Ride.class;
    }
}
