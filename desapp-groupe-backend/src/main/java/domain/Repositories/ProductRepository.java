package domain.Repositories;

import domain.gaming_service.product_service.Product;

public class ProductRepository extends HibernateGenericDao<Product> implements
        GenericRepository<Product> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Product> getDomainClass() {
        return Product.class;
    }
}
