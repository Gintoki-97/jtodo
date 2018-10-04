package cn.gin.module.config.security.oauth.github.service.api;

import cn.gin.common.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Implementation of Github OAuth service provider API interface in Spring Social
 */
public class GithubApiImpl extends AbstractOAuth2ApiBinding implements GithubApi {

    /**
     * The default root logger
     */
    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Get Github user's details information
     */
    private static final String URL_USER_DETAILS = "https://api.github.com/user";

    @Autowired
    private ObjectMapper mapper;

    public GithubApiImpl(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    @Override
    public GithubUserDetails getGithubUserDetails() {

        String resp = getRestTemplate().getForObject(URL_USER_DETAILS, String.class);
        GithubUserDetails userDetails = null;

        try {
            userDetails = mapper.readValue(resp, GithubUserDetails.class);
        } catch (IOException ioException) {
            log.error("Convert the github response content to a GithubUserDetails object failed", ioException);
        }

        return userDetails;
    }

    /**
     * Update the default encoding from ISO8859-1 to UTF-8
     */
    @Override
    protected List<HttpMessageConverter<?>> getMessageConverters() {

        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName(Constants.System.ENCODING)));

        return messageConverters;
    }
}
