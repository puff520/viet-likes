package com.likes.common.model.vo.lottery;

import com.likes.common.model.common.PageBaseInfo;
import com.likes.common.model.dto.LhcXsRecommendContentDTO;

import java.util.List;

/**
 * 六合彩心水推荐列表
 *
 * @author lzy
 * @create 2018-09-08 16:34
 **/
public class LhcXsRecommendAppVO extends PageBaseInfo {

    private Integer id;
    private Integer count; // 通用型Integer参数
    private String findNickNameOrTitle;// 搜索引擎: 名字或者标题
    private String orderSort_admire;// 排序方式:
    private Integer locked; // 是否锁贴
    private String xstype; // 1大厅2精华3热门4今日
    /**
     * 说明: 序号
     */
    private Integer sort;

    /**
     * 说明: 推荐人
     */
    private String referrer;

    /**
     * 说明: 推荐人微信号
     */
    private String wxh;

    /**
     * 说明: 推荐人头像
     */
    private String heads;

    /**
     * 说明: 推荐人二维码
     */
    private String qrCode;

    /**
     * 说明: 类型
     */
    private String type;

    /**
     * 说明: 标题
     */
    private String title;

    /**
     * 说明: 正文
     */
    private String content;

    /**
     * 说明: 二维码是否显示 0否 1是
     */
    private Integer qrShow;

    /**
     * 说明: 真实的阅读数
     */
    private Integer realViews;

    /**
     * 说明: 真实的点赞数
     */
    private Integer realAdmire;

    /**
     * 说明: 总点赞数
     */
    private Integer totalAdmire;

    /**
     * 粉丝数
     */
    private Integer fansNumber;

    /**
     * 说明: 真实的点赞数
     */
    private Integer commentCount;

    /**
     * 说明: 创建时间
     */
    private String createTime;

    /**
     * 说明: 审核状态 1.未审核 2.审核通过 3.审核拒绝
     */
    private Integer auditStatus;

    /**
     * 说明: 审核状态 未审核 审核通过 审核拒绝 中文返回
     */
    private String auditStatus_desc;

    /**
     * 说明: 推荐内容
     */
    private List<LhcXsRecommendContentDTO> contentDTOList;

    /**
     * 说明: 是否已经关注
     */
    private Integer alreadyFllow;

    /**
     * 说明: 是否已经点赞
     */
    private Integer alreadyAdmire;

    /**
     * 是否是个人的数据
     */
    private Integer isOwn;

    /**
     * 用户Id
     */
    private Integer parentMemberId;

    /**
     * 用户Id
     */
    private String commentTime;

    public Integer getFansNumber() {
        return fansNumber;
    }

    public void setFansNumber(Integer fansNumber) {
        this.fansNumber = fansNumber;
    }

    /**
     * 后台用户Id
     */
    private Integer referrerId;

    /**
     * 帖子用户ID
     */
    private Integer ownUserId;

    private Integer loginViewFlag;//登录可见

    private Integer replyViewFlag;//回复可见

    private String groupId;//群组ID

    private Integer rechargeViewMoney;

    public Integer getRechargeViewMoney() {
        return rechargeViewMoney;
    }

    public void setRechargeViewMoney(Integer rechargeViewMoney) {
        this.rechargeViewMoney = rechargeViewMoney;
    }

    private Integer privateGroup;

    public Integer getPrivateGroup() {
        return privateGroup;
    }

    public void setPrivateGroup(Integer privateGroup) {
        this.privateGroup = privateGroup;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getOwnUserId() {
        return ownUserId;
    }

    public void setOwnUserId(Integer ownUserId) {
        this.ownUserId = ownUserId;
    }

    public String getXstype() {
        return xstype;
    }

    public void setXstype(String xstype) {
        this.xstype = xstype;
    }

    public Integer getParentMemberId() {
        return parentMemberId;
    }

    public void setParentMemberId(Integer parentMemberId) {
        this.parentMemberId = parentMemberId;
    }

    public Integer getReferrerId() {
        return referrerId;
    }

    public void setReferrerId(Integer referrerId) {
        this.referrerId = referrerId;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public String getOrderSort_admire() {
        return orderSort_admire;
    }

    public void setOrderSort_admire(String orderSort_admire) {
        this.orderSort_admire = orderSort_admire;
    }

    public Integer getIsOwn() {
        return isOwn;
    }

    public void setIsOwn(Integer isOwn) {
        this.isOwn = isOwn;
    }

    public Integer getAlreadyAdmire() {
        return alreadyAdmire;
    }

    public void setAlreadyAdmire(Integer alreadyAdmire) {
        this.alreadyAdmire = alreadyAdmire;
    }

    public Integer getTotalAdmire() {
        return totalAdmire;
    }

    public void setTotalAdmire(Integer totalAdmire) {
        this.totalAdmire = totalAdmire;
    }

    public Integer getAlreadyFllow() {
        return alreadyFllow;
    }

    public void setAlreadyFllow(Integer alreadyFllow) {
        this.alreadyFllow = alreadyFllow;
    }

    public String getFindNickNameOrTitle() {
        return findNickNameOrTitle;
    }

    public void setFindNickNameOrTitle(String findNickNameOrTitle) {
        this.findNickNameOrTitle = findNickNameOrTitle;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditStatus_desc() {
        return auditStatus_desc;
    }

    public void setAuditStatus_desc(String auditStatus_desc) {
        this.auditStatus_desc = auditStatus_desc;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getWxh() {
        return wxh;
    }

    public void setWxh(String wxh) {
        this.wxh = wxh;
    }

    public String getHeads() {
        return heads;
    }

    public void setHeads(String heads) {
        this.heads = heads;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRealViews() {
        return realViews;
    }

    public void setRealViews(Integer realViews) {
        this.realViews = realViews;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<LhcXsRecommendContentDTO> getContentDTOList() {
        return contentDTOList;
    }

    public void setContentDTOList(List<LhcXsRecommendContentDTO> contentDTOList) {
        this.contentDTOList = contentDTOList;
    }

    public Integer getRealAdmire() {
        return realAdmire;
    }

    public void setRealAdmire(Integer realAdmire) {
        this.realAdmire = realAdmire;
    }

    public Integer getQrShow() {
        return qrShow;
    }

    public void setQrShow(Integer qrShow) {
        this.qrShow = qrShow;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getLoginViewFlag() {
        return loginViewFlag;
    }

    public void setLoginViewFlag(Integer loginViewFlag) {
        this.loginViewFlag = loginViewFlag;
    }

    public Integer getReplyViewFlag() {
        return replyViewFlag;
    }

    public void setReplyViewFlag(Integer replyViewFlag) {
        this.replyViewFlag = replyViewFlag;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

}
