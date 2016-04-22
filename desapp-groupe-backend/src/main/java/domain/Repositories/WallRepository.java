package domain.Repositories;


import domain.Wall;

public class WallRepository extends HibernateGenericDao<Wall> implements
        GenericRepository<Wall> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Wall> getDomainClass() {
        return Wall.class;
    }
}
