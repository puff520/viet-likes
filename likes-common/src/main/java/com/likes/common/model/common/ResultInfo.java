package com.likes.common.model.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.likes.common.enums.StatusCode;
import com.likes.common.util.TimeHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回结果封装
 *
 * @param <T>
 * @author Qiang
 * @date 2017年7月27日 上午11:16:40
 */
public class ResultInfo<T> implements Serializable {

    public static final String SUCCESS_RESULT_NAME = "success";
    public static final String FAILED_RESULT_NAME = "failed";

    /**
     * 状态码
     */
    private String status;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 时间
     */
    private Long time;

    /**
     * 状态描述，类型：String
     */
    private String info;

    public static <T> ResultInfo<T> getInstance() {
        return new ResultInfo<>();
    }

    public ResultInfo() {
        this.status = StatusCode.OK.getCode();
        this.time = TimeHelper.time();
    }

    public ResultInfo(T data) {
        this();
        this.data = data;
    }

    public ResultInfo setResultInfo(StatusCode code) {
        return setResultInfo(code.getCode(), code.getValue());
    }

   /* public ResultInfo setResultInfo(BusinessException exception) {
        return setResultInfo(exception.getCode(), exception.getMessage());
    }*/

    public ResultInfo setResultInfo(String status, String msg) {
        return setResultInfo(status, msg, null);
    }

    public ResultInfo setResultInfo(String status, String msg, T data) {
        this.setStatus(status);
        this.setInfo(msg);
        this.setData(data);
        return this;
    }

    public ResultInfo<T> toOk() {
        this.setStatus(StatusCode.OK.getCode());
        this.setInfo(StatusCode.OK.getValue());
        return this;
    }

    public ResultInfo toError(String error) {
        return this.toError(error, null);
    }

    public ResultInfo toError(String error, T data) {
        this.setStatus(StatusCode.SERVER_ERROR.getCode());
        this.setInfo(error);
        this.setData(data);
        return this;
    }

    public ResultInfo toSuccess(String info) {
        return this.toSuccess(info, null);
    }

    public ResultInfo toSuccess(String info, T data) {
        this.setStatus(StatusCode.OK.getCode());
        this.setInfo(info);
        this.setData(data);
        return this;
    }

    public static <T> ResultInfo<T> ok() {
        return getInstance(StatusCode.OK);
    }

    public static <T> ResultInfo<T> ok(T data) {
        return getInstance(data, StatusCode.OK);
    }

    public static <T> ResultInfo<T> fail(String msg) {
        ResultInfo resultInfo = getInstance(null, StatusCode.FAIL);
        resultInfo.setInfo(msg);
        return resultInfo;
    }

   /* public static <T> ResultInfo<T> fail(BusinessException exception) {
        ResultInfo<T> resultInfo = new ResultInfo<>();
        resultInfo.setStatus(exception.getCode());
        resultInfo.setInfo(exception.getMessage());
        return resultInfo;
    }*/

    public static <T> ResultInfo<T> error() {
        return getInstance(null, StatusCode.SERVER_ERROR);
    }

    public static <T> ResultInfo<T> error(String msg) {
        ResultInfo<T> resultInfo = getInstance(null, StatusCode.SERVER_ERROR);
        resultInfo.setInfo(msg);
        return resultInfo;
    }


    public static <T> ResultInfo<T> error(String code,String msg) {
        ResultInfo<T> resultInfo = getInstance(null, code);
        resultInfo.setInfo(msg);
        return resultInfo;
    }


    public static <T> ResultInfo<T> timeoutError() {
        return timeoutError(null);
    }

    public static <T> ResultInfo<T> timeoutError(String msg) {
        ResultInfo<T> resultInfo = getInstance(null, StatusCode.TIMEOUT_NETWORK);
        if (null != msg && !"".equals(msg.trim())) {
            resultInfo.setInfo(msg);
        }
        return resultInfo;
    }

