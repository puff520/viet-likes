package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FinanceDamaAdjustment implements Serializable {
    /**
     * 字段: finance_dama_adjustment.id<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 字段: finance_dama_adjustment.type<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 加码:1,减码:-1
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * 字段: finance_dama_adjustment.accno<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 40<br/>
     * 说明: 会员标识
     *
     * @mbggenerated
     */
    private String accno;

    /**
     * 字段: finance_dama_adjustment.damaliang<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 加扣打码量
     *
     * @mbggenerated
     */
    private BigDecimal damaliang;

    /**
     * 字段: finance_dama_adjustment.remark<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 加扣打码原因
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 字段: finance_dama_adjustment.is_delete<br/>
     * 必填: true<br/>
     * 缺省: b'0'<br/>
     * 长度: 1<br/>
     * 说明: 是否删除
     *
     * @mbggenerated
     */
    private Boolean isDelete;

    /**
     * 字段: finance_dama_adjustment.create_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 40<br/>
     * 说明: 创建人
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * 字段: finance_dama_adjustment.create_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 字段: finance_dama_adjustment.update_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 40<br/>
     * 说明: 最后修改人
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * 字段: finance_dama_adjustment.update_time<br/>
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
     * This field corresponds to the database table finance_dama_adjustment
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return finance_dama_adjustment.id: 
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 字段: finance_dama_adjustment.id<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return finance_dama_adjustment.type: 加码:1,减码:-1
     *
     * @mbggenerated
     */
    public Integer getType() {
        return type;
    }

    /**
     * 字段: finance_dama_adjustment.type<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 加码:1,减码:-1
     *
     * @mbggenerated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return finance_dama_adjustment.accno: 会员标识
     *
     * @mbggenerated
     */
    public String getAccno() {
        return accno;
    }

    /**
     * 字段: finance_dama_adjustment.accno<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 40<br/>
     * 说明: 会员标识
     *
     * @mbggenerated
     */
    public void setAccno(String accno) {
        this.accno = accno;
    }

    /**
     * @return finance_dama_adjustment.damaliang: 加扣打码量
     *
     * @mbggenerated
     */
    public BigDecimal getDamaliang() {
        return damaliang;
    }

    /**
     * 字段: finance_dama_adjustment.damaliang<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 加扣打码量
     *
     * @mbggenerated
     */
    public void setDamaliang(BigDecimal damaliang) {
        this.damaliang = damaliang;
    }

    /**
     * @return finance_dama_adjustment.remark: 加扣打码原因
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 字段: finance_dama_adjustment.remark<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 加扣打码原因
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return finance_dama_adjustment.is_delete: 是否删除
     *
     * @mbggenerated
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 字段: finance_dama_adjustment.is_delete<br/>
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
     * @return finance_dama_adjustment.create_user: 创建人
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 字段: finance_dama_adjustment.create_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 40<br/>
     * 说明: 创建人
     *
     * @mbggenerated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * @return finance_dama_adjustment.create_time: 创建时间
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 字段: finance_dama_adjustment.create_time<br/>
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
     * @return finance_dama_adjustment.update_user: 最后修改人
     *
     * @mbggenerated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 字段: finance_dama_adjustment.update_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 40<br/>
     * 说明: 最后修改人
     *
     * @mbggenerated
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * @return finance_dama_adjustment.update_time: 更新时间
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 字段: finance_dama_adjustment.update_time<br/>
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
     * This method corresponds to the database table finance_dama_adjustment
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
        FinanceDamaAdjustment other = (FinanceDamaAdjustment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getAccno() == null ? other.getAccno() == null : this.getAccno().equals(other.getAccno()))
            && (this.getDamaliang() == null ? other.getDamaliang() == null : this.getDamaliang().equals(other.getDamaliang()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table finance_dama_adjustment
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getAccno() == null) ? 0 : getAccno().hashCode());
        result = prime * result + ((getDamaliang() == null) ? 0 : getDamaliang().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table finance_dama_adjustment
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
        sb.append(", type=").append(type);
        sb.append(", accno=").append(accno);
        sb.append(", damaliang=").append(damaliang);
        sb.append(", remark=").append(remark);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}