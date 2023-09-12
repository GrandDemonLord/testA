/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.common.enums.common;

/**
* 工单类型枚举类
*
* @author yangliu
* @date 2023-08-25
*/
public enum DataTypeEnum {
    ASLISTING("as_isting", "【资产管理】资产上架申请"),
    ASDELIST("as_delist", "【资产管理】资产下架申请"),
    SWTASKWORKORDER("sw_taskWorkOrder", "【安全工作】任务工单申请"),
    LOUPLOAD("lo_Upload", "【漏洞管理】漏洞信息上传"),
    LOTASKWORKORDER("lo_taskWorkOrder", "【漏洞管理】漏洞修复工单"),
    LOTASKWORKORDERAPPROVAL("lo_taskWorkOrderApproval", "【漏洞管理】漏洞修复工单审批");

    private String code;
    private String name;

    DataTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getCodeByName(String name) {
        for (DataTypeEnum statusEnum : DataTypeEnum.values()) {
            if (statusEnum.name.equals(name)) {
                return statusEnum.code;
            }
        }
        return null;
    }

    public static String getNameByCode(String code) {
        for (DataTypeEnum statusEnum : DataTypeEnum.values()) {
            if (statusEnum.code.equals(code)) {
                return statusEnum.name;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
