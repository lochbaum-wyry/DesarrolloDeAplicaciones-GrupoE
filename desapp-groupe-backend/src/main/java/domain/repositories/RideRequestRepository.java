package domain.repositories;

import domain.RequestStatus;
import domain.RideRequest;
import domain.User;
import org.hibernate.Query;
import org.omg.CORBA.Request;

import java.util.List;

public class RideRequestRepository extends HibernateGenericDao<RideRequest> implements
        GenericRepository<RideRequest> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<RideRequest> getDomainClass() {
        return RideRequest.class;
    }

    public List<RideRequest> getPendingRequestsFor(User driver)
    {
        String hql = "select rr FROM RideRequest rr WHERE rr.driver = :driver and rr.status = :status";
        Query q = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);

        q.setEntity("driver", driver);
        q.setParameter("status", RequestStatus.Pending);

        return q.list();
    }

}
