package cn.gin.module.config.security.oauth.github.service.operation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * The component of Github service provider in Spring Social is used to provide the operation method of OAuth2
 */
public class GithubOAuth2Template extends OAuth2Template {

    /**
     * The default root logger
     */
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper mapper;

    public GithubOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }
}
