package domain.repositories;

import domain.Post;
import domain.User;
import org.hibernate.Query;

import java.util.List;

public class PostRepository extends HibernateGenericDao<Post> implements
        GenericRepository<Post> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Post> getDomainClass() {
        return Post.class;
    }


    public List<Post> findByUser(User user){
        String hql = "from " + persistentClass.getName() +
                " as p WHERE  p.wallOwner = :user";

        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setEntity("user",user);

        return query.list();
    }
}
