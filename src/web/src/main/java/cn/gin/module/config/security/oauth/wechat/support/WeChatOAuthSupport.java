package cn.gin.module.config.security.oauth.wechat.support;

import cn.gin.common.Constants;
import cn.gin.common.util.JCodec;
import cn.gin.common.util.StringUtils;
import cn.gin.module.config.security.WebSecurityProperties;
import cn.gin.module.config.security.oauth.SocialProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeChatOAuthSupport {

    @Autowired
    private WebSecurityProperties securityProperties;

    /**
     * Guiding users into URL of WeChat authentication server (WeChat open platform)
     */
    public static final String URL_CODE = "https://open.weixin.qq.com/connect/qrconnect?" +
            "appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_login&state=%s#wechat_redirect";

    /**
     * Guiding users into URL of WeChat authentication server (WeChat mp platform)
     */
    public static final String URL_CODE_MP = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
            "appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=%s#wechat_redirect";


    /**
     * Get the URL that could guiding users into WeChat authentication server, it's the 1st step of the
     * OAuth2 to request the authorization code, and then our program could exchange the access token
     * through this authorization code
     *
     * @param redirectUrl The address that WeChat will redirect to the user after consent is authorized,
     *                    usually the page that the user visited before or the homepage
     * @param state The extended parameters provided by WeChat are used to implement the security
     *              policy of the 3rd party application
     * @return The URL that could guiding users into WeChat authentication server
     */
    public String getAuthorizeUrl(String redirectUrl, String state) {

        if (StringUtils.isEmpty(redirectUrl)) {
            redirectUrl = "https://www.360.com";
        }
        redirectUrl = JCodec.urlEncode(redirectUrl);
        state = state == null ? Constants.Symbol.EMPTY : state;

        SocialProperties social = securityProperties.getSocial();
        String url = String.format(URL_CODE_MP, social.getWeChat().getAppId(), redirectUrl, state);

        return url;
    }
}
