/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.common.enums.assets;

/**
* @description
*
* @author yangliu
* @date 2023-09-04
*/
public class Test {
    public static void main(String[] args) {
        System.out.println(AssetsLevelEnum.getAssetsLevelIdByName("核心"));
        System.out.println(AssetsLevelEnum.getAssetsLevelNameById("2"));

        System.out.println(AssetsTypeEnum.getAssetsTypeIdByName("服务器"));
        System.out.println(AssetsTypeEnum.getAssetsTypeNameById("1"));
    }
}
