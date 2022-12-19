package com.likes.common.model.dto.member;

/**
 * @ClassName: UserOperationLogDTO
 * @Description: 用户操作日志参数
 * @author: HANS
 * @date: 2019年11月5日 下午1:57:17
 */
public class UserOperationLogDTO {
    // 方法名
    private String methodName;
    // 被操作用户ID
    private Integer userId;
    // 批量操作记录
    private String manyIds;
    // 操作类型
    private Integer operationType;
    // 操作人名称
    private String operationName;
    // 操作人ID
    private Integer operationId;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public String getManyIds() {
        return manyIds;
    }

    public void setManyIds(String manyIds) {
        this.manyIds = manyIds;
    }

}

