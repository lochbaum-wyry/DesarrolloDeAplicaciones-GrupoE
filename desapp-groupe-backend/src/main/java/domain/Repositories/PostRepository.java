package domain.Repositories;

import domain.Post;

public class PostRepository extends HibernateGenericDao<Post> implements
        GenericRepository<Post> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Post> getDomainClass() {
        return Post.class;
    }
}
