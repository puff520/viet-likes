package com.likes.common.util;

import com.alibaba.fastjson.JSON;
import com.likes.common.util.encrypt.MD5;
import com.likes.common.util.http.HttpRespons;
import com.likes.common.util.http.HttpUtils;
import kotlin.random.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UdunUtils {


    private static final Logger logger = LoggerFactory.getLogger(UdunUtils.class);

    private final String URI = "/mch/address/create";

    public static void main(String[] args) {

        Map<String, String> packageParams = new HashMap<>();

        String nonce =  RandomUtil.getKillNumber(6);
        String timestamp = System.currentTimeMillis()+"";

        packageParams.put("timestamp",timestamp);
        packageParams.put("nonce", nonce);

        UdunBody udunBody = new UdunBody();
        udunBody.setMerchantId("309057");
        udunBody.setCallUrl("http://localhost:8080/callBack");
        udunBody.setMainCoinType("520");
        List<Object> list = new LinkedList<>();
        list.add(udunBody);

       String body=  JSON.toJSONString(list);
        packageParams.put("body",body );


        StringBuffer sb = new StringBuffer();
        sb.append(JSON.toJSONString(list));
        sb.append("d7c30ea269dab31d626433b28010fc36");
        sb.append(nonce);
        sb.append(timestamp);
        packageParams.put("sign", MD5.md5(sb.toString()));


        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("timestamp",timestamp);
        hashMap.put("nonce",nonce);
        hashMap.put("sign",MD5.md5(sb.toString()));
        hashMap.put("body",JSON.toJSONString(list));


        try {
            String  httpRespons =  HttpUtils.doPost("https://sig11.udun.io", JSON.toJSONString(hashMap));
            System.out.println(JSON.toJSONString(httpRespons));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }





}
