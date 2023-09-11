/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.service.loophole;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.common.enums.common.ApproveStatusEnum;
import com.kunyu.assets.safety.common.enums.loophole.LoopholeTaskStatusEnum;
import com.kunyu.assets.safety.domain.model.loophole.LoLoopholeDo;
import com.kunyu.assets.safety.domain.model.loophole.LoLoopholeSearchDo;
import com.kunyu.assets.safety.domain.respository.loophole.ILoLoopholeRepository;
import com.kunyu.common.enums.ModelStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author poet_wei
 * @description 漏洞信息管理模块领域层
 * @date 2023-09-06 11:26
 */
@Service
public class LoopholeDomain {


    @Autowired
    private ILoLoopholeRepository iLoLoopholeRepository;

    /**
     * @param loLoopholeSearchDo pageNum pageSize
     * @return LoLoopholeDo
     * @description 漏洞信息条件分页查询
     * @author poet_wei
     * @date 2023/9/6
     */
    public PageInfo<LoLoopholeDo> listingList(LoLoopholeSearchDo loLoopholeSearchDo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<LoLoopholeDo> loLoopholeDos = iLoLoopholeRepository.listingList(loLoopholeSearchDo);
        if (CollectionUtils.isEmpty(loLoopholeDos)) {
            return null;
        }
        return new PageInfo<>(loLoopholeDos);
    }

    /**
     * @param loLoopholeDo 漏洞信息Do对象
     * @return LoLoopholeDo
     * @description 新增漏洞信息
     * @author poet_wei
     * @date 2023/9/6
     */
    public LoLoopholeDo saveLoophole(LoLoopholeDo loLoopholeDo) {
        loLoopholeDo.setStatus(ModelStatusEnum.VAILD.getCode());
        loLoopholeDo.setTaskStatus(LoopholeTaskStatusEnum.FIXING.getCode());
        return iLoLoopholeRepository.saveLoophole(loLoopholeDo);
    }

    /**
     * @param loLoopholeSearchDo pageNum pageSize
     * @return LoLoopholeDo
     * @description 修复工单信息查询--用户端
     * @author poet_wei
     * @date 2023/9/8
     */
    public PageInfo<LoLoopholeDo> selectRepairWorkOrder(LoLoopholeSearchDo loLoopholeSearchDo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<LoLoopholeDo> loLoopholeDos = iLoLoopholeRepository.selectRepairWorkOrder(loLoopholeSearchDo);
        if (CollectionUtils.isEmpty(loLoopholeDos)) {
            return null;
        }
        return new PageInfo<>(loLoopholeDos);
    }

    /**
     * @description 修复工单审批查询--管理端
     *
     * @param loLoopholeSearchDo pageNum pageSize
     * @return LoLoopholeDo
     * @author poet_wei
     * @date 2023/9/8
     */
    public PageInfo<LoLoopholeDo> selectApproveOrder(LoLoopholeSearchDo loLoopholeSearchDo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<LoLoopholeDo> loLoopholeDos = iLoLoopholeRepository.selectApproveOrder(loLoopholeSearchDo);
        if (CollectionUtils.isEmpty(loLoopholeDos)) {
            return null;
        }
        return new PageInfo<>(loLoopholeDos);
    }

    public LoLoopholeDo saveRepairOrder(LoLoopholeDo loLoopholeDo) {
        loLoopholeDo.setTaskStatus(LoopholeTaskStatusEnum.FIXING.getCode());
        loLoopholeDo.setApprovalStatus(ApproveStatusEnum.PENDING.getCode());
        return iLoLoopholeRepository.updateLoLoopholeById(loLoopholeDo);
    }
}
