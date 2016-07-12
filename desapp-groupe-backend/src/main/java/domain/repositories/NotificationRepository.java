package domain.repositories;

import domain.Notification;
import domain.User;
import domain.notifications.SystemNotification;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationRepository extends HibernateGenericDao<Notification> implements
        GenericRepository<Notification> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Notification> getDomainClass() {
        return Notification.class;
    }


    public List<SystemNotification> systemNotificationsFor(User user)
    {
        String hql = "SELECT n FROM " + SystemNotification.class.getName() + " n "; // WHERE receiver = :user AND seen <> FALSE ";
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);

//        query.setEntity("user", user) ;

        return query.list();
    }


    public List<Notification> notificationsFor(User user)
    {
        String hql = "SELECT n FROM Notification n " ; // WHERE receiver = :user AND seen <> FALSE ";
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);

//        query.setEntity("user", user) ;

        return query.list();
    }
}
