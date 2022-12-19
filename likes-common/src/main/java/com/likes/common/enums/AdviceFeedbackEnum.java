/**
 *
 */
package com.likes.common.enums;

/**
 * @author Administrator
 */
public enum AdviceFeedbackEnum {

    FEEDBACK(1, "问题反馈"),
    PROPOSAL(2, "投诉建议");

    private final Integer value;
    private final String name;

    AdviceFeedbackEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
