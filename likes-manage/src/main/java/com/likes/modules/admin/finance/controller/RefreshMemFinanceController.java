package com.likes.modules.admin.finance.controller;

import com.likes.common.model.common.ResultInfo;
import com.likes.modules.admin.finance.service.RefreshMemFinanceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("/refresh")
public class RefreshMemFinanceController {

    @Resource
    private RefreshMemFinanceService refreshMemFinanceService;

    @PostMapping("/mem/consume")
    public ResultInfo refreshConsumeAmount(@RequestParam(value = "accno", required = false) String accno) {
        refreshMemFinanceService.refreshConsume(accno);
        return ResultInfo.ok();
    }

}
