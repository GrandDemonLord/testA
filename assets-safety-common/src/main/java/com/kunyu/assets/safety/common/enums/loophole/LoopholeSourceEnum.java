/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.common.enums.loophole;

/**
* 漏洞来源
*
* @author poet_wei
* @date 2023-09-06
*/
public enum LoopholeSourceEnum {
    INTERNAL_SECURITY_INSPECTION("1", "内部巡检"),
    EXTERNAL_SECURITY_AUDIT("2", "外部安服");

    private String id;
    private String name;

    LoopholeSourceEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getLoopholeTypeIdByName(String name) {
        for (LoopholeSourceEnum assetsLevel : LoopholeSourceEnum.values()) {
            if (assetsLevel.name.equals(name)) {
                return assetsLevel.id;
            }
        }
        return null;
    }

    public static String getLoopholeTypeNameById(String id) {
        for (LoopholeSourceEnum assetsLevel : LoopholeSourceEnum.values()) {
            if (assetsLevel.id.equals(id)) {
                return assetsLevel.name;
            }
        }
        return null;
    }
}
