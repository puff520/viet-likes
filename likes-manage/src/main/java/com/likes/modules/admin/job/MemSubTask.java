package com.likes.modules.admin.job;

import com.likes.common.mybatis.entity.MemSubInfo;
import com.likes.common.mybatis.mapper.AgentMapper;
import com.likes.common.mybatis.mapper.EveryDayReportMapper;
import com.likes.common.mybatis.mapper.MemSubInfoMapper;
import com.likes.common.util.CommonUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;

@Component
public class MemSubTask {
    @Resource
    private MemSubInfoMapper memSubInfoMapper;
    @Resource
    private EveryDayReportMapper everyDayReportMapper;
    @Resource
    private AgentMapper agentMapper;

    private static final ExecutorService taskCachedThreadPool = CommonUtils.getMaxThreadPoolExecutor();

//    @PostConstruct
//    public void dailyReportTask() {
//        System.out.println("程序执行");
//        taskCachedThreadPool.execute(this::dailyReportTask1);
//        taskCachedThreadPool.execute(this::dailyReportTask2);
//        taskCachedThreadPool.execute(this::dailyReportTask3);
//        taskCachedThreadPool.execute(this::dailyReportTask4);
//        taskCachedThreadPool.execute(this::dailyReportTask5);
//        taskCachedThreadPool.execute(this::dailyReportTask6);
//        taskCachedThreadPool.execute(this::dailyReportTask7);
//        taskCachedThreadPool.execute(this::dailyReportTask8);
//
//    }


    public void dailyReportTask1() {
        System.out.println("程序1 执行");
        Integer countAgentAccNo = everyDayReportMapper.countAgentAccNo();
        int totalSize = countAgentAccNo;
        int pageSize = 500;
        int totalPage = (totalSize + pageSize - 1) / pageSize;

        for (int pageNo = 1; pageNo <= totalSize; pageNo++) {
            Set<MemSubInfo> subInfoList = new HashSet<>();
            Integer page = (pageNo - 1) * pageSize;
            if (page >= 5000) {
                break;
            }
            List<String> accNoList = everyDayReportMapper.agentAccnoList(page, pageSize);
            for (String accNo : accNoList) {

                List<String> oneList = agentMapper.level1SubAccList(accNo);
                List<String> twoList = agentMapper.level2SubAccList(accNo);
                List<String> threeList = agentMapper.level3SubAccList(accNo);

                MemSubInfo subInfo = new MemSubInfo();
                subInfo.setLevelOneAgents(oneList);
                subInfo.setLevelTwoAgents(twoList);
                subInfo.setLevelThreeAgents(threeList);

                subInfo.setAccno(accNo);
                subInfoList.add(subInfo);
            }
            memSubInfoMapper.insertBatch(subInfoList);
            System.out.println((pageNo - 1) * pageSize + "---" + pageSize);

            System.out.println("当前执行到了" + pageNo + "页");
        }

    }

    public void dailyReportTask2() {
        System.out.println("程序2 执行");
        Integer countAgentAccNo = everyDayReportMapper.countAgentAccNo();
        int totalSize = countAgentAccNo;
        int pageSize = 500;
        int totalPage = (totalSize + pageSize - 1) / pageSize;


        for (int pageNo = 1; pageNo <= totalSize; pageNo++) {
            Set<MemSubInfo> subInfoList = new HashSet<>();
            Integer page = (pageNo - 1) * pageSize + 5000;
            if (page >= 10000) {
                break;
            }
            List<String> accNoList = everyDayReportMapper.agentAccnoList(page, pageSize);
            for (String accNo : accNoList) {

                List<String> oneList = agentMapper.level1SubAccList(accNo);
                List<String> twoList = agentMapper.level2SubAccList(accNo);
                List<String> threeList = agentMapper.level3SubAccList(accNo);

                MemSubInfo subInfo = new MemSubInfo();
                subInfo.setLevelOneAgents(oneList);
                subInfo.setLevelTwoAgents(twoList);
                subInfo.setLevelThreeAgents(threeList);

                subInfo.setAccno(accNo);
                subInfoList.add(subInfo);
            }
            memSubInfoMapper.insertBatch(subInfoList);
            System.out.println((pageNo - 1) * pageSize + "---" + pageSize);

            System.out.println("当前执行到了" + pageNo + "页");
        }

    }

