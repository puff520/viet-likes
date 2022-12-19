package com.likes.modules.admin.user.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.likes.common.mybatis.entity.User;
import com.likes.common.mybatis.mapper.UserMapper;
import com.likes.common.util.IdGeneratorUtil;
import com.likes.modules.admin.user.service.User2Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.apache.shardingsphere.transaction.annotation.ShardingSphereTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author zhengqingya
 * @description
 * @date 2021/01/13 10:11
 */
@Slf4j
@Service
public class User2ServiceImpl implements User2Service {

    @Resource
    private UserMapper userMapper;

    @Autowired
    IdGeneratorUtil idGeneratorUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @ShardingSphereTransactionType(TransactionType.XA)
    public String addBatchData(int addSum) {
        LocalDateTime saveBeforeDateTime = LocalDateTime.now();
        // 第1次插入
        int page = 1;
        // 每次插入数据条数
        int pageSize = 5000;
        // 累计插入数量
        int total = 0;
        // 循环插入数据
        for (int index = 1; index <= addSum; ) {
            total = page * pageSize;
            log.info("page:[{}] pageSize:[{}] total:[{}] index:[{}]", page, pageSize, total, index);
            if (total > addSum) {
                int finalNum = addSum - ((page - 1) * pageSize);
                log.info("最后一页新增数：[{}]", finalNum);
                this.insertData(finalNum);
            } else {
                this.insertData(pageSize);
            }
            page += 1;
            index = total + 1;
        }

        LocalDateTime saveAfterDateTime = LocalDateTime.now();
        Duration duration = Duration.between(saveBeforeDateTime, saveAfterDateTime);
        long millis = duration.toMillis();
        String msg = String.format("测试插入%s条数据用时: [%s ms]  [%s s]", addSum, millis, millis / 1000);
        log.info(msg);
        return msg;
    }

    public void insertData(int addSum) {
//        List<User> demoList = Lists.newLinkedList();
//        for (int i = 1; i <= addSum; i++) {
//
//            demoList.add(item);
//        }

        User item = new User();
        item.setUserId(idGeneratorUtil.snowflakeId());
        item.setUsername("username - ");
        item.setPassword("123456");
        item.setRemark("this is username:");
        item.setCreateTime(new Date());
        userMapper.insertSelective(item);
        throw new RuntimeException("1");
    }


    @Override
    public List<User> listPage(User params) {
        // 清除掉上一次的规则，否则会报错
        HintManager.clear();
        HintManager hintManager = HintManager.getInstance();
//        hintManager.addDatabaseShardingValue("t_user", 202223);
        // 指定表的分片健 => 指定查t_user0
        hintManager.addTableShardingValue("t_user", 0);
//        hintManager.addTableShardingValue("t_user", 1);
        // 读写分离强制读主库，避免造成主从复制导致的延迟
        hintManager.setWriteRouteOnly();
        List<User> result = userMapper.selectAll();
        // 清除规则
        hintManager.close();
        return result;
    }

}
