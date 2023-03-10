package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReturnLotterySet implements Serializable {
    /**
     * 字段: return_lottery_set.id<br/>
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
     * 字段: return_lottery_set.lottery_name<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 彩种名称
     *
     * @mbggenerated
     */
    private String lotteryName;

    /**
     * 字段: return_lottery_set.lottery_category_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    private Integer lotteryCategoryId;

    /**
     * 字段: return_lottery_set.lottery_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 彩种ID
     *
     * @mbggenerated
     */
    private Integer lotteryId;

    /**
     * 字段: return_lottery_set.water_amout<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 返水比例
     *
     * @mbggenerated
     */
    private Integer waterAmout;

    /**
     * 字段: return_lottery_set.amount<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 金额
     *
     * @mbggenerated
     */
    private BigDecimal amount;

    /**
     * 字段: return_lottery_set.edit_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 修改人
     *
     * @mbggenerated
     */
    private String editUser;

    /**
     * 字段: return_lottery_set.edit_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 修改时间
     *
     * @mbggenerated
     */
    private Date editTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table return_lottery_set
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return return_lottery_set.id: 
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 字段: return_lottery_set.id<br/>
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
     * @return return_lottery_set.lottery_name: 彩种名称
     *
     * @mbggenerated
     */
    public String getLotteryName() {
        return lotteryName;
    }

    /**
     * 字段: return_lottery_set.lottery_name<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 彩种名称
     *
     * @mbggenerated
     */
    public void setLotteryName(String lotteryName) {
        this.lotteryName = lotteryName;
    }

    /**
     * @return return_lottery_set.lottery_category_id: 
     *
     * @mbggenerated
     */
    public Integer getLotteryCategoryId() {
        return lotteryCategoryId;
    }

    /**
     * 字段: return_lottery_set.lottery_category_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    public void setLotteryCategoryId(Integer lotteryCategoryId) {
        this.lotteryCategoryId = lotteryCategoryId;
    }

    /**
     * @return return_lottery_set.lottery_id: 彩种ID
     *
     * @mbggenerated
     */
    public Integer getLotteryId() {
        return lotteryId;
    }

    /**
     * 字段: return_lottery_set.lottery_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 彩种ID
     *
     * @mbggenerated
     */
    public void setLotteryId(Integer lotteryId) {
        this.lotteryId = lotteryId;
    }

    /**
     * @return return_lottery_set.water_amout: 返水比例
     *
     * @mbggenerated
     */
    public Integer getWaterAmout() {
        return waterAmout;
    }

    /**
     * 字段: return_lottery_set.water_amout<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 返水比例
     *
     * @mbggenerated
     */
    public void setWaterAmout(Integer waterAmout) {
        this.waterAmout = waterAmout;
    }

    /**
     * @return return_lottery_set.amount: 金额
     *
     * @mbggenerated
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 字段: return_lottery_set.amount<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 金额
     *
     * @mbggenerated
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return return_lottery_set.edit_user: 修改人
     *
     * @mbggenerated
     */
    public String getEditUser() {
        return editUser;
    }

    /**
     * 字段: return_lottery_set.edit_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 修改人
     *
     * @mbggenerated
     */
    public void setEditUser(String editUser) {
        this.editUser = editUser;
    }

    /**
     * @return return_lottery_set.edit_time: 修改时间
     *
     * @mbggenerated
     */
    public Date getEditTime() {
        return editTime;
    }

    /**
     * 字段: return_lottery_set.edit_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 修改时间
     *
     * @mbggenerated
     */
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table return_lottery_set
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
        ReturnLotterySet other = (ReturnLotterySet) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLotteryName() == null ? other.getLotteryName() == null : this.getLotteryName().equals(other.getLotteryName()))
            && (this.getLotteryCategoryId() == null ? other.getLotteryCategoryId() == null : this.getLotteryCategoryId().equals(other.getLotteryCategoryId()))
            && (this.getLotteryId() == null ? other.getLotteryId() == null : this.getLotteryId().equals(other.getLotteryId()))
            && (this.getWaterAmout() == null ? other.getWaterAmout() == null : this.getWaterAmout().equals(other.getWaterAmout()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getEditUser() == null ? other.getEditUser() == null : this.getEditUser().equals(other.getEditUser()))
            && (this.getEditTime() == null ? other.getEditTime() == null : this.getEditTime().equals(other.getEditTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table return_lottery_set
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLotteryName() == null) ? 0 : getLotteryName().hashCode());
        result = prime * result + ((getLotteryCategoryId() == null) ? 0 : getLotteryCategoryId().hashCode());
        result = prime * result + ((getLotteryId() == null) ? 0 : getLotteryId().hashCode());
        result = prime * result + ((getWaterAmout() == null) ? 0 : getWaterAmout().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getEditUser() == null) ? 0 : getEditUser().hashCode());
        result = prime * result + ((getEditTime() == null) ? 0 : getEditTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table return_lottery_set
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
        sb.append(", lotteryName=").append(lotteryName);
        sb.append(", lotteryCategoryId=").append(lotteryCategoryId);
        sb.append(", lotteryId=").append(lotteryId);
        sb.append(", waterAmout=").append(waterAmout);
        sb.append(", amount=").append(amount);
        sb.append(", editUser=").append(editUser);
        sb.append(", editTime=").append(editTime);
        sb.append("]");
        return sb.toString();
    }
}