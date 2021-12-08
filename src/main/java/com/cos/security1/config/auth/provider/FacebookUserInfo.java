package com.cos.security1.config.auth.provider;

import java.util.Map;

public class FacebookUserInfo implements OAuth2UserInfo {

    private Map<String,Object> attributes;

    public FacebookUserInfo(Map<String,Object> attributes){
        this.attributes = attributes;
    }
    @Override
    public String getProciderId() {
        return (String) attributes.get("id");
    }

    @Override
    public String getProcider() {
        return "google";
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getname() {
        return (String) attributes.get("name");
    }
}
