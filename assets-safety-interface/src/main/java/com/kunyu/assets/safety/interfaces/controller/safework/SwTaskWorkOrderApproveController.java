/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.controller.safework;

import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.application.safework.SafeWorkApplication;
import com.kunyu.assets.safety.domain.model.safework.SwTaskWorkOrderDo;
import com.kunyu.assets.safety.domain.model.safework.SwTaskWorkOrderSearchDo;
import com.kunyu.assets.safety.interfaces.converter.safework.SwDtoDoConverter;
import com.kunyu.assets.safety.interfaces.dto.safework.SwTaskWorkOrderSearchDto;
import com.kunyu.common.exception.PlatformException;
import com.kunyu.common.result.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 任务工单审批接口层
 *
 * @author yangliu
 * @date 2023-09-11
 */
@RestController
@RequestMapping("/safework/taskworkorder/approve")
public class SwTaskWorkOrderApproveController {
    private final String userId = "zong-guanliyuan";

    private final String userName = "总管理员";

    private final String unitId = "5301";
    private final String unitName = "玉溪";
    private final String deptId = "5301";
    private final String deptName = "玉溪";

    @Autowired
    private SafeWorkApplication safeWorkApplication;

    @RequestMapping(path = "/pendingList", method = RequestMethod.POST)
    public ApiResponse<PageInfo<SwTaskWorkOrderDo>> taskWorkOrderPendingList(@RequestBody SwTaskWorkOrderSearchDto searchDto,
                                                                      @RequestParam(defaultValue = "1") int pageNum,
                                                                      @RequestParam(defaultValue = "10") int pageSize) {
        if (pageSize > 200) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "每页条数不能超过200。");
        }
        SwTaskWorkOrderSearchDo searchDo = SwDtoDoConverter.INSTANCE.getTaskWorkOrderSearchDo(searchDto);
        // todo 从ThreadLocalUtil内拿
        searchDo.setUserId(userId);
        return ApiResponse.success(safeWorkApplication.taskWorkOrderPendingList(searchDo, pageNum, pageSize));
    }

    @RequestMapping(path = "/approved/{id}", method = RequestMethod.GET)
    public ApiResponse<Boolean> approved(@PathVariable Integer id) {
        // todo 从ThreadLocalUtil内拿
        return ApiResponse.success(safeWorkApplication.taskWorkOrderApproved(id, userId, userName));
    }

    public ApiResponse<Boolean> rejection(@PathVariable Integer id) {
        // todo 从ThreadLocalUtil内拿
        return ApiResponse.success(safeWorkApplication.taskWorkOrderRejection(id, userId, userName));
    }
}
