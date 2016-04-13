package domain.builders;

import domain.System;
import domain.SystemSettings;
import domain.User;
import domain.gaming_service.GamingService;

import java.util.ArrayList;
import java.util.List;

public class SystemBuilder {

    private List<User> users = new ArrayList<User>();
    private GamingService gamingService = new GamingService();
    private SystemSettings settings = new SystemSettings();

    public static SystemBuilder aSystem() {
        return new SystemBuilder();
    }

    public System build(){
        System system =  new System(settings, gamingService);

        gamingService.setSystem(system);

        users.stream().forEach(user -> {system.signUp(user);});
        return system;
    }

    public SystemBuilder withUsers(List<User> users) {
        this.users = users;
        return this;
    }

}
