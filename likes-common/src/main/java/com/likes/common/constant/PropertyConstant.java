package com.likes.common.constant;

import java.util.HashMap;
import java.util.Map;

public class PropertyConstant {

    /**
     * 是否删除
     *
     * @author Jack
     */
    public interface IfDelete {
        int No = 0; // 否  (不删除,有效)
        int YES = 1; // 是 (删除,无效)

    }

    /**
     * 数据库是否删除
     *
     * @author Jack
     */
    public interface DBIsDelete {
        Integer No = 0; // 否  (不删除,有效)
        Integer YES = 1; // 是 (删除,无效)

    }

    /**
     * 是否
     *
     * @author Jack
     */
    public interface YesNo {
        int No = 0; // 否
        int YES = 1; // 是

    }

    /**
     * 是否
     *
     * @author Jack
     */
    public interface AdmireYesNo {
        int No = 1; // 取消点赞
        int YES = 0; // 点赞

    }

    /**
     * 支付结果类支付过程
     *
     * @author Jack
     */
    public interface PaymentResultJetpayStauts {
        int LAUNCH = 1; // 发起充值
        int SUCCESS = 2; // 充值成功
        int FAIL = 3; // 充值失败
        int UNCONFIRMED = 4; // 未确定
    }

    public static Map<Integer, Object> PaymentResultJetpayStautsMap = new HashMap<Integer, Object>();

    static {
        PaymentResultJetpayStautsMap.put(PaymentResultJetpayStauts.LAUNCH, "发起充值");
        PaymentResultJetpayStautsMap.put(PaymentResultJetpayStauts.SUCCESS, "支付中");
        PaymentResultJetpayStautsMap.put(PaymentResultJetpayStauts.FAIL, "支付成功");
        PaymentResultJetpayStautsMap.put(PaymentResultJetpayStauts.UNCONFIRMED, "未确定");
    }

    public static Map<Integer, Object> PaymentRequestJetpayPayTypeMap = new HashMap<Integer, Object>();

    static {
        PaymentRequestJetpayPayTypeMap.put(PaymentResultJetpayStauts.LAUNCH, "发起充值");
        PaymentRequestJetpayPayTypeMap.put(PaymentResultJetpayStauts.SUCCESS, "支付中");
        PaymentRequestJetpayPayTypeMap.put(PaymentResultJetpayStauts.FAIL, "支付成功");
        PaymentRequestJetpayPayTypeMap.put(PaymentResultJetpayStauts.UNCONFIRMED, "未确定");
    }

    /**
     * 支付请求类状态
     *
     * @author Jack
     */
    public interface PaymentRequestJetpayStatus {
        int LAUNCH = 1; // 发起充值
        int SUCCESS = 2; // 充值成功
        int FAIL = 3; // 充值失败
        int UNCONFIRMED = 4; // 未确定
    }

    public static Map<Integer, Object> PaymentRequestJetpayStatusMap = new HashMap<Integer, Object>();

    static {
        PaymentRequestJetpayStatusMap.put(PaymentRequestJetpayStatus.LAUNCH, "发起充值");
        PaymentRequestJetpayStatusMap.put(PaymentRequestJetpayStatus.SUCCESS, "充值成功");
        PaymentRequestJetpayStatusMap.put(PaymentRequestJetpayStatus.FAIL, "充值失败");
        PaymentRequestJetpayStatusMap.put(PaymentRequestJetpayStatus.UNCONFIRMED, "未确定");
    }

    /**
     * 支付结果类支付状态
     *
     * @author Jack
     */
    public interface PaymentResultJetpayPaymentState {
        int WAIT = 1; // 待支付
        int SUCCESS = 2; // 支付成功
        int FAIL = 3; // 支付失败
        int DOING = 4; // 订单冻结
        int Unknown = 5; // 未知
    }

    public static Map<Integer, Object> PaymentResultJetpayPaymentStateMap = new HashMap<Integer, Object>();

    static {
        PaymentResultJetpayPaymentStateMap.put(PaymentResultJetpayPaymentState.WAIT, "待支付");
        PaymentResultJetpayPaymentStateMap.put(PaymentResultJetpayPaymentState.SUCCESS, "支付中");
        PaymentResultJetpayPaymentStateMap.put(PaymentResultJetpayPaymentState.FAIL, "支付成功");
        PaymentResultJetpayPaymentStateMap.put(PaymentResultJetpayPaymentState.DOING, "订单冻结");
        PaymentResultJetpayPaymentStateMap.put(PaymentResultJetpayPaymentState.Unknown, "未知");
    }


    /**
     * 支付第三方: (rx) 支付结果
     *
     * @author Jack
     */
    public interface PaymentResultJetpayStauts_RX {
        int LAUNCH = 1; // 发起充值
        int SUCCESS = 2; // 充值成功
        int FAIL = 3; // 充值失败
        int UNCONFIRMED = 4; // 未确定
    }

    /**
     * 支付第三方对应关系: (rx) 支付结果
     */
    public static Map<Integer, Object> PaymentResultJetpayStauts_RXMap = new HashMap<Integer, Object>();

    static {
        PaymentResultJetpayStautsMap.put(PaymentResultJetpayStauts_RX.LAUNCH, PaymentResultJetpayStauts.LAUNCH);
        PaymentResultJetpayStautsMap.put(PaymentResultJetpayStauts_RX.SUCCESS, PaymentResultJetpayStauts.SUCCESS);
        PaymentResultJetpayStautsMap.put(PaymentResultJetpayStauts_RX.FAIL, PaymentResultJetpayStauts.FAIL);
        PaymentResultJetpayStautsMap.put(PaymentResultJetpayStauts_RX.UNCONFIRMED, PaymentResultJetpayStauts.UNCONFIRMED);
    }

