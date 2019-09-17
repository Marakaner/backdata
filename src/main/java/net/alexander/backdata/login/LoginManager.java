package net.alexander.backdata.login;

import net.alexander.backdata.service.Service;

public class LoginManager implements Service {

    public LoginManager() {

    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
