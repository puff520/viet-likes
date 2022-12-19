package com.likes.common.model.vo.member;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 后台-用户报表列表页
 */
public class UserReportDetailVO {

    /**
     * 说明: 金币变动明细id
     */
    private Long goldchangid;

    /**
     * 说明: 帐变类型名称说明
     */
    private String changeName;

    /**
     * 说明: 变动金额
     */
    private BigDecimal quantity;

    /**
     * 说明: 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public Long getGoldchangid() {
        return goldchangid;
    }

    public void setGoldchangid(Long goldchangid) {
        this.goldchangid = goldchangid;
    }

    public String getChangeName() {
        return changeName;
    }

    public void setChangeName(String changeName) {
        this.changeName = changeName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
