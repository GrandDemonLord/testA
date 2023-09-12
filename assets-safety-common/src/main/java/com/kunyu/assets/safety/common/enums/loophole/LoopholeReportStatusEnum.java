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
public enum LoopholeReportStatusEnum {


    FIXING( "fixing"), // 待开始
    UPLOAD("upload"), // 待上传
    SUBMISSION("submission"), // 待结束
    FINISHED("finished"); // 已结束

    private String code;

    LoopholeReportStatusEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
