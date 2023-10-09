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

    NOTSTARTED("notStarted"), // 未开始 （用户创建漏洞信息初始化状态）
    UPLOADING("uploading"), // 上传中 （管理员审批漏洞信息状态）
    END( "end"); // 结束 （管理员审批漏洞信息状态）

    private String code;

    LoopholeReportStatusEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
