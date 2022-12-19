package com.likes.common.rest.money;


import com.likes.common.model.response.pay.*;
import com.likes.common.model.vo.money.PaymentJctVo;
import com.likes.common.model.vo.pay.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface PayCallBackWriteRest {


    /**
     * 金蝉子支付接口回调
     */
    String paymentJctCallback( PaymentJctVo paymentJctVo);


    /**
     * 腾飞
     */
    String tfCallback( TfCallbackVo tfCallbackVo);


    /**
     * 777支付
     */
    String sevenCallback( SevenCallbackVo sevenCallbackVo);

    /**
     * 熊猫
     */
    String xmCallback( XmCallBackVo xmCallBackVo);


    /**
     * 富豪
     */
    String fhCallback( FhCallbackVo fhCallbackVo);


    /**
     * ck
     */
    String ckCallback( CkCollbackVo ckCollbackVo);

    /**
     * 飞付
     */
    String ffCallBack( FfCollbackVo ffCollbackVo);


    /**
     * 超凡
     */
    String cfCallback( CfCallBackReq req);


    /**
     * 信达
     */
    String xdCallback( XdCallBackReq req);

    /**
     * 喜付
     */
    String xfCallback( XfCallBackReq req);

    /**
     * 亿咖
     */
    String ykCallback( YjCallBackReq req);


    /**
     * fy
     */
    String fyCallback( FyCallBackReq req);

    /**
     * 钉钉
     */
    String ddCallback( DdCallBackReq req);

    /**
     * 橘子
     */
    String jzCallback( JzCallBackReq req);

    /**
     * 渔夫
     */
    String yfCallback( YfCallBackReq req);

    /**
     * epay
     */
    String ePayCallback( ECallBackReq req);

    /**
     * 路路通
     */
    String lltCallback( LltCallBackReq lltCallbackVo);

    /**
     * 谷歌金服
     */
    String ggCallback( GgCallBackReq req);

    /**
     * ESPAY
     */
    String esCallback( EsCallBackReq req);

    /**
     * 33aa
     */
    String aaPayCallback( AaCallBackReq req);

    /**
     * 亿盛
     */
    String ysCallback( YsCallBackReq req);

    /**
     * 四方
     */
    String sfCallback( SfCallBackReq req);

    /**
     * 99支付
     */
    String jjCallback( JjCallBackReq req);

    /**
     * 灿星
     */
    String cxCallback( CxCallBackReq cxCallbackVo);

    /**
     * king支付
     */
    String kingCallback( KingCallBackReq req);

    /**
     * ACE
     */
    String acePayCallback( AceCallBackReq req);

    /**
     * 新乐福
     */
    String xlfPayCallback( XlfCallBackReq req);

    /**
     * 爱支付
     */
    String aiCallback( AiCallBackReq req);

    /**
     * AKM
     *
     * @param req
     * @return
     */
    String akmCallback( AkmCallBackReq req);

    /**
     * 默默支付
     */
    String moCallback( MoCallBackReq req);

    /**
     * zb
     */
    String zbCallback(ZbCollbackReq req);

    /**
     * 通用
     */
    String publicCallback(@PathVariable(value = "channelName") String channelName, @RequestParam("toJson") String toJson, @RequestParam("ip") String ip);

}
