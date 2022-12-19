package com.likes.common.model.request.body;

import com.likes.common.model.common.PageBounds;
import com.likes.common.model.vo.MemFamilyIncomeStatementVO;

import java.util.List;

public class GetTraAnchorDoListBodyReq {

    private List<Long> objects;

    private MemFamilyIncomeStatementVO memFamilyIncomeStatementVO;

    private PageBounds pageBounds;

    public GetTraAnchorDoListBodyReq() {
    }

    public List<Long> getObjects() {
        return objects;
    }

    public void setObjects(List<Long> objects) {
        this.objects = objects;
    }

    public MemFamilyIncomeStatementVO getMemFamilyIncomeStatementVO() {
        return memFamilyIncomeStatementVO;
    }

    public void setMemFamilyIncomeStatementVO(MemFamilyIncomeStatementVO memFamilyIncomeStatementVO) {
        this.memFamilyIncomeStatementVO = memFamilyIncomeStatementVO;
    }

    public PageBounds getPageBounds() {
        return pageBounds;
    }

    public void setPageBounds(PageBounds pageBounds) {
        this.pageBounds = pageBounds;
    }
}
