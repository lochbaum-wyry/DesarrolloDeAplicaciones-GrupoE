package domain.builders;

import domain.gaming_service.scoring_service.Scorer;
import domain.gaming_service.scoring_service.ScoringService;

import java.util.ArrayList;
import java.util.List;

public class ScoringSystemBuilder {

    private List<Scorer> scorers = new ArrayList<Scorer>();
    public static ScoringSystemBuilder aScoringSystem() {
        return new ScoringSystemBuilder();
    }

    public ScoringService build()
    {
        ScoringService scoringService = new ScoringService();
        scorers.stream().forEach( scorer -> scoringService.addScorer(scorer) );
        return scoringService;
    }

    public ScoringSystemBuilder withScorer(Scorer scorer)
    {
        this.scorers.add(scorer);
        return this;
    }
}
