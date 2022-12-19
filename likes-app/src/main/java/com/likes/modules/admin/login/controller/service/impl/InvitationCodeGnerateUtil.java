package com.likes.modules.admin.login.controller.service.impl;

import com.likes.common.mybatis.entity.MemBaseinfo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @author liushuaic
 * @date 2015/11/18 14:40
 * @desc 邀请码生成工具类
 */

public class InvitationCodeGnerateUtil {
    private static final String[] storeInvitationChars = {"a", "c", "b", "d", "f", "e", "h", "i", "j", "k", "l", "m", "n", "o", "p"

            , "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    /**
     * @author liushuaic
     * @date 2015/11/26 18:01
     * @desc 生成邀请码
     * <p>
     * 格式： abcd12
     */

    public static String generateInvitationCodeTwo(MemBaseinfo user) {
        String userIdStr = user.getMemid().toString();
        int forSize = 5 - userIdStr.length();
        String randomStr = "";
        for (int i = 0; i <= forSize; i++) {
            Random random = new Random();
            int randomIndex = random.nextInt(35);
            randomStr = randomStr + storeInvitationChars[randomIndex];
        }
        return randomStr + userIdStr;
    }

    public static void main(String[] args) {
        MemBaseinfo user = new MemBaseinfo();
        user.setMemid(12l);
        String invitationCode = InvitationCodeGnerateUtil.generateInvitationCodeTwo(user);
        System.out.println(invitationCode);
    }

}

