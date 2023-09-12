/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.service.safework;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.common.enums.common.ApproveStatusEnum;
import com.kunyu.assets.safety.domain.model.assets.AsAssetsApproveBuilder;
import com.kunyu.assets.safety.domain.model.assets.AsAssetsDo;
import com.kunyu.assets.safety.domain.model.safework.*;
import com.kunyu.assets.safety.domain.respository.safework.ISwTaskTypeRepository;
import com.kunyu.assets.safety.domain.respository.safework.ISwTaskWorkOrderAssetsRepository;
import com.kunyu.assets.safety.domain.respository.safework.ISwTaskWorkOrderRepository;
import com.kunyu.common.enums.ModelStatusEnum;
import com.kunyu.common.exception.PlatformException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 安全工作管理领域层
 *
 * @author yangliu
 * @description
 * @date 2023-09-07
 */
@Service
public class SafeWorkDomain {

    @Autowired
    private ISwTaskTypeRepository iSwTaskTypeRepository;

    @Autowired
    private ISwTaskWorkOrderRepository iSwTaskWorkOrderRepository;

    @Autowired
    private ISwTaskWorkOrderAssetsRepository iSwTaskWorkOrderAssetsRepository;

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
        PageHelper.startPage(pageNum, pageSize);
        List<SwTaskTypeDo> taskTypeDos = iSwTaskTypeRepository.taskTypeList(searchDo);
        if (CollectionUtils.isEmpty(taskTypeDos)) {
            return null;
        }
        return new PageInfo<>(taskTypeDos);
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
        taskTypeDo.setStatus(ModelStatusEnum.VAILD.getCode());
        return iSwTaskTypeRepository.addTaskType(taskTypeDo);
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
        SwTaskTypeDo taskTypeDoExist = iSwTaskTypeRepository.getTaskTypeById(taskTypeDo.getId());
        if (null == taskTypeDoExist) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "任务类型不存在。");
        }
        return iSwTaskTypeRepository.updateTaskType(taskTypeDo);
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
        SwTaskTypeDo taskTypeDoExist = iSwTaskTypeRepository.getTaskTypeById(id);
        if (null == taskTypeDoExist) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "任务类型不存在。");
        }
        return iSwTaskTypeRepository.deleteTaskTypeById(id, ModelStatusEnum.INVALID.getCode(), userId);
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
        PageHelper.startPage(pageNum, pageSize);
        List<SwTaskWorkOrderDo> taskWorkOrderDos = iSwTaskWorkOrderRepository.taskWorkOrderList(searchDo);
        if (CollectionUtils.isEmpty(taskWorkOrderDos)) {
            return null;
        }
        return new PageInfo<>(taskWorkOrderDos);
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
        taskWorkOrderDo.setStatus(ModelStatusEnum.VAILD.getCode());
        taskWorkOrderDo.setApproveStatus(ApproveStatusEnum.SOURCEPENDING.getCode());
        taskWorkOrderDo = iSwTaskWorkOrderRepository.addTaskWorkOrder(taskWorkOrderDo);
        if (!CollectionUtils.isEmpty(taskWorkOrderDo.getAssetIds())) {
            SwTaskWorkOrderDo orderDo = taskWorkOrderDo;
            List<SwTaskWorkOrderAssetsDo> assetsDos = taskWorkOrderDo.getAssetIds().stream().map(assetId -> {
                SwTaskWorkOrderAssetsDo assetsDo = new SwTaskWorkOrderAssetsDo();
                assetsDo.setBaseId(orderDo.getId());
                assetsDo.setAssetId(assetId);
                assetsDo.setCreateBy(orderDo.getCreateBy());
                assetsDo.setStatus(ModelStatusEnum.VAILD.getCode());
                return assetsDo;
            }).collect(Collectors.toList());
            iSwTaskWorkOrderAssetsRepository.deleteTaskWorkOrderAssetsByBaseId(taskWorkOrderDo.getId(), ModelStatusEnum.INVALID.getCode(), taskWorkOrderDo.getCreateBy());
            iSwTaskWorkOrderAssetsRepository.addTaskWorkOrderAssets(assetsDos);
        }
        return taskWorkOrderDo;
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
        SwTaskWorkOrderDo taskWorkOrderDoExist = iSwTaskWorkOrderRepository.getTaskWorkOrderById(taskWorkOrderDo.getId());
        if (null == taskWorkOrderDoExist) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "任务工单不存在。");
        }
        iSwTaskWorkOrderRepository.updateTaskWorkOrder(taskWorkOrderDo);
        if (CollectionUtils.isEmpty(taskWorkOrderDo.getAssetIds())) {
            iSwTaskWorkOrderAssetsRepository.deleteTaskWorkOrderAssetsByBaseId(taskWorkOrderDo.getId(), ModelStatusEnum.INVALID.getCode(), taskWorkOrderDo.getCreateBy());
        } else {
            List<SwTaskWorkOrderAssetsDo> assetsDos = taskWorkOrderDo.getAssetIds().stream().map(assetId -> {
                SwTaskWorkOrderAssetsDo assetsDo = new SwTaskWorkOrderAssetsDo();
                assetsDo.setBaseId(taskWorkOrderDo.getId());
                assetsDo.setAssetId(assetId);
                assetsDo.setCreateBy(taskWorkOrderDo.getCreateBy());
                assetsDo.setStatus(ModelStatusEnum.VAILD.getCode());
                return assetsDo;
            }).collect(Collectors.toList());
            iSwTaskWorkOrderAssetsRepository.deleteTaskWorkOrderAssetsByBaseId(taskWorkOrderDo.getId(), ModelStatusEnum.INVALID.getCode(), taskWorkOrderDo.getCreateBy());
            iSwTaskWorkOrderAssetsRepository.addTaskWorkOrderAssets(assetsDos);
        }
        return true;
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
        SwTaskWorkOrderDo taskWorkOrderDoExist = iSwTaskWorkOrderRepository.getTaskWorkOrderById(id);
        if (null == taskWorkOrderDoExist) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "任务工单不存在。");
        }
        iSwTaskWorkOrderRepository.deleteTaskWorkOrderById(id, ModelStatusEnum.INVALID.getCode(), userId);
        iSwTaskWorkOrderAssetsRepository.deleteTaskWorkOrderAssetsByBaseId(id, ModelStatusEnum.INVALID.getCode(), userId);
        return true;
    }

    public PageInfo<SwTaskWorkOrderDo> taskWorkOrderPendingList(SwTaskWorkOrderSearchDo searchDo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        searchDo.setApproveStatus(ApproveStatusEnum.SOURCEPENDING.getCode());
        List<SwTaskWorkOrderDo> taskWorkOrderDos = iSwTaskWorkOrderRepository.taskWorkOrderPendingList(searchDo);
        if (CollectionUtils.isEmpty(taskWorkOrderDos)) {
            return null;
        }
        return new PageInfo<>(taskWorkOrderDos);
    }

    public boolean taskWorkOrderApproved(Integer id, String userId, String userName) {
        SwTaskWorkOrderDo taskWorkOrderDoExist = iSwTaskWorkOrderRepository.getTaskWorkOrderById(id);
        if (null == taskWorkOrderDoExist) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "任务工单不存在。");
        }
        // 只有待审批的数据才可进行此操作，防止数据被恶意篡改
        if (taskWorkOrderDoExist.getApproveStatus().equals(ApproveStatusEnum.SOURCEPENDING.getCode())) {
            return iSwTaskWorkOrderRepository.taskWorkOrderApproved(AsAssetsApproveBuilder.builder()
                    .id(id)
                    .approveStatus(ApproveStatusEnum.SOURCEAPPROVED.getCode())
                    .approveUserId(userId)
                    .approveUserName(userName)
                    .build());
        }
        throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "非法操作。");
    }

    public boolean taskWorkOrderRejection(Integer id, String userId, String userName) {
        SwTaskWorkOrderDo taskWorkOrderDoExist = iSwTaskWorkOrderRepository.getTaskWorkOrderById(id);
        if (null == taskWorkOrderDoExist) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "任务工单不存在。");
        }
        // 只有待审批的数据才可进行此操作，防止数据被恶意篡改
        if (taskWorkOrderDoExist.getApproveStatus().equals(ApproveStatusEnum.SOURCEPENDING.getCode())) {
            return iSwTaskWorkOrderRepository.taskWorkOrderRejection(AsAssetsApproveBuilder.builder()
                    .id(id)
                    .approveStatus(ApproveStatusEnum.SOURCEREJECTION.getCode())
                    .approveUserId(userId)
                    .approveUserName(userName)
                    .build());
        }
        throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "非法操作。");
    }
}
