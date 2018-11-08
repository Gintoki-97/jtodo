package cn.gin.module.web.controller;

import cn.gin.common.Constants;
import cn.gin.common.util.JsonObject;
import cn.gin.module.config.security.oauth.github.support.GithubOAuthSupport;
import cn.gin.module.config.security.oauth.wechat.support.WeChatOAuthSupport;
import cn.gin.module.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(Constants.Path.CTRL_USER)
public class UserController {

    @Autowired
    private WeChatOAuthSupport weChatOAuthSupport;

    @Autowired
    private GithubOAuthSupport githubOAuthSupport;

    //// Non REST interface

    @GetMapping(Constants.Path.CTRL_USER_DETAIL)
    public String detail(Model model) {

        model.addAttribute("wechatAuthorizeUrl", "/user/auth/wechat");
        model.addAttribute("githubAuthorizeUrl", "/user/auth/github");

        return "user";
    }

    @GetMapping(Constants.Path.CTRL_USER_AUTH_GITHUB)
    @ResponseBody
    public String authGithub() {

        return JsonObject.ok("Request github oauth success");
    }

    @GetMapping(Constants.Path.CTRL_USER_AUTH_WECHAT)
    @ResponseBody
    public String authWeChat() {

        return JsonObject.ok("Request wechat oauth success");
    }

    //// REST interface

    @GetMapping({Constants.Symbol.EMPTY, Constants.Path.CTRL_USER_INDEX})
    @ResponseBody
    public String currentUser() {

        UserDetails currentUser = UserService.getCurrentUser();
        JsonObject jsonObject = new JsonObject();

        if (currentUser == null) {
            jsonObject.setCode(1);
            jsonObject.setSuccess(false);
            jsonObject.setMsg("No user signin");
        } else {
            jsonObject.setCode(0);
            jsonObject.setSuccess(true);
            jsonObject.setMsg("Request success");
            jsonObject.addParam("user", currentUser);
        }

        return jsonObject.toJson();
    }
}
