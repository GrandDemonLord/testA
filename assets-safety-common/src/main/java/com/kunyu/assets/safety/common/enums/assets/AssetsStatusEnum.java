/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.common.enums.assets;

/**
* 资产状态枚举类
*
* @author yangliu
* @date 2023-08-25
*/
public enum AssetsStatusEnum {
    VALID("valid"), // 使用中（上架申请审批通过的）
    INVALID("inValid"); // 已下架（下架申请审批通过的）

    private String code;

    AssetsStatusEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
