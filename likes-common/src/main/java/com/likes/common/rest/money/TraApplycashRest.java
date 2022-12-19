package com.likes.common.rest.money;


import com.github.pagehelper.Page;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.dto.TraApplycashDO;
import com.likes.common.model.request.FamilyIncarnateRequest;
import com.likes.common.mybatis.entity.TraApplycash;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * ClassName: TraApplycashRest
 * Description: 描述
 *
 * @author ping
 * @since JDK 1.8
 * date: 2020/6/19 21:26
 */
public interface TraApplycashRest {

    ResultInfo getTraApplycashList(@RequestBody FamilyIncarnateRequest req, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize);

    TraApplycash selectByPrimaryKey(@RequestParam(name = "apycid") Long apycid);

    BigDecimal getOrderBetRecords(@RequestParam(name = "startDate") String startDate, @RequestParam(name = "endDate") String endDate, @RequestParam(name = "roomId") Long roomId);

    int updateByPrimaryKeySelective(@RequestBody TraApplycash record);

    int insertSelective(@RequestBody TraApplycash record);

    Page<TraApplycashDO> getTraApplycashMemFamilyList(@RequestParam(name = "accno") String accno, @RequestBody RowBounds toRowBounds);

    int updateIncarnateConfirmApplycash(@RequestBody TraApplycash traApplycash);
}
