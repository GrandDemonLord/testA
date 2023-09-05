/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.common.enums.common;

/**
* 审批状态枚举类
*
* @author yangliu
* @date 2023-08-25
*/
public enum ApproveStatusEnum {
    PENDING("pending"), // 待审批
    APPROVED("approved"), // 审批通过
    REJECTION("rejection"); // 审批驳回

    private String code;

    ApproveStatusEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
