package com.likes.common.model.dto.pay;

import com.likes.common.mybatis.entity.SysThreepayset;

import java.math.BigDecimal;

/**
 *
 */
public class SysThreePaysetDTO extends SysThreepayset {

    //优惠率

    private BigDecimal discount;

    private String provider;

    private String paydns;

    private String backurl;

    private String torderurl;

    private String paygateway;

    private String accountno;

    private String secretcode;

    private String pubsecret;

    private String prisecret;

    private String serversecret;

    private String providercode;

    private String allowips;

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getPaydns() {
        return paydns;
    }

    public void setPaydns(String paydns) {
        this.paydns = paydns;
    }

    public String getBackurl() {
        return backurl;
    }

    public void setBackurl(String backurl) {
        this.backurl = backurl;
    }

    public String getTorderurl() {
        return torderurl;
    }

    public void setTorderurl(String torderurl) {
        this.torderurl = torderurl;
    }

    public String getPaygateway() {
        return paygateway;
    }

    public void setPaygateway(String paygateway) {
        this.paygateway = paygateway;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getSecretcode() {
        return secretcode;
    }

    public void setSecretcode(String secretcode) {
        this.secretcode = secretcode;
    }

    public String getPubsecret() {
        return pubsecret;
    }

    public void setPubsecret(String pubsecret) {
        this.pubsecret = pubsecret;
    }

    public String getPrisecret() {
        return prisecret;
    }

    public void setPrisecret(String prisecret) {
        this.prisecret = prisecret;
    }

    public String getServersecret() {
        return serversecret;
    }

    public void setServersecret(String serversecret) {
        this.serversecret = serversecret;
    }

    public String getProvidercode() {
        return providercode;
    }

    public void setProvidercode(String providercode) {
        this.providercode = providercode;
    }

    public String getAllowips() {
        return allowips;
    }

    public void setAllowips(String allowips) {
        this.allowips = allowips;
    }
}
