package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class SysOperdatalog implements Serializable {
    /**
     * 字段: sys_operdatalog.oplogid<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 日志id
     *
     * @mbggenerated
     */
    private Long oplogid;

    /**
     * 字段: sys_operdatalog.accno<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 操作用户
     *
     * @mbggenerated
     */
    private String accno;

    /**
     * 字段: sys_operdatalog.dbname<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 数据库名称
     *
     * @mbggenerated
     */
    private String dbname;

    /**
     * 字段: sys_operdatalog.modulename<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 模块名称
     *
     * @mbggenerated
     */
    private String modulename;

    /**
     * 字段: sys_operdatalog.refcollname<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 表或集合名称
     *
     * @mbggenerated
     */
    private String refcollname;

    /**
     * 字段: sys_operdatalog.optcontent<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1000<br/>
     * 说明: 日志内容
     *
     * @mbggenerated
     */
    private String optcontent;

    /**
     * 字段: sys_operdatalog.operstatus<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1<br/>
     * 说明: 操作状态0成功 9失败
     *
     * @mbggenerated
     */
    private Integer operstatus;

    /**
     * 字段: sys_operdatalog.operdate<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 操作时间
     *
     * @mbggenerated
     */
    private Date operdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_operdatalog
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return sys_operdatalog.oplogid: 日志id
     *
     * @mbggenerated
     */
    public Long getOplogid() {
        return oplogid;
    }

    /**
     * 字段: sys_operdatalog.oplogid<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 日志id
     *
     * @mbggenerated
     */
    public void setOplogid(Long oplogid) {
        this.oplogid = oplogid;
    }

    /**
     * @return sys_operdatalog.accno: 操作用户
     *
     * @mbggenerated
     */
    public String getAccno() {
        return accno;
    }

    /**
     * 字段: sys_operdatalog.accno<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 操作用户
     *
     * @mbggenerated
     */
    public void setAccno(String accno) {
        this.accno = accno;
    }

    /**
     * @return sys_operdatalog.dbname: 数据库名称
     *
     * @mbggenerated
     */
    public String getDbname() {
        return dbname;
    }

    /**
     * 字段: sys_operdatalog.dbname<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 数据库名称
     *
     * @mbggenerated
     */
    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    /**
     * @return sys_operdatalog.modulename: 模块名称
     *
     * @mbggenerated
     */
    public String getModulename() {
        return modulename;
    }

    /**
     * 字段: sys_operdatalog.modulename<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 模块名称
     *
     * @mbggenerated
     */
    public void setModulename(String modulename) {
        this.modulename = modulename;
    }

    /**
     * @return sys_operdatalog.refcollname: 表或集合名称
     *
     * @mbggenerated
     */
    public String getRefcollname() {
        return refcollname;
    }

    /**
     * 字段: sys_operdatalog.refcollname<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 表或集合名称
     *
     * @mbggenerated
     */
    public void setRefcollname(String refcollname) {
        this.refcollname = refcollname;
    }

    /**
     * @return sys_operdatalog.optcontent: 日志内容
     *
     * @mbggenerated
     */
    public String getOptcontent() {
        return optcontent;
    }

    /**
     * 字段: sys_operdatalog.optcontent<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1000<br/>
     * 说明: 日志内容
     *
     * @mbggenerated
     */
    public void setOptcontent(String optcontent) {
        this.optcontent = optcontent;
    }

    /**
     * @return sys_operdatalog.operstatus: 操作状态0成功 9失败
     *
     * @mbggenerated
     */
    public Integer getOperstatus() {
        return operstatus;
    }

    /**
     * 字段: sys_operdatalog.operstatus<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1<br/>
     * 说明: 操作状态0成功 9失败
     *
     * @mbggenerated
     */
    public void setOperstatus(Integer operstatus) {
        this.operstatus = operstatus;
    }

    /**
     * @return sys_operdatalog.operdate: 操作时间
     *
     * @mbggenerated
     */
    public Date getOperdate() {
        return operdate;
    }

    /**
     * 字段: sys_operdatalog.operdate<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 操作时间
     *
     * @mbggenerated
     */
    public void setOperdate(Date operdate) {
        this.operdate = operdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operdatalog
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
        SysOperdatalog other = (SysOperdatalog) that;
        return (this.getOplogid() == null ? other.getOplogid() == null : this.getOplogid().equals(other.getOplogid()))
            && (this.getAccno() == null ? other.getAccno() == null : this.getAccno().equals(other.getAccno()))
            && (this.getDbname() == null ? other.getDbname() == null : this.getDbname().equals(other.getDbname()))
            && (this.getModulename() == null ? other.getModulename() == null : this.getModulename().equals(other.getModulename()))
            && (this.getRefcollname() == null ? other.getRefcollname() == null : this.getRefcollname().equals(other.getRefcollname()))
            && (this.getOptcontent() == null ? other.getOptcontent() == null : this.getOptcontent().equals(other.getOptcontent()))
            && (this.getOperstatus() == null ? other.getOperstatus() == null : this.getOperstatus().equals(other.getOperstatus()))
            && (this.getOperdate() == null ? other.getOperdate() == null : this.getOperdate().equals(other.getOperdate()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operdatalog
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOplogid() == null) ? 0 : getOplogid().hashCode());
        result = prime * result + ((getAccno() == null) ? 0 : getAccno().hashCode());
        result = prime * result + ((getDbname() == null) ? 0 : getDbname().hashCode());
        result = prime * result + ((getModulename() == null) ? 0 : getModulename().hashCode());
        result = prime * result + ((getRefcollname() == null) ? 0 : getRefcollname().hashCode());
        result = prime * result + ((getOptcontent() == null) ? 0 : getOptcontent().hashCode());
        result = prime * result + ((getOperstatus() == null) ? 0 : getOperstatus().hashCode());
        result = prime * result + ((getOperdate() == null) ? 0 : getOperdate().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operdatalog
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", oplogid=").append(oplogid);
        sb.append(", accno=").append(accno);
        sb.append(", dbname=").append(dbname);
        sb.append(", modulename=").append(modulename);
        sb.append(", refcollname=").append(refcollname);
        sb.append(", optcontent=").append(optcontent);
        sb.append(", operstatus=").append(operstatus);
        sb.append(", operdate=").append(operdate);
        sb.append("]");
        return sb.toString();
    }
}