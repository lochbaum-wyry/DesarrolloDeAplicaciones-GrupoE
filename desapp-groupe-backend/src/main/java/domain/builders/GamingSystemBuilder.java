package domain.builders;

import domain.GamingSystem;

public class GamingSystemBuilder {

    public static GamingSystemBuilder aGamingSystem() {
        return new GamingSystemBuilder();
    }

    public GamingSystem build() {
        return new GamingSystem();
    }
}
