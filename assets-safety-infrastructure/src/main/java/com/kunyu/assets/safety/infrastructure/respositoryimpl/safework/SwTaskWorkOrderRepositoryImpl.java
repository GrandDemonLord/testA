/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.infrastructure.respositoryimpl.safework;

import com.kunyu.assets.safety.domain.model.assets.AsAssetsApproveBuilder;
import com.kunyu.assets.safety.domain.model.safework.SwTaskWorkOrderDo;
import com.kunyu.assets.safety.domain.model.safework.SwTaskWorkOrderSearchDo;
import com.kunyu.assets.safety.domain.respository.safework.ISwTaskWorkOrderRepository;
import com.kunyu.assets.safety.infrastructure.converter.safework.SwDoPoConverter;
import com.kunyu.assets.safety.infrastructure.dao.safework.SwTaskWorkOrderMapper;
import com.kunyu.assets.safety.infrastructure.po.safework.SwTaskWorkOrderPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 任务工单仓储接口实现类
 *
 * @author yangliu
 * @date 2023-09-09
 */
@Repository
public class SwTaskWorkOrderRepositoryImpl implements ISwTaskWorkOrderRepository {

    @Autowired
    private SwTaskWorkOrderMapper taskWorkOrderMapper;


    @Override
    public List<SwTaskWorkOrderDo> taskWorkOrderList(SwTaskWorkOrderSearchDo searchDo) {
        return SwDoPoConverter.INSTANCE.getSwTaskWorkOrderDos(taskWorkOrderMapper.taskWorkOrderList(searchDo));
    }

    @Override
    public SwTaskWorkOrderDo addTaskWorkOrder(SwTaskWorkOrderDo taskWorkOrderDo) {
        SwTaskWorkOrderPo taskWorkOrderPo = SwDoPoConverter.INSTANCE.getSwTaskWorkOrderPo(taskWorkOrderDo);
        taskWorkOrderMapper.addTaskWorkOrder(taskWorkOrderPo);
        taskWorkOrderDo.setId(taskWorkOrderPo.getId());
        return taskWorkOrderDo;
    }

    @Override
    public boolean updateTaskWorkOrder(SwTaskWorkOrderDo taskWorkOrderDo) {
        return taskWorkOrderMapper.updateTaskWorkOrder(SwDoPoConverter.INSTANCE.getSwTaskWorkOrderPo(taskWorkOrderDo)) > 0 ? true : false;
    }

    @Override
    public boolean deleteTaskWorkOrderById(Integer id, int status, String userId) {
        return taskWorkOrderMapper.deleteTaskWorkOrderById(id, status, userId) > 0 ? true : false;
    }

    @Override
    public SwTaskWorkOrderDo getTaskWorkOrderById(Integer id) {
        return SwDoPoConverter.INSTANCE.getSwTaskWorkOrderDo(taskWorkOrderMapper.getTaskWorkOrderById(id));
    }

    @Override
    public List<SwTaskWorkOrderDo> taskWorkOrderPendingList(SwTaskWorkOrderSearchDo searchDo) {
        return SwDoPoConverter.INSTANCE.getSwTaskWorkOrderDos(taskWorkOrderMapper.taskWorkOrderPendingList(searchDo));
    }

    @Override
    public boolean taskWorkOrderApproved(AsAssetsApproveBuilder build) {
        return taskWorkOrderMapper.taskWorkOrderApproved(build) > 0 ? true : false;
    }

    @Override
    public boolean taskWorkOrderRejection(AsAssetsApproveBuilder build) {
        return taskWorkOrderMapper.taskWorkOrderRejection(build) > 0 ? true : false;
    }
}
