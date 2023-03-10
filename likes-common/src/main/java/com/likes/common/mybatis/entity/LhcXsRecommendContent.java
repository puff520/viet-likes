package com.likes.common.mybatis.entity;

import java.io.Serializable;

public class LhcXsRecommendContent implements Serializable {
    /**
     * 字段: lhc_xs_recommend_content.id<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 字段: lhc_xs_recommend_content.issue<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 期号
     *
     * @mbggenerated
     */
    private String issue;

    /**
     * 字段: lhc_xs_recommend_content.play<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 玩法
     *
     * @mbggenerated
     */
    private String play;

    /**
     * 字段: lhc_xs_recommend_content.number<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 号码
     *
     * @mbggenerated
     */
    private String number;

    /**
     * 字段: lhc_xs_recommend_content.recommend_id<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 推荐正文id
     *
     * @mbggenerated
     */
    private Integer recommendId;

    /**
     * 字段: lhc_xs_recommend_content.sg<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 玩法
     *
     * @mbggenerated
     */
    private String sg;

    /**
     * 字段: lhc_xs_recommend_content.create_time<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    private String createTime;

    /**
     * 字段: lhc_xs_recommend_content.deleted<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 是否删除
     *
     * @mbggenerated
     */
    private Integer deleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table lhc_xs_recommend_content
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return lhc_xs_recommend_content.id: 
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 字段: lhc_xs_recommend_content.id<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return lhc_xs_recommend_content.issue: 期号
     *
     * @mbggenerated
     */
    public String getIssue() {
        return issue;
    }

    /**
     * 字段: lhc_xs_recommend_content.issue<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 期号
     *
     * @mbggenerated
     */
    public void setIssue(String issue) {
        this.issue = issue;
    }

    /**
     * @return lhc_xs_recommend_content.play: 玩法
     *
     * @mbggenerated
     */
    public String getPlay() {
        return play;
    }

    /**
     * 字段: lhc_xs_recommend_content.play<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 玩法
     *
     * @mbggenerated
     */
    public void setPlay(String play) {
        this.play = play;
    }

    /**
     * @return lhc_xs_recommend_content.number: 号码
     *
     * @mbggenerated
     */
    public String getNumber() {
        return number;
    }

    /**
     * 字段: lhc_xs_recommend_content.number<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 号码
     *
     * @mbggenerated
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return lhc_xs_recommend_content.recommend_id: 推荐正文id
     *
     * @mbggenerated
     */
    public Integer getRecommendId() {
        return recommendId;
    }

    /**
     * 字段: lhc_xs_recommend_content.recommend_id<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 推荐正文id
     *
     * @mbggenerated
     */
    public void setRecommendId(Integer recommendId) {
        this.recommendId = recommendId;
    }

    /**
     * @return lhc_xs_recommend_content.sg: 玩法
     *
     * @mbggenerated
     */
    public String getSg() {
        return sg;
    }

    /**
     * 字段: lhc_xs_recommend_content.sg<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 玩法
     *
     * @mbggenerated
     */
    public void setSg(String sg) {
        this.sg = sg;
    }

    /**
     * @return lhc_xs_recommend_content.create_time: 创建时间
     *
     * @mbggenerated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 字段: lhc_xs_recommend_content.create_time<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * @return lhc_xs_recommend_content.deleted: 是否删除
     *
     * @mbggenerated
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 字段: lhc_xs_recommend_content.deleted<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 是否删除
     *
     * @mbggenerated
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_xs_recommend_content
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
        LhcXsRecommendContent other = (LhcXsRecommendContent) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIssue() == null ? other.getIssue() == null : this.getIssue().equals(other.getIssue()))
            && (this.getPlay() == null ? other.getPlay() == null : this.getPlay().equals(other.getPlay()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getRecommendId() == null ? other.getRecommendId() == null : this.getRecommendId().equals(other.getRecommendId()))
            && (this.getSg() == null ? other.getSg() == null : this.getSg().equals(other.getSg()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_xs_recommend_content
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIssue() == null) ? 0 : getIssue().hashCode());
        result = prime * result + ((getPlay() == null) ? 0 : getPlay().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getRecommendId() == null) ? 0 : getRecommendId().hashCode());
        result = prime * result + ((getSg() == null) ? 0 : getSg().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_xs_recommend_content
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
        sb.append(", issue=").append(issue);
        sb.append(", play=").append(play);
        sb.append(", number=").append(number);
        sb.append(", recommendId=").append(recommendId);
        sb.append(", sg=").append(sg);
        sb.append(", createTime=").append(createTime);
        sb.append(", deleted=").append(deleted);
        sb.append("]");
        return sb.toString();
    }
}