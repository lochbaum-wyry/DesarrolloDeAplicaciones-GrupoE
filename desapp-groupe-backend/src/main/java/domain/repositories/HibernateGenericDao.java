package domain.repositories;

import java.io.Serializable;
import java.util.List;

import domain.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Generic hibernate DAO
 *
 * @param <T>
 */
public abstract class HibernateGenericDao<T> extends HibernateDaoSupport implements GenericRepository<T>, Serializable {

    private static final long serialVersionUID = 5058950102420892922L;

    protected Class<T> persistentClass = this.getDomainClass();

    @Override
    @SuppressWarnings("unchecked")
    public int count() {
        List<Long> list = this.getHibernateTemplate().find(
                "select count(*) from " + this.persistentClass.getName() + " o");

        // this.getHibernateTemplate().execute(new HibernateCallback<Car>() {
        //
        // @Override
        // public Car doInHibernate(final Session session) throws
        // HibernateException, SQLException {
        // throw new UnsupportedOperationException();
        // }
        // });
        Long count = list.get(0);
        return count.intValue();

    }

    @Override
    public void delete(final T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    @Override
    public void deleteById(final Serializable id) {
        T obj = this.findById(id);
        this.getHibernateTemplate().delete(obj);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return this.getHibernateTemplate().find("from " + this.persistentClass.getName() + " o");

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByExample(final T exampleObject) {
        return this.getHibernateTemplate().findByExample(exampleObject);

    }

    @Override
    public T findById(final Serializable id) {
        return this.getHibernateTemplate().get(this.persistentClass, id);
    }


    public boolean exists(final Serializable id)
    {
        return this.getHibernateTemplate().get(this.persistentClass, id) != null;

    }

    protected abstract Class<T> getDomainClass();

    @Override
    public void save(final T entity) {
        this.getHibernateTemplate().save(entity);
        this.getHibernateTemplate().flush();
    }

    @Override
    public void update(final T entity) {
        this.getHibernateTemplate().update(entity);
    }


    public void saveOrUpdate(final T entity) {
        this.getHibernateTemplate().saveOrUpdate(entity);
//        this.getHibernateTemplate().flush();
    }

    public void merge(final T entity) {
        this.getHibernateTemplate().merge(entity);
    }


}