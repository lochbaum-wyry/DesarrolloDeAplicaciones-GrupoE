package domain.repositories;

import domain.Product;

public class ProductRepository extends HibernateGenericDao<Product> implements
        GenericRepository<Product> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Product> getDomainClass() {
        return Product.class;
    }
}
