package com.likes.modules.admin.finance.service;

import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.StatisticRequest;
import com.likes.common.model.vo.member.UserReportVO;
import com.likes.common.model.vo.money.AccountSummaryVO;
import com.likes.common.model.vo.money.MemGoldchangeVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface StatisticService {

    /**
     * 后台统计-帐变列表
     */
    PageResult<List<MemGoldchangeVO>> pageGoldChange(StatisticRequest req, PageBounds page);

    /**
     * 后台统计-帐变列表-帐变总金额
     */
    BigDecimal tatolGoldChange(StatisticRequest req);

    /**
     * 后台统计-帐变列表-显示帐变类型
     */
    Map listChangeType();

    /**
     * 后台统计-出入账统计
     */
    AccountSummaryVO getAccountSummary(StatisticRequest req);

    /**
     * 会员报表
     */
    Page<UserReportVO> userReport(String nickname, RowBounds rowBounds);

    /**
     * 会员报表详情页
     */
    Map<String, Object> userReportDetail(StatisticRequest req, PageBounds page);
}
