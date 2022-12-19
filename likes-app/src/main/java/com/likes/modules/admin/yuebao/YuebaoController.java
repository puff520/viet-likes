package com.likes.modules.admin.yuebao;

import com.likes.common.BaseController;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.req.YuebaoOperateReq;
import com.likes.common.model.req.YuebaoRecordReq;
import com.likes.common.model.vo.YuebaoBaseInfoVO;
import com.likes.common.model.vo.YuebaoRecordVO;
import com.likes.common.service.yuebao.YuebaoService;
import com.likes.common.util.redis.RedisLock;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * usdt 余额宝
 *
 * @author puff
 */
@Api(tags = "余额宝接口")
@RestController
@Log4j2
@RequestMapping(value = "/yuebao")
public class YuebaoController extends BaseController {

    @Resource
    private YuebaoService yuebaoService;
    @Resource
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "余额宝账户总览", httpMethod = "GET", response = YuebaoBaseInfoVO.class)
    @GetMapping(value = "baseInfo")
    public ResultInfo baseInfo() {
        try {
            return ResultInfo.ok(yuebaoService.queryBaseInfo(getLoginUserAPP()));
        } catch (BusinessException e) {
            log.error("{}.v1/baseInfo,失败:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.getInstance(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("{}.v1/baseInfo,出错:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.error();
        }
    }

    @ApiOperation(value = "余额转入余额宝", response = YuebaoRecordVO.class)
    @PostMapping(value = "intoYuebao")
    public ResultInfo intoYuebao(@ModelAttribute YuebaoOperateReq req) {
        try {
            LoginUser loginUser = getLoginUserAPP();
            // 控制频率
            String keySuffix = RedisLock.FINANCE_APP_YUEBAO_APPLY + loginUser.getMemid();
            if (redisTemplate.hasKey(keySuffix)) {
                new BusinessException(StatusCode.LIVE_ERROR_501.getCode(), "提现操作频繁，请稍后再试！");
            }
            boolean haveAuth = redisTemplate.opsForValue().setIfAbsent(keySuffix, "1", 2, TimeUnit.SECONDS);
            if (!haveAuth) {
                new BusinessException(StatusCode.LIVE_ERROR_501.getCode(), "提现操作频繁，请稍后再试！");
            }
            return ResultInfo.ok(yuebaoService.intoYuebao(req, loginUser));
        } catch (BusinessException e) {
            log.error("{}.v1/deposit,失败:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.getInstance(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("{}.v1/deposit,出错:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.error();
        }
    }

    @ApiOperation(value = "余额转出到余额", response = YuebaoRecordVO.class)
    @PostMapping(value = "outYuebao")
    public ResultInfo outYuebao(@ModelAttribute YuebaoOperateReq req) {
        try {
            LoginUser loginUser = getLoginUserAPP();
            // 控制频率
            String keySuffix = RedisLock.FINANCE_APP_YUEBAO_APPLY + loginUser.getMemid();
            if (redisTemplate.hasKey(keySuffix)) {
                new BusinessException(StatusCode.LIVE_ERROR_501.getCode(), "提现操作频繁，请稍后再试！");
            }
            boolean haveAuth = redisTemplate.opsForValue().setIfAbsent(keySuffix, "1", 2, TimeUnit.SECONDS);
            if (!haveAuth) {
                new BusinessException(StatusCode.LIVE_ERROR_501.getCode(), "提现操作频繁，请稍后再试！");
            }
            return ResultInfo.ok(yuebaoService.outYuebao(req, loginUser));
        } catch (BusinessException e) {
            log.error("{}.v1/deposit,失败:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.getInstance(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("{}.v1/deposit,出错:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.error();
        }
    }


    @ApiOperation(value = "理财产品提取到余额宝", response = YuebaoRecordVO.class)
    @PostMapping(value = "take")
    public ResultInfo take(@ModelAttribute YuebaoOperateReq req) {
        try {
            LoginUser loginUser = getLoginUserAPP();
            // 控制频率
            String keySuffix = RedisLock.FINANCE_APP_ASSET_APPLY + loginUser.getMemid();
            if (redisTemplate.hasKey(keySuffix)) {
                new BusinessException(StatusCode.LIVE_ERROR_501.getCode(), "提现操作频繁，请稍后再试！");
            }
            boolean haveAuth = redisTemplate.opsForValue().setIfAbsent(keySuffix, "1", 2, TimeUnit.SECONDS);
            if (!haveAuth) {
                new BusinessException(StatusCode.LIVE_ERROR_501.getCode(), "提现操作频繁，请稍后再试！");
            }
            return ResultInfo.ok(yuebaoService.take(req, loginUser));
        } catch (BusinessException e) {
            log.error("{}.v1/deposit,失败:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.getInstance(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("{}.v1/deposit,出错:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.error();
        }
    }


    @ApiOperation(value = "余额宝购买理财产品", response = YuebaoRecordVO.class)
    @PostMapping(value = "deposit")
    public ResultInfo deposit(@ModelAttribute YuebaoOperateReq req) {
        try {
            LoginUser loginUser = getLoginUserAPP();
            // 控制频率
            String keySuffix = RedisLock.FINANCE_APP_ASSET_APPLY + loginUser.getMemid();
            if (redisTemplate.hasKey(keySuffix)) {
                new BusinessException(StatusCode.LIVE_ERROR_501.getCode(), "提现操作频繁，请稍后再试！");
            }
            boolean haveAuth = redisTemplate.opsForValue().setIfAbsent(keySuffix, "1", 2, TimeUnit.SECONDS);
            if (!haveAuth) {
                new BusinessException(StatusCode.LIVE_ERROR_501.getCode(), "提现操作频繁，请稍后再试！");
            }
            return ResultInfo.ok(yuebaoService.deposit(req, loginUser));
        } catch (BusinessException e) {
            log.error("{}.v1/deposit,失败:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.getInstance(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("{}.v1/deposit,出错:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.error();
        }
    }


    @ApiOperation(value = "余额宝转入明细", response = YuebaoRecordVO.class)
    @GetMapping(value = "intoList")
    public ResultInfo intoList(@ModelAttribute YuebaoRecordReq req) {
        try {
            req.setChangeType(1);
            LoginUser loginUser = getLoginUserAPP();
            return ResultInfo.ok(yuebaoService.operateList(req, loginUser));
        } catch (BusinessException e) {
            log.error("{}.v1/record,失败:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.getInstance(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("{}.v1/record,出错:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.error();
        }
    }

    @ApiOperation(value = "余额宝转出明细", response = YuebaoRecordVO.class)
    @GetMapping(value = "outList")
    public ResultInfo outList(@ModelAttribute YuebaoRecordReq req) {
        try {
            req.setChangeType(2);
            LoginUser loginUser = getLoginUserAPP();
            return ResultInfo.ok(yuebaoService.operateList(req, loginUser));
        } catch (BusinessException e) {
            log.error("{}.v1/record,失败:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.getInstance(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("{}.v1/record,出错:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.error();
        }
    }

    @ApiOperation(value = "余额宝收益明细", response = YuebaoRecordVO.class)
    @GetMapping(value = "earnList")
    public ResultInfo earnList(@ModelAttribute YuebaoRecordReq req) {
        try {
            LoginUser loginUser = getLoginUserAPP();
            return ResultInfo.ok(yuebaoService.earnList(req, loginUser));
        } catch (BusinessException e) {
            log.error("{}.v1/record,失败:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.getInstance(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("{}.v1/record,出错:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.error();
        }
    }


}
