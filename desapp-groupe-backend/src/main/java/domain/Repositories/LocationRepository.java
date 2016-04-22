package domain.Repositories;

import domain.Location;

public class LocationRepository extends HibernateGenericDao<Location> implements
        GenericRepository<Location> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Location> getDomainClass() {
        return Location.class;
    }
}
