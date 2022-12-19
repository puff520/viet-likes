package com.likes.common.util.sms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class CloudFastConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String appId;
    public static String sid;
    public static String token;
    public static String templateid;
    public static String url = "https://open.ucpaas.com/ol/sms/sendsms";
    public static String cloudRegisterTempl;
    public static String cloudResetCodeTempl;
    public static String cloudResetNumTempl;

    public static String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        CloudFastConfig.appId = appId;
    }

    public static String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        CloudFastConfig.sid = sid;
    }

    public static String getToken() {
        return token;
    }

    public void setToken(String token) {
        CloudFastConfig.token = token;
    }

    public static String getTemplateid() {
        return templateid;
    }

    public void setTemplateid(String templateid) {
        CloudFastConfig.templateid = templateid;
    }

    public static String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        CloudFastConfig.url = url;
    }

    public static String getCloudRegisterTempl() {
        return cloudRegisterTempl;
    }

    public void setCloudRegisterTempl(String cloudRegisterTempl) {
        CloudFastConfig.cloudRegisterTempl = cloudRegisterTempl;
    }

    public static String getCloudResetCodeTempl() {
        return cloudResetCodeTempl;
    }

    public void setCloudResetCodeTempl(String cloudResetCodeTempl) {
        CloudFastConfig.cloudResetCodeTempl = cloudResetCodeTempl;
    }

    public static String getCloudResetNumTempl() {
        return cloudResetNumTempl;
    }

    public void setCloudResetNumTempl(String cloudResetNumTempl) {
        CloudFastConfig.cloudResetNumTempl = cloudResetNumTempl;
    }
}
