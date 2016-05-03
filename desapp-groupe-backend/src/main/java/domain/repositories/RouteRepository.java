package domain.repositories;

import domain.LatLng;
import domain.Route;
import domain.RoutePoint;
import org.hibernate.Query;
import org.hibernate.type.DoubleType;
import org.joda.time.DateTime;

import java.util.List;

public class RouteRepository extends HibernateGenericDao<Route> implements
        GenericRepository<Route> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Route> getDomainClass() {
        return Route.class;
    }

    public List<Route> findRoutesSatisfiying(DateTime date, Integer secondsDateCloseness, LatLng departurePoint, LatLng arrivalPoint, Double closenessInMts)
    {
        // El cálculo de cercanía se hace por el cálculo de distancia entre dos puntos geográficos (LatLng) .
        // Los algoritmos usados fueron adaptados de las funciones de la api de googlemaps
        // Math.PI;

//
//        String hql =    " SELECT r " +
//                        " FROM Route r " +
//                        " INNER JOIN r.routePoints rpd " +
//                        " INNER JOIN r.routePoints rpa " +
//                        " WHERE rpd.indexInRoute < rpa.indexInRoute " +
//                        "   AND POWER( rpd.longitude - :rpdlng , 2 ) + POWER( rpd.latitude - :rpdlat, 2) < POWER(:radioCloseness, 2) " +
//                        "   AND POWER( rpa.longitude - :rpalng , 2 ) + POWER( rpa.latitude - :rpalat, 2) < POWER(:radioCloseness, 2) " ;

        String hql =    " SELECT r " +
                " FROM Route r " +
                " INNER JOIN r.routePoints rpd " +
//                " INNER JOIN r.routePoints rpa " +
                " WHERE" +
//                " rpd.indexInRoute < rpa.indexInRoute AND" +
//                "   :earth_radius*(2*asin(sqrt(power(sin(((rpd.latitude*:PI/180)-(:rpdlat*:PI/180))/2),2)+cos(rpd.latitude*:PI/180)*cos(:rpdlat*:PI/180)*power(sin(((rpd.longitude*:PI/180)-(:rpdlng*:PI/180))/2),2)))) < :closenessInMts " ; //+
                  "   6378137d * (2* asin( sqrt( power( sin(((rpd.latitude*:PI/180) - ((:dplat*:PI)/180)) / 2), 2) + cos(rpd.latitude*:PI/180) * cos(((:dplat*:PI)/180)) * power(sin((rpd.longitude - :dplng) / 2), 2)))) < :closenessInMts " ; //+
//                "   AND :earth_radius*(2*asin(sqrt(power(sin(((rpa.latitude*:PI/180)-(:rpalat*:PI/180))/2),2)+cos(rpa.latitude*:PI/180)*cos(:rpalat*:PI/180)*power(sin(((rpa.longitude*:PI/180)-(:rpalng*:PI/180))/2),2)))) < :closenessInMts " ;
//                "   AND (2*asin(sqrt(power(sin(((lat_a*:PI/180)-(lat_b*:PI/180))/2),2)+cos(lat_a*:PI/180)*cos(lat_b*:PI/180)*power(sin(((lng_a*:PI/180)-(lng_b*:PI/180))/2),2)))) <= :closenessInMts " ;

        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);

        query.setParameter("dplat", departurePoint.getLatitude(), DoubleType.INSTANCE);
        query.setParameter("dplng", departurePoint.getLongitude(), DoubleType.INSTANCE);

//        query.setParameter("rpalng", arrivalRoutePoint.getLongitude(), DoubleType.INSTANCE);
//        query.setParameter("rpalat", arrivalRoutePoint.getLatitude(), DoubleType.INSTANCE);

        query.setParameter("PI", 3.141592653589793, DoubleType.INSTANCE);
        query.setParameter("closenessInMts", closenessInMts, DoubleType.INSTANCE);
//        query.setParameter("earth_radius", 6378137d, DoubleType.INSTANCE);


        return query.list();
    }
}

/*
6378137d * (2* asin( sqrt( pow( sin(((rpd.latitude*:PI/180) - ((dplat*:PI)/180)) / 2), 2) + cos(rpd.latitude*:PI/180) * cos(((dplat*:PI)/180)) * pow(sin((rpd.latitude - :dplng) / 2), 2))))


(2*asin(sqrt(power(sin(((lat_a*:PI/180)-(lat_b*:PI/180))/2),2)+cos(lat_a*:PI/180)*cos(lat_b*:PI/180)*power(sin(((lng_a*:PI/180)-(lng_b*:PI/180))/2),2))))


Funciones de la API de Google Maps

 computeDistanceBetween: function(a, b, c) {
     c =
     c || 6378137;
     return My.Kg(a, b) * c
 };

 Kg: function(a, b) {
     var c = _.Wb(a),
     d = _.Xb(a),
     e = _.Wb(b),
     f = _.Xb(b);
     return 2 * Math.asin(Math.sqrt(Math.pow(Math.sin((c - e) / 2), 2) + Math.cos(c) * Math.cos(e) * Math.pow(Math.sin((d - f) / 2), 2)))
 };


 _.L = function(a) {  /// Cálculo del ángulo ??
    return a * Math.PI / 180
 };


 _.Wb = function(a) {  /// angulo de la latitud
    return _.L(a.lat())
 };

 _.Xb = function(a) {  /// angulo de la longitud
 return _.L(a.lng())
 };




 */