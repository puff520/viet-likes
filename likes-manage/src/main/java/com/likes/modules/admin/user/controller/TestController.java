package com.likes.modules.admin.user.controller;

import com.likes.common.annotation.AllowAccess;
import com.likes.common.mybatis.entity.User;
import com.likes.modules.admin.user.service.User2Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 测试api
 * </p>
 *
 * @author zhengqingya
 * @description
 * @date 2021/01/13 10:11
 */
@Slf4j
@RestController
@RequestMapping("/web/api/test")
@Api(tags = {"测试api"})
public class TestController {
    @Autowired
    private User2Service userService;


    @AllowAccess
    @PostMapping("add/batch/data")
    @ApiOperation("测试插入数据用时")
    public String addBatchData(@ApiParam(value = "插入数据量", required = true, example = "10000")
                               @RequestParam int addSum) {
        return this.userService.addBatchData(addSum);
    }

    @AllowAccess
    @GetMapping("list/page")
    @ApiOperation("列表分页")
    public List<User> listPage(@ModelAttribute User params) {
        return this.userService.listPage(params);
    }

}