    public void dailyReportTask3() {
        System.out.println("程序3 执行");
        Integer countAgentAccNo = everyDayReportMapper.countAgentAccNo();
        int totalSize = countAgentAccNo;
        int pageSize = 500;
        int totalPage = (totalSize + pageSize - 1) / pageSize;

        for (int pageNo = 1; pageNo <= totalSize; pageNo++) {
            Integer page = (pageNo - 1) * pageSize + 10000;
            if (page >= 15000) {
                break;
            }
            Set<MemSubInfo> subInfoList = new HashSet<>();
            List<String> accNoList = everyDayReportMapper.agentAccnoList(page, pageSize);
            for (String accNo : accNoList) {
                List<String> oneList = agentMapper.level1SubAccList(accNo);
                List<String> twoList = agentMapper.level2SubAccList(accNo);
                List<String> threeList = agentMapper.level3SubAccList(accNo);

                MemSubInfo subInfo = new MemSubInfo();
                subInfo.setLevelOneAgents(oneList);
                subInfo.setLevelTwoAgents(twoList);
                subInfo.setLevelThreeAgents(threeList);

                subInfo.setAccno(accNo);
                subInfoList.add(subInfo);
            }
            memSubInfoMapper.insertBatch(subInfoList);
            System.out.println((pageNo - 1) * pageSize + "---" + pageSize);

            System.out.println("当前执行到了" + pageNo + "页");
        }

    }

    public void dailyReportTask4() {
        System.out.println("程序4 执行");
        Integer countAgentAccNo = everyDayReportMapper.countAgentAccNo();
        int totalSize = countAgentAccNo;
        int pageSize = 500;
        int totalPage = (totalSize + pageSize - 1) / pageSize;

        for (int pageNo = 1; pageNo <= totalSize; pageNo++) {
            Integer page = (pageNo - 1) * pageSize + 15000;
            if (page >= 20000) {
                break;
            }
            Set<MemSubInfo> subInfoList = new HashSet<>();
            List<String> accNoList = everyDayReportMapper.agentAccnoList(page, pageSize);
            for (String accNo : accNoList) {
                List<String> oneList = agentMapper.level1SubAccList(accNo);
                List<String> twoList = agentMapper.level2SubAccList(accNo);
                List<String> threeList = agentMapper.level3SubAccList(accNo);

                MemSubInfo subInfo = new MemSubInfo();
                subInfo.setLevelOneAgents(oneList);
                subInfo.setLevelTwoAgents(twoList);
                subInfo.setLevelThreeAgents(threeList);

                subInfo.setAccno(accNo);
                subInfoList.add(subInfo);
            }
            memSubInfoMapper.insertBatch(subInfoList);
            System.out.println((pageNo - 1) * pageSize + "---" + pageSize);
            System.out.println("当前执行到了" + pageNo + "页");
        }

    }

    public void dailyReportTask5() {
        System.out.println("程序5 执行");
        Integer countAgentAccNo = everyDayReportMapper.countAgentAccNo();
        int totalSize = countAgentAccNo;
        int pageSize = 500;
        for (int pageNo = 1; pageNo <= totalSize; pageNo++) {
            Integer page = (pageNo - 1) * pageSize + 20000;
            if (page >= 25000) {
               break;
            }
            Set<MemSubInfo> subInfoList = new HashSet<>();
            List<String> accNoList = everyDayReportMapper.agentAccnoList(page, pageSize);
            for (String accNo : accNoList) {
                List<String> oneList = agentMapper.level1SubAccList(accNo);
                List<String> twoList = agentMapper.level2SubAccList(accNo);
                List<String> threeList = agentMapper.level3SubAccList(accNo);

                MemSubInfo subInfo = new MemSubInfo();
                subInfo.setLevelOneAgents(oneList);
                subInfo.setLevelTwoAgents(twoList);
                subInfo.setLevelThreeAgents(threeList);

                subInfo.setAccno(accNo);
                subInfoList.add(subInfo);
            }
            memSubInfoMapper.insertBatch(subInfoList);
            System.out.println((pageNo - 1) * pageSize + "---" + pageSize);
            System.out.println("当前执行到了" + pageNo + "页");
            if (pageSize >= 50000) {
                System.out.println("最后一页" + pageNo);
                break;
            }
        }

    }


