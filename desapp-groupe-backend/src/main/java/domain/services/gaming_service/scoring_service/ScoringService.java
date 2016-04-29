package domain.services.gaming_service.scoring_service;

import domain.Rate;
import domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScoringService
{
    private List<Scorer> scorers;

    public ScoringService()
    {
        scorers = new ArrayList<Scorer>();
    }

    public void addScorer(Scorer scorer)
    {
        scorers.add(scorer);
    }

    public void applyRateEventScorers(User user, Rate rate)
    {
        scorers.stream()
            .filter(scorer-> scorer instanceof RateEventScorer)
            .map(scorer -> (RateEventScorer) scorer)
            .collect(Collectors.toList())
            .forEach( scorer ->  scorer.apply(user, rate) );
    }

}
