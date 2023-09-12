/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.infrastructure.dao.safework;

import com.kunyu.assets.safety.domain.model.assets.AsAssetsApproveBuilder;
import com.kunyu.assets.safety.domain.model.safework.SwTaskWorkOrderSearchDo;
import com.kunyu.assets.safety.infrastructure.po.safework.SwTaskWorkOrderPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 任务工单持久层接口
 *
 * @author yangliu
 * @date 2023-09-09
 */
public interface SwTaskWorkOrderMapper {

    /**
     * 任务工单清单
     *
     * @param searchDo searchDo
     * @return List SwTaskWorkOrderPo
     * @author yangliu
     * @date 2023/9/7
     */
    List<SwTaskWorkOrderPo> taskWorkOrderList(SwTaskWorkOrderSearchDo searchDo);

    /**
     * 新增任务工单
     *
     * @param taskWorkOrderPo taskWorkOrderPo
     * @return int int
     * @author yangliu
     * @date 2023/9/7
     */
    int addTaskWorkOrder(SwTaskWorkOrderPo taskWorkOrderPo);

    /**
     * 修改任务工单
     *
     * @param swTaskWorkOrderPo taskWorkOrderDo
     * @return int
     * @author yangliu
     * @date 2023/9/7
     */
    int updateTaskWorkOrder(SwTaskWorkOrderPo swTaskWorkOrderPo);

    /**
     * 删除任务工单
     *
     * @param id
     * @return int
     * @author yangliu
     * @date 2023/9/7
     */
    int deleteTaskWorkOrderById(@Param("id") Integer id, @Param("status") int status, @Param("userId") String userId);

    /**
     * 通过id查询任务工单
     *
     * @param id
     * @return SwTaskWorkOrderPo
     * @author yangliu
     * @date 2023/9/9
     */
    SwTaskWorkOrderPo getTaskWorkOrderById(Integer id);

    List<SwTaskWorkOrderPo> taskWorkOrderPendingList(SwTaskWorkOrderSearchDo searchDo);

    int taskWorkOrderApproved(AsAssetsApproveBuilder build);

    int taskWorkOrderRejection(AsAssetsApproveBuilder build);
}
