/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.infrastructure.dao.safework;

import com.kunyu.assets.safety.domain.model.safework.SwTaskTypeSearchDo;
import com.kunyu.assets.safety.domain.model.safework.SwTaskWorkOrderSearchDo;
import com.kunyu.assets.safety.infrastructure.po.safework.SwTaskTypePo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 任务类型持久层接口
 *
 * @author yangliu
 * @date 2023-09-07
 */

public interface SwTaskTypeMapper {
    /**
     * 任务类型清单
     *
     * @param searchDo searchDo
     * @return List SwTaskTypePo
     * @author yangliu
     * @date 2023/9/7
     */
    List<SwTaskTypePo> taskTypeList(SwTaskTypeSearchDo searchDo);

    /**
     * 新增任务类型
     *
     * @param taskTypePo taskTypePo
     * @return int int
     * @author yangliu
     * @date 2023/9/7
     */
    int addTaskType(SwTaskTypePo taskTypePo);

    /**
     * 修改任务类型
     *
     * @param swTaskTypePo swTaskTypePo
     * @return int int
     * @author yangliu
     * @date 2023/9/7
     */
    int updateTaskType(SwTaskTypePo swTaskTypePo);

    /**
     * 删除任务类型
     *
     * @param id id
     * @param userId userId
     * @return int
     * @author yangliu
     * @date 2023/9/7
     */
    int deleteTaskTypeById(@Param("id") Integer id, @Param("status") int status, @Param("userId") String userId);

    SwTaskTypePo getTaskTypeById(Integer id);
}
