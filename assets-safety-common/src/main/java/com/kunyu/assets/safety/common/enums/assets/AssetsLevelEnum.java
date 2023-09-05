/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.common.enums.assets;

/**
* 资产等级
*
* @author yangliu
* @date 2023-09-04
*/
public enum AssetsLevelEnum {
    CORE("1","核心"),
    IMPORTANT("2","重要"),
    MARGINAL("3","边缘");

    private String id;
    private String name;

    AssetsLevelEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getAssetsLevelIdByName(String name) {
        for (AssetsLevelEnum assetsLevel : AssetsLevelEnum.values()) {
            if (assetsLevel.name.equals(name)) {
                return assetsLevel.id;
            }
        }
        return null;
    }

    public static String getAssetsLevelNameById(String id) {
        for (AssetsLevelEnum assetsLevel : AssetsLevelEnum.values()) {
            if (assetsLevel.id.equals(id)) {
                return assetsLevel.name;
            }
        }
        return null;
    }
}
