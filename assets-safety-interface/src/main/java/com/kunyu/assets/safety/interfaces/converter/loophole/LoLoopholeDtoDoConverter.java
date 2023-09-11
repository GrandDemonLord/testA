/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.converter.loophole;

import com.kunyu.assets.safety.domain.model.loophole.LoLoopholeDo;
import com.kunyu.assets.safety.domain.model.loophole.LoLoopholeSearchDo;
import com.kunyu.assets.safety.interfaces.dto.loophole.LoLoopholeDto;
import com.kunyu.assets.safety.interfaces.dto.loophole.LoLoopholeSearchDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author poet_wei
 * @description
 * @date 2023-09-06 11:50
 */
@Mapper
public interface LoLoopholeDtoDoConverter {
    LoLoopholeDtoDoConverter INSTANCE = Mappers.getMapper(LoLoopholeDtoDoConverter.class);

    LoLoopholeSearchDo getLoopholeSearchDo(LoLoopholeSearchDto loopholeSearchDto);

    LoLoopholeDo getLoopholeDo(LoLoopholeDto loopholeDto);
}
