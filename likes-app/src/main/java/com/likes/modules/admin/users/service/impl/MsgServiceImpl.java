package com.likes.modules.admin.users.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.constant.Constants;
import com.likes.common.constant.RedisKeys;
import com.likes.common.enums.ChatMsgTypeEnum;
import com.likes.common.enums.LoginUserTypeEnum;
import com.likes.common.enums.MessageTypeEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.ChatBody;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.CommentDO;
import com.likes.common.model.dto.InfSysnoticeDO;
import com.likes.common.model.dto.sys.InfoRemindNumDO;
import com.likes.common.model.request.InfSysnoticeRequest;
import com.likes.common.model.request.UsersRequest;
import com.likes.common.model.request.body.UsersRequestLoginUser;
import com.likes.common.mybatis.entity.InfSysremindinfo;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.service.sys.InfSysnoticeService;
import com.likes.common.service.sys.InfSysremindinfoService;
import com.likes.common.util.DateUtils;
import com.likes.common.util.JsonUtil;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.modules.admin.users.service.MsgService;
import com.github.pagehelper.Page;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MsgServiceImpl extends BaseServiceImpl implements MsgService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RedisTemplate redisTemplate;
    @Resource
    private InfSysremindinfoService infSysremindinfoService;
    @Resource
    private InfSysnoticeService infSysnoticeService;

    @Override
    public Map<String, Object> myMsgNum(String accno) {
        Map<String, Object> entries = redisTemplate.opsForHash().entries(RedisKeys.LIVE_INF_SYSREMINDINFO_NUM + accno);
        if (!entries.isEmpty()) {
            return entries;
        } else {
            Map<String, Object> dataMap = new HashMap<>();
            Integer systemNum = 0;
            Integer sourcesAuditNum = 0;
            Integer commentNum = 0;
            List<InfoRemindNumDO> list = infSysremindinfoService.getNumByAccno(accno);
            for (InfoRemindNumDO infoRemindNumDO : list) {
                if (Constants.RMTYPE_SYSTEM.equals(infoRemindNumDO.getRmtype()) || Constants.RMTYPE_SYSTEMNOTICE.equals(infoRemindNumDO.getRmtype()) || Constants.RMTYPE_NOTALK.equals(infoRemindNumDO.getRmtype()) || Constants.RMTYPE_OFFLINE.equals(infoRemindNumDO.getRmtype())) {
                    systemNum += infoRemindNumDO.getNum();
                } else if (Constants.RMTYPE_AUDITIMG.equals(infoRemindNumDO.getRmtype()) || Constants.RMTYPE_AUDITVIDEO.equals(infoRemindNumDO.getRmtype())) {
                    sourcesAuditNum += infoRemindNumDO.getNum();
                } else if (Constants.RMTYPE_COMMENT.equals(infoRemindNumDO.getRmtype())) {
                    commentNum += infoRemindNumDO.getNum();
                }
            }
            dataMap.put("systemNum", systemNum);
            dataMap.put("sourcesAuditNum", sourcesAuditNum);
            dataMap.put("commentNum", commentNum);
            RedisBusinessUtil.putallmap(RedisKeys.LIVE_INF_SYSREMINDINFO_NUM + accno, dataMap);
            return dataMap;
        }

//        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(accno);
//        if (memBaseinfo != null) {
//            dataMap.put("goldnum", memBaseinfo.getGoldnum().setScale(3, BigDecimal.ROUND_HALF_DOWN).doubleValue());
//        } else {
//            dataMap.put("goldnum", 0);
//        }
//        Map<String, Object> param = new HashMap<>();
//        // 9未查看
//        param.put("issee", 9);
//        param.put("recipienter", accno);
//
//        List<String> rmtypelist = new ArrayList<>();
//        rmtypelist.add(Constants.RMTYPE_AUDITVIDEO);
//        rmtypelist.add(Constants.RMTYPE_AUDITIMG);
//        param.put("rmtypelist", rmtypelist);
//        // 审核消息数量
//        // 获取未读的 资源审核消息
//        Integer sourcesAuditNum = infSysremindinfoService.getRmtypeNum(param);
//        dataMap.put("sourcesAuditNum", sourcesAuditNum);
//
//        // 评论回应消息数量
//        rmtypelist.clear();
//        rmtypelist.add(Constants.RMTYPE_COMMENT);
//        param.put("rmtypelist", rmtypelist);
//        Integer commentNum = infSysremindinfoService.getRmtypeNum(param);
//        dataMap.put("commentNum", commentNum);
//
//        // 系统通知消息 数量
//        rmtypelist.clear();
//        rmtypelist.add(Constants.RMTYPE_SYSTEM);
//        rmtypelist.add(Constants.RMTYPE_SYSTEMNOTICE);
//        //rmtypelist.add(Constants.RMTYPE_NOTALK);
//        //rmtypelist.add(Constants.RMTYPE_OFFLINE);
//        param.put("rmtypelist", rmtypelist);
//        //param.put("type", "system");
//        Integer systemNum = infSysremindinfoService.getRmtypeNum(param);
//        dataMap.put("systemNum", systemNum);
//        return dataMap;
    }

    @Override
    public PageResult mySourceMsgList(PageBounds page, UsersRequest usersRequest) {
        List<String> rmtypelist = new ArrayList<>();
        rmtypelist.add(Constants.RMTYPE_AUDITVIDEO);
        rmtypelist.add(Constants.RMTYPE_AUDITIMG);
        usersRequest.setRmtypelist(rmtypelist);
        Page<InfSysremindinfo> list = infSysremindinfoService.myMsgList(usersRequest, page.toRowBounds());

        // 消除资源审核未读设置
        Map<String, Object> param = new HashMap<>();
        // 9未查看
        param.put("issee", 9);
        param.put("recipienter", usersRequest.getAccno());
        param.put("rmtypelist", rmtypelist);
        Integer num = infSysremindinfoService.getRmtypeNum(param);
        if (0 < num) {
            // 全部更新成 已读
            param.put("updateUser", usersRequest.getAccno());
            infSysremindinfoService.updateRmtypeNum(param);
        }
        RedisBusinessUtil.delete(RedisKeys.LIVE_INF_SYSREMINDINFO_NUM + usersRequest.getAccno());

        return PageResult.getPageResult((int) list.getTotal(), page, list);
    }

    @Override
    public PageResult systemMsgList(PageBounds page, UsersRequest usersRequest) {
        List<String> rmtypelist = new ArrayList<>();
        rmtypelist.add(Constants.RMTYPE_SYSTEM);
        rmtypelist.add(Constants.RMTYPE_SYSTEMNOTICE);
        rmtypelist.add(Constants.RMTYPE_NOTALK);
        rmtypelist.add(Constants.RMTYPE_OFFLINE);
        usersRequest.setRmtypelist(rmtypelist);
        Page<InfSysremindinfo> list = infSysremindinfoService.myMsgList(usersRequest, page.toRowBounds());

        // 消除资源审核未读设置
        Map<String, Object> param = new HashMap<>();
        // 9未查看
        param.put("issee", 9);
        param.put("recipienter", usersRequest.getAccno());
        param.put("rmtypelist", rmtypelist);
        Integer num = infSysremindinfoService.getRmtypeNum(param);
        if (0 < num) {
            // 全部更新成 已读
            param.put("updateUser", usersRequest.getAccno());
            infSysremindinfoService.updateRmtypeNum(param);
        }
        RedisBusinessUtil.delete(RedisKeys.LIVE_INF_SYSREMINDINFO_NUM + usersRequest.getAccno());

        return PageResult.getPageResult((int) list.getTotal(), page, list);
    }

    @Override
    public List<InfSysnoticeDO> infSysnoticeList(InfSysnoticeRequest req) {
        if (null == req.getType()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_998.getCode(), "类型为空");
        }
        Integer type = req.getType();
        List<InfSysnoticeDO> list = infSysnoticeService.getInfSysnoticeList(type);
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<InfSysnoticeDO> infSysnoticeMsgList(InfSysnoticeRequest req) {
        if (null == req.getType()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_998.getCode(), "类型为空");
        }
        Integer type = req.getType();
        List<InfSysnoticeDO> infSysnoticeDOS;
        if (MessageTypeEnum.APP_START.getValue().equals(type)) {
            infSysnoticeDOS = infSysnoticeService.selectAPPNoticeList(type);
        } else {
            infSysnoticeDOS = infSysnoticeService.selectNoticeList(type);
        }
        if (CollectionUtils.isEmpty(infSysnoticeDOS)) {
            return new ArrayList<>();
        }
        return infSysnoticeDOS;
    }

}
