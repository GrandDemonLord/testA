/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.common.enums.loophole;

/**
* 漏洞等级
*
* @author poet_wei
* @date 2023-09-06
*/
public enum LoopholeLevelEnum {
    LOW("1", "低危"),
    MEDIUM("2", "中危"),
    HIGH("3", "高危");

    private String id;
    private String name;

    LoopholeLevelEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getLoopholeLevelIdByName(String name) {
        for (LoopholeLevelEnum assetsLevel : LoopholeLevelEnum.values()) {
            if (assetsLevel.name.equals(name)) {
                return assetsLevel.id;
            }
        }
        return null;
    }

    public static String getLoopholeLevelNameById(String id) {
        for (LoopholeLevelEnum assetsLevel : LoopholeLevelEnum.values()) {
            if (assetsLevel.id.equals(id)) {
                return assetsLevel.name;
            }
        }
        return null;
    }
}
