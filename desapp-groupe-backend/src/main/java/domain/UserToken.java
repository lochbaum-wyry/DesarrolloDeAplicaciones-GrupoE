package domain;

import helpers.TokenGenerator;


public class UserToken extends Entity{

    private TokenGenerator tokenGenerator = TokenGenerator.defaultGenerator();

    private String token;

    private User userModel;

    public void generateToken() {
        this.setToken(this.tokenGenerator.generate());
    }

    public User getUserModel() {
        return userModel;
    }

    public void setUserModel(User userModel) {
        this.userModel = userModel;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}