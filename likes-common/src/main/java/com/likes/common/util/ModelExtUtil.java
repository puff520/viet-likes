package com.likes.common.util;
import com.likes.common.mybatis.entity.MemBaseinfo;

import java.math.BigDecimal;

public class ModelExtUtil {

    public static void changeWithdrawalRemainder(MemBaseinfo appMember, BigDecimal money) {
        changeWithdrawalRemainder(appMember, money, true);
    }

    public static void changeWithdrawalRemainder(MemBaseinfo appMember, BigDecimal money, boolean subtract) {
        if (null == money || null == appMember || null == appMember.getWithdrawalRemainder()
                || appMember.getWithdrawalRemainder().compareTo(BigDecimal.ZERO) == 0) {
            return;
        }
        money = money.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : money;
        if (subtract) {
            appMember.getWithdrawalRemainder().subtract(money);
        } else {
            appMember.getWithdrawalRemainder().add(money);
        }
    }
}
