package domain.repositories;

import domain.Ride;
import domain.RideRequest;
import domain.TakenSeat;
import domain.User;
import org.hibernate.Query;

import java.sql.Date;
import java.util.List;

public class RideRepository extends HibernateGenericDao<Ride> implements
        GenericRepository<Ride> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Ride> getDomainClass() {
        return Ride.class;
    }

    public Ride getRideOfDriverSuitableForRideRequest(RideRequest rideRequest)
    {
        String hql = "SELECT r " +
                " FROM " + Ride.class.getName() + " r " +
                " WHERE r.route = :route  AND r.date = :date AND r.driver = :driver" ;

        Query query =  getHibernateTemplate()
                .getSessionFactory()
                .getCurrentSession()
                .createQuery(hql);

        query.setEntity("route", rideRequest.getRoute());
        query.setParameter("date", rideRequest.getDate());
        query.setEntity("driver", rideRequest.getDriver());

        Ride ride = (Ride)query.uniqueResult();
        return ride;
    }

    public List<Ride> getRidesAwaingRates(User user) {
        String hql = "SELECT distinct r FROM " + TakenSeat.class.getName() + " ts " +
                " INNER JOIN ts.passenger u " +
                " INNER JOIN ts.ride r  " +
                " WHERE (u =:user OR r.driver=:user) AND r.date < now()";

        Query query =  getHibernateTemplate()
                .getSessionFactory()
                .getCurrentSession()
                .createQuery(hql);

        query.setEntity("user", user);


        return query.list();
    }
}
