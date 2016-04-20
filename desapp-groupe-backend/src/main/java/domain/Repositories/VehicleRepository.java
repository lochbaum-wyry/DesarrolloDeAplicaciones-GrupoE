package domain.Repositories;

import domain.Vehicle;

public class VehicleRepository extends HibernateGenericDao<Vehicle> implements
        GenericRepository<Vehicle> {

private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Vehicle> getDomainClass() {
        return Vehicle.class;
    }

}
