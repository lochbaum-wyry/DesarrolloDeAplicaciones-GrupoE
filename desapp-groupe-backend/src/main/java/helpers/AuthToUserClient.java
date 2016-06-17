package helpers;

import com.google.api.client.auth.oauth2.Credential;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import domain.GoogleOauthCredential;
import domain.repositories.GoogleOauthCredentialRepository;


import java.util.List;



public class AuthToUserClient extends AuthClient{

    private GoogleOauthCredentialRepository googleOauthCredentialRepository;

    public AuthToUserClient(GoogleClientSecrets clientSecrets, List<String> scopes, String redirectURI, GoogleOauthCredentialRepository googleOauthCredentialRepository) {
        super(clientSecrets, scopes, redirectURI);
        this.setGoogleOauthCredentialRepository(googleOauthCredentialRepository);
    }

    @Override
    Credential getStoredCredentials(String userId) {
        return null;
    }

    @Override
    void storeCredentials(String userId, Credential credentials) {
        GoogleOauthCredential user = new GoogleOauthCredential();
        user.setGoogleUserId(userId);
        user.setGoogleAccessToken(credentials.getAccessToken());
        user.setGoogleRefreshToken(credentials.getRefreshToken());
        this.getGoogleOauthCredentialRepository().save(user);
    }

    public GoogleOauthCredentialRepository getGoogleOauthCredentialRepository() {
        return googleOauthCredentialRepository;
    }

    public void setGoogleOauthCredentialRepository(GoogleOauthCredentialRepository googleOauthCredentialRepository) {
        this.googleOauthCredentialRepository = googleOauthCredentialRepository;
    }
}