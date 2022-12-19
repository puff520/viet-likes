package com.likes.common.service.money;

import com.likes.common.model.dto.TraApplycashDO;
import com.likes.common.model.request.FamilyIncarnateRequest;
import com.likes.common.model.response.IncarnateRecordResponse;
import com.likes.common.mybatis.entity.TraApplycash;
import com.likes.common.mybatis.entity.TraApplycashExample;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface TraApplycashService {

    int countByExample(TraApplycashExample example);

    int deleteByExample(TraApplycashExample example);

    int deleteByPrimaryKey(Long apycid);

    int insert(TraApplycash record);

    int insertSelective(TraApplycash record);

    TraApplycash selectOneByExample(TraApplycashExample example);

    List<TraApplycash> selectByExample(TraApplycashExample example);

    TraApplycash selectByPrimaryKey(Long apycid);

    int updateByExampleSelective(TraApplycash record, TraApplycashExample example);

    int updateByExample(TraApplycash record, TraApplycashExample example);

    int updateByPrimaryKeySelective(TraApplycash record);

    int updateByPrimaryKey(TraApplycash record);

    TraApplycash findByOrderid(Long orderid);

    int doUpdateIncarnateHandleOrder(TraApplycash traApplycash);

    int doUpdateIncarnateCancelSureOrder(TraApplycash traApplycash);

    int updateIncarnateConfirmApplycash(TraApplycash traApplycash);
    int udunUpdateIncarnateConfirmApplycash(TraApplycash traApplycash);

    Page<TraApplycashDO> getTraApplycashList(FamilyIncarnateRequest req, RowBounds rowBounds);

    TraApplycash getLastTraApplycash(String accno);

    int doInsertIncarnate(TraApplycash traApplycash);

    Page<IncarnateRecordResponse> getIncarnateRecordList(String accno, RowBounds rowBounds);


    Double getIncarnateRecordListTotal(String accno);

    TraApplycash findCashByCash(TraApplycash param);

    int updateTraApplycash(TraApplycash traApplycash);

    int doInsert(TraApplycash traApplycash);

    TraApplycash findNotInCashByCash(Map<String, Object> timeMap);

    TraApplycash findNotInCashByCashByAccno(String accno);

    Double getXianshiIncarnateRecordListTotal(String accno);

    BigDecimal getOrderBetRecord(Long familyid);

    BigDecimal getOrderBetRecords(String startDate, String endDate, Long roomId);

    Page<TraApplycashDO> getTraApplycashMemFamilyList(String accno, RowBounds toRowBounds);
}
