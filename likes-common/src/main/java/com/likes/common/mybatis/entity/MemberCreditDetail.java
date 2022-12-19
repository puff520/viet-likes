package com.likes.common.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "`member_credit_detail`")
public class MemberCreditDetail {
    /**
     * id
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 会员标识
     */
    @Column(name = "`num`")
    private Integer num;

    /**
     * 积分
     */
    @Column(name = "`integral`")
    private Integer integral;

    /**
     * 1 增加 2 扣除
     */
    @Column(name = "`value`")
    private String value;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-5")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "`update_time`")
    private Date updateTime;

    /**
     * 更新人
     */
    @Column(name = "`update_user`")
    private String updateUser;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取会员标识
     *
     * @return num - 会员标识
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置会员标识
     *
     * @param num 会员标识
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取积分
     *
     * @return integral - 积分
     */
    public Integer getIntegral() {
        return integral;
    }

    /**
     * 设置积分
     *
     * @param integral 积分
     */
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /**
     * 获取1 增加 2 扣除
     *
     * @return value - 1 增加 2 扣除
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置1 增加 2 扣除
     *
     * @param value 1 增加 2 扣除
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取更新人
     *
     * @return update_user - 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置更新人
     *
     * @param updateUser 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
