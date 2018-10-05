package cn.gin.module.config.security.oauth.github.service;

import cn.gin.module.config.security.oauth.github.service.api.GithubApi;
import cn.gin.module.config.security.oauth.github.service.api.GithubApiImpl;
import cn.gin.module.config.security.oauth.github.service.operation.GithubOAuth2Template;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * The OAuth2 service provider of Github
 */
public class GithubServiceProvider extends AbstractOAuth2ServiceProvider<GithubApi> {

    /**
     * Authorized interface provided by Github. If the user agrees to authorize, a code can be returned.
     * The returned <code>code</code> could used to exchange the access token
     */
    public static final String URL_AUTHORIZE = "https://github.com/login/oauth/authorize";

    /**
     * Get access token through the last step response code
     */
    private static final String URL_ACCESS_TOKEN = "https://github.com/login/oauth/access_token";

    public GithubServiceProvider(String clientId, String clientSecret) {
        super(new GithubOAuth2Template(clientId, clientSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
    }

    @Override
    public GithubApi getApi(String accessToken) {

        return new GithubApiImpl(accessToken);
    }
}