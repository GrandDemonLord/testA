/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.infrastructure.converter.loophole;

import com.kunyu.assets.safety.domain.model.loophole.LoLoopholeDo;
import com.kunyu.assets.safety.infrastructure.po.loophole.LoLoopholePo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author poet_wei
 * @description
 * @date 2023-09-06 13:12
 */
@Mapper
public interface LoopholeDoPoConverter {
    LoopholeDoPoConverter INSTANCE = Mappers.getMapper(LoopholeDoPoConverter.class);

    LoLoopholePo getLoLoopholePo(LoLoopholeDo loLoopholeDo);

    List<LoLoopholeDo> getLoLoopholeDos(List<LoLoopholePo> loLoopholePos);

    LoLoopholeDo getLoLoopholeDo(LoLoopholePo LoLoopholePo);

    List<LoLoopholePo> getLoLoopholePos(List<LoLoopholeDo> loLoopholeDos);
}
