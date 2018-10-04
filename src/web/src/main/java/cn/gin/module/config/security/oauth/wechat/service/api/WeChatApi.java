package cn.gin.module.config.security.oauth.wechat.service.api;

/**
 * Spring Social api interface implementation for WeChat
 */
public interface WeChatApi {

    /**
     * Get the WeChat user information
     *
     * @param openid WeChat user openid
     * @return The specified WeChat user information
     */
    WeChatUserDetails getWeChatUserDetails(String openid);
}
