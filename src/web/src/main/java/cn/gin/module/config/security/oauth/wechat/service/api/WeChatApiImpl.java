package cn.gin.module.config.security.oauth.wechat.service.api;

import cn.gin.common.Constants;
import cn.gin.common.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Implementation of WeChat OAuth service provider API interface in Spring Social
 */
public class WeChatApiImpl extends AbstractOAuth2ApiBinding implements WeChatApi {

    /**
     * The default root logger
     */
    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Get WeChat user's details information
     */
    private static final String URL_USER_DETAILS = "https://api.weixin.qq.com/sns/userinfo?openid=%s";

    @Autowired
    private ObjectMapper mapper;

    /**
     * <p>Override constructor to change the access token strategy</p>
     *
     * <p>Use the ACCESS_TOKEN_PARAMETER strategy, the access token parameter is automatically processed
     * when the request is made, and the access token is appended to the request parameter at each request</p>
     *
     * @param accessToken Incoming parameter access token by Spring Security
     */
    public WeChatApiImpl(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    @Override
    public WeChatUserDetails getWeChatUserDetails(String openid) {

        String url  = String.format(URL_USER_DETAILS, openid);
        String resp = getRestTemplate().getForObject(url, String.class);

        if (StringUtils.contains(resp, "errcode")) {

            return null;
        }

        WeChatUserDetails userDetails = null;

        try {
            userDetails = mapper.readValue(resp, WeChatUserDetails.class);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
        return userDetails;
    }

    /**
     * Update the default encoding from ISO8859-1 to UTF-8
     */
    @Override
    protected List<HttpMessageConverter<?>> getMessageConverters() {

        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName(Constants.System.ENCODING)));

        return messageConverters;
    }
}