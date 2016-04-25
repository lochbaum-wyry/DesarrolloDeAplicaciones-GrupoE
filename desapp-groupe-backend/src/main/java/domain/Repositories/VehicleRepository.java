package domain.Repositories;

import domain.Vehicle;

import java.util.List;

public class VehicleRepository extends HibernateGenericDao<Vehicle> implements
        GenericRepository<Vehicle> {

private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Vehicle> getDomainClass() {
        return Vehicle.class;
    }

    public List<Vehicle> getVehiclesUsedInMonthYear(Integer month, Integer year) {
        return null;
    }

    public List<Vehicle> getBestVehiclesInMonthYear(Integer month, Integer year)
    {
        return null;
    }

    public List<Vehicle> getWorstVehiclesInMonthYear(Integer month, Integer year)
    {
        return null;
    }
}
