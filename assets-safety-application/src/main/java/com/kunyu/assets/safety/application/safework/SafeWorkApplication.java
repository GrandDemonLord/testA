/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.application.safework;

import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.domain.model.safework.SwTaskTypeDo;
import com.kunyu.assets.safety.domain.model.safework.SwTaskTypeSearchDo;
import com.kunyu.assets.safety.domain.model.safework.SwTaskWorkOrderDo;
import com.kunyu.assets.safety.domain.model.safework.SwTaskWorkOrderSearchDo;
import com.kunyu.assets.safety.domain.service.safework.SafeWorkDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 安全工作管理应用层
 *
 * @author yangliu
 * @date 2023-09-07
 */
@Service
public class SafeWorkApplication {

    @Autowired
    private SafeWorkDomain safeWorkDomain;

    /**
     * 任务类型清单
     *
     * @param searchDo searchDo
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @return PageInfo SwTaskTypeDo
     * @author yangliu
     * @date 2023/9/7
     */
    public PageInfo<SwTaskTypeDo> taskTypeList(SwTaskTypeSearchDo searchDo, int pageNum, int pageSize) {
        return safeWorkDomain.taskTypeList(searchDo, pageNum, pageSize);
    }

    /**
     * 新增任务类型
     *
     * @param taskTypeDo taskTypeDo
     * @return SwTaskTypeDo SwTaskTypeDo
     * @author yangliu
     * @date 2023/9/7
     */
    public SwTaskTypeDo addTaskType(SwTaskTypeDo taskTypeDo) {
        return safeWorkDomain.addTaskType(taskTypeDo);
    }

    /**
     * 修改任务类型
     *
     * @param taskTypeDo taskTypeDo
     * @return boolean
     * @author yangliu
     * @date 2023/9/7
     */
    public boolean updateTaskType(SwTaskTypeDo taskTypeDo) {
        return safeWorkDomain.updateTaskType(taskTypeDo);
    }

    /**
     * 删除任务类型
     *
     * @param id id
     * @param userId userId
     * @return boolean
     * @author yangliu
     * @date 2023/9/7
     */
    public boolean deleteTaskTypeById(Integer id, String userId) {
        return safeWorkDomain.deleteTaskTypeById(id, userId);
    }

    /**
     * 任务工单清单
     *
     * @param searchDo searchDo
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @return PageInfo SwTaskWorkOrderDo
     * @author yangliu
     * @date 2023/9/7
     */
    public PageInfo<SwTaskWorkOrderDo> taskWorkOrderList(SwTaskWorkOrderSearchDo searchDo, int pageNum, int pageSize) {
        return safeWorkDomain.taskWorkOrderList(searchDo, pageNum, pageSize);
    }

    /**
     * 新增任务工单
     *
     * @param taskWorkOrderDo taskWorkOrderDo
     * @return ApiResponse SwTaskWorkOrderDo
     * @author yangliu
     * @date 2023/9/7
     */
    public SwTaskWorkOrderDo addTaskWorkOrder(SwTaskWorkOrderDo taskWorkOrderDo) {
        return safeWorkDomain.addTaskWorkOrder(taskWorkOrderDo);
    }

    /**
     * 修改任务工单
     *
     * @param taskWorkOrderDo taskWorkOrderDo
     * @return ApiResponse boolean
     * @author yangliu
     * @date 2023/9/7
     */
    public boolean updateTaskWorkOrder(SwTaskWorkOrderDo taskWorkOrderDo) {
        return safeWorkDomain.updateTaskWorkOrder(taskWorkOrderDo);
    }

    /**
     * 删除任务工单
     *
     * @param id
     * @return ApiResponse boolean
     * @author yangliu
     * @date 2023/9/7
     */
    public boolean deleteTaskWorkOrderById(Integer id, String userId) {
        return safeWorkDomain.deleteTaskWorkOrderById(id, userId);
    }

    public PageInfo<SwTaskWorkOrderDo> taskWorkOrderPendingList(SwTaskWorkOrderSearchDo searchDo, int pageNum, int pageSize) {
        return safeWorkDomain.taskWorkOrderPendingList(searchDo, pageNum, pageSize);
    }

    public boolean taskWorkOrderApproved(Integer id, String userId, String userName) {
        return safeWorkDomain.taskWorkOrderApproved(id, userId, userName);
    }

    public boolean taskWorkOrderRejection(Integer id, String userId, String userName) {
        return safeWorkDomain.taskWorkOrderRejection(id, userId, userName);
    }
}
