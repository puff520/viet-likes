package com.likes.modules.admin.ad.service;

import com.likes.common.model.dto.BdBannerpicinfoDO;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.mybatis.entity.BasInvestors;
import com.likes.common.mybatis.entity.BdBannerpicinfo;
import com.likes.common.mybatis.entity.BdBannerseat;

public interface BannerService {

    PageResult bannerseatList(BdBannerseat req, PageBounds page);

    BdBannerseat getBannerseatDetail(BdBannerseat req);

    Long saveBannerseat(BdBannerseat req, LoginUser loginUser);

    Long updateBannerseat(BdBannerseat req, LoginUser loginUser);

    String delBannerseat(BdBannerseat req, LoginUser loginUser);

    PageResult adList(BdBannerpicinfo req, PageBounds page);

    Long saveAd(BdBannerpicinfo req, LoginUser loginUser);

    Long updateAd(BdBannerpicinfo req, LoginUser loginUser);


    String delAd(BdBannerpicinfo req, LoginUser loginUser);


}
