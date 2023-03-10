package com.likes.common.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class BdBannerpicinfo implements Serializable {
    /**
     * 字段: bd_bannerpicinfo.bannerpicid<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 广告详情id
     *
     * @mbggenerated
     */
    private Long bannerpicid;

    /**
     * 字段: bd_bannerpicinfo.bseatid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 位置id
     *
     * @mbggenerated
     */
    private Long bseatid;

    /**
     * 字段: bd_bannerpicinfo.investorsid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 冠名商家id
     *
     * @mbggenerated
     */
    private Long investorsid;

    /**
     * 字段: bd_bannerpicinfo.linktype<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 链接跳转类型   1 文本 / 2 图片 / 3链接 / 4 参数 /
     *
     * @mbggenerated
     */
    private Integer linktype;

    /**
     * 字段: bd_bannerpicinfo.bndispic<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * 说明: 广告图片id
     *
     * @mbggenerated
     */
    private String bndispic;

    /**
     * 字段: bd_bannerpicinfo.bndisptxt<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 广告显示文字
     *
     * @mbggenerated
     */
    private String bndisptxt;

    /**
     * 字段: bd_bannerpicinfo.expirydates<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 有效期起
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expirydates;

    /**
     * 字段: bd_bannerpicinfo.expirydatee<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 有效期止
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expirydatee;

    /**
     * 字段: bd_bannerpicinfo.bndlink<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 链接
     *
     * @mbggenerated
     */
    private String bndlink;

    /**
     * 字段: bd_bannerpicinfo.specparame<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 200<br/>
     * 说明: 特殊参数
     *
     * @mbggenerated
     */
    private String specparame;

    /**
     * 字段: bd_bannerpicinfo.sortby<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 排序权重
     *
     * @mbggenerated
     */
    private Integer sortby;

    /**
     * 字段: bd_bannerpicinfo.is_delete<br/>
     * 必填: true<br/>
     * 缺省: b'0'<br/>
     * 长度: 1<br/>
     * 说明: 是否删除
     *
     * @mbggenerated
     */
    private Boolean isDelete;

    /**
     * 字段: bd_bannerpicinfo.create_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 创建人
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * 字段: bd_bannerpicinfo.create_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 字段: bd_bannerpicinfo.update_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 最后修改人
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * 字段: bd_bannerpicinfo.update_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 更新时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 字段: bd_bannerpicinfo.title<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 标题
     *
     * @mbggenerated
     */
    private String title;

    /**
     * 字段: bd_bannerpicinfo.path_url<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * 说明: 图片路径
     *
     * @mbggenerated
     */
    private String pathUrl;

    /**
     * 字段: bd_bannerpicinfo.within_link<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * 说明: 内部链接
     *
     * @mbggenerated
     */
    private String withinLink;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bd_bannerpicinfo
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return bd_bannerpicinfo.bannerpicid: 广告详情id
     *
     * @mbggenerated
     */
    public Long getBannerpicid() {
        return bannerpicid;
    }

    /**
     * 字段: bd_bannerpicinfo.bannerpicid<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 广告详情id
     *
     * @mbggenerated
     */
    public void setBannerpicid(Long bannerpicid) {
        this.bannerpicid = bannerpicid;
    }

    /**
     * @return bd_bannerpicinfo.bseatid: 位置id
     *
     * @mbggenerated
     */
    public Long getBseatid() {
        return bseatid;
    }

    /**
     * 字段: bd_bannerpicinfo.bseatid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 位置id
     *
     * @mbggenerated
     */
    public void setBseatid(Long bseatid) {
        this.bseatid = bseatid;
    }

    /**
     * @return bd_bannerpicinfo.investorsid: 冠名商家id
     *
     * @mbggenerated
     */
    public Long getInvestorsid() {
        return investorsid;
    }

    /**
     * 字段: bd_bannerpicinfo.investorsid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 冠名商家id
     *
     * @mbggenerated
     */
    public void setInvestorsid(Long investorsid) {
        this.investorsid = investorsid;
    }

    /**
     * @return bd_bannerpicinfo.linktype: 链接跳转类型   1 文本 / 2 图片 / 3链接 / 4 参数 /
     *
     * @mbggenerated
     */
    public Integer getLinktype() {
        return linktype;
    }

    /**
     * 字段: bd_bannerpicinfo.linktype<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 链接跳转类型   1 文本 / 2 图片 / 3链接 / 4 参数 /
     *
     * @mbggenerated
     */
    public void setLinktype(Integer linktype) {
        this.linktype = linktype;
    }

    /**
     * @return bd_bannerpicinfo.bndispic: 广告图片id
     *
     * @mbggenerated
     */
    public String getBndispic() {
        return bndispic;
    }

    /**
     * 字段: bd_bannerpicinfo.bndispic<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * 说明: 广告图片id
     *
     * @mbggenerated
     */
    public void setBndispic(String bndispic) {
        this.bndispic = bndispic;
    }

    /**
     * @return bd_bannerpicinfo.bndisptxt: 广告显示文字
     *
     * @mbggenerated
     */
    public String getBndisptxt() {
        return bndisptxt;
    }

    /**
     * 字段: bd_bannerpicinfo.bndisptxt<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 广告显示文字
     *
     * @mbggenerated
     */
    public void setBndisptxt(String bndisptxt) {
        this.bndisptxt = bndisptxt;
    }

    /**
     * @return bd_bannerpicinfo.expirydates: 有效期起
     *
     * @mbggenerated
     */
    public Date getExpirydates() {
        return expirydates;
    }

    /**
     * 字段: bd_bannerpicinfo.expirydates<br/>
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
     * @return bd_bannerpicinfo.expirydatee: 有效期止
     *
     * @mbggenerated
     */
    public Date getExpirydatee() {
        return expirydatee;
    }

    /**
     * 字段: bd_bannerpicinfo.expirydatee<br/>
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
     * @return bd_bannerpicinfo.bndlink: 链接
     *
     * @mbggenerated
     */
    public String getBndlink() {
        return bndlink;
    }

    /**
     * 字段: bd_bannerpicinfo.bndlink<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 链接
     *
     * @mbggenerated
     */
    public void setBndlink(String bndlink) {
        this.bndlink = bndlink;
    }

    /**
     * @return bd_bannerpicinfo.specparame: 特殊参数
     *
     * @mbggenerated
     */
    public String getSpecparame() {
        return specparame;
    }

    /**
     * 字段: bd_bannerpicinfo.specparame<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 200<br/>
     * 说明: 特殊参数
     *
     * @mbggenerated
     */
    public void setSpecparame(String specparame) {
        this.specparame = specparame;
    }

    /**
     * @return bd_bannerpicinfo.sortby: 排序权重
     *
     * @mbggenerated
     */
    public Integer getSortby() {
        return sortby;
    }

    /**
     * 字段: bd_bannerpicinfo.sortby<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 排序权重
     *
     * @mbggenerated
     */
    public void setSortby(Integer sortby) {
        this.sortby = sortby;
    }

    /**
     * @return bd_bannerpicinfo.is_delete: 是否删除
     *
     * @mbggenerated
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 字段: bd_bannerpicinfo.is_delete<br/>
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
     * @return bd_bannerpicinfo.create_user: 创建人
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 字段: bd_bannerpicinfo.create_user<br/>
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
     * @return bd_bannerpicinfo.create_time: 创建时间
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 字段: bd_bannerpicinfo.create_time<br/>
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
     * @return bd_bannerpicinfo.update_user: 最后修改人
     *
     * @mbggenerated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 字段: bd_bannerpicinfo.update_user<br/>
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
     * @return bd_bannerpicinfo.update_time: 更新时间
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 字段: bd_bannerpicinfo.update_time<br/>
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
     * @return bd_bannerpicinfo.title: 标题
     *
     * @mbggenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * 字段: bd_bannerpicinfo.title<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 标题
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return bd_bannerpicinfo.path_url: 图片路径
     *
     * @mbggenerated
     */
    public String getPathUrl() {
        return pathUrl;
    }

    /**
     * 字段: bd_bannerpicinfo.path_url<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * 说明: 图片路径
     *
     * @mbggenerated
     */
    public void setPathUrl(String pathUrl) {
        this.pathUrl = pathUrl;
    }

    /**
     * @return bd_bannerpicinfo.within_link: 内部链接
     *
     * @mbggenerated
     */
    public String getWithinLink() {
        return withinLink;
    }

    /**
     * 字段: bd_bannerpicinfo.within_link<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * 说明: 内部链接
     *
     * @mbggenerated
     */
    public void setWithinLink(String withinLink) {
        this.withinLink = withinLink;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bd_bannerpicinfo
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
        BdBannerpicinfo other = (BdBannerpicinfo) that;
        return (this.getBannerpicid() == null ? other.getBannerpicid() == null : this.getBannerpicid().equals(other.getBannerpicid()))
            && (this.getBseatid() == null ? other.getBseatid() == null : this.getBseatid().equals(other.getBseatid()))
            && (this.getInvestorsid() == null ? other.getInvestorsid() == null : this.getInvestorsid().equals(other.getInvestorsid()))
            && (this.getLinktype() == null ? other.getLinktype() == null : this.getLinktype().equals(other.getLinktype()))
            && (this.getBndispic() == null ? other.getBndispic() == null : this.getBndispic().equals(other.getBndispic()))
            && (this.getBndisptxt() == null ? other.getBndisptxt() == null : this.getBndisptxt().equals(other.getBndisptxt()))
            && (this.getExpirydates() == null ? other.getExpirydates() == null : this.getExpirydates().equals(other.getExpirydates()))
            && (this.getExpirydatee() == null ? other.getExpirydatee() == null : this.getExpirydatee().equals(other.getExpirydatee()))
            && (this.getBndlink() == null ? other.getBndlink() == null : this.getBndlink().equals(other.getBndlink()))
            && (this.getSpecparame() == null ? other.getSpecparame() == null : this.getSpecparame().equals(other.getSpecparame()))
            && (this.getSortby() == null ? other.getSortby() == null : this.getSortby().equals(other.getSortby()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getPathUrl() == null ? other.getPathUrl() == null : this.getPathUrl().equals(other.getPathUrl()))
            && (this.getWithinLink() == null ? other.getWithinLink() == null : this.getWithinLink().equals(other.getWithinLink()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bd_bannerpicinfo
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBannerpicid() == null) ? 0 : getBannerpicid().hashCode());
        result = prime * result + ((getBseatid() == null) ? 0 : getBseatid().hashCode());
        result = prime * result + ((getInvestorsid() == null) ? 0 : getInvestorsid().hashCode());
        result = prime * result + ((getLinktype() == null) ? 0 : getLinktype().hashCode());
        result = prime * result + ((getBndispic() == null) ? 0 : getBndispic().hashCode());
        result = prime * result + ((getBndisptxt() == null) ? 0 : getBndisptxt().hashCode());
        result = prime * result + ((getExpirydates() == null) ? 0 : getExpirydates().hashCode());
        result = prime * result + ((getExpirydatee() == null) ? 0 : getExpirydatee().hashCode());
        result = prime * result + ((getBndlink() == null) ? 0 : getBndlink().hashCode());
        result = prime * result + ((getSpecparame() == null) ? 0 : getSpecparame().hashCode());
        result = prime * result + ((getSortby() == null) ? 0 : getSortby().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getPathUrl() == null) ? 0 : getPathUrl().hashCode());
        result = prime * result + ((getWithinLink() == null) ? 0 : getWithinLink().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bd_bannerpicinfo
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bannerpicid=").append(bannerpicid);
        sb.append(", bseatid=").append(bseatid);
        sb.append(", investorsid=").append(investorsid);
        sb.append(", linktype=").append(linktype);
        sb.append(", bndispic=").append(bndispic);
        sb.append(", bndisptxt=").append(bndisptxt);
        sb.append(", expirydates=").append(expirydates);
        sb.append(", expirydatee=").append(expirydatee);
        sb.append(", bndlink=").append(bndlink);
        sb.append(", specparame=").append(specparame);
        sb.append(", sortby=").append(sortby);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", title=").append(title);
        sb.append(", pathUrl=").append(pathUrl);
        sb.append(", withinLink=").append(withinLink);
        sb.append("]");
        return sb.toString();
    }
}
