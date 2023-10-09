/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.common.enums.loophole;

/**
* 流程环节枚举类
*
* @author poet_wei
* @date 2023-09-14
*/
public enum InstanceNodeOfLoopholeEnum {
    APPLY("提交漏洞信息"),
    ADMIN("管理员审批"),
    PROCESSED("处置人处理");

    private String name;

    InstanceNodeOfLoopholeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
