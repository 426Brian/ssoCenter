package com.sso.bean;

/**
 * Created by Brian in 1:21 2018/12/12
 */
public class ClientInfo {
    private String clientUrl;
    private String jsessionid;

    public String getClientUrl() {
        return clientUrl;
    }

    public void setClientUrl(String clientUrl) {
        this.clientUrl = clientUrl;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }
}
