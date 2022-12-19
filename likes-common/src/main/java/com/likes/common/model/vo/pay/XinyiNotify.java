package com.likes.common.model.vo.pay;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 新意支付
 *
 * @author 瑞夫
 * @version 1.0
 * @date 2020/7/22
 */
@Scope("prototype")
@Component
public class XinyiNotify extends Notify {

    /**
     * 状态
     */
    private String status;
    /**
     * 商户ID
     */
    private String shid;
    /**
     * 平台订单号
     */
    private String ptddh;
    /**
     * 商户订单号
     */
    private String ddh;
    /**
     * 订单金额
     */
    private String je;
    /**
     * 支付通道
     */
    private String zftd;
    /**
     * 异步通知URL
     */
    private String ybtz;
    /**
     * 同步跳转URL
     */
    private String tbtz;
    /**
     * 订单名称
     */
    private String ddmc;
    /**
     * 订单备注
     */
    private String ddbz;
    /**
     * md5签名串
     */
    private String sign;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShid() {
        return shid;
    }

    public void setShid(String shid) {
        this.shid = shid;
    }

    public String getPtddh() {
        return ptddh;
    }

    public void setPtddh(String ptddh) {
        this.ptddh = ptddh;
    }

    public String getDdh() {
        return ddh;
    }

    public void setDdh(String ddh) {
        this.ddh = ddh;
    }

    public String getJe() {
        return je;
    }

    public void setJe(String je) {
        this.je = je;
    }

    public String getZftd() {
        return zftd;
    }

    public void setZftd(String zftd) {
        this.zftd = zftd;
    }

    public String getYbtz() {
        return ybtz;
    }

    public void setYbtz(String ybtz) {
        this.ybtz = ybtz;
    }

    public String getTbtz() {
        return tbtz;
    }

    public void setTbtz(String tbtz) {
        this.tbtz = tbtz;
    }

    public String getDdmc() {
        return ddmc;
    }

    public void setDdmc(String ddmc) {
        this.ddmc = ddmc;
    }

    public String getDdbz() {
        return ddbz;
    }

    public void setDdbz(String ddbz) {
        this.ddbz = ddbz;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
