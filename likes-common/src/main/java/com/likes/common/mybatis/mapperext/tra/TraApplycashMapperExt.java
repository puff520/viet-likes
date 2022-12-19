package com.likes.common.mybatis.mapperext.tra;

import com.likes.common.model.dto.TraApplycashDO;
import com.likes.common.model.request.FamilyIncarnateRequest;
import com.likes.common.model.response.IncarnateRecordResponse;
import com.likes.common.mybatis.entity.TraApplycash;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.math.BigDecimal;
import java.util.Map;

public interface TraApplycashMapperExt {

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

    @Select(" select ifnull(sum(o.bet_amount),0.00) from order_bet_record o where  o.is_delete = 0 and TO_DAYS( NOW( ) ) - TO_DAYS(o.create_time) = 1 and o.familyid = #{familyid} ")
    BigDecimal getOrderBetRecord(@Param("familyid") Long familyid);

    @Select(" select ifnull(sum(o.bet_amount),0.000) from order_bet_record o where  o.is_delete = 0 and o.room_id = #{roomId} and o.create_time >= #{startDate} and o.create_time <= #{endDate}")
    BigDecimal getOrderBetRecords(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("roomId") Long roomId);

    Page<TraApplycashDO> getTraApplycashMemFamilyList(@Param("accno") String accno, RowBounds toRowBounds);

    Page<TraApplycash> findByBankaccid(@Param("bankaccid") Long bankaccid, RowBounds rowBounds);


}
