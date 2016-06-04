package domain.services;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.oauth2.model.Userinfoplus;
import domain.GoogleOauthCredential;
import domain.repositories.GoogleOauthCredentialRepository;

import helpers.AuthClient;
import helpers.ClientFactory;
import helpers.exceptions.CodeExchangeException;
import helpers.exceptions.NoRefreshTokenException;
import helpers.exceptions.NoUserIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

public class GoogleCredentialsService {

    private GoogleOauthCredentialRepository googleOauthCredentialRepository;
    private ClientFactory clientFactory;

    public GoogleCredentialsService(){}

    public GoogleCredentialsService(GoogleOauthCredentialRepository googleOauthCredentialRepository,ClientFactory clientFactory){
        this.googleOauthCredentialRepository = googleOauthCredentialRepository;
        this.clientFactory = clientFactory;
    }

    @Transactional
    public GoogleOauthCredential get(String userId) {
        return this.getGoogleOauthCredentialRepository().findByUserId(userId);
    }

    @Transactional
    public Userinfoplus getUserinfo(Credential credential) {
        try {
            return this.getClientFactory().getDefaultClient(this.getGoogleOauthCredentialRepository()).getUserInfo(credential);
        } catch (NoUserIdException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public Credential create(String authorizationCode) {

        AuthClient authClient = this.getClientFactory().getDefaultClient(this.getGoogleOauthCredentialRepository());
        try {
            return authClient.getCredentials(authorizationCode, "failed");
        } catch (CodeExchangeException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (NoRefreshTokenException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public GoogleOauthCredentialRepository getGoogleOauthCredentialRepository() {
        return googleOauthCredentialRepository;
    }

    @Autowired
    public void setGoogleOauthCredentialRepository(GoogleOauthCredentialRepository googleOauthCredentialRepository) {
        this.googleOauthCredentialRepository = googleOauthCredentialRepository;
    }

    public ClientFactory getClientFactory() {
        return clientFactory;
    }

    @Autowired
    public void setClientFactory(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }
}
