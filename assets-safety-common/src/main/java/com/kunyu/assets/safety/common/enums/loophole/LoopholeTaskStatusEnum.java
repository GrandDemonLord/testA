/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.common.enums.loophole;

/**
 * 任务状态
 *
 * @author poet_wei
 * @date 2023-09-06
 */
public enum LoopholeTaskStatusEnum {


    FIXING( "fixing"), // 待修复
    FIXED("fixed"), // 已修复
    IGNORED("ignored"); // 已忽略

    private String code;

    LoopholeTaskStatusEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
