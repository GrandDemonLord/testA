/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.controller.safework;

import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.application.safework.SafeWorkApplication;
import com.kunyu.assets.safety.domain.model.safework.SwTaskWorkOrderDo;
import com.kunyu.assets.safety.domain.model.safework.SwTaskWorkOrderSearchDo;
import com.kunyu.assets.safety.interfaces.converter.safework.SwDtoDoConverter;
import com.kunyu.assets.safety.interfaces.dto.safework.SwTaskWorkOrderDto;
import com.kunyu.assets.safety.interfaces.dto.safework.SwTaskWorkOrderSearchDto;
import com.kunyu.assets.safety.interfaces.valid.safework.SwTaskWorkOrderAddValid;
import com.kunyu.assets.safety.interfaces.valid.safework.SwTaskWorkOrderUpdateValid;
import com.kunyu.common.exception.PlatformException;
import com.kunyu.common.result.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 任务工单接口层
 *
 * @author yangliu
 * @date 2023-09-08
 */
@RestController
@RequestMapping("/safework/taskworkorder")
public class SwTaskWorkOrderController {
    private final String userId = "yuxi-guanliyuan";

    private final String unitId = "5301";
    private final String unitName = "玉溪";
    private final String deptId = "5301";
    private final String deptName = "玉溪";

    @Autowired
    private SafeWorkApplication safeWorkApplication;

    /**
     * 任务工单清单
     *
     * @param searchDto searchDto
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @return PageInfo SwTaskWorkOrderDo
     * @author yangliu
     * @date 2023/9/7
     */
    @RequestMapping(path = "/list", method = RequestMethod.POST)
    public ApiResponse<PageInfo<SwTaskWorkOrderDo>> taskWorkOrderList(@RequestBody SwTaskWorkOrderSearchDto searchDto,
                                                                 @RequestParam(defaultValue = "1") int pageNum,
                                                                 @RequestParam(defaultValue = "10") int pageSize) {
        if (pageSize > 200) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "每页条数不能超过200。");
        }
        SwTaskWorkOrderSearchDo searchDo = SwDtoDoConverter.INSTANCE.getTaskWorkOrderSearchDo(searchDto);
        // todo 从ThreadLocalUtil内拿
        searchDo.setUserId(userId);
        return ApiResponse.success(safeWorkApplication.taskWorkOrderList(searchDo, pageNum, pageSize));
    }

    /**
     * 新增任务工单
     *
     * @param taskWorkOrderDto taskWorkOrderDto
     * @return ApiResponse SwTaskWorkOrderDo
     * @author yangliu
     * @date 2023/9/7
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ApiResponse<SwTaskWorkOrderDo> addTaskWorkOrder(@Validated(SwTaskWorkOrderAddValid.class) @RequestBody SwTaskWorkOrderDto taskWorkOrderDto) {
        if (ObjectUtils.isEmpty(taskWorkOrderDto.getAssetName()) && CollectionUtils.isEmpty(taskWorkOrderDto.getAssetIds())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "涉及资产不能为空。");
        }
        SwTaskWorkOrderDo taskWorkOrderDo = SwDtoDoConverter.INSTANCE.getTaskWorkOrderDo(taskWorkOrderDto);
        // todo 从ThreadLocalUtil内拿
        taskWorkOrderDo.setUnitId(unitId);
        taskWorkOrderDo.setUnitName(unitName);
        taskWorkOrderDo.setDeptId(deptId);
        taskWorkOrderDo.setDeptName(deptName);
        taskWorkOrderDo.setCreateBy(userId);
        return ApiResponse.success(safeWorkApplication.addTaskWorkOrder(taskWorkOrderDo));
    }

    /**
     * 修改任务工单
     *
     * @param taskWorkOrderDto taskWorkOrderDto
     * @return ApiResponse boolean
     * @author yangliu
     * @date 2023/9/7
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ApiResponse<Boolean> updateTaskWorkOrder(@Validated(SwTaskWorkOrderUpdateValid.class) @RequestBody SwTaskWorkOrderDto taskWorkOrderDto) {
        SwTaskWorkOrderDo taskWorkOrderDo = SwDtoDoConverter.INSTANCE.getTaskWorkOrderDo(taskWorkOrderDto);
        // todo 从ThreadLocalUtil内拿
        taskWorkOrderDo.setUnitId(unitId);
        taskWorkOrderDo.setUnitName(unitName);
        taskWorkOrderDo.setDeptId(deptId);
        taskWorkOrderDo.setDeptName(deptName);
        taskWorkOrderDo.setCreateBy(userId);
        return ApiResponse.success(safeWorkApplication.updateTaskWorkOrder(taskWorkOrderDo));
    }

    /**
     * 删除任务工单
     *
     * @param id
     * @return ApiResponse boolean
     * @author yangliu
     * @date 2023/9/7
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ApiResponse<Boolean> deleteTaskWorkOrderById(@PathVariable Integer id) {
        // todo 从ThreadLocalUtil内拿
        return ApiResponse.success(safeWorkApplication.deleteTaskWorkOrderById(id, userId));
    }
}
