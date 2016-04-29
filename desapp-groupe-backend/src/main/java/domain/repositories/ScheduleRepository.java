package domain.repositories;

import domain.Schedule;

public class ScheduleRepository extends HibernateGenericDao<Schedule> implements
        GenericRepository<Schedule> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<Schedule> getDomainClass() {
        return Schedule.class;
    }
}
