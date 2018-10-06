package cn.gin.module.config.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

/**
 * The interface provided by spring security to customize user information for application
 */
@Service
public class UserDetailsService implements
        org.springframework.security.core.userdetails.UserDetailsService,
        SocialUserDetailsService {

    /**
     * The default root logger
     */
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("Form sign in for user name [{}]", username);

        return createUser(username);
    }


    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {

        log.info("Social sign in for user id [{}]", userId);

        return createUser(userId);
    }

    /**
     * Temporary method for test, create a new user use the default password, this method makes each
     * user is legal
     *
     * @param userId The identifier of the user (just require a unique key)
     * @return A social user object
     */
    private SocialUserDetails createUser(String userId) {

        String password = passwordEncoder.encode("123456");
        log.info("The password of user [{}] in database is [{}]", userId, password);

        return new SocialUser(userId, password,
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
