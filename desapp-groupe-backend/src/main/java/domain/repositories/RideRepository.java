package domain.repositories;

import domain.Ride;
import domain.RideRequest;
import org.hibernate.Query;

import java.util.List;

import static jdk.nashorn.internal.objects.Global.println;

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
                " WHERE r.route = :route  AND r.date = :date AND driver = :driver" ;

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
}
