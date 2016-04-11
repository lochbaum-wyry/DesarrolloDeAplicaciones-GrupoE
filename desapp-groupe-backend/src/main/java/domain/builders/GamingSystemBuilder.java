package domain.builders;

import domain.gaming_service.GamingService;
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
    public GamingService build() {
        return new GamingService(this.system);
    }
}
