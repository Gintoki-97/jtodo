package cn.gin.module.config.security.oauth.wechat.service.operation;

import cn.gin.common.Constants;
import cn.gin.module.config.security.oauth.wechat.connection.WeChatAccessGrant;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * The component of service provider in Spring Social is used to provide the operation method of OAuth2
 */
public class WeChatOAuth2Template extends OAuth2Template {

    /**
     * The default root logger
     */
    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Get WeChat authentication access token
     */
    private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?" +
                    "grant_type=client_credential&appid=%s&secret=%s";

    /**
     * Refresh WeChat authentication access token
     */
    private static final String URL_REFRESH_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?";

    @Autowired
    private ObjectMapper mapper;

    /**
     * Client ID means APPID of WeChat, because of this parameter in OAuth2 names clientId
     */
    private String clientId;

    /**
     * Client secret means APPID of WeChat, because of this parameter in OAuth2 names clientSecret
     */
    private String clientSecret;

    /**
     * Get the URL requested by access Token
     */
    private String accessTokenUrl;


    public WeChatOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.accessTokenUrl = accessTokenUrl;
    }

    @Override
    public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri,
                                         MultiValueMap<String, String> additionalParameters) {

        StringBuilder builder = new StringBuilder(accessTokenUrl);
        builder.append("?appid" + clientId);
        builder.append("&secret=" + clientSecret);
        builder.append("&code=" + authorizationCode);
        builder.append("&redirect_uri=" + redirectUri);
        builder.append("&grant_type=client_credential");

        return getAccessToken(builder);
    }

    @Override
    public AccessGrant refreshAccess(String refreshToken, MultiValueMap<String, String> additionalParameters) {

        StringBuilder builder = new StringBuilder(WeChatOAuth2Template.URL_REFRESH_ACCESS_TOKEN);
        builder.append("?appid" + clientId);
        builder.append("&refresh_token=" + refreshToken);
        builder.append("&grant_type=refresh_token");

        return super.refreshAccess(refreshToken, additionalParameters);
    }

    /**
     * <p>Override the default behavior of spring social, get the access token in the way of WeChat
     * developer documentation</p>
     *
     * @param accessTokenRequestUrl Get WeChat authentication access token
     * @return {@link AccessGrant}
     */
    private AccessGrant getAccessToken(StringBuilder accessTokenRequestUrl) {

        String accessTokenUrl = accessTokenRequestUrl.toString();

        log.info("Request the WeChat access token, request URL = {}", accessTokenUrl);
        String resp = getRestTemplate().getForObject(accessTokenUrl, String.class);
        log.info("Access token has been obtained, the response content = {}", resp);

        Map<String, Object> result = null;

        try {
            result = mapper.readValue(resp, Map.class);
        } catch (Exception exception) {
            log.error("An error occurred when resolving the return value of the " +
                    "\"WeChat access token request\" to map.", exception);
        }

        if (StringUtils.isBlank(MapUtils.getString(result, "errcode"))) {

            String errcode = MapUtils.getString(result, "errcode");
            String errmsg = MapUtils.getString(result, "errmsg");

            throw new RuntimeException(String.format("Request WeChat access token failed, " +
                    "{errcode = %s, errmsg = %s}", errcode, errmsg));
        }

        WeChatAccessGrant accessGrant = new WeChatAccessGrant(
                MapUtils.getString(result, "access_token"),
                MapUtils.getString(result, "scope"),
                MapUtils.getString(result, "refresh_token"),
                MapUtils.getLong(result, "expires_in")
        );
        accessGrant.setOpenid(MapUtils.getString(result, "openid"));

        return accessGrant;
    }

    /**
     * <p>Build the URL to get the authorization code request, that is, to get the URL that guides the user
     * into the authentication server in OAuth2</p>
     *
     * <p>The parameters contains the OAuth2 required parameters, but the 3rd OAuth service provider maybe has
     * its own parameters, such as the "scope" parameter is WeChat required but the OAuth2 protocol has no rules</p>
     *
     * @param parameters Contains the OAuth2 required parameters
     * @return Authenticate URL, this URL towards to the 3rd OAuth service provider (WeChat)
     */
    @Override
    public String buildAuthenticateUrl(OAuth2Parameters parameters) {

        String url = super.buildAuthenticateUrl(parameters);
        url = url + "&scope=snsapi_userinfo&appid=" + clientId;
        return url;
    }

    @Override
    public String buildAuthorizeUrl(OAuth2Parameters parameters) {

        return buildAuthenticateUrl(parameters);
    }

    /**
     * The response content type of WeChat is "html/text", it needs the specified message convert
     *
     * @return {@link RestTemplate}
     */
    @Override
    protected RestTemplate createRestTemplate() {

        RestTemplate template = super.createRestTemplate();
        template.getMessageConverters()
                .add(new StringHttpMessageConverter(Charset.forName(Constants.System.ENCODING)));

        return template;
    }
}