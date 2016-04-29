package domain.repositories;

import domain.ProductExchange;

public class ProductExchangeRepository extends HibernateGenericDao<ProductExchange> implements
        GenericRepository<ProductExchange> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<ProductExchange> getDomainClass() {
        return ProductExchange.class;
    }
}
