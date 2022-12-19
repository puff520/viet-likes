package com.likes.common.model.dto.money;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.enums.withdrawal.WithdrawHandleStatusEnum;
import com.likes.common.model.common.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WithdrawPickDTO extends MoneyBaseDTO {

    private static final Logger logger = LoggerFactory.getLogger(WithdrawPickDTO.class);

    private Integer memberId;
    //页面参数
    private String rechargeAccount;
    //    private Integer customDataSize;
    private String checkUser;
    //提现相关页面 status 可以有多个值
    private String status;

    private String bank;
    private String source;
    private String opId;
    private String opName;
    private Integer opType;
    private String opTime;
    //是否显示已捡取订单
    private Boolean showPickuped;
    /** 标识业务场景，RETRIEVE：捡取；RETRIEVE_ME：我的；COMPLETE：完成 */
    private String businessType;

    //提现处理状态，单个状态
    private Integer hdstatus;

    /**
     * 根据searchType设置搜索的值
     */
    public void fillSearchValueByType() {
        if (StringUtils.isNotBlank(this.searchType)) {
            if ("account".equals(this.searchType )) {
                this.setAccount(this.searchValue);

            }
            if ("orderNo".equals(this.searchType)) {
                this.setOrderNo(this.searchValue);
            }
            if ("memberId".equals(this.searchType )) {
                try {
                    Integer memberId = Integer.parseInt(this.searchValue);
                    this.setMemberId(memberId);
                } catch (NumberFormatException e) {
                }
            }
        }
    }

    public Map<String, Object> toMap(PageResult pageResult) {
        return toMap(pageResult, false);
    }

    public Map<String, Object> toMap(PageResult pageResult, boolean isStatOperate) {
        Map<String, Object> map = new HashMap<>();
        try {
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(this));
            Set<Map.Entry<String, Object>> entries = jsonObject.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                map.put(entry.getKey(), entry.getValue());
            }
            //兼容老代码逻辑
            if (StringUtils.isNotBlank(status)) {
                if (status.contains("41")) {
                    this.opType = 1;
                    status = WithdrawHandleStatusEnum.SUCCESS.getValue() + "";
                } else if (status.contains("42")) {
                    this.opType = 2;
                    status = WithdrawHandleStatusEnum.SUCCESS.getValue() + "";
                }
                String[] statusArr = status.split(",");
                map.put("opType", this.opType);
                map.put("status", statusArr);
            }
            PageResult.fillPageFields(pageResult, map);
            //查询我负责的记录和完成记录时，时间过滤按 op_time 过滤
            if (!isStatOperate && (WITHDRAW_BUS_RETRIEVE_ME.equals(this.businessType) || WITHDRAW_BUS_COMPLETE.equals(this.businessType))) {
                map.put("startOpTime", map.remove("startTime"));
                map.put("endOpTime", map.remove("endTime"));
            }
            //当页面单独选某一状态时清除其业务类型状态
            if (null != hdstatus && hdstatus > 0) {
                map.remove("status");
            }
        } catch (Exception e) {
            logger.error("toMap occur error.", e);
        }
        logger.info("toMap result:{}", JSONObject.toJSONString(map));
        return map;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getRechargeAccount() {
        return rechargeAccount;
    }

    public void setRechargeAccount(String rechargeAccount) {
        this.rechargeAccount = rechargeAccount;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getOpId() {
        return opId;
    }

    public void setOpId(String opId) {
        this.opId = opId;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName;
    }

    public Integer getOpType() {
        return opType;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    public String getOpTime() {
        return opTime;
    }

    public void setOpTime(String opTime) {
        this.opTime = opTime;
    }

    public Boolean getShowPickuped() {
        return null != showPickuped && showPickuped;
    }

    public void setShowPickuped(Boolean showPickuped) {
        this.showPickuped = showPickuped;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    //    public Integer getCustomDataSize() {
//        return null != customDataSize && customDataSize > 500 ? 500 : customDataSize;
//    }
//
//    public void setCustomDataSize(Integer customDataSize) {
//        this.customDataSize = customDataSize;
//    }

    public Integer getHdstatus() {
        return hdstatus;
    }

    public void setHdstatus(Integer hdstatus) {
        this.hdstatus = hdstatus;
    }
}
