/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.converter.supplychain;

import com.kunyu.assets.safety.domain.model.supplychain.ScSupplyChainDo;
import com.kunyu.assets.safety.domain.model.supplychain.ScSupplyChainSelectDo;
import com.kunyu.assets.safety.interfaces.dto.supplychain.ScSupplyChainDto;
import com.kunyu.assets.safety.interfaces.dto.supplychain.ScSupplyChainSelectDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author poet_wei
 * @description 供应链Dto转换Do
 * @date 2023-09-22 16:24
 */
@Mapper
public interface ScDtoDoConverter {
    ScDtoDoConverter INSTANCE = Mappers.getMapper(ScDtoDoConverter.class);

    ScSupplyChainDo getScSupplyChainDo(ScSupplyChainDto scSupplyChaindto);

    ScSupplyChainSelectDo getScSupplyChainSelectDo(ScSupplyChainSelectDto scSupplyChainSelectDto);
}
