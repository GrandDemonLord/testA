/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.converter.common;

import com.kunyu.assets.safety.domain.model.common.ApApproveHistorySearchDo;
import com.kunyu.assets.safety.domain.model.loophole.ReportFilesDo;
import com.kunyu.assets.safety.interfaces.dto.common.ApApproveHistorySearchDto;
import com.kunyu.assets.safety.interfaces.dto.common.FilesDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 审批记录 Dto Do 互转
 *
 * @author yangliu
 * @date 2023-09-12
 */
@Mapper
public interface ApDtoDoConverter {
    ApDtoDoConverter INSTANCE = Mappers.getMapper(ApDtoDoConverter.class);

    ApApproveHistorySearchDo historySearchDo(ApApproveHistorySearchDto historySearchDto);

    ReportFilesDo getFilesDo(FilesDto filesDto);

}
