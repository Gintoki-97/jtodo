package cn.gin.module.web.controller;

import cn.gin.common.Constants;
import cn.gin.common.util.JsonObject;
import cn.gin.module.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiteController {

    @Autowired
    private ObjectMapper mapper;

    @GetMapping({Constants.Symbol.EMPTY, Constants.Path.CTRL_SITE_INDEX})
    public String index() {

        UserDetails userDetails = UserService.getCurrentUser();
        JsonObject json = new JsonObject(true, "Request success");
        json.addParam("user", userDetails);

        return json.toJson();
    }

    @GetMapping(Constants.Path.CTRL_SITE_AUTH_GITHUB)
    public String authGithub() {

        return JsonObject.ok("Request success");
    }
}