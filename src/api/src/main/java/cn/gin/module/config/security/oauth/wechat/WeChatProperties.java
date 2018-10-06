package cn.gin.module.config.security.oauth.wechat;

import org.springframework.boot.autoconfigure.social.SocialProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * WeChat properties storage
 */
public class WeChatProperties extends SocialProperties {

    private String providerId;

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
