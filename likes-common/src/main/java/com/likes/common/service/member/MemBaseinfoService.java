package com.likes.common.service.member;

import com.likes.common.model.GreatPersonList;
import com.likes.common.model.dto.bas.BasMemInfoStatusReq;
import com.likes.common.model.dto.bas.BasMemInfoStatusResp;
import com.likes.common.model.dto.member.MemBaseinfoDO;
import com.likes.common.model.dto.member.UserDO;
import com.likes.common.model.dto.member.UserGoldDO;
import com.likes.common.model.request.UserRequest;
import com.likes.common.model.request.UsersRequest;
import com.likes.common.model.vo.ProfitAndLossCountVO;
import com.likes.common.mybatis.entity.MemBaseinfo;
import com.likes.common.mybatis.entity.MemBaseinfoExample;
import com.likes.common.mybatis.entity.TraAgentclearing;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface MemBaseinfoService {

    boolean updateMemNameById(String memName, Long memId);

    void updateMemBalance(BigDecimal amount, BigDecimal namount, BigDecimal payamount, String accno);

    BigDecimal getOrderBetRecordAmount(List<Integer> list);

    BigDecimal getOrderRobotRecordAmount(List<Integer> list);


    Integer countAvailableAppMember();

    MemBaseinfo getUserByInvitecode(String recomcode);

    MemBaseinfo getUserByAccno(String accno);

    MemBaseinfo getUserByUniqueId(String uniqueId);

    MemBaseinfo getUserByAccnoNoCache(String accno);

    MemBaseinfo getMemById(Long memberId);

    BigDecimal getBalanceById(Integer memberId);

    MemBaseinfo getUserByInvitecodeAll(String recomcode);

    void updateFansNum(UsersRequest ur);

//    Integer getAttentionnum(String accno);

//    Integer getCollectionnum(String accno);

    Integer getResourcesnum(String accno);

    void updateAddGold(UsersRequest usersRequest);

    UserGoldDO getUserRecomcodeGold(String accno);

    void updateSubtractGold(UsersRequest usersRequest);

    void updateAddress(Long uid, MemBaseinfo memBaseinfo);

    MemBaseinfo getRepeateNickname(String nickname);

    void updateAddGold(UserRequest r);

    List<UserDO> findUsers(UserDO userDO);

    List<UserDO> findAllUsers();

    Page<MemBaseinfoDO> applyAnchorList(UserRequest req, RowBounds rowBounds);

    int updatePayAgent(TraAgentclearing req);

    MemBaseinfo selectByAccno(String accno);

    List<String> getNickNameList(String[] arrstr);

    Page<GreatPersonList> getGpList(RowBounds toRowBounds);

    MemBaseinfo selectById(Long memId);

    int updateMemberAmount(BigDecimal amount, BigDecimal pamount, BigDecimal bamount, BigDecimal namount, BigDecimal wamount, BigDecimal waitamount, String accno, Long userId);

    int updateMemberAmount(BigDecimal amount, BigDecimal pamount, BigDecimal bamount, BigDecimal namount, BigDecimal consumeAcmount, BigDecimal wamount, BigDecimal waitamount, String accno, Long userId);

    BigDecimal countAllBalanceAmount(String startTime, String endTime);

    String selectAccountbyId(Long userId);

    int updateMemberForbit(MemBaseinfo memBaseinfo);

    BigDecimal countFirstDepositAmount(ProfitAndLossCountVO vo);

    int updateWithdrawalAmount(MemBaseinfo memBaseinfo);

    //mapper
    MemBaseinfo selectOneByExample(Long uid, MemBaseinfoExample example);

    MemBaseinfo selectOneByExample(MemBaseinfoExample example);

    MemBaseinfo selectByPrimaryKey(Long memid);

    MemBaseinfo selectByPrimaryKeyNoCache(Long memid);

    int updateByPrimaryKeySelective(MemBaseinfo record);

    List<MemBaseinfo> selectByExample(MemBaseinfoExample example);

    int insertSelective(MemBaseinfo record);

    void delUserHeadimgByPrimaryKey(Long memid);

    /**
     * 更新用户注册来源
     */
    int updateMemorigin(String accno, String origin);

    int updateSuperiorId(String accno, String uniqueId);

    Page<BasMemInfoStatusResp> getMemInfoStatusList(BasMemInfoStatusReq basMemInfoStatusReq, RowBounds rowBounds);

    /**
     * 历史数据处理：mem.uniqueId
     */
    void handMemUniqueId();

    /**
     * 根据账号列表 获取昵称、性别、头像
     */
    List<Map<String, Object>> getNicknameSexHeadimg(List<String> accnoList);

    void updateLastLoginDev(String accno, String source);

    void updateRegisterIp(String accno, String registerIp);

    /**
     * 历史数据处理：
     * 提现总次数,首次提现金额,最大提现金额,充值总次数,首次充值金额,最大充值金额
     */
    void handMemWithdrawalAndPayInfo();
}
