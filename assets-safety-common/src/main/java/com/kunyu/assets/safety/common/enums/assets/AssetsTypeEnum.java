/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.common.enums.assets;

/**
* 资产类型
*
* @author yangliu
* @date 2023-09-04
*/
public enum AssetsTypeEnum {
    SERVER("1", "服务器"),
    FIREWALL("2", "防火墙");

    private String id;
    private String name;

    AssetsTypeEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getAssetsTypeIdByName(String name) {
        for (AssetsTypeEnum assetsType : AssetsTypeEnum.values()) {
            if (assetsType.name.equals(name)) {
                return assetsType.id;
            }
        }
        return null;
    }

    public static String getAssetsTypeNameById(String id) {
        for (AssetsTypeEnum assetsType : AssetsTypeEnum.values()) {
            if (assetsType.id.equals(id)) {
                return assetsType.name;
            }
        }
        return null;
    }
}
