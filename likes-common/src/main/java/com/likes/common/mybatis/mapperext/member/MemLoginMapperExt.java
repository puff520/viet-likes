package com.likes.common.mybatis.mapperext.member;

import com.likes.common.model.LoginUser;
import com.likes.common.model.dto.NameValueDO;
import com.likes.common.model.dto.OperatorDO;
import com.likes.common.model.dto.member.MemLoginDO;
import com.likes.common.model.dto.member.UsersBdDO;
import com.likes.common.model.dto.member.UsersDO;
import com.likes.common.model.request.TrialAccountRequest;
import com.likes.common.model.request.UserRequest;
import com.likes.common.model.vo.finance.FinanceMemBaseVo;
import com.likes.common.model.vo.member.UserReportVO;
import com.likes.common.model.vo.member.UserVO;
import com.likes.common.mybatis.entity.MemLogin;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemLoginMapperExt {

    void updateLogin(MemLogin memLoginParam);

    MemLogin findByMobile(String tel);

    MemLogin findByEmail(String email);

    int insertSelectiveMemLogin(MemLogin memLogin);

    MemLogin findByEmailRegister(String tel);

    MemLogin getMemLoginByParam(MemLogin loginParam);

    MemLogin selectByAcclogin(@Param("acclogin") String acclogin);

    LoginUser selectEurekaByAcclogin(@Param("acclogin") String acclogin, @Param("password") String password);

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

    Page<FinanceMemBaseVo> selectMemForFInance(@Param("email") String email, @Param("nickname") String nickname, @Param("uniqueId") String uniqueId, RowBounds rowBounds);

    Page<UserReportVO> userReportList(@Param("nickname") String nickname, RowBounds rowBounds);

    LoginUser selectUserByAccLogin(String acclogin);

    Integer getRegisteredNumByDate(@Param("startTime") String startTime, @Param("endTime") String endTime);

    Page<UserVO> trialList(TrialAccountRequest req, RowBounds toRowBounds);
}
