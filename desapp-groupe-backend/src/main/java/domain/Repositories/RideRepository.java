package domain.Repositories;

import domain.Ride;
import domain.RideRequest;
import org.hibernate.Query;
import org.hibernate.annotations.Type;

import java.util.List;

public class RideRepository extends HibernateGenericDao<Ride> implements
        GenericRepository<Ride> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Ride> getDomainClass() {
        return Ride.class;
    }

    public Ride getRideSuitableForRideRequest(RideRequest rideRequest)
    {
        String hql = "SELECT r FROM Ride WHERE";

        Query q = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);

        Ride ride =  (Ride)q.uniqueResult();

    }
}
