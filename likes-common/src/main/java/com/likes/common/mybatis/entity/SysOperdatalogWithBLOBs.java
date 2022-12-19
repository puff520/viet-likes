package com.likes.common.mybatis.entity;

import java.io.Serializable;

public class SysOperdatalogWithBLOBs extends SysOperdatalog implements Serializable {
    /**
     * 字段: sys_operdatalog.beforedata<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16777215<br/>
     * 说明: 操作前数据
     *
     * @mbggenerated
     */
    private String beforedata;

    /**
     * 字段: sys_operdatalog.lastdate<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16777215<br/>
     * 说明: 操作后数据
     *
     * @mbggenerated
     */
    private String lastdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_operdatalog
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return sys_operdatalog.beforedata: 操作前数据
     *
     * @mbggenerated
     */
    public String getBeforedata() {
        return beforedata;
    }

    /**
     * 字段: sys_operdatalog.beforedata<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16777215<br/>
     * 说明: 操作前数据
     *
     * @mbggenerated
     */
    public void setBeforedata(String beforedata) {
        this.beforedata = beforedata;
    }

    /**
     * @return sys_operdatalog.lastdate: 操作后数据
     *
     * @mbggenerated
     */
    public String getLastdate() {
        return lastdate;
    }

    /**
     * 字段: sys_operdatalog.lastdate<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16777215<br/>
     * 说明: 操作后数据
     *
     * @mbggenerated
     */
    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
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
        SysOperdatalogWithBLOBs other = (SysOperdatalogWithBLOBs) that;
        return (this.getOplogid() == null ? other.getOplogid() == null : this.getOplogid().equals(other.getOplogid()))
            && (this.getAccno() == null ? other.getAccno() == null : this.getAccno().equals(other.getAccno()))
            && (this.getDbname() == null ? other.getDbname() == null : this.getDbname().equals(other.getDbname()))
            && (this.getModulename() == null ? other.getModulename() == null : this.getModulename().equals(other.getModulename()))
            && (this.getRefcollname() == null ? other.getRefcollname() == null : this.getRefcollname().equals(other.getRefcollname()))
            && (this.getOptcontent() == null ? other.getOptcontent() == null : this.getOptcontent().equals(other.getOptcontent()))
            && (this.getOperstatus() == null ? other.getOperstatus() == null : this.getOperstatus().equals(other.getOperstatus()))
            && (this.getOperdate() == null ? other.getOperdate() == null : this.getOperdate().equals(other.getOperdate()))
            && (this.getBeforedata() == null ? other.getBeforedata() == null : this.getBeforedata().equals(other.getBeforedata()))
            && (this.getLastdate() == null ? other.getLastdate() == null : this.getLastdate().equals(other.getLastdate()));
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
        result = prime * result + ((getBeforedata() == null) ? 0 : getBeforedata().hashCode());
        result = prime * result + ((getLastdate() == null) ? 0 : getLastdate().hashCode());
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
        sb.append(", beforedata=").append(beforedata);
        sb.append(", lastdate=").append(lastdate);
        sb.append("]");
        return sb.toString();
    }
}