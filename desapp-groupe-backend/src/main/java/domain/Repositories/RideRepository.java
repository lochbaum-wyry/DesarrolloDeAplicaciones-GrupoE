package domain.Repositories;

import domain.Ride;
import domain.RideRequest;
import org.hibernate.Query;

public class RideRepository extends HibernateGenericDao<Ride> implements
        GenericRepository<Ride> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Ride> getDomainClass() {
        return Ride.class;
    }

    public Ride getRideOfDriverSuitableForRideRequest(RideRequest rideRequest)
    {
        String hql = "SELECT r FROM Ride r WHERE r.route = :route AND r.date = :date AND driver = :driver";

        Query q = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        q.setParameter("route", rideRequest.getRoute());
        q.setParameter("date", rideRequest.getDate());
        q.setParameter("driver", rideRequest.getDriver());

        Ride ride = (Ride)q.uniqueResult();
        return ride;
    }
}
