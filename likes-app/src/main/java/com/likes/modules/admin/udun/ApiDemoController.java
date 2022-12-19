package com.likes.modules.admin.udun;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.likes.common.annotation.AllowAccess;
import com.likes.modules.admin.login.controller.service.impl.SnowFlakeUtil;
import com.uduncloud.sdk.client.UdunClient;
import com.uduncloud.sdk.domain.Address;
import com.uduncloud.sdk.domain.Coin;
import com.uduncloud.sdk.domain.ResultMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiDemoController {
    Logger log = LoggerFactory.getLogger(ApiDemoController.class);
    @Autowired
    UdunClient udunClient;
    private static final String SUCCESS = "success";

    /**
     * 生成地址
     *
     * @return 返回参数
     */
    @AllowAccess
    @GetMapping("/create")
    public String create() {
        Address address = udunClient.createAddress("195", "puff", null, "http://18.167.208.122:5858/back/notify");
        log.info(JSONUtil.toJsonStr(address));
        return SUCCESS;
    }

    /**
     * 提币
     *
     * @return 返回参数
     */
    @AllowAccess
    @GetMapping("/withdraw")
    public String withdraw() {
        ResultMsg resultMsg = udunClient.withdraw("TPVVTbsdD3BNXDqrWTd9dqvWcKhH9Whs4f", BigDecimal.TEN,
                "195", "TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t",
                SnowFlakeUtil.getId()+"", null,"http://18.167.208.122:5858/back/notify");
        log.info(resultMsg.toString());
        return SUCCESS;
    }



    /**
     * 检验地址合法性
     *
     * @return 返回参数
     */
    @AllowAccess
    @GetMapping("/checkAddress")
    public String checkAddress() {
        boolean b = udunClient.checkAddress("195", "TEDqZtezBEmBb4UNx2xGhMeHEqHUrWWUDc");
        log.info("{}", b);
        return SUCCESS;
    }

    /**
     * 获取商户支持币种
     *
     * @return 返回参数
     */
    @AllowAccess
    @GetMapping("/listSupportCoin")
    public String listSupportCoin() {
        List<Coin> coinList = udunClient.listSupportCoin(true);
        log.info("{}", coinList.size());
        log.info("{}", JSON.toJSONString(coinList));
        return SUCCESS;
    }


    @AllowAccess
    @GetMapping("/test")
    public test test() {
        test test = new test();
        test.setTime(new Date());
        return test;
    }


    class test{


        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date time;

        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }
    }
}
