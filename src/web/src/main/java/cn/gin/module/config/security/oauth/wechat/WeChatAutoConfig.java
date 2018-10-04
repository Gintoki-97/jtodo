package cn.gin.module.config.security.oauth.wechat;

import cn.gin.module.config.security.WebSecurityProperties;
import cn.gin.module.config.security.oauth.SocialProperties;
import cn.gin.module.config.security.oauth.wechat.connection.WeChatConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

@Configuration
@ConditionalOnProperty(prefix = "todo.security.social.wechat", name="app-id")
public class WeChatAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private WebSecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {

        SocialProperties social = securityProperties.getSocial();

        return new WeChatConnectionFactory(
                social.getWeChat().getProviderId(),
                social.getWeChat().getAppId(),
                social.getWeChat().getAppSecret());
    }
}