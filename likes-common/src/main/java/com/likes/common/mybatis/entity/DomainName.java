package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class DomainName implements Serializable {
    /**
     * 字段: domain_name.id<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 主键
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 字段: domain_name.name<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 名称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 字段: domain_name.url<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 域名
     *
     * @mbggenerated
     */
    private String url;

    /**
     * 字段: domain_name.type<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 类型：app:app域名 web：web域名，file:文件域名，oss:oss域名
     *
     * @mbggenerated
     */
    private String type;

    /**
     * 字段: domain_name.desc<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 描述
     *
     * @mbggenerated
     */
    private String desc;

    /**
     * 字段: domain_name.tb_status<br/>
     * 必填: true<br/>
     * 缺省: NORMAL<br/>
     * 长度: 50<br/>
     * 说明: 状态：NORMAL:正常，DELETED:删除
     *
     * @mbggenerated
     */
    private String tbStatus;

    /**
     * 字段: domain_name.create_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 字段: domain_name.update_time<br/>
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
     * This field corresponds to the database table domain_name
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return domain_name.id: 主键
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 字段: domain_name.id<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 主键
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return domain_name.name: 名称
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * 字段: domain_name.name<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 名称
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return domain_name.url: 域名
     *
     * @mbggenerated
     */
    public String getUrl() {
        return url;
    }

    /**
     * 字段: domain_name.url<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 域名
     *
     * @mbggenerated
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return domain_name.type: 类型：app:app域名 web：web域名，file:文件域名，oss:oss域名
     *
     * @mbggenerated
     */
    public String getType() {
        return type;
    }

    /**
     * 字段: domain_name.type<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 类型：app:app域名 web：web域名，file:文件域名，oss:oss域名
     *
     * @mbggenerated
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return domain_name.desc: 描述
     *
     * @mbggenerated
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 字段: domain_name.desc<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 描述
     *
     * @mbggenerated
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return domain_name.tb_status: 状态：NORMAL:正常，DELETED:删除
     *
     * @mbggenerated
     */
    public String getTbStatus() {
        return tbStatus;
    }

    /**
     * 字段: domain_name.tb_status<br/>
     * 必填: true<br/>
     * 缺省: NORMAL<br/>
     * 长度: 50<br/>
     * 说明: 状态：NORMAL:正常，DELETED:删除
     *
     * @mbggenerated
     */
    public void setTbStatus(String tbStatus) {
        this.tbStatus = tbStatus;
    }

    /**
     * @return domain_name.create_time: 创建时间
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 字段: domain_name.create_time<br/>
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
     * @return domain_name.update_time: 更新时间
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 字段: domain_name.update_time<br/>
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
     * This method corresponds to the database table domain_name
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
        DomainName other = (DomainName) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getDesc() == null ? other.getDesc() == null : this.getDesc().equals(other.getDesc()))
                && (this.getTbStatus() == null ? other.getTbStatus() == null : this.getTbStatus().equals(other.getTbStatus()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table domain_name
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getDesc() == null) ? 0 : getDesc().hashCode());
        result = prime * result + ((getTbStatus() == null) ? 0 : getTbStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table domain_name
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
        sb.append(", url=").append(url);
        sb.append(", type=").append(type);
        sb.append(", desc=").append(desc);
        sb.append(", tbStatus=").append(tbStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}