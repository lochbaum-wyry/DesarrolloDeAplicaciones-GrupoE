package domain.repositories;

import domain.Chat;
import org.hibernate.Query;

public class ChatRepository extends HibernateGenericDao<Chat> implements
        GenericRepository<Chat> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Chat> getDomainClass() {
        return Chat.class;
    }

    public Chat findByUserId(Integer userId){

        String hql = "from " + this.persistentClass.getName() +
                " as c WHERE  c.userId = :user";

        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("user", userId);

        return ((Chat) query.uniqueResult()); //TODO : feo feo el cast
    }
}
