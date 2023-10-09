/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.common.enums.supplychain;

/**
 * @author poet_wei
 * @description 评估状态枚举类
 * @date 2023-09-22 17:56
 */
public enum AssessmentStatusEnum {
    NOTASSESSED("NotAssessed"), // 未评估
    ASSESSED("Assessed"); // 已评估

    private String code;

    AssessmentStatusEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
