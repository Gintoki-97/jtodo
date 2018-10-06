package cn.gin.module.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static boolean isAuthenticated() {

        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }
}
