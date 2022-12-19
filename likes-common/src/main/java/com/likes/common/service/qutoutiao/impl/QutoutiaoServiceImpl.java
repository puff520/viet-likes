package com.likes.common.service.qutoutiao.impl;

import com.likes.common.model.EastTransform;
import com.likes.common.model.LoginUser;
import com.likes.common.model.MemBuryingPoint;
import com.likes.common.model.QutoutiaoTransform;
import com.likes.common.model.request.BuryingPointRequest;
import com.likes.common.model.request.EastToutiaoRequest;
import com.likes.common.model.request.QutoutiaoRequest;
import com.likes.common.mybatis.mapper.EastTransformMapper;
import com.likes.common.mybatis.mapper.MemBuryingPointMapper;
import com.likes.common.mybatis.mapper.QutoutiaoTransformMapper;
import com.likes.common.service.qutoutiao.QutoutiaoService;
import com.likes.common.util.StringUtils;
import com.likes.common.util.http.HttpUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;

@Service
public class QutoutiaoServiceImpl implements QutoutiaoService {

    @Resource
    private MemBuryingPointMapper memBuryingPointMapper;

    @Resource
    private QutoutiaoTransformMapper transformMapper;


    @Resource
    private EastTransformMapper eastTransformMapper;


    @Override
    public boolean saveData(QutoutiaoRequest request) {
        QutoutiaoTransform transform = null;
        if (request.getOs().equals(0)) {
            transform = transformMapper.selectQutoutiaoByAndroididmd5(request.getAndroididmd5());
            if (transform == null) {
                transform = transformMapper.selectQutoutiaoByImeimd5(request.getImeimd5());
            }

        } else if (request.getOs().equals(1)) {
            transform = transformMapper.selectQutoutiaoByIos(request.getIdfa());
        }
        if (transform == null) {
            QutoutiaoTransform transformAdd = new QutoutiaoTransform();
            transformAdd.setCid(request.getCid());
            transformAdd.setOs(request.getOs());
            transformAdd.setImeimd5(request.getImeimd5());
            transformAdd.setIdfa(request.getIdfa());
            transformAdd.setTs(request.getTs());
            transformAdd.setTsms(request.getTsms());
            transformAdd.setCallbackUrl(request.getCallbackUrl());
            transformAdd.setUnit(request.getUnit());
            transformAdd.setPlan(request.getPlan());
            transformAdd.setUid(request.getUid());
            transformAdd.setUa(request.getUa());
            transformAdd.setAndroididmd5(request.getAndroididmd5());
            transformAdd.setIp(request.getIp());
            transformAdd.setOaid(request.getOaid());
            transformAdd.setDpLink(request.getDpLink());
            transformAdd.setCreateTime(new Date());
            transformMapper.insertSelective(transformAdd);
            return true;
        } else {
            transform.setCid(request.getCid());
            transform.setOs(request.getOs());
            transform.setImeimd5(request.getImeimd5());
            transform.setIdfa(request.getIdfa());
            transform.setTs(request.getTs());
            transform.setTsms(request.getTsms());
            transform.setCallbackUrl(request.getCallbackUrl());
            transform.setUnit(request.getUnit());
            transform.setPlan(request.getPlan());
            transform.setUid(request.getUid());
            transform.setUa(request.getUa());
            transform.setAndroididmd5(request.getAndroididmd5());
            transform.setIp(request.getIp());
            transform.setOaid(request.getOaid());
            transform.setDpLink(request.getDpLink());
            transform.setUpdateTime(new Date());
            transformMapper.updateByPrimaryKeySelective(transform);
            return true;
        }

    }

