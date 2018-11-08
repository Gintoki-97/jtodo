package cn.gin.module.web.controller;

import cn.gin.common.Constants;
import cn.gin.common.util.JsonObject;
import cn.gin.common.util.Servlets;
import cn.gin.module.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SiteController {

    @Autowired
    private ObjectMapper mapper;

    /**
     * The root URL of website, will jump according to whether the current user is logged in
     *
     * @return Redirect to passport controller if the user is not logged in
     */
    @GetMapping({Constants.Symbol.EMPTY, Constants.Path.CTRL_SITE_INDEX})
    public String entry() {

        UserDetails currentUser = UserService.getCurrentUser();

        if (currentUser != null) {
            return Servlets.redirect(Constants.Path.CTRL_TODO);
        }

        return Servlets.redirect(Constants.Path.CTRL_SITE_PASSPORT);
    }

    /**
     * Passport view request. Redirect to to do list page if the user is logged in
     *
     * @return Passport view
     */
    @GetMapping(Constants.Path.CTRL_SITE_PASSPORT)
    public String passport(Model model) {

        UserDetails currentUser = UserService.getCurrentUser();

        if (currentUser != null) {
            return Servlets.redirect(Constants.Path.CTRL_TODO);
        }

        return Constants.Path.VIEW_SITE_PASSPORT;
    }

    @GetMapping(Constants.Path.CTRL_SITE_HELLO)
    public String hello() {

        return JsonObject.ok("Hello, world!");
    }
}