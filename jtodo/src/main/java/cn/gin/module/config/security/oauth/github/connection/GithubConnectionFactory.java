package cn.gin.module.config.security.oauth.github.connection;

import cn.gin.module.config.security.oauth.github.service.GithubServiceProvider;
import cn.gin.module.config.security.oauth.github.service.api.GithubApi;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * Factory classes for building OAuth Connection
 */
public class GithubConnectionFactory extends OAuth2ConnectionFactory<GithubApi> {

    public GithubConnectionFactory(String providerId, String clientId, String clientSecret) {
        super(providerId, new GithubServiceProvider(clientId, clientSecret), new GithubAdaptor());
    }

    public Connection<GithubApi> createConnection(ConnectionData data) {

        return new OAuth2Connection<GithubApi>(data, getOAuth2ServiceProvider(),
                getApiAdapter());
    }

    private OAuth2ServiceProvider getOAuth2ServiceProvider() {

        return (OAuth2ServiceProvider<GithubApi>) getServiceProvider();
    }
}
