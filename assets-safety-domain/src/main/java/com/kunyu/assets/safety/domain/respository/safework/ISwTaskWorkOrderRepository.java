/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.respository.safework;

import com.kunyu.assets.safety.domain.model.assets.AsAssetsApproveBuilder;
import com.kunyu.assets.safety.domain.model.safework.SwTaskWorkOrderDo;
import com.kunyu.assets.safety.domain.model.safework.SwTaskWorkOrderSearchDo;

import java.util.List;

/**
 * 任务工单仓储接口
 *
 * @author yangliu
 * @date 2023-09-09
 */
public interface ISwTaskWorkOrderRepository {

    /**
     * 任务工单清单
     *
     * @param searchDo searchDo
     * @return List SwTaskWorkOrderDo
     * @author yangliu
     * @date 2023/9/7
     */
    List<SwTaskWorkOrderDo> taskWorkOrderList(SwTaskWorkOrderSearchDo searchDo);

    /**
     * 新增任务工单
     *
     * @param taskWorkOrderDo taskWorkOrderDo
     * @return ApiResponse SwTaskWorkOrderDo
     * @author yangliu
     * @date 2023/9/7
     */
    SwTaskWorkOrderDo addTaskWorkOrder(SwTaskWorkOrderDo taskWorkOrderDo);

    /**
     * 修改任务工单
     *
     * @param taskWorkOrderDo taskWorkOrderDo
     * @return boolean
     * @author yangliu
     * @date 2023/9/7
     */
    boolean updateTaskWorkOrder(SwTaskWorkOrderDo taskWorkOrderDo);

    /**
     * 删除任务工单
     *
     * @param id id
     * @param status status
     * @param userId userId
     * @return ApiResponse boolean
     * @author yangliu
     * @date 2023/9/7
     */
    boolean deleteTaskWorkOrderById(Integer id, int status, String userId);

    /**
     * 通过id查询任务工单
     *
     * @param id
     * @return SwTaskWorkOrderDo
     * @author yangliu
     * @date 2023/9/9
     */
    SwTaskWorkOrderDo getTaskWorkOrderById(Integer id);

    List<SwTaskWorkOrderDo> taskWorkOrderPendingList(SwTaskWorkOrderSearchDo searchDo);

    boolean taskWorkOrderApproved(AsAssetsApproveBuilder build);

    boolean taskWorkOrderRejection(AsAssetsApproveBuilder build);
}
