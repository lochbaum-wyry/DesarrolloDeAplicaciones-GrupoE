package domain.repositories;

import domain.Chat;
import domain.User;
import org.hibernate.Query;

import java.util.List;

public class ChatRepository extends HibernateGenericDao<Chat> implements
        GenericRepository<Chat> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Chat> getDomainClass() {
        return Chat.class;
    }

    public List<Chat> findByUserId(Integer userId){

        String hql = "SELECT u.chats from " + User.class.getName() +
                " as u WHERE  u.id = :id";

        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("id", userId);

        return query.list();
    }

    public Chat findChatByUsers(Integer userId1,Integer userId2)
    {
        return  null;
    }
}
