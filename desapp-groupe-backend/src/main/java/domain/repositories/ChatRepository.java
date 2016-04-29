package domain.repositories;

import domain.Chat;

public class ChatRepository extends HibernateGenericDao<Chat> implements
        GenericRepository<Chat> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Chat> getDomainClass() {
        return Chat.class;
    }
}
