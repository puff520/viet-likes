package com.likes.modules.admin.job;

import com.alibaba.fastjson.JSON;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.likes.common.annotation.ReadOnlyConnection;
import com.likes.common.model.dto.RankDto;
import com.likes.common.mybatis.entity.MemGoldchange;
import com.likes.common.mybatis.mapper.MemGoldchangeMapper;
import com.likes.common.mybatis.mapper.MemLevelMapper;
import com.likes.common.mybatis.mapper.TaskOrderMapper;
import com.likes.common.mybatis.mapper.UserMapper;
import com.likes.common.mybatis.mapperext.member.MemLevelMapperExt;
import com.likes.common.util.CollectionUtil;
import com.likes.common.util.DateUtils;
import com.likes.common.util.IdGeneratorUtil;
import com.likes.common.util.redis.RedisBaseUtil;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
public class TestTask {

    @Resource
    private TaskOrderMapper taskOrderMapper;
    @Resource
    private MemLevelMapperExt memLevelMapperExt;
    @Resource
    private MemGoldchangeMapper memGoldchangeMapper;
    @Autowired
    IdGeneratorUtil idGeneratorUtil;
    @Resource
    private UserMapper userMapper;

    Logger log = LoggerFactory.getLogger(TestTask.class);

//    @Scheduled(fixedDelay = 50000)
//    @Transactional
    public void rankingTask() {
//        MemGoldchange memGoldchange = new MemGoldchange();
//        memGoldchange.setIsDelete(false);
//        memGoldchange.setCreateTime(new Date());
//        memGoldchange.setUpdateTime(new Date());
//        memGoldchange.setSnowSn(idGeneratorUtil.snowflakeId());
        MemGoldchange memGoldchange=  memGoldchangeMapper.selectByPrimaryKey(3374219L);
        System.out.println(JSON.toJSONString(memGoldchange));
    }



}
