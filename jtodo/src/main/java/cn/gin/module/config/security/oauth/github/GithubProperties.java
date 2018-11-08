package cn.gin.module.config.security.oauth.github;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * Github properties storage
 */
public class GithubProperties extends SocialProperties {

    private String providerId;

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
