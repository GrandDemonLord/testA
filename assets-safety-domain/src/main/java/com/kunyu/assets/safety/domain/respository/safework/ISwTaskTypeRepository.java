/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.respository.safework;

import com.kunyu.assets.safety.domain.model.safework.SwTaskTypeDo;
import com.kunyu.assets.safety.domain.model.safework.SwTaskTypeSearchDo;
import com.kunyu.assets.safety.domain.model.safework.SwTaskWorkOrderDo;
import com.kunyu.assets.safety.domain.model.safework.SwTaskWorkOrderSearchDo;

import java.util.List;

/**
 * 任务类型仓储接口
 *
 * @author yangliu
 * @date 2023-09-07
 */
public interface ISwTaskTypeRepository {
    /**
     * 任务类型清单
     *
     * @param searchDo searchDo
     * @return List SwTaskTypeDo
     * @author yangliu
     * @date 2023/9/7
     */
    List<SwTaskTypeDo> taskTypeList(SwTaskTypeSearchDo searchDo);

    /**
     * 新增任务类型
     *
     * @param taskTypeDo taskTypeDo
     * @return SwTaskTypeDo SwTaskTypeDo
     * @author yangliu
     * @date 2023/9/7
     */
    SwTaskTypeDo addTaskType(SwTaskTypeDo taskTypeDo);

    /**
     * 修改任务类型
     *
     * @param taskTypeDo taskTypeDo
     * @return boolean
     * @author yangliu
     * @date 2023/9/7
     */
    boolean updateTaskType(SwTaskTypeDo taskTypeDo);

    /**
     * 删除任务类型
     *
     * @param id id
     * @param status status
     * @param userId userId
     * @return boolean
     * @author yangliu
     * @date 2023/9/7
     */
    boolean deleteTaskTypeById(Integer id, int status, String userId);

    /**
     * 通过id查询任务类型
     *
     * @param id id
     * @return taskTypeDo taskTypeDo
     * @author yangliu
     * @date 2023/9/7
     */
    SwTaskTypeDo getTaskTypeById(Integer id);
}
