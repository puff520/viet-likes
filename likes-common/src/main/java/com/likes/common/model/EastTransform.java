package com.likes.common.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "`east_transform`")
public class EastTransform {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`muid`")
    private String muid;

    @Column(name = "`os`")
    private String os;

    @Column(name = "`imei`")
    private String imei;

    @Column(name = "`androidid`")
    private String androidid;

    @Column(name = "`idfa`")
    private String idfa;

    @Column(name = "`callback`")
    private String callback;

    @Column(name = "`oaid`")
    private String oaid;

    @Column(name = "`ip`")
    private String ip;

    @Column(name = "`create_time`")
    private Date createTime;

    @Column(name = "`update_time`")
    private Date updateTime;

    @Column(name = "`status`")
    private Integer status;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return muid
     */
    public String getMuid() {
        return muid;
    }

    /**
     * @param muid
     */
    public void setMuid(String muid) {
        this.muid = muid;
    }

    /**
     * @return os
     */
    public String getOs() {
        return os;
    }

    /**
     * @param os
     */
    public void setOs(String os) {
        this.os = os;
    }

    /**
     * @return imei
     */
    public String getImei() {
        return imei;
    }

    /**
     * @param imei
     */
    public void setImei(String imei) {
        this.imei = imei;
    }

    /**
     * @return androidid
     */
    public String getAndroidid() {
        return androidid;
    }

    /**
     * @param androidid
     */
    public void setAndroidid(String androidid) {
        this.androidid = androidid;
    }

    /**
     * @return idfa
     */
    public String getIdfa() {
        return idfa;
    }

    /**
     * @param idfa
     */
    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    /**
     * @return callback
     */
    public String getCallback() {
        return callback;
    }

    /**
     * @param callback
     */
    public void setCallback(String callback) {
        this.callback = callback;
    }

    /**
     * @return oaid
     */
    public String getOaid() {
        return oaid;
    }

    /**
     * @param oaid
     */
    public void setOaid(String oaid) {
        this.oaid = oaid;
    }

    /**
     * @return ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
