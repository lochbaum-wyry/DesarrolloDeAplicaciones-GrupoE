package domain.repositories;

import domain.Chat;
import domain.User;
import org.hibernate.Query;

import java.util.List;

public class ChatRepository extends HibernateGenericDao<Chat> implements
        GenericRepository<Chat> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Chat> getDomainClass() {
        return Chat.class;
    }
}
