package domain.repositories;

import domain.RideRequest;

public class RideRequestRepository extends HibernateGenericDao<RideRequest> implements
        GenericRepository<RideRequest> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<RideRequest> getDomainClass() {
        return RideRequest.class;
    }
}
