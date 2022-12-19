package com.likes.modules.admin.pay;

import com.likes.common.BaseController;
import com.likes.common.annotation.AllowAccess;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.ResultInfo;
import com.likes.modules.admin.pay.service.MemWalletService;
import com.likes.modules.admin.users.service.MoneyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * usdt 充值
 *
 * @author puff
 */
@RestController
@RequestMapping(value = "/pay")
public class PayController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private MemWalletService memWalletService;

    @RequestMapping(name = "提交充值", value = "/submitRecharge", method = RequestMethod.POST)
    public ResultInfo submitRecharge(String coinName, BigDecimal amount, String moneyAddress) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response.setData(memWalletService.submitRecharge(coinName, amount, moneyAddress, loginUserAPP));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.moneyAddress 失败:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("获取钱包地址出错");
            logger.error("{}.moneyAddress 出错:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/moneyAddress耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }


    @RequestMapping(name = "获取充值地址", value = "/moneyAddress", method = RequestMethod.POST)
    public ResultInfo moneyAddress(String coinName) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response.setData(memWalletService.getMemMoneyAddress(coinName, loginUserAPP));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.moneyAddress 失败:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("获取钱包地址出错");
            logger.error("{}.moneyAddress 出错:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/moneyAddress耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }





    @RequestMapping(name = "获取币种类型", value = "/coinList", method = RequestMethod.POST)
    public ResultInfo coinList( ) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response.setData(memWalletService.coinList(loginUserAPP));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.moneyAddress 失败:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("获取钱包地址出错");
            logger.error("{}.moneyAddress 出错:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/moneyAddress耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }
}
