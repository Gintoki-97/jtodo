package cn.gin.common.util;

import cn.gin.common.Constants;

public class Servlets {

    /**
     * Build the redirect URL
     *
     * @param url The URL that need to redirect
     * @return Wrapped URL
     */
    public static String redirect(String url) {

        return "redirect:" + (StringUtils.isBlank(url) ? Constants.Symbol.EMPTY : url);
    }
}
