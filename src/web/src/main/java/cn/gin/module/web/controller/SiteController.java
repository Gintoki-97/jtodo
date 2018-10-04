package cn.gin.module.web.controller;

import cn.gin.common.Constants;
import cn.gin.common.util.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiteController {

    @GetMapping(Constants.Path.CTRL_SITE_INDEX)
    public String index() {

        return JsonObject.ok("Hello world!");
    }
}
