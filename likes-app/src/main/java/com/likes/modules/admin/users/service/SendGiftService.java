package com.likes.modules.admin.users.service;

import com.likes.common.model.LoginUser;
import com.likes.common.model.request.GiftReq;
import com.likes.common.model.vo.gift.SendGiftVO;

public interface SendGiftService {

    SendGiftVO sendGift(GiftReq req, LoginUser loginUserAPP, String source);

    void doGiftNotice(SendGiftVO sendGiftVO);

    SendGiftVO sendTrailGift(GiftReq req, LoginUser loginUserAPP, String source);
}
