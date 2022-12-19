package com.likes.common.model.request;

/**
 * 订单列表请求
 * @author 瑞夫
 * @version 1.0
 * @Description
 * @date 2020/5/7
 **/
public class RepayorderListReq {

    /**
     * 代充人会员标识
     */
    private String accno;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
