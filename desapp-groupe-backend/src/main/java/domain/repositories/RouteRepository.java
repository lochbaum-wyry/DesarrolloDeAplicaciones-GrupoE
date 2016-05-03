package domain.repositories;

import domain.LatLng;
import domain.Route;
import domain.RoutePoint;
import org.hibernate.Query;
import org.joda.time.DateTime;

import java.util.List;

public class RouteRepository extends HibernateGenericDao<Route> implements
        GenericRepository<Route> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Route> getDomainClass() {
        return Route.class;
    }

    public List<Route> findRoutesSatisfiying(DateTime date, Integer secondsDateCloseness, LatLng departureRoutePoint, LatLng arrivalRoutePoint, Float radioCloseness)
    {
        String hql =    " SELECT r " +
                        " FROM Route r " +
                        " INNER JOIN r.routePoints rpd " +
                        " INNER JOIN r.routePoints rpa " +
                        " WHERE rpd.indexInRoute < rpa.indexInRoute " +
                        "   AND POWER( rpd.longitude - :rpdlng , 2 ) + POWER( rpd.latitude - :rpdlat, 2) < POWER(:radioCloseness, 2) " +
                        "   AND POWER( rpa.longitude - :rpalng , 2 ) + POWER( rpa.latitude - :rpalat, 2) < POWER(:radioCloseness, 2) " +
                        "" ;

        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);

        query.setParameter("rpdlng", departureRoutePoint.getLongitude());
        query.setParameter("rpdlat", departureRoutePoint.getLatitude());

        query.setParameter("rpalng", arrivalRoutePoint.getLongitude());
        query.setParameter("rpalat", arrivalRoutePoint.getLatitude());

        query.setParameter("radioCloseness", radioCloseness);

        return query.list();
    }
}
