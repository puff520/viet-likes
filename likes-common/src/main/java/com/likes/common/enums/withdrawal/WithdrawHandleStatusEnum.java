package com.likes.common.enums.withdrawal;

import java.util.ArrayList;
import java.util.List;

/**
 * 提现处理状态
 */
public enum WithdrawHandleStatusEnum {
    PRE_HANDING(1, "待处理"),
    RETRIEVED(2, "已捡取"),
    REJECT(3, "拒绝"),
    SUCCESS(4, "成功"),
    VERIFICATION(5, "核对中"),
    BATCH_HANDING(6, "批量处理中"),
    FAILURE(7, "代付中");

    private final int value;
    private final String desc;

    WithdrawHandleStatusEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 获取我负责的提现页面展示状态
     *
     * @return
     */
    public static List<WithdrawHandleStatusEnum> getOwnerPageStatus() {
        List<WithdrawHandleStatusEnum> list = new ArrayList<>();
        list.add(RETRIEVED);
//        list.add(REJECT);
//        list.add(SUCCESS);
        list.add(VERIFICATION);
        return list;
    }

    /**
     * 获取我负责的提现页面展示状态
     *
     * @return
     */
    public static List<WithdrawHandleStatusEnum> getAllPageStatus() {
        List<WithdrawHandleStatusEnum> list = new ArrayList<>();
        list.add(RETRIEVED);
        list.add(REJECT);
        list.add(SUCCESS);
        list.add(VERIFICATION);
        return list;
    }

    /**
     * 根据状态判断是否可以执行
     *
     * @param preStatus 当前状态
     * @param status    要更新的状态
     * @return
     */
    public static boolean canBeUpdate(Integer preStatus, Integer status) {
        if (null == status) {
            return false;
        }

        if (FAILURE.getValue() == preStatus) {
            return RETRIEVED.getValue() == preStatus;
        }

        if (RETRIEVED.getValue() == status) {
            return PRE_HANDING.getValue() == preStatus;
        }

        //return RETRIEVED.getValue() == preStatus;
        if (VERIFICATION.getValue() == status || BATCH_HANDING.getValue() == status || FAILURE.getValue() == status) {
            return RETRIEVED.getValue() == preStatus;
        }

        //其它状态都必须是已捡取,处理中
        return RETRIEVED.getValue() == preStatus || VERIFICATION.getValue() == preStatus || BATCH_HANDING.getValue() == preStatus || FAILURE.getValue() == preStatus;
    }

    public static String getFailInfoByStatus(Integer preStatus, String opName) {
        if (REJECT.getValue() == preStatus) {
            return "该记录已被操作员 \"" + opName + "\" 拒绝";
        }
        if (SUCCESS.getValue() == preStatus) {
            return "该记录已被操作员 \"" + opName + "\" 出款";
        }
        if (VERIFICATION.getValue() == preStatus) {
            return "该记录已被操作员 \"" + opName + "\" 导出审核中";
        }
        if (BATCH_HANDING.getValue() == preStatus) {
            return "该记录已被操作员 \"" + opName + "\" 批量操作中";
        }
        if (FAILURE.getValue() == preStatus) {
            return "该记录已被操作员 \"" + opName + "\" 代付操作中";
        }

        return null;
    }

    public static String getHandleStatusByValue(Integer status) {
        if (null == status || status <= 0) {
            return "未知状态";
        }
        WithdrawHandleStatusEnum[] statusEnums = WithdrawHandleStatusEnum.values();
        for (WithdrawHandleStatusEnum statusEnum : statusEnums) {
            if (statusEnum.getValue() == status) {
                return statusEnum.getDesc();
            }
        }
        return "未知状态";
    }

    public static String getStatusColorByValue(Integer status) {
        if (null == status || status <= 0) {
            return "";
        }
        if (VERIFICATION.getValue() == status || BATCH_HANDING.getValue() == status) {
            return "c-yellow";
        }
        if (RETRIEVED.getValue() == status) {
            return "c-orange";
        }
//        if (REJECT.getValue() == status || FAILURE.getValue() == status) {
        if (REJECT.getValue() == status) {
            return "c-red";
        }
        if (SUCCESS.getValue() == status) {
            return "c-green";
        }
        return "";
    }

    /**
     * 是否为待处理状态
     *
     * @param value
     * @return
     */
    public static boolean isPreHanding(int value) {
        return PRE_HANDING.getValue() == value;
    }

    /**
     * 是否为捡取中状态
     *
     * @param value
     * @return
     */
    public static boolean isHanding(int value) {
        return RETRIEVED.getValue() == value;
    }

    /**
     * 是否为拒绝状态
     *
     * @param value
     * @return
     */
    public static boolean isReject(int value) {
        return REJECT.getValue() == value;
    }

    /**
     * 是否为成功状态
     *
     * @param value
     * @return
     */
    public static boolean isSuccess(int value) {
        return SUCCESS.getValue() == value;
    }

    /**
     * 是否为核对中状态
     *
     * @param value
     * @return
     */
    public static boolean isVerification(int value) {
        return VERIFICATION.getValue() == value;
    }

    /**
     * 是否为批量处理状态
     *
     * @param value
     * @return
     */
    public static boolean isBatchHanding(int value) {
        return BATCH_HANDING.getValue() == value;
    }

    /**
     * 是否为失败状态
     *
     * @param value
     * @return
     */
    /*public static boolean isFailure(int value) {
        return FAILURE.getValue() == value;
    }*/

    /**
     * 捡取状态
     *
     * @return
     */
    public static String retrieveStatus() {
        return String.valueOf(PRE_HANDING.getValue());
    }

    /**
     * 我负责的订单状态
     *
     * @return
     */
    public static String retrieveMeStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append(RETRIEVED.getValue()).append(",");
        sb.append(VERIFICATION.getValue()).append(",");
        sb.append(FAILURE.getValue()).append(",");
        sb.append(BATCH_HANDING.getValue());
        return sb.toString();
    }

    /**
     * 已完成状态
     *
     * @param showPickuped
     * @return
     */
    public static String completeStatus(Boolean showPickuped) {
        StringBuilder sb = new StringBuilder();
        if (showPickuped) {
            sb.append(RETRIEVED.getValue()).append(",");
        }
        sb.append(REJECT.getValue()).append(",");
        sb.append(SUCCESS.getValue()).append(",");
        sb.append(VERIFICATION.getValue()).append(",");
        sb.append(BATCH_HANDING.getValue()).append(",");
        sb.append(FAILURE.getValue());
        return sb.toString();
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
