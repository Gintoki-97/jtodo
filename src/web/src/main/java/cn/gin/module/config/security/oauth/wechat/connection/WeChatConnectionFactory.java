package cn.gin.module.config.security.oauth.wechat.connection;

import cn.gin.module.config.security.oauth.wechat.service.WeChatServiceProvider;
import cn.gin.module.config.security.oauth.wechat.service.api.WeChatApi;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * Factory classes for building OAuth Connection
 */
public class WeChatConnectionFactory extends OAuth2ConnectionFactory<WeChatApi> {

    public WeChatConnectionFactory(String providerId, String appId, String secret) {

        super(providerId, new WeChatServiceProvider(appId, secret), new WeChatAdaptor());
    }

    @Override
    protected String extractProviderUserId(AccessGrant accessGrant) {

        if (accessGrant instanceof WeChatAccessGrant) {
            return ((WeChatAccessGrant) accessGrant).getOpenid();
        }

        return null;
    }

    @Override
    public Connection<WeChatApi> createConnection(AccessGrant accessGrant) {

        return new OAuth2Connection<WeChatApi>(getProviderId(), extractProviderUserId(accessGrant),
                accessGrant.getAccessToken(), accessGrant.getRefreshToken(), accessGrant.getExpireTime(),
                getOAuth2ServiceProvider(),  getApiAdapter(extractProviderUserId(accessGrant)));
    }

    @Override
    public Connection<WeChatApi> createConnection(ConnectionData data) {

        return new OAuth2Connection<WeChatApi>(data, getOAuth2ServiceProvider(),
                getApiAdapter(data.getProviderUserId()));
    }

    private ApiAdapter<WeChatApi> getApiAdapter(String providerUserId) {

        return new WeChatAdaptor(providerUserId);
    }

    private OAuth2ServiceProvider getOAuth2ServiceProvider() {

        return (OAuth2ServiceProvider<WeChatApi>) getServiceProvider();
    }
}
