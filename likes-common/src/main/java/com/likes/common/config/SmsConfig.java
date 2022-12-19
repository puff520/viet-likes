package com.likes.common.config;

import com.alibaba.fastjson.JSON;
import com.likes.common.enums.StatusCode;
import com.likes.common.enums.SysParameterEnum;
import com.likes.common.exception.BusinessException;
import com.likes.common.mybatis.entity.SysParameter;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.util.redis.RedisBusinessUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author puff
 */
@Component
public class SmsConfig implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -9164771012255704784L;


    @Resource
    private SysParamService sysParamService;

    /**
     * 阿里云 appkey
     */
    public static String SMS_ALIYUN_APPKEY = "SMS_ALIYUN_APPKEY";

    /**
     * 阿里云 appsecret
     */
    public static String SMS_ALIYUN_APPSECRET = "SMS_ALIYUN_APPSECRET";

    /**
     * 阿里云短信签名
     */
    public static String SMS_ALIYUN_SIGNNAME = "SMS_ALIYUN_SIGNNAME";

    /**
     * 聚合数据
     */
    public static String SMS_JUHESHUJU_APPKEY = "SMS_JUHESHUJU_APPKEY";

    /**
     * 凌凯通信 corpID
     */
    public static String SMS_LINKAI_CORPID = "SMS_LINKAI_CORPID";

    /**
     * 凌凯通信 pwd
     */
    public static String SMS_LINKAI_PWD = "SMS_LINKAI_PWD";

    /**
     * 凌凯 签名
     */
    public static String SMS_LINKAI_SIGNATURE= "SMS_LINKAI_SIGNATURE";

    /**
     * 美联 username
     */
    public static String SMS_MEILIAN_USERNAME = "SMS_MEILIAN_USERNAME";

    /**
     * 美联 pwd
     */
    public static String SMS_MEILIAN_PASSWORD = "SMS_MEILIAN_PASSWORD";

    /**
     * 美联 appkey
     */
    public static String SMS_MEILIAN_APPKEY = "SMS_MEILIAN_APPKEY";

    /**
     * 美联 签名
     */
    public static String SMS_MEILIAN_SIGNATURE= "SMS_MEILIAN_SIGNATURE";

    /**
     * 网健 uid
     */
    public static String SMS_WANGJIAN_UID = "SMS_WANGJIAN_UID";

    /**
     * 网健 key
     */
    public static String SMS_WANGJIAN_KEY = "SMS_WANGJIAN_KEY";

    /**
     * 网健 签名
     */
    public static String SMS_WANGJIAN_SIGNATURE= "SMS_MEILIAN_SIGNATURE";


    /**
     * 云片 key
     */
    public static String SMS_YUNPIAN_APPKEY = "SMS_YUNPIAN_APPKEY";

    /**
     * 容联云通讯 key
     */
    public static String SMS_RONGLIANYUN_ACCOUNTSID = "SMS_RONGLIANYUN_ACCOUNTSID";

    /**
     * 容联云通讯 key
     */
    public static String SMS_RONGLIANYUN_ACCOUNTTOKEN = "SMS_RONGLIANYUN_ACCOUNTTOKEN";

    /**
     * 容联云通讯 key
     */
    public static String SMS_RONGLIANYUN_APPID = "SMS_RONGLIANYUN_APPID";

    /**
     * 云信使 uid
     */
    public static String SMS_YUNXINSHI_UID = "SMS_YUNXINSHI_UID";

    /**
     * 云信使 appKey
     */
    public static String SMS_YUNXINSHI_KEY = "SMS_YUNXINSHI_KEY";


    /** 聚合数据短信模板 -- 公用 */
    public static String SMS_JUHESHUJU_TEMP_COMMUNAL = "SMS_JUHESHUJU_TEMP_COMMUNAL";

    /** 云片短信模板 -- 公用 */
    public static String SMS_YUNPIAN_TEMP_COMMUNAL = "SMS_YUNPIAN_TEMP_COMMUNAL";

    /** 容联云短信模板 -- 公用 */
    public static String SMS_RONGLIANYUN_TEMP_COMMUNA = "SMS_RONGLIANYUN_TEMP_COMMUNA";

    /** 云信使短信模板 -- 公用 */
    public static String SMS_YUNXINSHI_TEMP_COMMUNAL = "SMS_YUNXINSHI_TEMP_COMMUNAL";

    /** 阿里云短信模版 -- 注册用户 */
    public static String SMS_ALIYUN_TEMP_REGISTER = "SMS_ALIYUN_TEMP_REGISTER";

    /** 阿里云短信模版 -- 修改密码 */
    public static String SMS_ALIYUN_TEMP_UPDATEPASSWORD = "SMS_ALIYUN_TEMP_UPDATEPASSWORD";

    /** 阿里云短信模版 -- 修改手机号 */
    public static String SMS_ALIYUN_TEMP_UPDATEPHONE = "SMS_ALIYUN_TEMP_UPDATEPHONE";


    public static final String DEF_UTF8 = "UTF-8";

    public static final String DEF_GBK = "gbk";

    public static final String DEF_GET = "GET";

    public static final String DEF_POST = "POST";

    /**
     * 容联云成功状态码
     */
    public static final String RLY_SUCCESS = "000000";

    /**
     * 容联云状态码
     */
    public static final String RLY_STATUS_CODE = "statusCode";

    /**
     * 云片成功状态码
     */
    public static final Integer YUNPIAN_SUCCESS = 0;

    /**
     * 云信使成功状态码
     */
    public static final Integer YUNXINSHI_SUCCESS = 100;

    /**
     * 云信使状态码
     */
    public static final String YUNXINSHI_STATUS_CODE = "stat";

    /**
     * 聚合数据成功状态码
     */
    public static final Integer JUHE_SUCCESS = 0;

    /**
     * 聚合数据状态码
     */
    public static final String JUHE_STATUS_CODE = "error_code";

    /**
     * 美联状态码
     */
    public static final String MEILIAN_STATUS_CODE = "success";

    /**
     * 产品名称:云通信短信API产品,开发者无需替换
     */
    public static final String PRODUCT = "DySMSapi";

    /**
     * 产品域名,开发者无需替换
     */
    public static final String DOMAIN = "dySMSapi.aliyuncs.com";

    /** 极光定义应用的Key */
    public static String SMS_JIGUAN_APPKEY = "SMS_JIGUAN_APPKEY";

    /** 极光定义应用的签名 */
    public static String SMS_JIGUAN_APPSECRET = "SMS_JIGUAN_APPSECRET";

    /** 极光 定义短信签名Id */
    public static String SMS_JIGUAN_SIGN_ID = "SMS_JIGUAN_SIGN_ID";

    /** 定义短信模版 -- 注册用户 */
    public static String SMS_JIGUAN_REGISTER_TEMPLATE = "SMS_JIGUAN_REGISTER_TEMPLATE";

    /** 定义短信模版 -- 修改密码 */
    public static String SMS_JIGUAN_UPDATE_PASSWORD_TEMPLATE = "SMS_JIGUAN_UPDATE_PASSWORD_TEMPLATE";

    /** 定义短信模版 -- 修改手机号 */
    public static String SMS_JIGUAN_UPDATEP_PHONE_TEMPLATE = "SMS_JIGUAN_UPDATEP_PHONE_TEMPLATE";

    /** 云之讯定义应用的Key */
    public static String SMS_YUNZHIXUN_APPID = "SMS_YUNZHIXUN_APPID";

    /** 云之讯定义应用的SID */
    public static String SMS_YUNZHIXUN_SID = "SMS_YUNZHIXUN_SID";

    /** 云之讯定义应用的TOKEN */
    public static String SMS_YUNZHIXUN_TOKEN = "SMS_YUNZHIXUN_TOKEN";

    /** 云之讯定义短信模版 -- 注册用户 */
    public static String SMS_YUNZHIXUN_REGISTER_TEMPLATE = "SMS_YUNZHIXUN_REGISTER_TEMPLATE";

    /** 云之讯定义短信模版 -- 修改密码 */
    public static String SMS_YUNZHIXUN_UPDATE_PASSWORD_TEMPLATE = "SMS_YUNZHIXUN_UPDATE_PASSWORD_TEMPLATE";

    /** 云之讯定义短信模版 -- 修改手机号 */
    public static String SMS_YUNZHIXUN_UPDATEP_PHONE_TEMPLATE = "SMS_YUNZHIXUN_UPDATEP_PHONE_TEMPLATE";


