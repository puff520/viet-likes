package com.likes.modules.admin.job;

import com.alibaba.fastjson.JSON;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.likes.common.annotation.ReadOnlyConnection;
import com.likes.common.model.dto.RankDto;
import com.likes.common.mybatis.entity.MemLevel;
import com.likes.common.mybatis.entity.MemLevelExample;
import com.likes.common.mybatis.mapper.MemLevelMapper;
import com.likes.common.mybatis.mapper.TaskOrderMapper;
import com.likes.common.mybatis.mapperext.member.MemLevelMapperExt;
import com.likes.common.service.sys.SysBusParamService;
import com.likes.common.service.task.TaskOrderService;
import com.likes.common.util.CollectionUtil;
import com.likes.common.util.DateUtils;
import com.likes.common.util.redis.RedisBaseUtil;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RankingTask {

    @Resource
    private TaskOrderMapper taskOrderMapper;
    @Resource
    private MemLevelMapperExt memLevelMapperExt;
    @Resource
    private MemLevelMapper memLevelMapper;

    Logger log = LoggerFactory.getLogger(RankingTask.class);

    @Scheduled(cron = "0 */8 * * * ?")
    public void rankingTask() {
        try {
            String beginTime = DateUtils.dayBeginStr();
            String endTime =  DateUtils.dayEndStr();
            List<RankDto> rankList = new LinkedList<>();
            List<RankDto> taskList = taskOrderMapper.rankJobList(beginTime,endTime);
            List<RankDto> registerList = taskOrderMapper.registerGiveList(beginTime,endTime);
            rankList.addAll(taskList);
            rankList.addAll(registerList);
            if(CollectionUtil.isNotEmpty(rankList) && rankList.size() > 25){
                rankList.stream().sorted(Comparator.comparing(RankDto::getUserName)).collect(Collectors.toList());
                RedisBaseUtil.set("likes_ranking2", rankList);
            }
        } catch (Exception e) {
            log.error("执行自动审核定时任务错误 ：============》》 {}", e);
        }

    }


//    @Scheduled(fixedDelay =  5000000)
//    public  void disposeRepeat(){
//      List<String> list =  memLevelMapperExt.chongFuList();
//        for (String s : list) {
//            MemLevelExample memLevelExample = new MemLevelExample();
//            MemLevelExample.Criteria criteria = memLevelExample.createCriteria();
//            criteria.andAccnoEqualTo(s);
//            criteria.andIsDeleteEqualTo(false);
//            List<MemLevel> memLevelList  = memLevelMapper.selectByExample(memLevelExample);
//            memLevelList.sort(Comparator.comparing(MemLevel::getLevelid));
//            MemLevel maxAgePerson = memLevelList.stream().max(Comparator.comparing(MemLevel::getLevelid)).get();
//            List<MemLevel> filterList = memLevelList.stream().filter(userBean -> userBean.getLevelid() < maxAgePerson.getLevelid()).collect(Collectors.toList());
//            for (MemLevel memLevel : filterList) {
//                memLevel.setIsDelete(true);
//                memLevelMapper.updateByPrimaryKeySelective(memLevel);
//            }
//
//        }
//
//    }


}
