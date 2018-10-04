package cn.gin.module.config.security.oauth.wechat.service;

import cn.gin.module.config.security.oauth.wechat.service.operation.WeChatOAuth2Template;
import cn.gin.module.config.security.oauth.wechat.service.api.WeChatApi;
import cn.gin.module.config.security.oauth.wechat.service.api.WeChatApiImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * The OAuth2 service provider of WeChat
 */
public class WeChatServiceProvider extends AbstractOAuth2ServiceProvider<WeChatApi> {

    /**
     * Authorized interface provided by WeChat. If the user agrees to authorize, a code can be returned.
     * The returned <code>code</code> could used to exchange the access token
     *
     * <pre>
     *     APPID            The only identification of public platform account
     *     REDIRECT_URI     After callback, the callback link address is redirected, need URLEncode
     *     SCOPE            Application authorization scope
     *                          snsapi_base     Can only get the user's openid
     *                          snsapi_userinfo Can get the openid and other information
     *     STATE            The state parameter will be brought back after the reset. The developer can
     *                      fill in the a-zA-Z0-9 parameter value, up to 128 bytes
     * </pre>
     */
    private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";

    /**
     * Get access token through the last step response code
     */
    private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

    public WeChatServiceProvider(String appId, String secret) {
        super(new WeChatOAuth2Template(appId, secret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
    }

    @Override
    public WeChatApi getApi(String accessToken) {

        return new WeChatApiImpl(accessToken);
    }
}