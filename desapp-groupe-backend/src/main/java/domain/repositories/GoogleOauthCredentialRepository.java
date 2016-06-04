package domain.repositories;

import domain.GoogleOauthCredential;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class GoogleOauthCredentialRepository extends HibernateGenericDao<GoogleOauthCredential> implements
        GenericRepository<GoogleOauthCredential> {

    private static final long serialVersionUID = -4036535812105672112L;

    @Override
    protected Class<GoogleOauthCredential> getDomainClass() {
        return GoogleOauthCredential.class;
    }

    public GoogleOauthCredential findByUserId(String id){
        Criteria cr = this.getSession().createCriteria(this.getDomainClass());

        cr.add(Restrictions.eq("googleUserId",id));
        return (GoogleOauthCredential) cr.uniqueResult();
    }

}
