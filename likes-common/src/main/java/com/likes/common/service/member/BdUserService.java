package com.likes.common.service.member;

import com.likes.common.mybatis.entity.BdUser;
import com.likes.common.mybatis.entity.MemLogin;
import com.likes.common.service.BaseService;

import java.util.List;

public interface BdUserService extends BaseService {

    BdUser selectByAccno(String accno);

    BdUser getRandWeiChat();

    List<String> getKeFuWeiChat(String sysrolename);

    BdUser getRepeatPhoneno(BdUser bdUserParam);

    int insertSelective(BdUser record);

    int updateByPrimaryKeySelective(BdUser record);

    /**
     * 根据手机号换取系统用户登录信息登录
     * @param acclogin
     * @return
     */
    BdUser getAcclogin(String acclogin);

    BdUser selectByAcclogin(String acclogin);
}
