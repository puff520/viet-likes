package com.likes.common.mybatis.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "`mem_relationship`")
public class MemRelationship2 {
    /**
     * 关系id
     */
    @Id
    @Column(name = "`relaid`")
    private Long relaid;

    /**
     * 推荐人标识号
     */
    @Column(name = "`refaccno`")
    private String refaccno;

    @Column(name = "`parent_id`")
    private Long parentId;

    /**
     * 会员标识号
     */
    @Column(name = "`accno`")
    private String accno;

    /**
     * 真实姓名
     */
    @Column(name = "`memname`")
    private String memname;

    @Column(name = "`sub_num`")
    private Integer subNum;

    @Column(name = "`sup_recomcode`")
    private String supRecomcode;

    @Column(name = "`head_accno`")
    private String headAccno;

    /**
     * 是否删除
     */
    @Column(name = "`is_delete`")
    private Boolean isDelete;

    /**
     * 创建人
     */
    @Column(name = "`create_user`")
    private String createUser;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 最后修改人
     */
    @Column(name = "`update_user`")
    private String updateUser;

    /**
     * 更新时间
     */
    @Column(name = "`update_time`")
    private Date updateTime;

    /**
     * 获取关系id
     *
     * @return relaid - 关系id
     */
    public Long getRelaid() {
        return relaid;
    }

    /**
     * 设置关系id
     *
     * @param relaid 关系id
     */
    public void setRelaid(Long relaid) {
        this.relaid = relaid;
    }

    /**
     * 获取推荐人标识号
     *
     * @return refaccno - 推荐人标识号
     */
    public String getRefaccno() {
        return refaccno;
    }

    /**
     * 设置推荐人标识号
     *
     * @param refaccno 推荐人标识号
     */
    public void setRefaccno(String refaccno) {
        this.refaccno = refaccno;
    }

    /**
     * @return parent_id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取会员标识号
     *
     * @return accno - 会员标识号
     */
    public String getAccno() {
        return accno;
    }

    /**
     * 设置会员标识号
     *
     * @param accno 会员标识号
     */
    public void setAccno(String accno) {
        this.accno = accno;
    }

    /**
     * 获取真实姓名
     *
     * @return memname - 真实姓名
     */
    public String getMemname() {
        return memname;
    }

    /**
     * 设置真实姓名
     *
     * @param memname 真实姓名
     */
    public void setMemname(String memname) {
        this.memname = memname;
    }

    /**
     * @return sub_num
     */
    public Integer getSubNum() {
        return subNum;
    }

    /**
     * @param subNum
     */
    public void setSubNum(Integer subNum) {
        this.subNum = subNum;
    }

    /**
     * @return sup_recomcode
     */
    public String getSupRecomcode() {
        return supRecomcode;
    }

    /**
     * @param supRecomcode
     */
    public void setSupRecomcode(String supRecomcode) {
        this.supRecomcode = supRecomcode;
    }

    /**
     * @return head_accno
     */
    public String getHeadAccno() {
        return headAccno;
    }

    /**
     * @param headAccno
     */
    public void setHeadAccno(String headAccno) {
        this.headAccno = headAccno;
    }

    /**
     * 获取是否删除
     *
     * @return is_delete - 是否删除
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否删除
     *
     * @param isDelete 是否删除
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取创建人
     *
     * @return create_user - 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人
     *
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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
     * 获取最后修改人
     *
     * @return update_user - 最后修改人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置最后修改人
     *
     * @param updateUser 最后修改人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
