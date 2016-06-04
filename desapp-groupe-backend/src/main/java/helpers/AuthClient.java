package helpers;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;
import helpers.exceptions.CodeExchangeException;
import helpers.exceptions.NoRefreshTokenException;
import helpers.exceptions.NoUserIdException;

import java.io.IOException;
import java.util.List;

public abstract class AuthClient {

    private GoogleAuthorizationCodeFlow flow = null;
    private GoogleClientSecrets clientSecrets;
    private List<String> scopes;
    private String redirectURI;


    public AuthClient(GoogleClientSecrets clientSecrets, List<String> scopes, String redirectURI) {
        this.clientSecrets = clientSecrets;
        this.scopes = scopes;
        this.redirectURI = redirectURI;
    }

    /**
     * Retrieved stored credentials for the provided user ID.
     *
     * @param userId User's ID.
     * @return Stored Credential if found, {@code null} otherwise.
     * <p>
     * TODO: Implement this method to work with your database. Instantiate a new`
     * Credential instance with stored accessToken and refreshToken.
     * return null;
     * throw new UnsupportedOperationException();
     */
    abstract Credential getStoredCredentials(String userId);

    /**
     * Store OAuth 2.0 credentials in the application's database.
     *
     * @param userId User's ID.
     * @param credentials The OAuth 2.0 credentials to store.
     * <p>
     * TODO: Implement this method to work with your database.
     * Store the credentials.getAccessToken() and credentials.getRefreshToken()
     * string values in your database.
     * throw new UnsupportedOperationException();
     */
    abstract void storeCredentials(String userId, Credential credentials);

    /**
     * Build an authorization flow and store it as a static class attribute.
     *
     * @return GoogleAuthorizationCodeFlow instance.
     * @throws IOException Unable to load client_secrets.json.
     */
    GoogleAuthorizationCodeFlow getFlow() throws IOException {
        if (flow == null) {
            HttpTransport httpTransport = new NetHttpTransport();
            JacksonFactory jsonFactory = new JacksonFactory();
            GoogleClientSecrets googleClientSecrets = this.getClientSecrets();
            List<String> scopes = this.getScopes();
            flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory, googleClientSecrets, scopes)
                    .setAccessType("offline").setApprovalPrompt("force").build();
        }
        return flow;
    }

    /**
     * Exchange an authorization code for OAuth 2.0 credentials.
     *
     * @param authorizationCode Authorization code to exchange for OAuth 2.0
     * credentials.
     * @return OAuth 2.0 credentials.
     * @throws CodeExchangeException An error occurred.
     */
    Credential exchangeCode(String authorizationCode)
            throws CodeExchangeException {
        try {
            GoogleAuthorizationCodeFlow flow = getFlow();
            GoogleTokenResponse response =
                    flow.newTokenRequest(authorizationCode).setRedirectUri(this.getRedirectURI()).execute();
            return flow.createAndStoreCredential(response, null);
        } catch (IOException e) {
            System.err.println("An error occurred: " + e);
            throw new CodeExchangeException(null);
        }
    }

    /**
     * Send a request to the UserInfo API to retrieve the user's information.
     *
     * @param credentials OAuth 2.0 credentials to authorize the request.
     * @return User's information.
     * @throws NoUserIdException An error occurred.
     */
    public Userinfoplus getUserInfo(Credential credentials)
            throws NoUserIdException {
        Oauth2 userInfoService =
                new Oauth2.Builder(new NetHttpTransport(), new JacksonFactory(), credentials).build();
        Userinfoplus userInfo = null;
        try {
            userInfo = userInfoService.userinfo().get().execute();
        } catch (IOException e) {
            System.err.println("An error occurred: " + e);
        }
        if (userInfo != null && userInfo.getId() != null) {
            return userInfo;
        } else {
            throw new NoUserIdException();
        }
    }

    /**
     * Retrieve the authorization URL.
     *
     * @param userId User's Google ID.
     * @param state State for the authorization URL.
     * @return Authorization URL to redirect the user to.
     * @throws IOException Unable to load client_secrets.json.
     */
    public String getAuthorizationUrl(String userId, String state) throws IOException {
        GoogleAuthorizationCodeRequestUrl urlBuilder =
                getFlow().newAuthorizationUrl().setRedirectUri(this.getRedirectURI()).setState(state);
        urlBuilder.set("user_id", userId);
        return urlBuilder.build();
    }

    /**
     * Retrieve credentials using the provided authorization code.
     * <p>
     * This function exchanges the authorization code for an access token and
     * queries the UserInfo API to retrieve the user's Google ID. If a
     * refresh token has been retrieved along with an access token, it is stored
     * in the application database using the user's Google ID as key. If no
     * refresh token has been retrieved, the function checks in the application
     * database for one and returns it if found or throws a NoRefreshTokenException
     * with the authorization URL to redirect the user to.
     *
     * @param authorizationCode Authorization code to use to retrieve an access
     * token.
     * @param state State to set to the authorization URL in case of error.
     * @return OAuth 2.0 credentials instance containing an access and refresh
     * token.
     * @throws NoRefreshTokenException No refresh token could be retrieved from
     * the available sources.
     * @throws IOException Unable to load client_secrets.json.
     */
    public Credential getCredentials(String authorizationCode, String state)
            throws CodeExchangeException, NoRefreshTokenException, IOException {
        String userId = "";
        try {
            Credential credentials = exchangeCode(authorizationCode);
            Userinfoplus userInfo = getUserInfo(credentials);
            userId = userInfo.getId();
            if (credentials.getRefreshToken() != null) {
                storeCredentials(userId, credentials);
                return credentials;
            } else {
                credentials = getStoredCredentials(userId);
                if (credentials != null && credentials.getRefreshToken() != null) {
                    return credentials;
                }
            }
        } catch (CodeExchangeException e) {
            e.printStackTrace();
            // Glass services should try to retrieve the user and credentials for the current
            // session.
            // If none is available, redirect the user to the authorization URL.
            e.setAuthorizationUrl(getAuthorizationUrl(userId, state));
            throw e;
        } catch (NoUserIdException e) {
            e.printStackTrace();
        }
        // No refresh token has been retrieved.
        String authorizationUrl = getAuthorizationUrl(userId, state);
        throw new NoRefreshTokenException(authorizationUrl);
    }

    public List<String> getScopes() {
        return scopes;
    }

    public GoogleClientSecrets getClientSecrets() {
        return clientSecrets;
    }

    public String getRedirectURI() {
        return redirectURI;
    }
}