package domain.builders;

import domain.GamingSystem;
import domain.System;

public class GamingSystemBuilder {
    private System system;

    public GamingSystemBuilder(){
        system = null;
    }
    public static GamingSystemBuilder aGamingSystem() {
        return new GamingSystemBuilder();
    }

    public GamingSystemBuilder withSystem(System system){
        this.system = system;
        return this;
    }
    public GamingSystem build() {
        return new GamingSystem(this.system);
    }
}
