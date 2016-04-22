package domain.Repositories;

import domain.Message;

public class MessageRepository extends HibernateGenericDao<Message> implements
        GenericRepository<Message> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Message> getDomainClass() {
        return Message.class;
    }
}
