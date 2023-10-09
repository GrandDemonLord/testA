/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.common.enums.loophole;

/**
* 漏洞类型
*
* @author poet_wei
* @date 2023-09-06
*/
public enum LoopholeTypeEnum {
    XSS_ATTACK("1", "跨站脚本攻击（XSS）"),
    CSRF_VULNERABILITY("2", "跨站请求伪造（CSRF）"),
    SQL_INJECTION_ATTACK("3", "SQL注入攻击"),
    CODE_INJECTION_VULNERABILITY("4", "代码注入漏洞"),
    FILE_INCLUSION_VULNERABILITY("5", "文件包含漏洞"),
    UNAUTHENTICATED_ACCESS("6", "未经身份验证的访问"),
    REMOTE_CODE_EXECUTION("7", "远程代码执行（RCE）"),
    DENIAL_OF_SERVICE("8", "拒绝服务（DoS）");
    private String id;
    private String name;
    LoopholeTypeEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getLoopholeTypeIdByName(String name) {
        for (LoopholeTypeEnum assetsLevel : LoopholeTypeEnum.values()) {
            if (assetsLevel.name.equals(name)) {
                return assetsLevel.id;
            }
        }
        return null;
    }

    public static String getLoopholeTypeNameById(String id) {
        for (LoopholeTypeEnum assetsLevel : LoopholeTypeEnum.values()) {
            if (assetsLevel.id.equals(id)) {
                return assetsLevel.name;
            }
        }
        return null;
    }
}
