package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TraAgentclearing implements Serializable {
    /**
     * 字段: tra_agentclearing.cleanid<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 结算id
     *
     * @mbggenerated
     */
    private Long cleanid;

    /**
     * 字段: tra_agentclearing.agentid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 代理id
     *
     * @mbggenerated
     */
    private Long agentid;

    /**
     * 字段: tra_agentclearing.accno<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 代理会员标识号
     *
     * @mbggenerated
     */
    private String accno;

    /**
     * 字段: tra_agentclearing.cleantype<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 结算类型 day日结 week周结 month月结
     *
     * @mbggenerated
     */
    private String cleantype;

    /**
     * 字段: tra_agentclearing.refids<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1000<br/>
     * 说明: 关联订单id 多条以逗号分隔
     *
     * @mbggenerated
     */
    private String refids;

    /**
     * 字段: tra_agentclearing.chargeamt<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 充值总金额
     *
     * @mbggenerated
     */
    private BigDecimal chargeamt;

    /**
     * 字段: tra_agentclearing.reverseamt<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 返点总金额
     *
     * @mbggenerated
     */
    private BigDecimal reverseamt;

    /**
     * 字段: tra_agentclearing.commission<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 5<br/>
     * 说明: 返佣比(%)
     *
     * @mbggenerated
     */
    private BigDecimal commission;

    /**
     * 字段: tra_agentclearing.buttonnote<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 备注说明
     *
     * @mbggenerated
     */
    private String buttonnote;

    /**
     * 字段: tra_agentclearing.daily_withdrawal<br/>
     * 必填: true<br/>
     * 缺省: 0.000<br/>
     * 长度: 16<br/>
     * 说明: 本日提现总金额
     *
     * @mbggenerated
     */
    private BigDecimal dailyWithdrawal;

    /**
     * 字段: tra_agentclearing.daily_pay_users<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 本日首充人数
     *
     * @mbggenerated
     */
    private Integer dailyPayUsers;

    /**
     * 字段: tra_agentclearing.daily_pay_total<br/>
     * 必填: true<br/>
     * 缺省: 0.000<br/>
     * 长度: 16<br/>
     * 说明: 本日新增首充金额
     *
     * @mbggenerated
     */
    private BigDecimal dailyPayTotal;

    /**
     * 字段: tra_agentclearing.daily_new_users<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 本日下级人数
     *
     * @mbggenerated
     */
    private Integer dailyNewUsers;

    /**
     * 字段: tra_agentclearing.is_delete<br/>
     * 必填: true<br/>
     * 缺省: b'0'<br/>
     * 长度: 1<br/>
     * 说明: 是否删除
     *
     * @mbggenerated
     */
    private Boolean isDelete;

    /**
     * 字段: tra_agentclearing.create_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 创建人
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * 字段: tra_agentclearing.create_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 字段: tra_agentclearing.update_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 最后修改人
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * 字段: tra_agentclearing.update_time<br/>
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
     * This field corresponds to the database table tra_agentclearing
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return tra_agentclearing.cleanid: 结算id
     *
     * @mbggenerated
     */
    public Long getCleanid() {
        return cleanid;
    }

    /**
     * 字段: tra_agentclearing.cleanid<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 结算id
     *
     * @mbggenerated
     */
    public void setCleanid(Long cleanid) {
        this.cleanid = cleanid;
    }

    /**
     * @return tra_agentclearing.agentid: 代理id
     *
     * @mbggenerated
     */
    public Long getAgentid() {
        return agentid;
    }

    /**
     * 字段: tra_agentclearing.agentid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 代理id
     *
     * @mbggenerated
     */
    public void setAgentid(Long agentid) {
        this.agentid = agentid;
    }

    /**
     * @return tra_agentclearing.accno: 代理会员标识号
     *
     * @mbggenerated
     */
    public String getAccno() {
        return accno;
    }

    /**
     * 字段: tra_agentclearing.accno<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 代理会员标识号
     *
     * @mbggenerated
     */
    public void setAccno(String accno) {
        this.accno = accno;
    }

    /**
     * @return tra_agentclearing.cleantype: 结算类型 day日结 week周结 month月结
     *
     * @mbggenerated
     */
    public String getCleantype() {
        return cleantype;
    }

    /**
     * 字段: tra_agentclearing.cleantype<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 结算类型 day日结 week周结 month月结
     *
     * @mbggenerated
     */
    public void setCleantype(String cleantype) {
        this.cleantype = cleantype;
    }

    /**
     * @return tra_agentclearing.refids: 关联订单id 多条以逗号分隔
     *
     * @mbggenerated
     */
    public String getRefids() {
        return refids;
    }

    /**
     * 字段: tra_agentclearing.refids<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1000<br/>
     * 说明: 关联订单id 多条以逗号分隔
     *
     * @mbggenerated
     */
    public void setRefids(String refids) {
        this.refids = refids;
    }

    /**
     * @return tra_agentclearing.chargeamt: 充值总金额
     *
     * @mbggenerated
     */
    public BigDecimal getChargeamt() {
        return chargeamt;
    }

    /**
     * 字段: tra_agentclearing.chargeamt<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 充值总金额
     *
     * @mbggenerated
     */
    public void setChargeamt(BigDecimal chargeamt) {
        this.chargeamt = chargeamt;
    }

    /**
     * @return tra_agentclearing.reverseamt: 返点总金额
     *
     * @mbggenerated
     */
    public BigDecimal getReverseamt() {
        return reverseamt;
    }

    /**
     * 字段: tra_agentclearing.reverseamt<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 返点总金额
     *
     * @mbggenerated
     */
    public void setReverseamt(BigDecimal reverseamt) {
        this.reverseamt = reverseamt;
    }

    /**
     * @return tra_agentclearing.commission: 返佣比(%)
     *
     * @mbggenerated
     */
    public BigDecimal getCommission() {
        return commission;
    }

    /**
     * 字段: tra_agentclearing.commission<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 5<br/>
     * 说明: 返佣比(%)
     *
     * @mbggenerated
     */
    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    /**
     * @return tra_agentclearing.buttonnote: 备注说明
     *
     * @mbggenerated
     */
    public String getButtonnote() {
        return buttonnote;
    }

    /**
     * 字段: tra_agentclearing.buttonnote<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 备注说明
     *
     * @mbggenerated
     */
    public void setButtonnote(String buttonnote) {
        this.buttonnote = buttonnote;
    }

    /**
     * @return tra_agentclearing.daily_withdrawal: 本日提现总金额
     *
     * @mbggenerated
     */
    public BigDecimal getDailyWithdrawal() {
        return dailyWithdrawal;
    }

    /**
     * 字段: tra_agentclearing.daily_withdrawal<br/>
     * 必填: true<br/>
     * 缺省: 0.000<br/>
     * 长度: 16<br/>
     * 说明: 本日提现总金额
     *
     * @mbggenerated
     */
    public void setDailyWithdrawal(BigDecimal dailyWithdrawal) {
        this.dailyWithdrawal = dailyWithdrawal;
    }

    /**
     * @return tra_agentclearing.daily_pay_users: 本日首充人数
     *
     * @mbggenerated
     */
    public Integer getDailyPayUsers() {
        return dailyPayUsers;
    }

    /**
     * 字段: tra_agentclearing.daily_pay_users<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 本日首充人数
     *
     * @mbggenerated
     */
    public void setDailyPayUsers(Integer dailyPayUsers) {
        this.dailyPayUsers = dailyPayUsers;
    }

    /**
     * @return tra_agentclearing.daily_pay_total: 本日新增首充金额
     *
     * @mbggenerated
     */
    public BigDecimal getDailyPayTotal() {
        return dailyPayTotal;
    }

    /**
     * 字段: tra_agentclearing.daily_pay_total<br/>
     * 必填: true<br/>
     * 缺省: 0.000<br/>
     * 长度: 16<br/>
     * 说明: 本日新增首充金额
     *
     * @mbggenerated
     */
    public void setDailyPayTotal(BigDecimal dailyPayTotal) {
        this.dailyPayTotal = dailyPayTotal;
    }

    /**
     * @return tra_agentclearing.daily_new_users: 本日下级人数
     *
     * @mbggenerated
     */
    public Integer getDailyNewUsers() {
        return dailyNewUsers;
    }

    /**
     * 字段: tra_agentclearing.daily_new_users<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 本日下级人数
     *
     * @mbggenerated
     */
    public void setDailyNewUsers(Integer dailyNewUsers) {
        this.dailyNewUsers = dailyNewUsers;
    }

    /**
     * @return tra_agentclearing.is_delete: 是否删除
     *
     * @mbggenerated
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 字段: tra_agentclearing.is_delete<br/>
     * 必填: true<br/>
     * 缺省: b'0'<br/>
     * 长度: 1<br/>
     * 说明: 是否删除
     *
     * @mbggenerated
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * @return tra_agentclearing.create_user: 创建人
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 字段: tra_agentclearing.create_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 创建人
     *
     * @mbggenerated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * @return tra_agentclearing.create_time: 创建时间
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 字段: tra_agentclearing.create_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return tra_agentclearing.update_user: 最后修改人
     *
     * @mbggenerated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 字段: tra_agentclearing.update_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 最后修改人
     *
     * @mbggenerated
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * @return tra_agentclearing.update_time: 更新时间
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 字段: tra_agentclearing.update_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 更新时间
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_agentclearing
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
        TraAgentclearing other = (TraAgentclearing) that;
        return (this.getCleanid() == null ? other.getCleanid() == null : this.getCleanid().equals(other.getCleanid()))
            && (this.getAgentid() == null ? other.getAgentid() == null : this.getAgentid().equals(other.getAgentid()))
            && (this.getAccno() == null ? other.getAccno() == null : this.getAccno().equals(other.getAccno()))
            && (this.getCleantype() == null ? other.getCleantype() == null : this.getCleantype().equals(other.getCleantype()))
            && (this.getRefids() == null ? other.getRefids() == null : this.getRefids().equals(other.getRefids()))
            && (this.getChargeamt() == null ? other.getChargeamt() == null : this.getChargeamt().equals(other.getChargeamt()))
            && (this.getReverseamt() == null ? other.getReverseamt() == null : this.getReverseamt().equals(other.getReverseamt()))
            && (this.getCommission() == null ? other.getCommission() == null : this.getCommission().equals(other.getCommission()))
            && (this.getButtonnote() == null ? other.getButtonnote() == null : this.getButtonnote().equals(other.getButtonnote()))
            && (this.getDailyWithdrawal() == null ? other.getDailyWithdrawal() == null : this.getDailyWithdrawal().equals(other.getDailyWithdrawal()))
            && (this.getDailyPayUsers() == null ? other.getDailyPayUsers() == null : this.getDailyPayUsers().equals(other.getDailyPayUsers()))
            && (this.getDailyPayTotal() == null ? other.getDailyPayTotal() == null : this.getDailyPayTotal().equals(other.getDailyPayTotal()))
            && (this.getDailyNewUsers() == null ? other.getDailyNewUsers() == null : this.getDailyNewUsers().equals(other.getDailyNewUsers()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_agentclearing
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCleanid() == null) ? 0 : getCleanid().hashCode());
        result = prime * result + ((getAgentid() == null) ? 0 : getAgentid().hashCode());
        result = prime * result + ((getAccno() == null) ? 0 : getAccno().hashCode());
        result = prime * result + ((getCleantype() == null) ? 0 : getCleantype().hashCode());
        result = prime * result + ((getRefids() == null) ? 0 : getRefids().hashCode());
        result = prime * result + ((getChargeamt() == null) ? 0 : getChargeamt().hashCode());
        result = prime * result + ((getReverseamt() == null) ? 0 : getReverseamt().hashCode());
        result = prime * result + ((getCommission() == null) ? 0 : getCommission().hashCode());
        result = prime * result + ((getButtonnote() == null) ? 0 : getButtonnote().hashCode());
        result = prime * result + ((getDailyWithdrawal() == null) ? 0 : getDailyWithdrawal().hashCode());
        result = prime * result + ((getDailyPayUsers() == null) ? 0 : getDailyPayUsers().hashCode());
        result = prime * result + ((getDailyPayTotal() == null) ? 0 : getDailyPayTotal().hashCode());
        result = prime * result + ((getDailyNewUsers() == null) ? 0 : getDailyNewUsers().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_agentclearing
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cleanid=").append(cleanid);
        sb.append(", agentid=").append(agentid);
        sb.append(", accno=").append(accno);
        sb.append(", cleantype=").append(cleantype);
        sb.append(", refids=").append(refids);
        sb.append(", chargeamt=").append(chargeamt);
        sb.append(", reverseamt=").append(reverseamt);
        sb.append(", commission=").append(commission);
        sb.append(", buttonnote=").append(buttonnote);
        sb.append(", dailyWithdrawal=").append(dailyWithdrawal);
        sb.append(", dailyPayUsers=").append(dailyPayUsers);
        sb.append(", dailyPayTotal=").append(dailyPayTotal);
        sb.append(", dailyNewUsers=").append(dailyNewUsers);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}