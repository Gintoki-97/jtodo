package cn.gin.module.config.security;

import cn.gin.module.config.security.oauth.SocialProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application.security")
public class WebSecurityProperties {

    /**
     * Spring Social properties storage
     */
    private SocialProperties social = new SocialProperties();

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }
}
