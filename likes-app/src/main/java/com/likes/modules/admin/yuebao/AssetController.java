package com.likes.modules.admin.yuebao;

import com.likes.common.BaseController;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.req.AssetReq;
import com.likes.common.model.req.YuebaoOperateReq;
import com.likes.common.model.vo.AssetExpireVO;
import com.likes.common.model.vo.AssetProductVO;
import com.likes.common.service.yuebao.AssetService;
import com.likes.common.service.yuebao.YuebaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * usdt 余额宝
 *
 * @author puff
 */
@Api(tags = "理财产品接口")
@RestController
@Log4j2

@RequestMapping(value = "/asset")
public class AssetController extends BaseController {
    @Resource
    private AssetService assetService;

    @Resource
    private YuebaoService yuebaoService;

    @ApiOperation(value = "理财产品列表", response = AssetProductVO.class)
    @GetMapping(value = "list")
    public ResultInfo list(AssetReq req) {
        try {
            return ResultInfo.ok(assetService.list(req, getLoginUserAPP()));
        } catch (BusinessException e) {
            log.error("{}.v1/list,失败:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.getInstance(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("{}.v1/list,出错:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.error();
        }
    }


    @ApiOperation(value = "理财产品可提现金额")
    @GetMapping(value = "usableAmount")
    public ResultInfo usableAmount(@ModelAttribute YuebaoOperateReq req) {
        try {
            LoginUser loginUser = getLoginUserAPP();
            return ResultInfo.ok(yuebaoService.usableAmount(req,loginUser));
        } catch (BusinessException e) {
            log.error("{}.v1/usableAmount,失败:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.getInstance(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("{}.v1/usableAmount,出错:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.error();
        }
    }

    @ApiOperation(value = "理财产品是否到期", response = AssetExpireVO.class)
    @GetMapping(value = "expire")
    public ResultInfo expire(@ModelAttribute YuebaoOperateReq req) {
        try {
            LoginUser loginUser = getLoginUserAPP();
            return ResultInfo.ok(yuebaoService.expire(req,loginUser));
        } catch (BusinessException e) {
            log.error("{}.v1/usableAmount,失败:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.getInstance(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("{}.v1/usableAmount,出错:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.error();
        }
    }

}
