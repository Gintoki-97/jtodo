package cn.gin.module.config.security.oauth.github.support;

import cn.gin.common.Constants;
import cn.gin.common.util.JCodec;
import cn.gin.common.util.StringUtils;
import cn.gin.module.config.security.WebSecurityProperties;
import cn.gin.module.config.security.oauth.SocialProperties;
import cn.gin.module.config.security.oauth.github.connection.GithubConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Component;

@Component
public class GithubOAuthSupport {

    @Autowired
    private WebSecurityProperties securityProperties;

    /**
     * Get the URL that could guiding users into WeChat authentication server, it's the 1st step of the
     * OAuth2 to request the authorization code, and then our program could exchange the access token
     * through this authorization code
     *
     * @param redirectUrl The address that WeChat will redirect to the user after consent is authorized,
     *                    usually the page that the user visited before or the homepage
     * @param state The extended parameters provided by WeChat are used to implement the security
     *              policy of the 3rd party application
     * @return The URL that could guiding users into WeChat authentication server
     */
    public String getAuthorizeUrl(String redirectUrl, String state) {

        if (StringUtils.isEmpty(redirectUrl)) {
            redirectUrl = "http://106.14.157.181:8100/todo";
        }

        SocialProperties social = securityProperties.getSocial();
        GithubConnectionFactory connectionFactory = new GithubConnectionFactory(
                social.getGithub().getProviderId(),
                social.getGithub().getAppId(),
                social.getGithub().getAppSecret());
        OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
        OAuth2Parameters params = new OAuth2Parameters();
        params.setRedirectUri(redirectUrl);
        if (!StringUtils.isBlank(state)) {
            params.setState(state);
        }
        String authorizeUrl = oauthOperations.buildAuthorizeUrl(null, params);

        return authorizeUrl;
    }
}