    @Override
    public boolean saveEastData(EastToutiaoRequest request) {
        EastTransform transform = null;
//        if (request.getOs().equals("android")) {
            transform = eastTransformMapper.selectQutoutiaoByAndroididmd5(request.getAndroidid());
            if (transform == null) {
                transform = eastTransformMapper.selectQutoutiaoByImeimd5(request.getImei());
            }

//        }
//        else if (request.getOs().equals("ios")) {
//            transform = eastTransformMapper.selectQutoutiaoByIos(request.getIdfa());
//        }
        if (transform == null) {
            EastTransform transformAdd = new EastTransform();
            transformAdd.setMuid(request.getMuid());
            transformAdd.setAndroidid(request.getAndroidid());
            transformAdd.setImei(request.getImei());
            transformAdd.setIdfa(request.getIdfa());
            transformAdd.setCallback(request.getCallback());
            transformAdd.setIp(request.getIp());
            transformAdd.setOaid(request.getOaid());
            transformAdd.setOs(request.getOs());
            transformAdd.setCallback(request.getCallback());
            transformAdd.setCreateTime(new Date());
            eastTransformMapper.insertSelective(transformAdd);
            return true;
        } else {
            transform.setMuid(request.getMuid());
            transform.setAndroidid(request.getAndroidid());
            transform.setImei(request.getImei());
            transform.setIdfa(request.getIdfa());
            transform.setCallback(request.getCallback());
            transform.setIp(request.getIp());
            transform.setOaid(request.getOaid());
            transform.setOs(request.getOs());
            transform.setCallback(request.getCallback());
            transform.setUpdateTime(new Date());
            eastTransformMapper.updateByPrimaryKeySelective(transform);
            return true;
        }
    }

    @Override
    public boolean saveBuryingPoint(BuryingPointRequest pointRequest, LoginUser loginUser) {
        MemBuryingPoint memBuryingPoint = new MemBuryingPoint();
        memBuryingPoint.setCreateTime(new Date());
        memBuryingPoint.setMcode(pointRequest.getMcode());
        memBuryingPoint.setOptype(pointRequest.getMtype());
        int row = memBuryingPointMapper.insertSelective(memBuryingPoint);
        if (row > 0) {
            sendToToutiao(pointRequest);
            sendToEast(pointRequest);
        }
        return true;
    }


    @Async
    public void sendToToutiao(BuryingPointRequest pointRequest) {
        String url = "";
        QutoutiaoTransform transform;
        if (pointRequest.getMtype().equals(0)) {
            transform = transformMapper.selectQutoutiaoByAndroid(pointRequest.getMcode());
        } else {
            transform = transformMapper.selectQutoutiaoByIos(pointRequest.getMcode());

        }
        if (transform != null && StringUtils.isNotBlank(transform.getCallbackUrl())) {
            url = transform.getCallbackUrl();
        }
        try {
            if (StringUtils.isNotBlank(url)) {
                HttpUtils.sendGet(HttpUtils.decode(url) + "&op2=1&opt_active_time=" + System.currentTimeMillis() / 1000);
            }else {
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        QutoutiaoTransform upTransform = new QutoutiaoTransform();
        upTransform.setId(transform.getId());
        upTransform.setStatus(1);
        transformMapper.updateByPrimaryKeySelective(upTransform);

    }

    @Async
    public void sendToEast(BuryingPointRequest pointRequest) {
        String url = "";
        EastTransform transform ;
        if (pointRequest.getMtype().equals(0)) {
            transform = eastTransformMapper.selectQutoutiaoByAndroid(pointRequest.getMcode());
        } else {
            transform = eastTransformMapper.selectQutoutiaoByIos(pointRequest.getMcode());

        }
        if (transform != null && StringUtils.isNotBlank(transform.getCallback())) {
            url = transform.getCallback();
        }
        try {
            if (StringUtils.isNotBlank(url)) {

                String scUrl = url.replace("__SC__", "likeshare");
                String coveTimeUrl = scUrl.replace("__CONV_TIME__", System.currentTimeMillis() / 1000 + "");
                String eventTypeUrl = coveTimeUrl.replace("__EVENT_TYPE__", 3 + "");
                HttpUtils.sendGet(eventTypeUrl);
            }else {
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        EastTransform upTransform = new EastTransform();
        upTransform.setId(transform.getId());
        upTransform.setStatus(1);
        eastTransformMapper.updateByPrimaryKeySelective(upTransform);

    }


//    public static void main(String[] args) {
//        try {
//            HttpUtils.sendGet(HttpUtils.decode("http://adv.aiclk.com/tools/callback/target-url-monitor/42554?md5=95a8eb45fc37464c2fe0d961bbd6708c") + "&op2=1&opt_active_time=" + System.currentTimeMillis() / 1000);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
