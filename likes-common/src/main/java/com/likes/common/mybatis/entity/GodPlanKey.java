package com.likes.common.mybatis.entity;

import java.io.Serializable;

public class GodPlanKey implements Serializable {
    /**
     * 字段: god_plan.id<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 字段: god_plan.god_id<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 大神Id
     *
     * @mbggenerated
     */
    private Integer godId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table god_plan
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return god_plan.id: id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 字段: god_plan.id<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return god_plan.god_id: 大神Id
     *
     * @mbggenerated
     */
    public Integer getGodId() {
        return godId;
    }

    /**
     * 字段: god_plan.god_id<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 大神Id
     *
     * @mbggenerated
     */
    public void setGodId(Integer godId) {
        this.godId = godId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan
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
        GodPlanKey other = (GodPlanKey) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGodId() == null ? other.getGodId() == null : this.getGodId().equals(other.getGodId()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGodId() == null) ? 0 : getGodId().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan
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
        sb.append(", godId=").append(godId);
        sb.append("]");
        return sb.toString();
    }
}