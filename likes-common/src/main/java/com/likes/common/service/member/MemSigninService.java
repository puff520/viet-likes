package com.likes.common.service.member;

import com.likes.common.model.dto.member.MemSigninDO;
import com.likes.common.model.dto.order.SignDaoDO;
import com.likes.common.mybatis.entity.MemSignin;

public interface MemSigninService {

    MemSignin getTodayData(SignDaoDO param);

    int insertMemSignin(MemSigninDO memSignin);
}

