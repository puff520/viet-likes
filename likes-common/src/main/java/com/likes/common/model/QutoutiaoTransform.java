package com.likes.common.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "`qutoutiao_transform`")
public class QutoutiaoTransform {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`CID`")
    private String cid;

    @Column(name = "`OS`")
    private Integer os;

    @Column(name = "`IMEIMD5`")
    private String imeimd5;

    @Column(name = "`IDFA`")
    private String idfa;

    @Column(name = "`TS`")
    private Long ts;

    @Column(name = "`TSMS`")
    private Long tsms;

    @Column(name = "`CALLBACK_URL`")
    private String callbackUrl;

    @Column(name = "`UNIT`")
    private String unit;

    @Column(name = "`PLAN`")
    private String plan;

    @Column(name = "`UID`")
    private String uid;

    @Column(name = "`UA`")
    private String ua;

    @Column(name = "`ANDROIDIDMD5`")
    private String androididmd5;

    @Column(name = "`IP`")
    private String ip;

    @Column(name = "`OAID`")
    private String oaid;

    @Column(name = "`DP_LINK`")
    private String dpLink;

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
     * @return CID
     */
    public String getCid() {
        return cid;
    }

    /**
     * @param cid
     */
    public void setCid(String cid) {
        this.cid = cid;
    }

    /**
     * @return OS
     */
    public Integer getOs() {
        return os;
    }

    /**
     * @param os
     */
    public void setOs(Integer os) {
        this.os = os;
    }

    /**
     * @return IMEIMD5
     */
    public String getImeimd5() {
        return imeimd5;
    }

    /**
     * @param imeimd5
     */
    public void setImeimd5(String imeimd5) {
        this.imeimd5 = imeimd5;
    }

    /**
     * @return IDFA
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
     * @return TS
     */
    public Long getTs() {
        return ts;
    }

    /**
     * @param ts
     */
    public void setTs(Long ts) {
        this.ts = ts;
    }

    /**
     * @return TSMS
     */
    public Long getTsms() {
        return tsms;
    }

    /**
     * @param tsms
     */
    public void setTsms(Long tsms) {
        this.tsms = tsms;
    }

    /**
     * @return CALLBACK_URL
     */
    public String getCallbackUrl() {
        return callbackUrl;
    }

    /**
     * @param callbackUrl
     */
    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    /**
     * @return UNIT
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return PLAN
     */
    public String getPlan() {
        return plan;
    }

    /**
     * @param plan
     */
    public void setPlan(String plan) {
        this.plan = plan;
    }

    /**
     * @return UID
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @return UA
     */
    public String getUa() {
        return ua;
    }

    /**
     * @param ua
     */
    public void setUa(String ua) {
        this.ua = ua;
    }

    /**
     * @return ANDROIDIDMD5
     */
    public String getAndroididmd5() {
        return androididmd5;
    }

    /**
     * @param androididmd5
     */
    public void setAndroididmd5(String androididmd5) {
        this.androididmd5 = androididmd5;
    }

    /**
     * @return IP
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
     * @return OAID
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
     * @return DP_LINK
     */
    public String getDpLink() {
        return dpLink;
    }

    /**
     * @param dpLink
     */
    public void setDpLink(String dpLink) {
        this.dpLink = dpLink;
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

    public Date getUpdateTime() {
        return updateTime;
    }

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
