package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TraRechargemeal implements Serializable {
    /**
     * 字段: tra_rechargemeal.mealid<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 充值套餐id
     *
     * @mbggenerated
     */
    private Long mealid;

    /**
     * 字段: tra_rechargemeal.realamt<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 充值金额
     *
     * @mbggenerated
     */
    private BigDecimal realamt;

    /**
     * 字段: tra_rechargemeal.rechargegold<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 充值播币数
     *
     * @mbggenerated
     */
    private BigDecimal rechargegold;

    /**
     * 字段: tra_rechargemeal.givegold<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 赠送播币数
     *
     * @mbggenerated
     */
    private BigDecimal givegold;

    /**
     * 字段: tra_rechargemeal.givepercent<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 赠送率  两位小数
     *
     * @mbggenerated
     */
    private BigDecimal givepercent;

    /**
     * 字段: tra_rechargemeal.expirydates<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 有效期起
     *
     * @mbggenerated
     */
    private Date expirydates;

    /**
     * 字段: tra_rechargemeal.expirydatee<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 有效期止
     *
     * @mbggenerated
     */
    private Date expirydatee;

    /**
     * 字段: tra_rechargemeal.mealnote<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 备注
     *
     * @mbggenerated
     */
    private String mealnote;

    /**
     * 字段: tra_rechargemeal.sortby<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 排序权重 默认为0  数字越大排序越靠前
     *
     * @mbggenerated
     */
    private Integer sortby;

    /**
     * 字段: tra_rechargemeal.is_delete<br/>
     * 必填: true<br/>
     * 缺省: b'0'<br/>
     * 长度: 1<br/>
     * 说明: 是否删除
     *
     * @mbggenerated
     */
    private Boolean isDelete;

    /**
     * 字段: tra_rechargemeal.create_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 创建人
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * 字段: tra_rechargemeal.create_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 字段: tra_rechargemeal.update_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 最后修改人
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * 字段: tra_rechargemeal.update_time<br/>
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
     * This field corresponds to the database table tra_rechargemeal
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return tra_rechargemeal.mealid: 充值套餐id
     *
     * @mbggenerated
     */
    public Long getMealid() {
        return mealid;
    }

    /**
     * 字段: tra_rechargemeal.mealid<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 充值套餐id
     *
     * @mbggenerated
     */
    public void setMealid(Long mealid) {
        this.mealid = mealid;
    }

    /**
     * @return tra_rechargemeal.realamt: 充值金额
     *
     * @mbggenerated
     */
    public BigDecimal getRealamt() {
        return realamt;
    }

    /**
     * 字段: tra_rechargemeal.realamt<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 充值金额
     *
     * @mbggenerated
     */
    public void setRealamt(BigDecimal realamt) {
        this.realamt = realamt;
    }

    /**
     * @return tra_rechargemeal.rechargegold: 充值播币数
     *
     * @mbggenerated
     */
    public BigDecimal getRechargegold() {
        return rechargegold;
    }

    /**
     * 字段: tra_rechargemeal.rechargegold<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 充值播币数
     *
     * @mbggenerated
     */
    public void setRechargegold(BigDecimal rechargegold) {
        this.rechargegold = rechargegold;
    }

    /**
     * @return tra_rechargemeal.givegold: 赠送播币数
     *
     * @mbggenerated
     */
    public BigDecimal getGivegold() {
        return givegold;
    }

    /**
     * 字段: tra_rechargemeal.givegold<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 赠送播币数
     *
     * @mbggenerated
     */
    public void setGivegold(BigDecimal givegold) {
        this.givegold = givegold;
    }

    /**
     * @return tra_rechargemeal.givepercent: 赠送率  两位小数
     *
     * @mbggenerated
     */
    public BigDecimal getGivepercent() {
        return givepercent;
    }

    /**
     * 字段: tra_rechargemeal.givepercent<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 赠送率  两位小数
     *
     * @mbggenerated
     */
    public void setGivepercent(BigDecimal givepercent) {
        this.givepercent = givepercent;
    }

    /**
     * @return tra_rechargemeal.expirydates: 有效期起
     *
     * @mbggenerated
     */
    public Date getExpirydates() {
        return expirydates;
    }

    /**
     * 字段: tra_rechargemeal.expirydates<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 有效期起
     *
     * @mbggenerated
     */
    public void setExpirydates(Date expirydates) {
        this.expirydates = expirydates;
    }

    /**
     * @return tra_rechargemeal.expirydatee: 有效期止
     *
     * @mbggenerated
     */
    public Date getExpirydatee() {
        return expirydatee;
    }

    /**
     * 字段: tra_rechargemeal.expirydatee<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 有效期止
     *
     * @mbggenerated
     */
    public void setExpirydatee(Date expirydatee) {
        this.expirydatee = expirydatee;
    }

    /**
     * @return tra_rechargemeal.mealnote: 备注
     *
     * @mbggenerated
     */
    public String getMealnote() {
        return mealnote;
    }

    /**
     * 字段: tra_rechargemeal.mealnote<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 备注
     *
     * @mbggenerated
     */
    public void setMealnote(String mealnote) {
        this.mealnote = mealnote;
    }

    /**
     * @return tra_rechargemeal.sortby: 排序权重 默认为0  数字越大排序越靠前
     *
     * @mbggenerated
     */
    public Integer getSortby() {
        return sortby;
    }

    /**
     * 字段: tra_rechargemeal.sortby<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 排序权重 默认为0  数字越大排序越靠前
     *
     * @mbggenerated
     */
    public void setSortby(Integer sortby) {
        this.sortby = sortby;
    }

    /**
     * @return tra_rechargemeal.is_delete: 是否删除
     *
     * @mbggenerated
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 字段: tra_rechargemeal.is_delete<br/>
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
     * @return tra_rechargemeal.create_user: 创建人
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 字段: tra_rechargemeal.create_user<br/>
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
     * @return tra_rechargemeal.create_time: 创建时间
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 字段: tra_rechargemeal.create_time<br/>
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
     * @return tra_rechargemeal.update_user: 最后修改人
     *
     * @mbggenerated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 字段: tra_rechargemeal.update_user<br/>
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
     * @return tra_rechargemeal.update_time: 更新时间
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 字段: tra_rechargemeal.update_time<br/>
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
     * This method corresponds to the database table tra_rechargemeal
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
        TraRechargemeal other = (TraRechargemeal) that;
        return (this.getMealid() == null ? other.getMealid() == null : this.getMealid().equals(other.getMealid()))
            && (this.getRealamt() == null ? other.getRealamt() == null : this.getRealamt().equals(other.getRealamt()))
            && (this.getRechargegold() == null ? other.getRechargegold() == null : this.getRechargegold().equals(other.getRechargegold()))
            && (this.getGivegold() == null ? other.getGivegold() == null : this.getGivegold().equals(other.getGivegold()))
            && (this.getGivepercent() == null ? other.getGivepercent() == null : this.getGivepercent().equals(other.getGivepercent()))
            && (this.getExpirydates() == null ? other.getExpirydates() == null : this.getExpirydates().equals(other.getExpirydates()))
            && (this.getExpirydatee() == null ? other.getExpirydatee() == null : this.getExpirydatee().equals(other.getExpirydatee()))
            && (this.getMealnote() == null ? other.getMealnote() == null : this.getMealnote().equals(other.getMealnote()))
            && (this.getSortby() == null ? other.getSortby() == null : this.getSortby().equals(other.getSortby()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_rechargemeal
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMealid() == null) ? 0 : getMealid().hashCode());
        result = prime * result + ((getRealamt() == null) ? 0 : getRealamt().hashCode());
        result = prime * result + ((getRechargegold() == null) ? 0 : getRechargegold().hashCode());
        result = prime * result + ((getGivegold() == null) ? 0 : getGivegold().hashCode());
        result = prime * result + ((getGivepercent() == null) ? 0 : getGivepercent().hashCode());
        result = prime * result + ((getExpirydates() == null) ? 0 : getExpirydates().hashCode());
        result = prime * result + ((getExpirydatee() == null) ? 0 : getExpirydatee().hashCode());
        result = prime * result + ((getMealnote() == null) ? 0 : getMealnote().hashCode());
        result = prime * result + ((getSortby() == null) ? 0 : getSortby().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_rechargemeal
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", mealid=").append(mealid);
        sb.append(", realamt=").append(realamt);
        sb.append(", rechargegold=").append(rechargegold);
        sb.append(", givegold=").append(givegold);
        sb.append(", givepercent=").append(givepercent);
        sb.append(", expirydates=").append(expirydates);
        sb.append(", expirydatee=").append(expirydatee);
        sb.append(", mealnote=").append(mealnote);
        sb.append(", sortby=").append(sortby);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}