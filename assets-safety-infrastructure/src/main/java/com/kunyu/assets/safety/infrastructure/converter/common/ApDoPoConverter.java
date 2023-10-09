/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.infrastructure.converter.common;

import com.kunyu.assets.safety.domain.model.common.ApApproveHistoryDo;
import com.kunyu.assets.safety.domain.model.loophole.ReportFilesDo;
import com.kunyu.assets.safety.infrastructure.po.common.ApApproveHistoryPo;
import com.kunyu.assets.safety.infrastructure.po.loophole.ReportFilesPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 审批记录 Do Po 互转
 *
 * @author yangliu
 * @date 2023-09-12
 */
@Mapper
public interface ApDoPoConverter {
    ApDoPoConverter INSTANCE = Mappers.getMapper(ApDoPoConverter.class);

    List<ApApproveHistoryPo> getApApproveHistoryPos(List<ApApproveHistoryDo> historyDo);

    List<ApApproveHistoryDo> getApApproveHistoryDos(List<ApApproveHistoryPo> historyPo);

    ApApproveHistoryPo getApApproveHistoryPo(ApApproveHistoryDo historyDo);

    ApApproveHistoryDo getApApproveHistoryDo(ApApproveHistoryPo historyPo);

    ReportFilesPo getFilesPo(ReportFilesDo reportFilesDo);
}
