package domain;

import java.util.List;
import java.util.stream.Collectors;

public class BadRatingScorer extends RateEventScorer {

    public Boolean canApplyTo(User user)
    {
        List<Rate> badRates = user.getRates().stream().filter(rate -> rate.getValue().equals(RateValue.BAD)).collect(Collectors.toList());
        return isEven(badRates.size());
    }

    private Boolean isEven(int number) {
        return ((number % 2) == 0) && (number != 0);
    }

    public Integer calculatePointsFor(User user)
    {
        return -1000;
    }

}