//    public Map<String, String> getPropertyMap(List<String> propertys) {
//        Map<String, String> codesMap = sysParamService.getParamValueByCode(propertys);
//        if (codesMap.isEmpty()) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "初始化获取sms系统参数异常");
//
//        }
//        return codesMap;
//    }

//    public void initSmsParam() {
//        RedisBusinessUtil.init(redisTemplate);
//        try {
//            List<String> codes = new LinkedList<>();
//            Object smsConfig = Class.forName("com.likes.common.config.SmsConfig").newInstance();
//            Field[] fields = SmsConfig.class.getDeclaredFields();
//            for (int j = 0; j < fields.length; j++) {
//                String property = fields[j].getName();
//                if (property.contains("SMS_")) {
//                    codes.add(property);
//                }
//            }
//            Map<String, String> codesMap = getPropertyMap(codes);
//            BeanUtils.copyProperties(smsConfig, codesMap);
//            logger.info("初始化sms系统参数完成 值为：{}", codesMap);
//        } catch (Exception e) {
//            logger.error("初始化SMS短信参数全家变量出差 {}", e);
//        }
//    }
//
//
//    public static void modifySmsglobal(SysParameter info) {
//        try {
//            Object s2 = Class.forName("com.likes.common.config.SmsConfig").newInstance();
//            BeanUtils.copyProperty(s2, info.getSysparamcode(), info.getSysparamvalue());
//        } catch (Exception e) {
//            logger.error("更新SMS短信参数全家变量出差：sysparamcode {}", info.getSysparamcode());
//        }
//
//    }

    public String getKey(String key) {
        SysParameter sp = sysParamService.getByCode(key);
        if (sp == null || StringUtils.isEmpty(sp.getSysparamvalue())) {
          return  null;
        }
        return sp.getSysparamvalue();
    }

}
