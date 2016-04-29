package domain.repositories;

import domain.Route;

public class RouteRepository extends HibernateGenericDao<Route> implements
        GenericRepository<Route> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Route> getDomainClass() {
        return Route.class;
    }
}
