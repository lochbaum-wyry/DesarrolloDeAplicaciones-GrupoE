package helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.json.jackson.JacksonFactory;
import domain.repositories.GoogleOauthCredentialRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

@Component("clientFactory")
public class ClientFactory {

    // Path to client_secrets.json which should contain a JSON document such as:
    //   {
    //     "web": {
    //       "client_id": "[[YOUR_CLIENT_ID]]",
    //       "client_secret": "[[YOUR_CLIENT_SECRET]]",
    //       "auth_uri": "https://accounts.google.com/o/oauth2/auth",
    //       "token_uri": "https://accounts.google.com/o/oauth2/token"
    //     }
    //   }

    public ClientFactory(){}

    //@Value("#{ systemProperties['google.clientId'] }")
    private String envClientId = "845978166156-kfuknnh0pr4nboqmkjme9dtpjgv0v2h9.apps.googleusercontent.com";

    //@Value("#{ systemProperties['google.clientSecret'] }")
    private String envClientSecret = "uXFDuMaDA8ViCJfcb4evMKji";

    //@Value("#{ systemProperties['google.redirectURI'] }")
    private String envRedirectURI = "http://localhost:9000";

    public AuthClient getDefaultClient(GoogleOauthCredentialRepository googleOauthCredentialRepository) {
        try {
            return new AuthToUserClient(this.getCredentials(), this.getScopes(), this.getRedirectURI(), googleOauthCredentialRepository);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    private List<String> getScopes() {
        return Arrays.asList("https://www.googleapis.com/auth/glass.timeline",
                "https://www.googleapis.com/auth/userinfo.profile");
    }

    private String getRedirectURI() {
        return this.envRedirectURI;
    }

    private GoogleClientSecrets getCredentials() throws IOException {
        JacksonFactory jsonFactory = new JacksonFactory();
        StringReader reader = new StringReader(new ObjectMapper().writeValueAsString(new Credentials(this.getWebCredentials())));
        return GoogleClientSecrets.load(jsonFactory, reader);
    }

    private WebCredentials getWebCredentials() {
        return new WebCredentials(this.envClientId, this.envClientSecret);
    }

    private class WebCredentials {
        private String client_id;
        private String client_secret;
        private String auth_uri;
        private String token_uri;

        public WebCredentials(){}

        WebCredentials(String client_id, String client_secret) {
            this.auth_uri = "https://accounts.google.com/o/oauth2/auth";
            this.client_id = client_id;
            this.client_secret = client_secret;
            this.token_uri = "https://accounts.google.com/o/oauth2/token";
        }

        public String getClient_id() {
            return client_id;
        }

        public void setClient_id(String client_id) {
            this.client_id = client_id;
        }

        public String getClient_secret() {
            return client_secret;
        }

        public void setClient_secret(String client_secret) {
            this.client_secret = client_secret;
        }

        public String getAuth_uri() {
            return auth_uri;
        }

        public void setAuth_uri(String auth_uri) {
            this.auth_uri = auth_uri;
        }

        public String getToken_uri() {
            return token_uri;
        }

        public void setToken_uri(String token_uri) {
            this.token_uri = token_uri;
        }
    }

    class Credentials {
        private WebCredentials web;

        public Credentials(){}

        public Credentials(WebCredentials web) {
            this.setWeb(web);
        }

        public WebCredentials getWeb() {
            return web;
        }

        public void setWeb(WebCredentials web) {
            this.web = web;
        }
    }
}
