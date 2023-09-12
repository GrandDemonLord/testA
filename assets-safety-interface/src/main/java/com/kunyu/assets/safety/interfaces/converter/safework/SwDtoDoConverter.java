/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.converter.safework;

import com.kunyu.assets.safety.domain.model.safework.SwTaskTypeDo;
import com.kunyu.assets.safety.domain.model.safework.SwTaskTypeSearchDo;
import com.kunyu.assets.safety.domain.model.safework.SwTaskWorkOrderDo;
import com.kunyu.assets.safety.domain.model.safework.SwTaskWorkOrderSearchDo;
import com.kunyu.assets.safety.interfaces.dto.safework.SwTaskTypeDto;
import com.kunyu.assets.safety.interfaces.dto.safework.SwTaskTypeSearchDto;
import com.kunyu.assets.safety.interfaces.dto.safework.SwTaskWorkOrderDto;
import com.kunyu.assets.safety.interfaces.dto.safework.SwTaskWorkOrderSearchDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 安全工作 Dto Do 互转
 *
 * @author yangliu
 * @date 2023-09-07
 */
@Mapper
public interface SwDtoDoConverter {
    SwDtoDoConverter INSTANCE = Mappers.getMapper(SwDtoDoConverter.class);

    SwTaskTypeDo getTaskTypeDo(SwTaskTypeDto taskTypeDto);

    SwTaskTypeSearchDo getTaskTypeSearchDo(SwTaskTypeSearchDto taskTypeSearchDto);

    SwTaskWorkOrderDo getTaskWorkOrderDo(SwTaskWorkOrderDto taskWorkOrderDto);

    SwTaskWorkOrderSearchDo getTaskWorkOrderSearchDo(SwTaskWorkOrderSearchDto taskWorkOrderSearchDto);
}
