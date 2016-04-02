package domain.builders;

import domain.System;
import domain.User;

import java.util.ArrayList;
import java.util.List;

public class SystemBuilder {

    private List<User> users = new ArrayList<User>();

    public static SystemBuilder aSystem() {
        return new SystemBuilder();
    }

    public System build(){
        System system =  new System();

        users.stream().forEach(user -> {system.signUp(user);});
        return system;
    }

    public SystemBuilder withUsers(List<User> users) {
        this.users = users;
        return this;
    }

}
