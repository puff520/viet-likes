package com.likes.common.model.vo;

import com.likes.common.model.common.PageBounds;
import com.github.pagehelper.Page;

import java.math.BigDecimal;

public class MemFamilyIncomeStatementVO {
    //投注分成
    private BigDecimal betsincomeAmount;
    //礼物分成
    private BigDecimal giftincomeAmount;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public PageBounds getPage() {
        return page;
    }

    public void setPage(PageBounds page) {
        this.page = page;
    }

    //主播礼物分成列表
    private Page<TraAnchorVO> list;
    //分页条数
    private int total;
    //分页
    private PageBounds page;

    public BigDecimal getBetsincomeAmount() {
        return betsincomeAmount;
    }

    public void setBetsincomeAmount(BigDecimal betsincomeAmount) {
        this.betsincomeAmount = betsincomeAmount;
    }

    public BigDecimal getGiftincomeAmount() {
        return giftincomeAmount;
    }

    public void setGiftincomeAmount(BigDecimal giftincomeAmount) {
        this.giftincomeAmount = giftincomeAmount;
    }

    public Page<TraAnchorVO> getList() {
        return list;
    }

    public void setList(Page<TraAnchorVO> list) {
        this.list = list;
    }
}
