package domain.builders;

import domain.Scorer;
import domain.ScoringSystem;

import java.util.ArrayList;
import java.util.List;

public class ScoringSystemBuilder {

    private List<Scorer> scorers = new ArrayList<Scorer>();
    public static ScoringSystemBuilder aScoringSystem() {
        return new ScoringSystemBuilder();
    }

    public ScoringSystem build()
    {
        ScoringSystem scoringSystem = new ScoringSystem();
        scorers.stream().forEach( scorer -> scoringSystem.addScorer(scorer) );
        return scoringSystem;
    }

    public ScoringSystemBuilder withScorer(Scorer scorer)
    {
        this.scorers.add(scorer);
        return this;
    }
}
