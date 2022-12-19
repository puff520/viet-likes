package com.likes.modules.admin.member.controller;

import com.likes.common.BaseController;
import com.likes.common.annotation.AllowAccess;
import com.likes.common.annotation.Syslog;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.mybatis.entity.MemLevelConfig;
import com.likes.common.service.member.MemLevelConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 会员等级配置
 *
 * @author bjkj
 */
@Controller
@RequestMapping(value = "/memLevelConfig")
public class MemLevelConfigController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private MemLevelConfigService memLevelConfigService;

    /**
     * 分页查询会员等级配置
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(name = "分页查询会员等级配置 ", value = "/list", method = RequestMethod.POST)
    public ResultInfo list(
            @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo<>();
        try {
            response = ResultInfo.ok(memLevelConfigService.memLevelConfigListForManager(pageNo, pageSize));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.list分页查询会员等级配置失败,失败信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("分页查询会员等级配置出错");
            logger.error("{}.list分页查询会员等级配置出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/list耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "查询会员等级配置 ", value = "/levelConfig", method = RequestMethod.GET)
    public ResultInfo levelConfig() {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo<>();
        try {
            response = ResultInfo.ok(memLevelConfigService.memLevelConfigAlllist());
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.member/levelConfig,失败信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("查询会员等级配置出错");
            logger.error("{}.member/levelConfig查询会员等级配置出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/member/levelConfig耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }


    /**
     * 修改会员等级配置
     *
     * @param memLevelConfig
     * @return
     */
    @Syslog("修改会员等级配置")
    @ResponseBody
    @RequestMapping(name = "修改会员等级配置", value = "/update", method = RequestMethod.POST)
    public ResultInfo update(@RequestBody MemLevelConfig memLevelConfig) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginAdmin = getLoginAdmin();
            memLevelConfig.setUpdateTime(new Date());
            memLevelConfig.setCreateUser(loginAdmin.getAcclogin());
            response.setData(memLevelConfigService.updateForManager(memLevelConfig));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.update修改会员等级配置失败,失败信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("分页查询会员等级配置出错");
            logger.error("{}.update修改会员等级配置出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/update耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    /**
     * 添加会员等级配置
     *
     * @param memLevelConfig
     * @return
     */
    @Syslog("添加会员等级配置")
    @ResponseBody
    @RequestMapping(name = "添加会员等级配置", value = "/insert", method = RequestMethod.POST)
    public ResultInfo insert(@RequestBody MemLevelConfig memLevelConfig) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginAdmin = getLoginAdmin();
            memLevelConfig.setCreateTime(new Date());
            memLevelConfig.setCreateUser(loginAdmin.getAcclogin());
            response.setData(memLevelConfigService.insertForManager(memLevelConfig));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.insert添加会员等级配置失败,失败信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("添加会员等级配置出错出错");
            logger.error("{}.insert添加会员等级配置出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/insert耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }


    /**
     * 删除会员等级配置
     *
     * @param id
     * @return
     */
    @Syslog("删除会员等级配置")
    @ResponseBody
    @RequestMapping(name = "删除会员等级配置", value = "/delete", method = RequestMethod.POST)
    public ResultInfo delete(@RequestParam(name = "id", required = true) Long id) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(memLevelConfigService.deleteForManager(id));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.delete删除会员等级配置失败,失败信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("分页查询会员等级配置出错");
            logger.error("{}.delete删除会员等级配置出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/delete耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }
}
