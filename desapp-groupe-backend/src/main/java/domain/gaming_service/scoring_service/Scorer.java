package domain.gaming_service.scoring_service;

import domain.User;

public abstract class Scorer {

    public void apply(User user)
    {
        if (canApplyTo(user))
            addScoreTo(user);
    }

    public void addScoreTo(User user)
    {
        user.addPoints(calculatePointsFor(user));
    }

    abstract public Boolean canApplyTo(User user) ;
    abstract public Integer calculatePointsFor(User user) ;
}