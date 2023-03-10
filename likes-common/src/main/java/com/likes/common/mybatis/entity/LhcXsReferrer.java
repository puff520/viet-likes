package com.likes.common.mybatis.entity;

import java.io.Serializable;

public class LhcXsReferrer implements Serializable {
    /**
     * 字段: lhc_xs_referrer.id<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 字段: lhc_xs_referrer.name<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 名称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 字段: lhc_xs_referrer.wxh<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 微信号
     *
     * @mbggenerated
     */
    private String wxh;

    /**
     * 字段: lhc_xs_referrer.heads<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 头像
     *
     * @mbggenerated
     */
    private String heads;

    /**
     * 字段: lhc_xs_referrer.qr_code<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 二维码
     *
     * @mbggenerated
     */
    private String qrCode;

    /**
     * 字段: lhc_xs_referrer.create_time<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    private String createTime;

    /**
     * 字段: lhc_xs_referrer.deleted<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 是否删除
     *
     * @mbggenerated
     */
    private Integer deleted;

    /**
     * 字段: lhc_xs_referrer.money<br/>
     * 必填: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 打赏总金额
     *
     * @mbggenerated
     */
    private Integer money;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table lhc_xs_referrer
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return lhc_xs_referrer.id: 
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 字段: lhc_xs_referrer.id<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return lhc_xs_referrer.name: 名称
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * 字段: lhc_xs_referrer.name<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 名称
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return lhc_xs_referrer.wxh: 微信号
     *
     * @mbggenerated
     */
    public String getWxh() {
        return wxh;
    }

    /**
     * 字段: lhc_xs_referrer.wxh<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 微信号
     *
     * @mbggenerated
     */
    public void setWxh(String wxh) {
        this.wxh = wxh;
    }

    /**
     * @return lhc_xs_referrer.heads: 头像
     *
     * @mbggenerated
     */
    public String getHeads() {
        return heads;
    }

    /**
     * 字段: lhc_xs_referrer.heads<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 头像
     *
     * @mbggenerated
     */
    public void setHeads(String heads) {
        this.heads = heads;
    }

    /**
     * @return lhc_xs_referrer.qr_code: 二维码
     *
     * @mbggenerated
     */
    public String getQrCode() {
        return qrCode;
    }

    /**
     * 字段: lhc_xs_referrer.qr_code<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 二维码
     *
     * @mbggenerated
     */
    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    /**
     * @return lhc_xs_referrer.create_time: 创建时间
     *
     * @mbggenerated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 字段: lhc_xs_referrer.create_time<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * @return lhc_xs_referrer.deleted: 是否删除
     *
     * @mbggenerated
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 字段: lhc_xs_referrer.deleted<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 是否删除
     *
     * @mbggenerated
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * @return lhc_xs_referrer.money: 打赏总金额
     *
     * @mbggenerated
     */
    public Integer getMoney() {
        return money;
    }

    /**
     * 字段: lhc_xs_referrer.money<br/>
     * 必填: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 打赏总金额
     *
     * @mbggenerated
     */
    public void setMoney(Integer money) {
        this.money = money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_xs_referrer
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        LhcXsReferrer other = (LhcXsReferrer) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getWxh() == null ? other.getWxh() == null : this.getWxh().equals(other.getWxh()))
            && (this.getHeads() == null ? other.getHeads() == null : this.getHeads().equals(other.getHeads()))
            && (this.getQrCode() == null ? other.getQrCode() == null : this.getQrCode().equals(other.getQrCode()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_xs_referrer
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getWxh() == null) ? 0 : getWxh().hashCode());
        result = prime * result + ((getHeads() == null) ? 0 : getHeads().hashCode());
        result = prime * result + ((getQrCode() == null) ? 0 : getQrCode().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_xs_referrer
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", wxh=").append(wxh);
        sb.append(", heads=").append(heads);
        sb.append(", qrCode=").append(qrCode);
        sb.append(", createTime=").append(createTime);
        sb.append(", deleted=").append(deleted);
        sb.append(", money=").append(money);
        sb.append("]");
        return sb.toString();
    }
}