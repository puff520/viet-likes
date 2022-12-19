package com.likes.common.model.response;

import com.likes.common.constant.Constants;
import com.likes.common.util.URLParser;
import com.likes.common.util.encrypt.AESUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @author 阿布 srs callback bean
 */
public class CallbackResp {

    private String action;
    private String client_id;
    private String ip;
    private String vhost;
    private String app;
    private String tcUrl;
    private String pageUrl;
    private String stream;
    private String param;

    private Long roomid;
    private String acctoken;

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public String getAcctoken() {
        return acctoken;
    }

    public void setAcctoken(String acctoken) {
        this.acctoken = acctoken;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        try {
            if (StringUtils.isEmpty(acctoken) && !StringUtils.isEmpty(param)) {
                acctoken = URLParser.fromURL(param).getParameter(Constants.ACCTOKEN_KEY);
            }
        } catch (Exception e) {
        }
        this.param = param;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getVhost() {
        return vhost;
    }

    public void setVhost(String vhost) {
        this.vhost = vhost;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getTcUrl() {
        return tcUrl;
    }

    public void setTcUrl(String tcUrl) {
        try {
            if (!StringUtils.isEmpty(tcUrl)) {
                acctoken = URLParser.fromURL(tcUrl).getParameter(Constants.ACCTOKEN_KEY);
            }
        } catch (Exception e) {
        }
        this.tcUrl = tcUrl;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getStream() {
        if (!StringUtils.isEmpty(stream)) {
            return stream.split("_")[0];
        }
        return stream;
    }

    public void setStream(String stream) {
        try {
            if (!StringUtils.isEmpty(stream)) {
                String aes = AESUtils.decryptData(stream.split("_")[0], Constants.STREAMKEY);
                if (!StringUtils.isEmpty(aes)) {
                    this.roomid = Long.parseLong(aes.substring(8, 8 + aes.length() - 32));
                }
            }
        } catch (Exception e) {

        }
        this.stream = stream;
    }

    @Override
    public String toString() {
        return "CallbackResp [action=" + action + ", client_id=" + client_id + ", ip=" + ip + ", vhost=" + vhost + ", app=" + app
                + ", tcUrl=" + tcUrl + ", pageUrl=" + pageUrl + ", stream=" + stream + ", param=" + param + ", acctoken=" + acctoken + "]";
    }

}
