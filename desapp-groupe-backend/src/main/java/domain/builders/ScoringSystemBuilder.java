package domain.builders;

import domain.ScoringSystem;

public class ScoringSystemBuilder {

    public static ScoringSystemBuilder aScoringSystem() {
        return new ScoringSystemBuilder();
    }

    public ScoringSystem build() {
        return new ScoringSystem();
    }
}
