package helpers;

public class UserTokenResponse {
    public UserTokenResponse(String token) {
        this.setToken(token);
    }

    public UserTokenResponse(){}

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
