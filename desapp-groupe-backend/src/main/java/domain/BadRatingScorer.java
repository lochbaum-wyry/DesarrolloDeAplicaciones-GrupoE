package domain;

import java.util.List;
import java.util.stream.Collectors;

public class BadRatingScorer extends RateEventScorer {

    public Boolean canApplyTo(User user)
    {
        return isEven(user.getBadRates().size());

    }

    private Boolean isEven(int number) {
        return ((number % 2) == 0) && (number != 0);
    }

    public Integer calculatePointsFor(User user)
    {
        return -1000;
    }

}
