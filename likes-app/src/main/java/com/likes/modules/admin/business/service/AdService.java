package com.likes.modules.admin.business.service;

import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.AdRequest;

public interface AdService {

    PageResult adList(AdRequest req, PageBounds page);

}
