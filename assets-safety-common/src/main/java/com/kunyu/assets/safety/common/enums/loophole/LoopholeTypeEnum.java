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
    SQL_INJECTION_ATTACK("2", "SQL注入"),
    CSRF_VULNERABILITY("3", "跨站请求伪造（CSRF）");

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
