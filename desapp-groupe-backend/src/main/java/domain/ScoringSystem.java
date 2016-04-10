package domain;

import java.util.ArrayList;
import java.util.List;

public class ScoringSystem {
    private static List<Scorer> scorers;

    public ScoringSystem()
    {
        scorers = new ArrayList<Scorer>();
        scorers.add(new BadRatingScorer());
        scorers.add(new GoodRatingScorer());
    }

    public static void applyRateEventScorers(User user)
    {
        scorers.stream()
                .filter(scorer-> scorer instanceof RateEventScorer)
                .forEach(scorer ->  scorer.apply(user) ) ;
    }

    public Reputation calculateReputation(User user){
        Long countTotalRate = user.getRates().stream().count();
        Long countBadRate = user.getBadRates().stream().count();
        Long countGoodRate = user.getGoodRates().stream().count();

        return new Reputation(countTotalRate,countBadRate,countGoodRate);
    }
}
