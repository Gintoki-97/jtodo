package cn.gin.module.config.security.oauth.github;

import cn.gin.module.config.security.WebSecurityProperties;
import cn.gin.module.config.security.oauth.SocialProperties;
import cn.gin.module.config.security.oauth.github.connection.GithubConnectionFactory;
import cn.gin.module.config.security.oauth.wechat.connection.WeChatConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;

public class GithubAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private WebSecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {

        SocialProperties social = securityProperties.getSocial();

        return new GithubConnectionFactory(
                social.getGithub().getProviderId(),
                social.getGithub().getAppId(),
                social.getGithub().getAppSecret());
    }
}
