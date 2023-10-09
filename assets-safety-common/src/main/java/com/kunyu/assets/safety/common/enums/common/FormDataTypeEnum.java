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
public enum FormDataTypeEnum {
    ASLISTING("as_listing", "【资产管理】资产上架申请", "asset"),
    ASDELIST("as_delist", "【资产管理】资产下架申请", "asset"),

    SWTASKWORKORDER("sw_taskWorkOrder", "【安全工作】任务工单申请", "taskWorkOrder"),
    SWTASKWORKORDERPROCESSED("sw_taskWorkOrderProcessed", "【安全工作】任务工单处置申请", "taskWorkOrder"),

    LOUPLOADAPPROVAL("lo_UploadApproval", "【漏洞管理】漏洞信息上传审批","loopholeTaskWorkOrder"),
    LOTASKWORKORDERAPPROVAL("lo_taskWorkOrderApproval", "【漏洞审批】漏洞修复工单审批","loopholeTaskWorkOrder"),

    LEGALWORKORDER("lm_legalworkorder","【法律法规】法律法规检查工单","legalworkorder");
    private String code;
    private String name;
    private String module;

    FormDataTypeEnum(String code, String name, String module) {
        this.code = code;
        this.name = name;
        this.module = module;
    }

    public static String getCodeByName(String name) {
        for (FormDataTypeEnum statusEnum : FormDataTypeEnum.values()) {
            if (statusEnum.name.equals(name)) {
                return statusEnum.code;
            }
        }
        return null;
    }

    public static String getNameByCode(String code) {
        for (FormDataTypeEnum statusEnum : FormDataTypeEnum.values()) {
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

    public String getModule() {
        return module;
    }
}
