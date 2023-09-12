/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.service.loophole;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.common.enums.common.ApproveStatusEnum;
import com.kunyu.assets.safety.common.enums.loophole.LoopholeReportStatusEnum;
import com.kunyu.assets.safety.common.enums.loophole.LoopholeTaskStatusEnum;
import com.kunyu.assets.safety.domain.model.loophole.LoLoopholeDo;
import com.kunyu.assets.safety.domain.model.loophole.LoLoopholeSearchDo;
import com.kunyu.assets.safety.domain.respository.loophole.ILoLoopholeRepository;
import com.kunyu.common.enums.ModelStatusEnum;
import com.kunyu.common.exception.PlatformException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

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
        // TODO 记录表插入记录
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
     * @param loLoopholeSearchDo pageNum pageSize
     * @return LoLoopholeDo
     * @description 修复工单审批查询--管理端
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

    /**
     * @param loLoopholeDo pageNum pageSize
     * @return LoLoopholeDo
     * @description 创建修复工单--管理端
     * @author poet_wei
     * @date 2023/9/8
     */
    public LoLoopholeDo saveRepairOrder(LoLoopholeDo loLoopholeDo) {
        loLoopholeDo.setTaskStatus(LoopholeTaskStatusEnum.FIXING.getCode());
        loLoopholeDo.setReportStatus(LoopholeReportStatusEnum.FIXING.getCode());
        loLoopholeDo.setApprovalStatus(ApproveStatusEnum.SOURCEPENDING.getCode());
        // TODO 记录表插入记录
        return iLoLoopholeRepository.updateLoLoopholeById(loLoopholeDo);
    }

    /**
     * @param id 漏洞id
     * @return LoLoopholeDo
     * @description 创建修复工单--管理端
     * @author poet_wei
     * @date 2023/9/11
     */
    public LoLoopholeDo selectLoopholeById(Integer id) {
        return iLoLoopholeRepository.selectLoopholeById(id);
    }

    /**
     * @param loLoopholeSearchDo pageNum pageSize
     * @return LoLoopholeDo
     * @description 漏洞信息条件分页查询
     * @author poet_wei
     * @date 2023/9/6
     */
    public PageInfo<LoLoopholeDo> adminListingList(LoLoopholeSearchDo loLoopholeSearchDo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<LoLoopholeDo> loLoopholeDos = iLoLoopholeRepository.adminListingList(loLoopholeSearchDo);
        if (CollectionUtils.isEmpty(loLoopholeDos)) {
            return null;
        }
        return new PageInfo<>(loLoopholeDos);
    }

    /**
     * @param id 漏洞id
     * @return Boolean
     * @description 漏洞报告开始
     * @author poet_wei
     * @date 2023/9/12
     */
    public Boolean loopholeReortStart(Integer id, String updateBy) {
        String reportStatus = LoopholeReportStatusEnum.UPLOAD.getCode();
        VerifyLoopholeExists(id);
        // TODO 记录表插入数据
        return iLoLoopholeRepository.updateLoopholeReortStartById(id, reportStatus, updateBy) > 0;
    }

    /**
     * @param id 漏洞id
     * @return Boolean
     * @description 漏洞报告结束
     * @author poet_wei
     * @date 2023/9/12
     */
    public Boolean loopholeReortEnd(Integer id, String updateBy) {
        // TODO 记录表插入数据
        // 校验漏洞是否存在
        LoLoopholeDo loLoopholeDo = VerifyLoopholeExists(id);
        // 校验是否已经上传过报告
        String remediationReport = loLoopholeDo.getRemediationReport();
        if (StringUtils.isBlank(remediationReport)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "漏洞处理报告暂未上传，无法结束。");
        }
        String reportStatus = LoopholeReportStatusEnum.FINISHED.getCode();
        return iLoLoopholeRepository.loopholeReortEndById(id, reportStatus, updateBy) > 0;
    }

    private LoLoopholeDo VerifyLoopholeExists(Integer id) {
        LoLoopholeDo loLoopholeDo = iLoLoopholeRepository.selectLoopholeById(id);
        if (Objects.isNull(loLoopholeDo)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "漏洞信息不存在。");
        }
        return loLoopholeDo;
    }

    /**
     * @param id 工单id updateBy 更新人
     * @return LoLoopholeDo
     * @description 漏洞工单审批通过
     * @author poet_wei
     * @date 2023/9/12
     */
    public Boolean approved(Integer id, String updateId, String updateName) {
        // 校验工单是否存在
        LoLoopholeDo loLoopholeDo = VerifyLoopholeExists(id);
        // 校验工单状态是否是待审批
        VerifyApproverStstus(loLoopholeDo);
        loLoopholeDo.setApproveUserId(updateId);
        loLoopholeDo.setApproveUserName(updateName);
        loLoopholeDo.setUpdateBy(updateId);
        loLoopholeDo.setTaskStatus(LoopholeTaskStatusEnum.FIXED.getCode());
        loLoopholeDo.setApprovalStatus(ApproveStatusEnum.SOURCEAPPROVED.getCode());
        return iLoLoopholeRepository.approved(loLoopholeDo) > 0;

    }

    /**
     * @param id 工单id
     * @return LoLoopholeDo
     * @description 漏洞工单审批驳回
     * @author poet_wei
     * @date 2023/9/12
     */
    public Boolean rejection(Integer id, String updateId, String updateName) {
        // 校验工单是否存在
        LoLoopholeDo loLoopholeDo = VerifyLoopholeExists(id);
        // 校验工单状态是否是待审批
        VerifyApproverStstus(loLoopholeDo);
        loLoopholeDo.setApproveUserId(updateId);
        loLoopholeDo.setApproveUserName(updateName);
        loLoopholeDo.setUpdateBy(updateId);
        loLoopholeDo.setTaskStatus(LoopholeTaskStatusEnum.FIXING.getCode());
        loLoopholeDo.setApprovalStatus(ApproveStatusEnum.SOURCEREJECTION.getCode());
        return iLoLoopholeRepository.rejection(loLoopholeDo) > 0;

    }

    private static void VerifyApproverStstus(LoLoopholeDo loLoopholeDo) {
        if (!ApproveStatusEnum.SOURCEPENDING.getCode().equals(loLoopholeDo.getApprovalStatus()) ||
                !ApproveStatusEnum.WORKORDERPENDING.getCode().equals(loLoopholeDo.getApprovalStatus())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "非法操作。");
        }
    }
}
