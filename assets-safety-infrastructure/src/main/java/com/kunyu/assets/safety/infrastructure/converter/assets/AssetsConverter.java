/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.infrastructure.converter.assets;

import com.kunyu.assets.safety.domain.model.assets.AsAssetsDo;
import com.kunyu.assets.safety.infrastructure.po.assets.AsAssetsPo;
import org.springframework.beans.BeanUtils;

/**
* 资产数据对象转换类
*
* @author yangliu
* @date 2023-08-25
*/
public class AssetsConverter {
    /**
     * 资产数据po转do
     *
     * @param asAssetsPo 资产po
     * @return asAssetsDo 资产do
     * @author yangliu
     * @date 2023/8/25
     */
    public static AsAssetsDo convertPoToDo(AsAssetsPo asAssetsPo) {
        AsAssetsDo asAssetsDo = new AsAssetsDo();
        BeanUtils.copyProperties(asAssetsPo, asAssetsDo);
        return asAssetsDo;
    }
}
