package com.likes.common.mybatis.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "`member_credit_change`")
public class MemberCreditChange {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "`accno`")
    private String accno;

    @Column(name = "`ref_acclogin`")
    private String refAcclogin;

    @Column(name = "`integral`")
    private Integer integral;

    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return accno
     */
    public String getAccno() {
        return accno;
    }

    /**
     * @param accno
     */
    public void setAccno(String accno) {
        this.accno = accno;
    }

    /**
     * @return ref_acclogin
     */
    public String getRefAcclogin() {
        return refAcclogin;
    }

    /**
     * @param refAcclogin
     */
    public void setRefAcclogin(String refAcclogin) {
        this.refAcclogin = refAcclogin;
    }

    /**
     * @return integral
     */
    public Integer getIntegral() {
        return integral;
    }

    /**
     * @param integral
     */
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
