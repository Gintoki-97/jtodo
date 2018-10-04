package cn.gin.module.config.security.oauth.wechat.service.api;

/**
 * The entity class contains that the response data of WeChat access token request
 */
public class WeChatUserDetails {

    /**
     * Authorized user unique identifier (In an application)
     */
    private String openid;

    /**
     * This field will appear only if and only if the website application has obtained the userinfo authorization
     * of the user
     */
    private String unionid;

    /**
     * The nickname of current user
     */
    private String nickname;

    /**
     * The gender of current user, 1 means Male and 2 means female
     */
    private Integer sex;

    /**
     * Provinces where ordinary user profiles are filled out
     */
    private String province;

    /**
     * City where ordinary user profiles are filled out
     */
    private String city;

    /**
     * Countries such as China for CN
     */
    private String country;

    /**
     * User avatar, the last value represents the size of the square avatar (0, 46, 64, 96, 132 values are optional,
     * 0 is 640 * 640 square avatar), the item is empty when the user has no avatar
     */
    private String headImageUrl;

    /**
     * User privilege information, json array
     */
    private Object privilege;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    public Object getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Object privilege) {
        this.privilege = privilege;
    }
}