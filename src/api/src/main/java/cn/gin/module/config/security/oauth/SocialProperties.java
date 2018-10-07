package cn.gin.module.config.security.oauth;

import cn.gin.module.config.security.oauth.github.GithubProperties;
import cn.gin.module.config.security.oauth.wechat.WeChatProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Social properties storage
 */
public class SocialProperties {

    /**
     * URL that spring social will intercept, the default URL prefix is '/auth'
     */
    private String filterProcessesUrl = "/auth";

    /**
     * WeChat properties storage
     */
    private WeChatProperties weChat = new WeChatProperties();

    /**
     * Github properties storage
     */
    private GithubProperties github = new GithubProperties();

    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    public WeChatProperties getWeChat() {
        return weChat;
    }

    public void setWeChat(WeChatProperties weChat) {
        this.weChat = weChat;
    }

    public GithubProperties getGithub() {
        return github;
    }

    public void setGithub(GithubProperties github) {
        this.github = github;
    }
}
