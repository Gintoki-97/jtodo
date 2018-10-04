package cn.gin.module.config.security.oauth.wechat.connection;

import org.springframework.social.oauth2.AccessGrant;

/**
 * When processing the access token returned by WeChat, read the openid returned at the same time
 */
public class WeChatAccessGrant extends AccessGrant {

    /**
     * <p>WeChat's authentication process is somewhat different from OAuth2, which returns openid and access token
     * together after the user has logged in with the scanner QR code</p>
     *
     * <p>In the default oauth2 process, the return of access token and openid is not in the same request</p>
     */
    private String openid;

    public WeChatAccessGrant() {
        super("");
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public WeChatAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {

        super(accessToken, scope, refreshToken, expiresIn);
    }
}