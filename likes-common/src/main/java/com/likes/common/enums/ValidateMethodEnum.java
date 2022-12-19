package com.likes.common.enums;

/**
 * http签名访问接口路径
 */
public enum ValidateMethodEnum {

    SUPER_SYSNOTEICE_SITENOTICE("/sysnotice/siteNotice", "从超级后台获取站点公告"),
    SUPER_SYSREPORT_ONLINEMEMNUMS("/home/pushSuperOnlineNum", "从后台获取在线人数"),
    SUPER_SYSREPORT_EXPFUNDSLIST("/expfunds/collectList", "从超级后台获取站点公告");

    private String methodName;
    private String methoddesc;

    ValidateMethodEnum(String methodName, String methoddesc) {
        this.methodName = methodName;
        this.methoddesc = methoddesc;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethoddesc() {
        return methoddesc;
    }

    public void setMethoddesc(String methoddesc) {
        this.methoddesc = methoddesc;
    }
}