    public void dailyReportTask6() {
        System.out.println("程序6 执行");
        Integer countAgentAccNo = everyDayReportMapper.countAgentAccNo();
        int totalSize = countAgentAccNo;
        int pageSize = 500;
        for (int pageNo = 1; pageNo <= totalSize; pageNo++) {
            Integer page = (pageNo - 1) * pageSize + 25000;
            if (page >=30000) {
                break;
            }
            Set<MemSubInfo> subInfoList = new HashSet<>();
            List<String> accNoList = everyDayReportMapper.agentAccnoList(page, pageSize);
            for (String accNo : accNoList) {
                List<String> oneList = agentMapper.level1SubAccList(accNo);
                List<String> twoList = agentMapper.level2SubAccList(accNo);
                List<String> threeList = agentMapper.level3SubAccList(accNo);

                MemSubInfo subInfo = new MemSubInfo();
                subInfo.setLevelOneAgents(oneList);
                subInfo.setLevelTwoAgents(twoList);
                subInfo.setLevelThreeAgents(threeList);

                subInfo.setAccno(accNo);
                subInfoList.add(subInfo);
            }
            memSubInfoMapper.insertBatch(subInfoList);
            System.out.println((pageNo - 1) * pageSize + "---" + pageSize);
            System.out.println("当前执行到了" + pageNo + "页");
            if (pageSize >= 50000) {
                System.out.println("最后一页" + pageNo);
                break;
            }
        }

    }

    public void dailyReportTask7() {
        System.out.println("程序7 执行");
        Integer countAgentAccNo = everyDayReportMapper.countAgentAccNo();
        int totalSize = countAgentAccNo;
        int pageSize = 500;
        for (int pageNo = 1; pageNo <= totalSize; pageNo++) {
            Integer page = (pageNo - 1) * pageSize + 30000;
            if (page >=35000) {
                break;
            }
            Set<MemSubInfo> subInfoList = new HashSet<>();
            List<String> accNoList = everyDayReportMapper.agentAccnoList(page, pageSize);
            for (String accNo : accNoList) {
                List<String> oneList = agentMapper.level1SubAccList(accNo);
                List<String> twoList = agentMapper.level2SubAccList(accNo);
                List<String> threeList = agentMapper.level3SubAccList(accNo);

                MemSubInfo subInfo = new MemSubInfo();
                subInfo.setLevelOneAgents(oneList);
                subInfo.setLevelTwoAgents(twoList);
                subInfo.setLevelThreeAgents(threeList);

                subInfo.setAccno(accNo);
                subInfoList.add(subInfo);
            }
            memSubInfoMapper.insertBatch(subInfoList);
            System.out.println((pageNo - 1) * pageSize + "---" + pageSize);
            System.out.println("当前执行到了" + pageNo + "页");
            if (pageSize >= 50000) {
                System.out.println("最后一页" + pageNo);
                break;
            }
        }

    }

    public void dailyReportTask8() {
        System.out.println("程序5 执行");
        Integer countAgentAccNo = everyDayReportMapper.countAgentAccNo();
        int totalSize = countAgentAccNo;
        int pageSize = 500;
        for (int pageNo = 1; pageNo <= totalSize; pageNo++) {
            Integer page = (pageNo - 1) * pageSize + 35000;
            if (page >=38500) {
               pageSize =50000;
            }
            Set<MemSubInfo> subInfoList = new HashSet<>();
            List<String> accNoList = everyDayReportMapper.agentAccnoList(page, pageSize);
            for (String accNo : accNoList) {
                List<String> oneList = agentMapper.level1SubAccList(accNo);
                List<String> twoList = agentMapper.level2SubAccList(accNo);
                List<String> threeList = agentMapper.level3SubAccList(accNo);

                MemSubInfo subInfo = new MemSubInfo();
                subInfo.setLevelOneAgents(oneList);
                subInfo.setLevelTwoAgents(twoList);
                subInfo.setLevelThreeAgents(threeList);

                subInfo.setAccno(accNo);
                subInfoList.add(subInfo);
            }
            memSubInfoMapper.insertBatch(subInfoList);
            System.out.println((pageNo - 1) * pageSize + "---" + pageSize);
            System.out.println("当前执行到了" + pageNo + "页");
            if (pageSize >= 50000) {
                System.out.println("最后一页" + pageNo);
                break;
            }
        }

    }


}
