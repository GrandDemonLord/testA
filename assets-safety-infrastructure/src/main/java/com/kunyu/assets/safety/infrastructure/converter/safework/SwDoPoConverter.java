/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.infrastructure.converter.safework;

import com.kunyu.assets.safety.domain.model.safework.SwTaskTypeDo;
import com.kunyu.assets.safety.domain.model.safework.SwTaskWorkOrderAssetsDo;
import com.kunyu.assets.safety.domain.model.safework.SwTaskWorkOrderDo;
import com.kunyu.assets.safety.infrastructure.po.safework.SwTaskTypePo;
import com.kunyu.assets.safety.infrastructure.po.safework.SwTaskWorkOrderAssetsPo;
import com.kunyu.assets.safety.infrastructure.po.safework.SwTaskWorkOrderPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 安全工作 Do Po 互转
 *
 * @author yangliu
 * @date 2023-09-07
 */
@Mapper
public interface SwDoPoConverter {
    SwDoPoConverter INSTANCE = Mappers.getMapper(SwDoPoConverter.class);

    SwTaskTypePo getSwTaskTypePo(SwTaskTypeDo swTaskTypeDo);

    SwTaskTypeDo getSwTaskTypeDo(SwTaskTypePo swTaskTypePo);

    List<SwTaskTypeDo> getSwTaskTypeDos(List<SwTaskTypePo> swTaskTypePo);


    SwTaskWorkOrderPo getSwTaskWorkOrderPo(SwTaskWorkOrderDo swTaskWorkOrderDo);

    SwTaskWorkOrderDo getSwTaskWorkOrderDo(SwTaskWorkOrderPo swTaskWorkOrderPo);

    List<SwTaskWorkOrderDo> getSwTaskWorkOrderDos(List<SwTaskWorkOrderPo> swTaskWorkOrderPos);

    List<SwTaskWorkOrderAssetsPo> getSwTaskWorkOrderAssetsPos(List<SwTaskWorkOrderAssetsDo> swTaskWorkAssetsOrderDos);

    List<SwTaskWorkOrderAssetsDo> getSwTaskWorkOrderAssetsDos(List<SwTaskWorkOrderAssetsPo> swTaskWorkOrderAssetsPos);
}