    public static <T> ResultInfo<T> operateRepeatError() {
        return getInstance(null, StatusCode.OPERATE_REPEAT);
    }

    public static <T> ResultInfo<T> paramsError() {
        return getInstance(null, StatusCode.PARAM_ERROR);
    }

    public static <T> ResultInfo<T> nonLoginRegisterError() {
        return getInstance(null, StatusCode.NON_LOGIN_REGISTER);
    }

    public static <T> ResultInfo<T> payError(String msg) {
        ResultInfo<T> resultInfo = getInstance(null, StatusCode.PAY_SERVER_ERROR);
        resultInfo.setInfo(msg);
        return resultInfo;
    }

    public static <T> ResultInfo<T> redPackError(String msg) {
        ResultInfo<T> resultInfo = getInstance(null, StatusCode.RED_PACK_END);
        resultInfo.setInfo(msg);
        return resultInfo;
    }

    public static <T> ResultInfo<T> notAuthError(String msg) {
        ResultInfo<T> resultInfo = getInstance(null, StatusCode.NON_AUTHORIZE);
        resultInfo.setInfo(null == msg || "".equals(msg.trim()) ? StatusCode.NON_AUTHORIZE.getValue() : msg);
        return resultInfo;
    }

    public static <T> ResultInfo<T> getInstance(String status, String info) {
        return ResultInfo.getInstance(null, status, info);
    }

    public static <T> ResultInfo<T> getInstance(T data, String status, String info) {
        ResultInfo<T> resultInfo = ResultInfo.getInstance();
        resultInfo.setData(data);
        resultInfo.setStatus(status);
        resultInfo.setInfo(info);
        return resultInfo;
    }

    public static <T> ResultInfo<T> getInstance(StatusCode code) {
        return ResultInfo.getInstance(null, code);
    }

    public static <T> ResultInfo<T> getInstance(T data, StatusCode code) {
        ResultInfo<T> resultInfo = new ResultInfo<>();
        resultInfo.setStatus(code.getCode());
        resultInfo.setInfo(code.getValue());
        resultInfo.setData(data);
        return resultInfo;
    }

    /**
     * 结果集分组
     *
     * @param resultInfos
     * @return
     */
    public static Map<String, List<ResultInfo>> groupResultInfo(List<ResultInfo> resultInfos) {
        Map<String, List<ResultInfo>> map = new HashMap<>();
        List<ResultInfo> success = new ArrayList<>();
        List<ResultInfo> failed = new ArrayList<>();
        if (null != resultInfos && resultInfos.size() > 0) {
            for (ResultInfo result : resultInfos) {
                if (result.isOk()) {
                    success.add(result);
                } else {
                    failed.add(result);
                }
            }
        }
        map.put(SUCCESS_RESULT_NAME, success);
        map.put(FAILED_RESULT_NAME, failed);
        return map;
    }

    public ResultInfo valueOf(StatusCode code) {
        this.setStatus(code.getCode());
        this.setInfo(code.getValue());
        return this;
    }

    public static boolean isOk(ResultInfo resultInfo) {
        return null != resultInfo && resultInfo.getStatus().equals(StatusCode.OK.getCode());
    }

    @JSONField(serialize = false)
    @JsonIgnore
    public boolean isOk() {
        return ResultInfo.isOk(this);
    }

    public static boolean isServerError(ResultInfo resultInfo) {
        return null != resultInfo && resultInfo.getStatus().equals(StatusCode.SERVER_ERROR.getCode());
    }

    @JSONField(serialize = false)
    @JsonIgnore
    public boolean isServerError() {
        return ResultInfo.isServerError(this);
    }

    public static boolean hasData(ResultInfo resultInfo) {
        if (!isOk(resultInfo)) {
            return false;
        }
        return null != resultInfo.getData();
    }

    public boolean hasData() {
        return ResultInfo.hasData(this);
    }

    public T getData() {
        return data;
    }

    public ResultInfo<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this);
    }

}
