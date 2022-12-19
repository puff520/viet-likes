package com.likes.common.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "`mem_burying_point`")
public class MemBuryingPoint {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`mtype`")
    private String mtype;

    @Column(name = "`accno`")
    private String accno;

    @Column(name = "`mcode`")
    private String mcode;

    @Column(name = "`optype`")
    private Integer optype;

    @Column(name = "`create_time`")
    private Date createTime;

    @Column(name = "`update_time`")
    private Date updateTime;

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
     * @return mtype
     */
    public String getMtype() {
        return mtype;
    }

    /**
     * @param mtype
     */
    public void setMtype(String mtype) {
        this.mtype = mtype;
    }

    /**
     * @return mcode
     */
    public String getMcode() {
        return mcode;
    }

    /**
     * @param mcode
     */
    public void setMcode(String mcode) {
        this.mcode = mcode;
    }

    /**
     * @return optype
     */
    public Integer getOptype() {
        return optype;
    }

    /**
     * @param optype
     */
    public void setOptype(Integer optype) {
        this.optype = optype;
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

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
