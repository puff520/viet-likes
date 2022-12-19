package com.likes.common.model.dto.sys;

import com.likes.common.mybatis.entity.SysThreepayset;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SysThreepaysetDO  implements Serializable {
    /**
     * 字段: sys_threepayset.tpaysetid<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 三方账户id
     *
     * @mbggenerated
     */
    private Long tpaysetid;

    /**
     * 字段: sys_threepayset.providerid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 支付商id
     *
     * @mbggenerated
     */
    private Long providerid;

    /**
     * 字段: sys_threepayset.tpayname<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 设定名称 如 支付宝  微信  银联
     *
     * @mbggenerated
     */
    private String tpayname;

    /**
     * 字段: sys_threepayset.paytype<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 30<br/>
     * 说明: 支付方式  NETBANK 网银转账  WECHAT 微信收款  ALIPAY 支付宝支付
     *
     * @mbggenerated
     */
    private String paytype;

    /**
     * 字段: sys_threepayset.paycode<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 支付方式  wechatangel 微信话费扫码  wechatangelwap  微信话费H5  alipay   支付宝  alipayangel  支付宝话费扫码H5  alipaylst  支付宝转卡
     *
     * @mbggenerated
     */
    private String paycode;

    /**
     * 字段: sys_threepayset.payvalue<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 支付设置值
     *
     * @mbggenerated
     */
    private String payvalue;

    /**
     * 字段: sys_threepayset.minamt<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 单笔最低金额
     *
     * @mbggenerated
     */
    private BigDecimal minamt;

    /**
     * 字段: sys_threepayset.maxamt<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 单笔入账最高金额
     *
     * @mbggenerated
     */
    private BigDecimal maxamt;

    /**
     * 字段: sys_threepayset.minmemlevel<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 启用会员最低等级
     *
     * @mbggenerated
     */
    private String minmemlevel;

    /**
     * 字段: sys_threepayset.maxmemlevel<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 启用会员最高等级
     *
     * @mbggenerated
     */
    private String maxmemlevel;

    /**
     * 字段: sys_threepayset.stopamt<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 停用次卡上限金额
     *
     * @mbggenerated
     */
    private BigDecimal stopamt;

    /**
     * 字段: sys_threepayset.easyrecharge<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 200<br/>
     * 说明: 快捷充值套餐金额, 多条以","分隔
     *
     * @mbggenerated
     */
    private String easyrecharge;

    /**
     * 字段: sys_threepayset.isinput<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1<br/>
     * 说明: 是否允许输入金额  0允许 9禁止
     *
     * @mbggenerated
     */
    private Integer isinput;

    /**
     * 字段: sys_threepayset.status<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1<br/>
     * 说明: 启用状态: 0启用 9停用
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * 字段: sys_threepayset.is_delete<br/>
     * 必填: true<br/>
     * 缺省: b'0'<br/>
     * 长度: 1<br/>
     * 说明: 是否删除
     *
     * @mbggenerated
     */
    private Boolean isDelete;

    /**
     * 字段: sys_threepayset.create_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 创建人
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * 字段: sys_threepayset.create_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 字段: sys_threepayset.update_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 最后修改人
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * 字段: sys_threepayset.update_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 更新时间
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_threepayset
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;
    // 已经充值金额
    private BigDecimal rechargeamt;

    public BigDecimal getRechargeamt() {
        return rechargeamt;
    }

    public void setRechargeamt(BigDecimal rechargeamt) {
        this.rechargeamt = rechargeamt;
    }

    public Long getTpaysetid() {
        return tpaysetid;
    }

    public void setTpaysetid(Long tpaysetid) {
        this.tpaysetid = tpaysetid;
    }

    public Long getProviderid() {
        return providerid;
    }

    public void setProviderid(Long providerid) {
        this.providerid = providerid;
    }

    public String getTpayname() {
        return tpayname;
    }

    public void setTpayname(String tpayname) {
        this.tpayname = tpayname;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getPaycode() {
        return paycode;
    }

    public void setPaycode(String paycode) {
        this.paycode = paycode;
    }

    public String getPayvalue() {
        return payvalue;
    }

    public void setPayvalue(String payvalue) {
        this.payvalue = payvalue;
    }

    public BigDecimal getMinamt() {
        return minamt;
    }

    public void setMinamt(BigDecimal minamt) {
        this.minamt = minamt;
    }

    public BigDecimal getMaxamt() {
        return maxamt;
    }

    public void setMaxamt(BigDecimal maxamt) {
        this.maxamt = maxamt;
    }

    public String getMinmemlevel() {
        return minmemlevel;
    }

    public void setMinmemlevel(String minmemlevel) {
        this.minmemlevel = minmemlevel;
    }

    public String getMaxmemlevel() {
        return maxmemlevel;
    }

    public void setMaxmemlevel(String maxmemlevel) {
        this.maxmemlevel = maxmemlevel;
    }

    public BigDecimal getStopamt() {
        return stopamt;
    }

    public void setStopamt(BigDecimal stopamt) {
        this.stopamt = stopamt;
    }

    public String getEasyrecharge() {
        return easyrecharge;
    }

    public void setEasyrecharge(String easyrecharge) {
        this.easyrecharge = easyrecharge;
    }

    public Integer getIsinput() {
        return isinput;
    }

    public void setIsinput(Integer isinput) {
        this.isinput = isinput;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
