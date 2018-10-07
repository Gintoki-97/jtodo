package cn.gin.module.config.security.oauth.github.connection;

import cn.gin.module.config.security.oauth.github.service.api.GithubApi;
import cn.gin.module.config.security.oauth.github.service.api.GithubUserDetails;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class GithubAdaptor implements ApiAdapter<GithubApi> {

    public GithubAdaptor() {

    }

    /**
     * Needn't test, this method return whether the service provider worked well
     *
     * @param api {@link GithubApi}
     * @return Always return <code>true</code>
     */
    @Override
    public boolean test(GithubApi api) {
        return true;
    }

    @Override
    public void setConnectionValues(GithubApi api, ConnectionValues values) {

        GithubUserDetails userDetails = api.getGithubUserDetails();
        values.setProviderUserId(String.valueOf(userDetails.getOpenid()));
        values.setDisplayName(userDetails.getNickname());
        values.setImageUrl(userDetails.getAvatarUrl());
        values.setProfileUrl(userDetails.getProfileUrl());
    }

    @Override
    public UserProfile fetchUserProfile(GithubApi api) {
        return null;
    }

    @Override
    public void updateStatus(GithubApi api, String message) {

    }
}
