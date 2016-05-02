package domain.repositories;

import domain.ProductExchange;
import domain.User;
import org.hibernate.Query;

import java.util.List;

public class ProductExchangeRepository extends HibernateGenericDao<ProductExchange> implements
        GenericRepository<ProductExchange> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<ProductExchange> getDomainClass() {
        return ProductExchange.class;
    }

    public List<ProductExchange> exchangedProductsBy(User user){
        String hql = "from " + persistentClass.getName() +
                " as p WHERE  p.user = :user";

        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setEntity("user",user);

        return query.list();
    }
}
