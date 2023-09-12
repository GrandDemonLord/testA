/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.common.enums.common;

/**
 * 审批结果枚举类
 *
 * @author yangliu
 * @date 2023-09-12
 */
public enum ApproveResultEnum {
    APPLY("已提交"),
    APPROVERD("通过"),
    REJECTION("驳回");

    private String name;

    ApproveResultEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
