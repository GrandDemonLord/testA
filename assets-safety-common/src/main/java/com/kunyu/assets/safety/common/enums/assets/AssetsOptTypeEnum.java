/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.common.enums.assets;

/**
* 资产操作类型枚举类
*
* @author yangliu
* @date 2023-08-25
*/
public enum AssetsOptTypeEnum {
    LISTING("listing"), // 上架申请
    DELIST("delist"); // 下架申请

    private String code;

    AssetsOptTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