    /**
     * 支付第三方: (duobao) 支付结果
     *
     * @author Jack
     */
    public interface PaymentResultJetpayStauts_DuoBao {
        int SUCCESS = 0; //支付成功
        int INVALID = 1; // 商户订单号无效
        int SINGERROR = 2; // 签名错误
        int REQUESTINVALID = 3; //请求参数无效
        int DOING = -1; // 用户还未完成支付或者支付失败
    }

    /**
     * 支付第三方对应关系: (duobao) 支付结果
     */
    public static Map<Integer, Object> PaymentResultJetpayStauts_DuoBaoMap = new HashMap<Integer, Object>();

    static {
        PaymentResultJetpayStauts_DuoBaoMap.put(PaymentResultJetpayStauts_DuoBao.SUCCESS, PaymentResultJetpayStauts.SUCCESS);
        PaymentResultJetpayStauts_DuoBaoMap.put(PaymentResultJetpayStauts_DuoBao.INVALID, PaymentResultJetpayStauts.FAIL);
        PaymentResultJetpayStauts_DuoBaoMap.put(PaymentResultJetpayStauts_DuoBao.SINGERROR, PaymentResultJetpayStauts.FAIL);
        PaymentResultJetpayStauts_DuoBaoMap.put(PaymentResultJetpayStauts_DuoBao.REQUESTINVALID, PaymentResultJetpayStauts.FAIL);
        PaymentResultJetpayStauts_DuoBaoMap.put(PaymentResultJetpayStauts_DuoBao.DOING, PaymentResultJetpayStauts.FAIL);

    }

    /**
     * 支付第三方对应关系: (duobao) 支付状态 ,注:因为该第三方只返回一个状态值，所以这里和支付结果有一个值
     */
    public static Map<Integer, Object> PaymentResultJetpayPaymentStatus_DuoBaoMap = new HashMap<Integer, Object>();

    static {
        PaymentResultJetpayPaymentStatus_DuoBaoMap.put(PaymentResultJetpayStauts_DuoBao.SUCCESS, PaymentResultJetpayPaymentState.SUCCESS);
        PaymentResultJetpayPaymentStatus_DuoBaoMap.put(PaymentResultJetpayStauts_DuoBao.INVALID, PaymentResultJetpayPaymentState.FAIL);
        PaymentResultJetpayPaymentStatus_DuoBaoMap.put(PaymentResultJetpayStauts_DuoBao.SINGERROR, PaymentResultJetpayPaymentState.FAIL);
        PaymentResultJetpayPaymentStatus_DuoBaoMap.put(PaymentResultJetpayStauts_DuoBao.REQUESTINVALID, PaymentResultJetpayPaymentState.FAIL);
        PaymentResultJetpayPaymentStatus_DuoBaoMap.put(PaymentResultJetpayStauts_DuoBao.DOING, PaymentResultJetpayPaymentState.FAIL);

    }


    /**
     * 心水推荐:数据来源
     *
     * @author Jack
     */
    public interface LhcXsRecommendDataSources {
        int APPORDINARY = 1; // APP用户
        int WEBORDINARY = 2; // WEB普通用户
        int ADMIN = 3; // 后台用户
    }

    public static Map<Integer, Object> LhcXsRecommendDataSourcesMap = new HashMap<Integer, Object>();

    static {
        LhcXsRecommendDataSourcesMap.put(LhcXsRecommendDataSources.APPORDINARY, "APP用户");
        LhcXsRecommendDataSourcesMap.put(LhcXsRecommendDataSources.WEBORDINARY, "WEB用户");
        LhcXsRecommendDataSourcesMap.put(LhcXsRecommendDataSources.ADMIN, "后台用户");
    }

    /**
     * 心水推荐:审核状态
     *
     * @author Jack
     */
    public interface LhcXsRecommendAuditStatus {
        int NOTYET = 1; // 未审核
        int PASS = 2; // 审核通过
        int REFUSE = 3;//审核拒绝
    }

    public static Map<Integer, Object> LhcXsRecommendAuditStatusMap = new HashMap<Integer, Object>();

    static {
        LhcXsRecommendAuditStatusMap.put(LhcXsRecommendAuditStatus.NOTYET, "未审核");
        LhcXsRecommendAuditStatusMap.put(LhcXsRecommendAuditStatus.PASS, "审核通过");
        LhcXsRecommendAuditStatusMap.put(LhcXsRecommendAuditStatus.REFUSE, "审核拒绝");
    }


    /**
     * 支付统计记录:支付状态
     *
     * @author Jack
     */
    public interface PaymentSummaryStauts {
        int SUCCESS = 1; // 充值成功
        int FAIL = 2; // 充值失败
        int WAIT = 3; // 等待支付
    }

    /**
     * 支付统计记录:支付状态
     *
     * @author Jack
     */
    public interface AppMemberUserType {
        int FORMAL = 1; // 普通用户
        int ADMIN = 2; // 后台注册
    }


    /**
     * 数据值等级:编码
     *
     * @author Jack
     */
    public interface DataValueLevelCode {
        String RECOMMEND_ADMIRE = "RECOMMEND_ADMIRE"; // "心水推荐点赞级别
    }

    public static Map<String, String> DataValueLevelCodeMap = new HashMap<String, String>();

    static {
        DataValueLevelCodeMap.put(DataValueLevelCode.RECOMMEND_ADMIRE, "心水推荐点赞级别");
    }
}
