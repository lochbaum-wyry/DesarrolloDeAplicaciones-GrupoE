package domain;

import java.util.ArrayList;
import java.util.List;

public class ScoringSystem {
    private static List<Scorer> scorers;

    public ScoringSystem(){
        scorers = new ArrayList<Scorer>();
        scorers.add(new BadRatingScorer());
        scorers.add(new GoodRatingScorer());
    }

    public static void applyRateEventScorers(User user)
    {
        scorers.stream()
                .filter(scorer-> scorer instanceof RateEventScorer).forEach(scorer ->  scorer.apply(user) ) ;
    }
}
