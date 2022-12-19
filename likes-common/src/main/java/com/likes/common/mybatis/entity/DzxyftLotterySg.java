package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class DzxyftLotterySg implements Serializable {
    /**
     * 字段: dzxyft_lottery_sg.id<br/>
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
     * 字段: dzxyft_lottery_sg.issue<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 期号
     *
     * @mbggenerated
     */
    private String issue;

    /**
     * 字段: dzxyft_lottery_sg.number<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 开奖号码
     *
     * @mbggenerated
     */
    private String number;

    /**
     * 字段: dzxyft_lottery_sg.time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 实际开奖时间
     *
     * @mbggenerated
     */
    private Date time;

    /**
     * 字段: dzxyft_lottery_sg.ideal_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 官方开奖时间
     *
     * @mbggenerated
     */
    private Date idealTime;

    /**
     * 字段: dzxyft_lottery_sg.open_status<br/>
     * 必填: true<br/>
     * 缺省: WAIT<br/>
     * 长度: 50<br/>
     * 说明: 状态：WAIT 等待开奖 | AUTO 自动开奖 | HANDLE 手动开奖
     *
     * @mbggenerated
     */
    private String openStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table dzxyft_lottery_sg
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return dzxyft_lottery_sg.id: 
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 字段: dzxyft_lottery_sg.id<br/>
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
     * @return dzxyft_lottery_sg.issue: 期号
     *
     * @mbggenerated
     */
    public String getIssue() {
        return issue;
    }

    /**
     * 字段: dzxyft_lottery_sg.issue<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 期号
     *
     * @mbggenerated
     */
    public void setIssue(String issue) {
        this.issue = issue;
    }

    /**
     * @return dzxyft_lottery_sg.number: 开奖号码
     *
     * @mbggenerated
     */
    public String getNumber() {
        return number;
    }

    /**
     * 字段: dzxyft_lottery_sg.number<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 开奖号码
     *
     * @mbggenerated
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return dzxyft_lottery_sg.time: 实际开奖时间
     *
     * @mbggenerated
     */
    public Date getTime() {
        return time;
    }

    /**
     * 字段: dzxyft_lottery_sg.time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 实际开奖时间
     *
     * @mbggenerated
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @return dzxyft_lottery_sg.ideal_time: 官方开奖时间
     *
     * @mbggenerated
     */
    public Date getIdealTime() {
        return idealTime;
    }

    /**
     * 字段: dzxyft_lottery_sg.ideal_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 官方开奖时间
     *
     * @mbggenerated
     */
    public void setIdealTime(Date idealTime) {
        this.idealTime = idealTime;
    }

    /**
     * @return dzxyft_lottery_sg.open_status: 状态：WAIT 等待开奖 | AUTO 自动开奖 | HANDLE 手动开奖
     *
     * @mbggenerated
     */
    public String getOpenStatus() {
        return openStatus;
    }

    /**
     * 字段: dzxyft_lottery_sg.open_status<br/>
     * 必填: true<br/>
     * 缺省: WAIT<br/>
     * 长度: 50<br/>
     * 说明: 状态：WAIT 等待开奖 | AUTO 自动开奖 | HANDLE 手动开奖
     *
     * @mbggenerated
     */
    public void setOpenStatus(String openStatus) {
        this.openStatus = openStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dzxyft_lottery_sg
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
        DzxyftLotterySg other = (DzxyftLotterySg) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIssue() == null ? other.getIssue() == null : this.getIssue().equals(other.getIssue()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getIdealTime() == null ? other.getIdealTime() == null : this.getIdealTime().equals(other.getIdealTime()))
            && (this.getOpenStatus() == null ? other.getOpenStatus() == null : this.getOpenStatus().equals(other.getOpenStatus()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dzxyft_lottery_sg
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIssue() == null) ? 0 : getIssue().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getIdealTime() == null) ? 0 : getIdealTime().hashCode());
        result = prime * result + ((getOpenStatus() == null) ? 0 : getOpenStatus().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dzxyft_lottery_sg
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
        sb.append(", number=").append(number);
        sb.append(", time=").append(time);
        sb.append(", idealTime=").append(idealTime);
        sb.append(", openStatus=").append(openStatus);
        sb.append("]");
        return sb.toString();
    }
}