package cn.gin.module.config.security.oauth.github.service.api;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;

import java.util.Collection;

/**
 * The entity class contains that the response user information that provided by Github OAuth API
 */
public class GithubUserDetails extends SocialUser {

    public GithubUserDetails(String username, String password,
                             Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public GithubUserDetails(String username, String password, boolean enabled,
                             boolean accountNonExpired, boolean credentialsNonExpired,
                             boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
