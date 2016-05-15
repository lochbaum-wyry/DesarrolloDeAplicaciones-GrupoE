package domain.repositories;

import domain.RoutePoint;
import org.springframework.stereotype.Repository;

@Repository
public class RoutePointRepository extends HibernateGenericDao<RoutePoint> implements
        GenericRepository<RoutePoint> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<RoutePoint> getDomainClass() {
        return RoutePoint.class;
    }
}
