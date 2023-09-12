/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.common.enums.assets;

/**
* 流程环节枚举类
*
* @author yangliu
* @date 2023-09-04
*/
public enum InstanceNodeOfAssetsEnum {
    APPLY("发起申请"),
    ADMIN("管理员审批"),
    PROCESSED("处置人处理");

    private String name;

    InstanceNodeOfAssetsEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
