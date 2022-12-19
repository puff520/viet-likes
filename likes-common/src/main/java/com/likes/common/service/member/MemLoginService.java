package com.likes.common.service.member;

import com.likes.common.model.LoginUser;
import com.likes.common.model.dto.NameValueDO;
import com.likes.common.model.dto.OperatorDO;
import com.likes.common.model.dto.member.MemLoginDO;
import com.likes.common.model.dto.member.UsersBdDO;
import com.likes.common.model.dto.member.UsersDO;
import com.likes.common.model.request.TrialAccountRequest;
import com.likes.common.model.request.UserRequest;
import com.likes.common.model.vo.finance.FinanceMemBaseVo;
import com.likes.common.model.vo.member.UserVO;
import com.likes.common.mybatis.entity.MemLogin;
import com.likes.common.service.BaseService;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface MemLoginService extends BaseService {

    /**
     * 修改登录表
     */
    void updateLogin(MemLogin memLoginParam);

    /**
     * 根据tel查找登录信息
     */
    MemLogin findByMobile(String tel);


    MemLogin findByEmail(String email);

    /**
     * 插入
     */
    int insertSelectiveMemLogin(MemLogin memLogin);

    /**
     * 根据tel查找登录信息
     */
    MemLogin findByEmailRegister(String email);

    /**
     * 查找登录信息
     */
    MemLogin getMemLoginByParam(MemLogin loginParam);

    /**
     * 根据账号查找登录信息
     */
    MemLogin selectByAcclogin(String acclogin);

    /**
     * 查找eureka登录信息
     */
    LoginUser selectEurekaByAcclogin(String acclogin, String password);

    Page<UserVO> userList(UserRequest req, RowBounds rowBounds);

    MemLogin selectByAccno(String accno);

    Page<UsersBdDO> userBdList(UserRequest req, RowBounds rowBounds);

    UsersDO getAPPUserDetail(String accno);

    UsersBdDO getBdUserDetail(String accno);

//    Page<UsersDO> userAnchorList(UserRequest req, RowBounds rowBounds);

    UsersDO getAPPAnchorUserDetail(String accno);

    MemLogin getRepeat(MemLoginDO memLogin);

    int insertMemLogin(MemLoginDO memLogin);

    List<NameValueDO> getAddressStatics();

    List<NameValueDO> getAddressStaticsTen();

    Integer getAllRegisteredNum();

    Integer getThisWeekRegisteredNum(Map<String, Object> param);

    Integer getAllAnchorNum();

    MemLogin findByAccno(String accno);

    MemLogin findByAccloginAndLoginidRegister(@Param("acclogin") String acclogin, @Param("loginid") Long loginid);

    MemLogin findByAccloginRegister(String acclogin);

    List<OperatorDO> getOperatorList();

    Integer getLevelNum(Map<String, Object> param);

    List<Map<Integer, String>> getNewOrder();

    Integer getThisWeekAnchorNum(Map<String, Object> param);

    int saveMemLogin(MemLoginDO memLogin);

    Page<FinanceMemBaseVo> selectMemForFInance(String email , String nickname, String uniqueId, RowBounds rowBounds);

    Page<UserVO> trialList(TrialAccountRequest req, RowBounds toRowBounds);

    int updateByPrimaryKeySelective(MemLogin memLoginParam);

}
