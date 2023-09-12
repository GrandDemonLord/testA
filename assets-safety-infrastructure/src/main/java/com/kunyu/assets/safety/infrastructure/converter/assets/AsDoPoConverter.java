/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.infrastructure.converter.assets;

import com.kunyu.assets.safety.domain.model.assets.AsAssetsDo;
import com.kunyu.assets.safety.infrastructure.po.assets.AsAssetsPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 资产 Do Po 互转
*
* @author yangliu
* @date 2023-08-28
*/
@Mapper
public interface AsDoPoConverter {
    AsDoPoConverter INSTANCE = Mappers.getMapper(AsDoPoConverter.class);

    AsAssetsPo getAsAssetsPo(AsAssetsDo asAssetsDo);

    List<AsAssetsPo> getAsAssetsPos(List<AsAssetsDo> asAssetsDos);

    AsAssetsDo getAsAssetsDo(AsAssetsPo asAssetsPo);

    List<AsAssetsDo> getAsAssetsDos(List<AsAssetsPo> asAssetsPos);
}
