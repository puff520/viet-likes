package com.likes.common.model.request.body;

import com.likes.common.model.common.PageBounds;
import com.likes.common.model.request.FamilyIncarnateRequest;
import org.apache.ibatis.session.RowBounds;

public class FamilyIncarnateRequestReq {
    private FamilyIncarnateRequest familyIncarnateRequest;
    private PageBounds page;

    public FamilyIncarnateRequest getFamilyIncarnateRequest() {
        return familyIncarnateRequest;
    }

    public void setFamilyIncarnateRequest(FamilyIncarnateRequest familyIncarnateRequest) {
        this.familyIncarnateRequest = familyIncarnateRequest;
    }

    public PageBounds getPage() {
        return page;
    }

    public void setPage(PageBounds page) {
        this.page = page;
    }
}
