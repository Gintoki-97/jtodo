package cn.gin.module.web.controller;

import cn.gin.common.Constants;
import cn.gin.module.config.security.oauth.github.support.GithubOAuthSupport;
import cn.gin.module.config.security.oauth.wechat.support.WeChatOAuthSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(Constants.Path.CTRL_USER)
public class UserController {

    @Autowired
    private WeChatOAuthSupport weChatOAuthSupport;

    @Autowired
    private GithubOAuthSupport githubOAuthSupport;

    @GetMapping(Constants.Path.CTRL_USER_DETAIL)
    public String detail(Model model) {

        String wechatAuthorizeUrl = weChatOAuthSupport.getAuthorizeUrl(
                "http://106.14.157.181:8100/todo", "");
        String githubAuthorizeUrl = githubOAuthSupport.getAuthorizeUrl(
                "http://106.14.157.181:8100/todo", "");
        model.addAttribute("wechatAuthorizeUrl", wechatAuthorizeUrl);
        model.addAttribute("githubAuthorizeUrl", githubAuthorizeUrl);

        return "user";
    }
}
