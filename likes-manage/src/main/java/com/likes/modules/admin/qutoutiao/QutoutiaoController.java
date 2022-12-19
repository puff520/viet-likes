package com.likes.modules.admin.qutoutiao;

import com.alibaba.fastjson.JSON;
import com.likes.common.BaseController;
import com.likes.common.annotation.AllowAccess;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.QutoutiaoRequest;
import com.likes.common.service.qutoutiao.QutoutiaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/qutoutiao")
public class QutoutiaoController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private QutoutiaoService qutoutiaoService;

    @AllowAccess
    @ResponseBody
    @GetMapping(name = "趣头条接口上送 ", value = "/sendUp")
    public ResultInfo list(QutoutiaoRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo<>();
        try {
            boolean flag = qutoutiaoService.saveData(req);
            response = ResultInfo.ok(flag);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("趣头条上送数据失败,失败信息:{},请求参数:{}", e.getMessage(), JSON.toJSONString(req));
        }
        logger.info("qutoutiao/sendUp耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }


}
