package domain.repositories;

import domain.User;
import domain.UserToken;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class UserTokenRepository extends HibernateGenericDao<UserToken> implements
        GenericRepository<UserToken> {

    @Override
    protected Class<UserToken> getDomainClass() {
        return UserToken.class;
    }

    public UserToken findByUserId(int id) {
        Criteria cr = this.getSession().createCriteria(this.getDomainClass());
        cr.add(Restrictions.eq("userModel.id", id));
        return (UserToken) cr.uniqueResult();
    }

    public User findByUserToken(String token) {
        Criteria cr = this.getSession().createCriteria(this.getDomainClass());
        cr.add(Restrictions.eq("token", token));
        UserToken userToken = (UserToken) cr.uniqueResult();
        return userToken.getUserModel();
    }
}
