package com.likes.common.util;

/**
 * @description 短信发送工具类（示远科技短信接口）
 * @author abu
 * @date 2016.05.6
 */

import java.util.HashMap;
import java.util.Map;

public class MobileMessageUtils {

	//短信接口账户名
	public static String account = "003534";
	//短信接口账户密码
	public static String pswd = "Sd123456";
	//短信接口地址
	public static String url = "http://send.18sms.com/msg/HttpBatchSendSM";
	//短信群发接口地址
	public static String url_batch = "http://send.18sms.com/msg/HttpBatchSendSM";

	public static Map<Integer, String> resMap = new HashMap<Integer, String>();

	static{
		resMap.put(0, "发送成功");
		resMap.put(101, "无此用户");
		resMap.put(102, "密码错");
		resMap.put(103, "提交过快（提交速度超过流速限制）");
		resMap.put(104, "系统忙（因平台侧原因，暂时无法处理提交的短信）");
		resMap.put(105, "敏感短信（短信内容包含敏感词）");
		resMap.put(106, "消息长度错（>536或<=0）");
		resMap.put(107, "包含错误的手机号码");
		resMap.put(108, "手机号码个数错（群发>50000或<=0;单发>200或<=0）");
		resMap.put(109, "无发送额度（该用户可用短信数已使用完）");
		resMap.put(110, "不在发送时间内");
		resMap.put(111, "超出该账户当月发送额度限制");
		resMap.put(112, "无此产品，用户没有订购该产品");
		resMap.put(113, "extno格式错（非数字或者长度不对）");
		resMap.put(115, "自动审核驳回");
		resMap.put(116, "签名不合法，未带签名（用户必须带签名的前提下）");
		resMap.put(117, "IP地址认证错,请求调用的IP地址不是系统登记的IP地址");
		resMap.put(118, "用户没有相应的发送权限");
		resMap.put(119, "用户已过期");
		resMap.put(120, "内容不在白名单中");
	}
}