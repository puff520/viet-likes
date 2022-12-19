package com.likes.common.model.dto.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
@ApiModel
public class FundsResponse {

    @ApiModelProperty(value = "订单号")
    private long goldchangid;
    @ApiModelProperty(value = "流水号")
    private Long snowsn;
    @ApiModelProperty(value = "会员标识")
    private String accno;
    @ApiModelProperty(value = "账户")
    private String email;
    @ApiModelProperty(value = "交易类型")
    private Integer changetype;
    @ApiModelProperty(value = "交易前金额")
    private BigDecimal goldnum;
    @ApiModelProperty(value = "交易金额")
    private BigDecimal quantity;
    @ApiModelProperty(value = "交易后金额")
    private BigDecimal recgoldnum;
    @ApiModelProperty(value = "操作说明")
    private String opnote;
    @ApiModelProperty(value = "交易时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-5")
    private Date createtime;
    private String subEmail;

    public long getGoldchangid() {
        return goldchangid;
    }

    public void setGoldchangid(long goldchangid) {
        this.goldchangid = goldchangid;
    }

    public Long getSnowsn() {
        return snowsn;
    }

    public void setSnowsn(Long snowsn) {
        this.snowsn = snowsn;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getChangetype() {
        return changetype;
    }

    public void setChangetype(Integer changetype) {
        this.changetype = changetype;
    }

    public BigDecimal getGoldnum() {
        return goldnum;
    }

    public void setGoldnum(BigDecimal goldnum) {
        this.goldnum = goldnum;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getRecgoldnum() {
        return recgoldnum;
    }

    public void setRecgoldnum(BigDecimal recgoldnum) {
        this.recgoldnum = recgoldnum;
    }

    public String getOpnote() {
        return opnote;
    }

    public void setOpnote(String opnote) {
        this.opnote = opnote;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getSubEmail() {
        return subEmail;
    }

    public void setSubEmail(String subEmail) {
        this.subEmail = subEmail;
    }
}
