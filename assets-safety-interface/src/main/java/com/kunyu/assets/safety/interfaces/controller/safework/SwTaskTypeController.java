/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.controller.safework;

import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.application.safework.SafeWorkApplication;
import com.kunyu.assets.safety.domain.model.safework.SwTaskTypeDo;
import com.kunyu.assets.safety.domain.model.safework.SwTaskTypeSearchDo;
import com.kunyu.assets.safety.interfaces.converter.safework.SwDtoDoConverter;
import com.kunyu.assets.safety.interfaces.dto.safework.SwTaskTypeDto;
import com.kunyu.assets.safety.interfaces.dto.safework.SwTaskTypeSearchDto;
import com.kunyu.assets.safety.interfaces.valid.safework.SwTaskTypeAddValid;
import com.kunyu.assets.safety.interfaces.valid.safework.SwTaskTypeUpdateValid;
import com.kunyu.common.exception.PlatformException;
import com.kunyu.common.result.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 任务类型接口层
 *
 * @author yangliu
 * @date 2023-09-07
 */
@RestController
@RequestMapping("/safework/tasktype")
public class SwTaskTypeController {

    private final String userId = "yuxi-guanliyuan";

    private final String groupId = "5301";

    private final String groupName = "玉溪";

    @Autowired
    private SafeWorkApplication safeWorkApplication;

    /**
     * 任务类型清单
     *
     * @param searchDto searchDto
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @return PageInfo SwTaskTypeDo
     * @author yangliu
     * @date 2023/9/7
     */
    @RequestMapping(path = "/list", method = RequestMethod.POST)
    public ApiResponse<PageInfo<SwTaskTypeDo>> taskTypeList(@RequestBody SwTaskTypeSearchDto searchDto,
                                                            @RequestParam(defaultValue = "1") int pageNum,
                                                            @RequestParam(defaultValue = "10") int pageSize) {
        if (pageSize > 200) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "每页条数不能超过200。");
        }
        SwTaskTypeSearchDo searchDo = SwDtoDoConverter.INSTANCE.getTaskTypeSearchDo(searchDto);
        // todo 从ThreadLocalUtil内拿
        searchDo.setUserId(userId);
        return ApiResponse.success(safeWorkApplication.taskTypeList(searchDo, pageNum, pageSize));
    }

    /**
     * 新增任务类型
     *
     * @param taskTypeDto taskTypeDto
     * @return ApiResponse SwTaskTypeDo
     * @author yangliu
     * @date 2023/9/7
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ApiResponse<SwTaskTypeDo> addTaskType(@Validated(SwTaskTypeAddValid.class) @RequestBody SwTaskTypeDto taskTypeDto) {
        SwTaskTypeDo taskTypeDo = SwDtoDoConverter.INSTANCE.getTaskTypeDo(taskTypeDto);
        // todo 从ThreadLocalUtil内拿
        taskTypeDo.setUnitId(groupId);
        taskTypeDo.setUnitName(groupName);
        // taskTypeDo.setCreateBy(userId);
        return ApiResponse.success(safeWorkApplication.addTaskType(taskTypeDo));
    }

    /**
     * 修改任务类型
     *
     * @param taskTypeDto taskTypeDto
     * @return ApiResponse boolean
     * @author yangliu
     * @date 2023/9/7
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ApiResponse<Boolean> updateTaskType(@Validated(SwTaskTypeUpdateValid.class) @RequestBody SwTaskTypeDto taskTypeDto) {
        SwTaskTypeDo taskTypeDo = SwDtoDoConverter.INSTANCE.getTaskTypeDo(taskTypeDto);
        // todo 从ThreadLocalUtil内拿
        taskTypeDo.setUpdateBy(userId);
        return ApiResponse.success(safeWorkApplication.updateTaskType(taskTypeDo));
    }

    /**
     * 删除任务类型
     *
     * @param id
     * @return ApiResponse boolean
     * @author yangliu
     * @date 2023/9/7
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ApiResponse<Boolean> deleteTaskTypeById(@PathVariable Integer id) {
        // todo 从ThreadLocalUtil内拿
        return ApiResponse.success(safeWorkApplication.deleteTaskTypeById(id, userId));
    }
}
