/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.converter.assets;

import com.kunyu.assets.safety.domain.model.assets.AsAssetsDo;
import com.kunyu.assets.safety.domain.model.assets.AsAssetsSearchDo;
import com.kunyu.assets.safety.interfaces.dto.assets.AsAssetsDto;
import com.kunyu.assets.safety.interfaces.dto.assets.AsAssetsImportDto;
import com.kunyu.assets.safety.interfaces.dto.assets.AsAssetsSearchDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* @description
*
* @author yangliu
* @date 2023-08-27
*/
@Mapper
public interface DtoDoConverter {
    DtoDoConverter INSTANCE = Mappers.getMapper(DtoDoConverter.class);

    AsAssetsDo getAsAssetsDo(AsAssetsDto asAssetsDto);

    List<AsAssetsDo> getAsAssetsDos(List<AsAssetsImportDto> asAssetsImportDtos);

    AsAssetsSearchDo getAssetsSearchDo(AsAssetsSearchDto assetsSearchDto);
}
