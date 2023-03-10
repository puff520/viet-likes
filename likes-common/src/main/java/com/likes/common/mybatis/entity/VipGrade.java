package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class VipGrade implements Serializable {
    /**
     * 字段: vip_grade.id<br/>
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
     * 字段: vip_grade.name<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 等级名称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 字段: vip_grade.valid_bet<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 14<br/>
     * 说明: 有效投注
     *
     * @mbggenerated
     */
    private BigDecimal validBet;

    /**
     * 字段: vip_grade.handsel<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 14<br/>
     * 说明: 升级奖励
     *
     * @mbggenerated
     */
    private BigDecimal handsel;

    /**
     * 字段: vip_grade.backwater<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 返水分子
     *
     * @mbggenerated
     */
    private Integer backwater;

    /**
     * 字段: vip_grade.backwater_denominator<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 返水分母
     *
     * @mbggenerated
     */
    private Integer backwaterDenominator;

    /**
     * 字段: vip_grade.privilege<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 特权
     *
     * @mbggenerated
     */
    private String privilege;

    /**
     * 字段: vip_grade.deleted<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    private Integer deleted;

    /**
     * 字段: vip_grade.water_ratio<br/>
     * 必填: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 返水比例
     *
     * @mbggenerated
     */
    private Integer waterRatio;

    /**
     * 字段: vip_grade.recharge_amount<br/>
     * 必填: false<br/>
     * 缺省: 0.00<br/>
     * 长度: 20<br/>
     * 说明: 充值条件
     *
     * @mbggenerated
     */
    private BigDecimal rechargeAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table vip_grade
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return vip_grade.id: 
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 字段: vip_grade.id<br/>
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
     * @return vip_grade.name: 等级名称
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * 字段: vip_grade.name<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 等级名称
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return vip_grade.valid_bet: 有效投注
     *
     * @mbggenerated
     */
    public BigDecimal getValidBet() {
        return validBet;
    }

    /**
     * 字段: vip_grade.valid_bet<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 14<br/>
     * 说明: 有效投注
     *
     * @mbggenerated
     */
    public void setValidBet(BigDecimal validBet) {
        this.validBet = validBet;
    }

    /**
     * @return vip_grade.handsel: 升级奖励
     *
     * @mbggenerated
     */
    public BigDecimal getHandsel() {
        return handsel;
    }

    /**
     * 字段: vip_grade.handsel<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 14<br/>
     * 说明: 升级奖励
     *
     * @mbggenerated
     */
    public void setHandsel(BigDecimal handsel) {
        this.handsel = handsel;
    }

    /**
     * @return vip_grade.backwater: 返水分子
     *
     * @mbggenerated
     */
    public Integer getBackwater() {
        return backwater;
    }

    /**
     * 字段: vip_grade.backwater<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 返水分子
     *
     * @mbggenerated
     */
    public void setBackwater(Integer backwater) {
        this.backwater = backwater;
    }

    /**
     * @return vip_grade.backwater_denominator: 返水分母
     *
     * @mbggenerated
     */
    public Integer getBackwaterDenominator() {
        return backwaterDenominator;
    }

    /**
     * 字段: vip_grade.backwater_denominator<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 返水分母
     *
     * @mbggenerated
     */
    public void setBackwaterDenominator(Integer backwaterDenominator) {
        this.backwaterDenominator = backwaterDenominator;
    }

    /**
     * @return vip_grade.privilege: 特权
     *
     * @mbggenerated
     */
    public String getPrivilege() {
        return privilege;
    }

    /**
     * 字段: vip_grade.privilege<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 特权
     *
     * @mbggenerated
     */
    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    /**
     * @return vip_grade.deleted: 
     *
     * @mbggenerated
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 字段: vip_grade.deleted<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * @return vip_grade.water_ratio: 返水比例
     *
     * @mbggenerated
     */
    public Integer getWaterRatio() {
        return waterRatio;
    }

    /**
     * 字段: vip_grade.water_ratio<br/>
     * 必填: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 返水比例
     *
     * @mbggenerated
     */
    public void setWaterRatio(Integer waterRatio) {
        this.waterRatio = waterRatio;
    }

    /**
     * @return vip_grade.recharge_amount: 充值条件
     *
     * @mbggenerated
     */
    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    /**
     * 字段: vip_grade.recharge_amount<br/>
     * 必填: false<br/>
     * 缺省: 0.00<br/>
     * 长度: 20<br/>
     * 说明: 充值条件
     *
     * @mbggenerated
     */
    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_grade
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
        VipGrade other = (VipGrade) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getValidBet() == null ? other.getValidBet() == null : this.getValidBet().equals(other.getValidBet()))
            && (this.getHandsel() == null ? other.getHandsel() == null : this.getHandsel().equals(other.getHandsel()))
            && (this.getBackwater() == null ? other.getBackwater() == null : this.getBackwater().equals(other.getBackwater()))
            && (this.getBackwaterDenominator() == null ? other.getBackwaterDenominator() == null : this.getBackwaterDenominator().equals(other.getBackwaterDenominator()))
            && (this.getPrivilege() == null ? other.getPrivilege() == null : this.getPrivilege().equals(other.getPrivilege()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
            && (this.getWaterRatio() == null ? other.getWaterRatio() == null : this.getWaterRatio().equals(other.getWaterRatio()))
            && (this.getRechargeAmount() == null ? other.getRechargeAmount() == null : this.getRechargeAmount().equals(other.getRechargeAmount()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_grade
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getValidBet() == null) ? 0 : getValidBet().hashCode());
        result = prime * result + ((getHandsel() == null) ? 0 : getHandsel().hashCode());
        result = prime * result + ((getBackwater() == null) ? 0 : getBackwater().hashCode());
        result = prime * result + ((getBackwaterDenominator() == null) ? 0 : getBackwaterDenominator().hashCode());
        result = prime * result + ((getPrivilege() == null) ? 0 : getPrivilege().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getWaterRatio() == null) ? 0 : getWaterRatio().hashCode());
        result = prime * result + ((getRechargeAmount() == null) ? 0 : getRechargeAmount().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_grade
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
        sb.append(", validBet=").append(validBet);
        sb.append(", handsel=").append(handsel);
        sb.append(", backwater=").append(backwater);
        sb.append(", backwaterDenominator=").append(backwaterDenominator);
        sb.append(", privilege=").append(privilege);
        sb.append(", deleted=").append(deleted);
        sb.append(", waterRatio=").append(waterRatio);
        sb.append(", rechargeAmount=").append(rechargeAmount);
        sb.append("]");
        return sb.toString();
    }
}