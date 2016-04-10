package domain;

import java.lang.*;

public abstract class RateEventScorer extends Scorer
{
    abstract public Boolean canApplyTo(User user, Rate rate);

    public void apply(User user, Rate rate)
    {
        if (canApplyTo(user, rate))
            addScoreTo(user);
    }
}
