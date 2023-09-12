/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.controller.common;

import com.kunyu.assets.safety.application.common.ApApproveHistoryApplication;
import com.kunyu.assets.safety.domain.model.common.ApApproveHistoryDo;
import com.kunyu.assets.safety.interfaces.converter.common.ApDtoDoConverter;
import com.kunyu.assets.safety.interfaces.dto.common.ApApproveHistorySearchDto;
import com.kunyu.common.result.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 审批历史接口层
 *
 * @author yangliu
 * @date 2023-09-12
 */
@RestController
@RequestMapping("/approve/history")
public class ApApproveHistoryController {

    @Autowired
    private ApApproveHistoryApplication apApproveHistoryApplication;

    /**
     * 审批历史查询
     *
     * @param searchDto searchDto
     * @return List ApApproveHistoryDo
     * @author yangliu
     * @date 2023/9/12
     */
    @RequestMapping(path = "/list", method = RequestMethod.POST)
    public ApiResponse<List<ApApproveHistoryDo>> historyList(@RequestBody ApApproveHistorySearchDto searchDto) {
        return ApiResponse.success(apApproveHistoryApplication.historyList(ApDtoDoConverter.INSTANCE.historySearchDo(searchDto)));
    }
}
