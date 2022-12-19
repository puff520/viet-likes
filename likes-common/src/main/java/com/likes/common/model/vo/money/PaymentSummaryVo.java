package com.likes.common.model.vo.money;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PaymentSummaryVo {

    private Integer id;

    private Long createTime;
    private BigDecimal amount;
    private Integer status;
    private String remark;
    private Integer userId;
    private String userIds;
    private String selectIds;
    private String checkUser;

    public boolean hasExistId() {
        return null != this.selectIds && !"".equals(this.selectIds.trim());
    }

    public boolean check() {
        List<Integer> idList = obtainPaymentSummaryIdList();
        if (null == idList || idList.size() == 0) {
            return false;
        }
        return null != status && status > 0;
    }

    public List<Integer> obtainPaymentSummaryIdList() {
        if (!hasExistId()) {
            return null;
        }
        String[] idArr = this.selectIds.split(",");
        List<Integer> list = new ArrayList<>();
        for (String id : idArr) {
            try {
                list.add(Integer.valueOf(id));
            } catch (Exception e) {
            }
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getSelectIds() {
        return selectIds;
    }

    public void setSelectIds(String selectIds) {
        this.selectIds = selectIds;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }
}
