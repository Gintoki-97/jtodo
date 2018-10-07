package cn.gin.module.config.security.oauth;

import cn.gin.module.config.security.WebSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;

import javax.sql.DataSource;

@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private WebSecurityProperties securityProperties;

    @Autowired
    private AutoConnectionSignup connectionSignup;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {

        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator, Encryptors.noOpText());
        repository.setConnectionSignUp(connectionSignup);

        return repository;
    }

    @Bean
    public SpringSocialConfigurer socialSecurityConfig() {

        String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();

        return new SpringSocialConfigurer(filterProcessesUrl);
    }
}
