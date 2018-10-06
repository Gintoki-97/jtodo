package cn.gin.module.config.security.oauth.wechat.connection;

import cn.gin.module.config.security.oauth.wechat.service.api.WeChatApi;
import cn.gin.module.config.security.oauth.wechat.service.api.WeChatUserDetails;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * Convert the user information format from 3rd format (WeChat) to Spring Social general format
 */
public class WeChatAdaptor implements ApiAdapter<WeChatApi> {

    private String openid;

    public WeChatAdaptor() {

    }

    public WeChatAdaptor(String openid) {
        this.openid = openid;
    }

    /**
     * Needn't test, this method return whether the service provider worked well
     *
     * @param api {@link WeChatApi}
     * @return Always return <code>true</code>
     */
    @Override
    public boolean test(WeChatApi api) {
        return true;
    }

    @Override
    public void setConnectionValues(WeChatApi api, ConnectionValues values) {

        WeChatUserDetails userDetails = api.getWeChatUserDetails(openid);
        values.setProviderUserId(userDetails.getOpenid());
        values.setDisplayName(userDetails.getNickname());
        values.setImageUrl(userDetails.getHeadImageUrl());
    }

    /**
     * The WeChat has no profile page for each user, so just need returned <code>null</code>
     *
     * @param api {@link WeChatApi}
     * @return Always return <code>null</code>
     */
    @Override
    public UserProfile fetchUserProfile(WeChatApi api) {
        return null;
    }

    @Override
    public void updateStatus(WeChatApi api, String message) {

    }
}
