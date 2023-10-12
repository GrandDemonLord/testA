/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.service.loophole;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.common.enums.common.ApproveResultEnum;
import com.kunyu.assets.safety.common.enums.common.ApproveStatusEnum;
import com.kunyu.assets.safety.common.enums.common.FormDataTypeEnum;
import com.kunyu.assets.safety.common.enums.common.UserDo;
import com.kunyu.assets.safety.common.enums.loophole.InstanceNodeOfLoopholeEnum;
import com.kunyu.assets.safety.common.enums.common.ReportStatusEnum;
import com.kunyu.assets.safety.domain.feignclients.SecurityClient;
import com.kunyu.assets.safety.domain.model.common.ApApproveHistoryDo;
import com.kunyu.assets.safety.domain.model.common.ApApproveHistorySearchDo;
import com.kunyu.assets.safety.domain.model.loophole.LoLoopholeDo;
import com.kunyu.assets.safety.domain.model.loophole.LoLoopholeSearchDo;
import com.kunyu.assets.safety.domain.respository.commoon.IApApproveHistoryRepository;
import com.kunyu.assets.safety.domain.respository.loophole.ILoLoopholeRepository;
import com.kunyu.common.enums.ModelStatusEnum;
import com.kunyu.common.enums.RoleEnum;
import com.kunyu.common.exception.PlatformException;
import com.kunyu.common.result.ApiResponse;
import com.kunyu.common.util.ThreadLocalUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author poet_wei
 * @description 漏洞信息管理模块领域层
 * @date 2023-09-06 11:26
 */
@Service
public class LoopholeDomain {

    @Resource
    private SecurityClient securityClient;
    @Autowired
    private ILoLoopholeRepository iLoLoopholeRepository;

    @Autowired
    private IApApproveHistoryRepository iApApproveHistoryRepository;

    /**
     * @param loLoopholeSearchDo pageNum pageSize
     * @return LoLoopholeDo
     * @description 漏洞信息条件分页查询
     * @author poet_wei
     * @date 2023/9/6
     */
    public PageInfo<LoLoopholeDo> selectLoophole(LoLoopholeSearchDo loLoopholeSearchDo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<LoLoopholeDo> loLoopholeDos = iLoLoopholeRepository.selectLoophole(loLoopholeSearchDo);
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
        loLoopholeDo.setApproveStatus(ApproveStatusEnum.SOURCEPENDING.getCode());
        loLoopholeDo.setFormdataTypeCode(FormDataTypeEnum.LOUPLOADAPPROVAL.getCode());
        loLoopholeDo.setFormdataTypeName(FormDataTypeEnum.LOUPLOADAPPROVAL.getName());
        loLoopholeDo.setFormdataModule(FormDataTypeEnum.LOUPLOADAPPROVAL.getModule());
        // 添加漏洞信息
        LoLoopholeDo loLoophole = iLoLoopholeRepository.saveLoophole(loLoopholeDo);
        // 历史记录表--记录漏洞审批信息
        List<LoLoopholeDo> loLoopholeDos = new ArrayList<>();
        loLoopholeDos.add(loLoopholeDo);
        iApApproveHistoryRepository.addApproveHistory(getHistoryDos(loLoopholeDos));
        return loLoophole;
    }

    private List<ApApproveHistoryDo> getHistoryDos(List<LoLoopholeDo> loLoopholeDos) {
        List<ApApproveHistoryDo> historyDos = new ArrayList<>();
        for (LoLoopholeDo loLoopholeDo : loLoopholeDos) {
            Date date = new Date();
            ApApproveHistoryDo historyApply = new ApApproveHistoryDo();
            historyApply.setBaseId(loLoopholeDo.getId());
            historyApply.setFormdataTypeCode(loLoopholeDo.getFormdataTypeCode());
            historyApply.setFormdataTypeName(loLoopholeDo.getFormdataTypeName());
            historyApply.setFormdataModule(loLoopholeDo.getFormdataModule());
            historyApply.setInstanceNode(InstanceNodeOfLoopholeEnum.APPLY.getName());
            historyApply.setApproveUserId(loLoopholeDo.getCreateBy());
            historyApply.setApproveUserName(loLoopholeDo.getCreateBy());
            historyApply.setApproveStartTime(date);
            historyApply.setApproveEndTime(date);
            historyApply.setApproveResult(ApproveResultEnum.APPLY.getName());
            historyApply.setStatus(ModelStatusEnum.VAILD.getCode());
            historyDos.add(historyApply);

            ApApproveHistoryDo historyAdmin = new ApApproveHistoryDo();
            historyAdmin.setBaseId(loLoopholeDo.getId());
            historyAdmin.setFormdataTypeCode(loLoopholeDo.getFormdataTypeCode());
            historyAdmin.setFormdataTypeName(loLoopholeDo.getFormdataTypeName());
            historyAdmin.setFormdataModule(loLoopholeDo.getFormdataModule());
            historyAdmin.setInstanceNode(InstanceNodeOfLoopholeEnum.ADMIN.getName());
            historyAdmin.setApproveStartTime(date);
            historyAdmin.setStatus(ModelStatusEnum.VAILD.getCode());
            historyDos.add(historyAdmin);
        }

        return historyDos;
    }




    /**
     * @param loLoopholeSearchDo pageNum pageSize
     * @return LoLoopholeDo
     * @description 修复工单审批查询
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
     * @param loLoopholeDo
     * @return LoLoopholeDo
     * @description 创建修复工单
     * @author poet_wei
     * @date 2023/9/8
     */
    public LoLoopholeDo saveRepairOrder(LoLoopholeDo loLoopholeDo, LoLoopholeSearchDo loLoopholeSearchDo) {
        // 校验对应的漏洞信息是否存在
        LoLoopholeDo loopholeExists = VerifyLoopholeExists(loLoopholeDo.getId());
        // 权限校验
        verifyPermission(loopholeExists, loLoopholeSearchDo);
        // 校验漏洞信息是否是审批状态
        if (!ApproveStatusEnum.SOURCEPENDING.getCode().equals(loopholeExists.getApproveStatus())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "非法操作。");
        }
        // 封装漏洞工单信息
        loLoopholeDo.setReportStatus(ReportStatusEnum.NOTSTARTED.getCode());
        loLoopholeDo.setApproveStatus(ApproveStatusEnum.VULNERABILITYORDERPROCESSED.getCode());
        loLoopholeDo.setFormdataTypeCode(FormDataTypeEnum.LOTASKWORKORDERAPPROVAL.getCode());
        loLoopholeDo.setFormdataTypeName(FormDataTypeEnum.LOTASKWORKORDERAPPROVAL.getName());
        loLoopholeDo.setFormdataModule(FormDataTypeEnum.LOTASKWORKORDERAPPROVAL.getModule());
        LoLoopholeDo loLoophole = iLoLoopholeRepository.updateLoLoopholeById(loLoopholeDo);
        // 封装历史记录信息
        ApApproveHistoryDo historyDo = getApApproveHistoryDo(loLoopholeDo.getId(), loLoopholeDo);
        historyDo.setBaseId(loLoopholeDo.getId());
        historyDo.setApproveUserId(loLoopholeDo.getUpdateBy());
        historyDo.setApproveUserName(loLoopholeDo.getUpdateBy());
        historyDo.setApproveEndTime(new Date());
        historyDo.setApproveResult(ApproveResultEnum.APPROVERD.getName());
        // 更新之前节点历史记录表信息
        iApApproveHistoryRepository.updateHistoryDo(historyDo);
        ApApproveHistoryDo historyApply = new ApApproveHistoryDo();
        historyApply.setBaseId(loLoopholeDo.getId());
        historyApply.setApproveUserId(loLoopholeDo.getCreateBy());
        historyApply.setApproveUserName(loLoopholeDo.getCreateBy());
        historyApply.setApproveStartTime(new Date());
        historyApply.setApproveEndTime(new Date());
        historyApply.setStatus(ModelStatusEnum.VAILD.getCode());
        // 封装历史记录状态信息
        historyApply.setApproveResult(ApproveResultEnum.APPLY.getName());
        historyApply.setInstanceNode(InstanceNodeOfLoopholeEnum.PROCESSED.getName());
        historyApply.setFormdataTypeCode(FormDataTypeEnum.LOTASKWORKORDERAPPROVAL.getCode());
        historyApply.setFormdataTypeName(FormDataTypeEnum.LOTASKWORKORDERAPPROVAL.getName());
        historyApply.setFormdataModule(FormDataTypeEnum.LOTASKWORKORDERAPPROVAL.getModule());
        // 记录表插入记录
        ArrayList<ApApproveHistoryDo> apApproveHistoryDos = new ArrayList<>();
        apApproveHistoryDos.add(historyApply);
        iApApproveHistoryRepository.addApproveHistory(apApproveHistoryDos);
        return loLoophole;
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
    public PageInfo<LoLoopholeDo> adminSelectLoophole(LoLoopholeSearchDo loLoopholeSearchDo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<LoLoopholeDo> loLoopholeDos = iLoLoopholeRepository.adminSelectLoophole(loLoopholeSearchDo);
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
    public Boolean loopholeReportStart(Integer id, String userId) {
        // 漏洞信息是否存在
        LoLoopholeDo loLoopholeDo = VerifyLoopholeExists(id);
        // 校验处置人是否对应
        verifyLoopholeProcessed(loLoopholeDo, userId);
        // 报告是否是待开始状态
        if (!ReportStatusEnum.NOTSTARTED.getCode().equals(loLoopholeDo.getReportStatus())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "报告状态有误。");
        }
        String reportStatus = ReportStatusEnum.UPLOADING.getCode();
        return iLoLoopholeRepository.updateLoopholeReportStartById(id, reportStatus, userId);
    }


    /**
     * @param id 漏洞id
     * @return Boolean
     * @description 漏洞报告结束
     * @author poet_wei
     * @date 2023/9/12
     */
    public Boolean loopholeReportEnd(Integer id, String userId) {
        // 校验漏洞是否存在
        LoLoopholeDo loLoopholeDo = VerifyLoopholeExists(id);
        // 校验处置人是否对应
        verifyLoopholeProcessed(loLoopholeDo, userId);
        // 校验是否已经上传过报告
        String remediationReport = loLoopholeDo.getLoopholeReportId();
        if (StringUtils.isBlank(remediationReport)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "漏洞处理报告暂未上传，无法结束。");
        }
        // 报告是否是待结束状态
        if (!ReportStatusEnum.UPLOADING.getCode().equals(loLoopholeDo.getReportStatus())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "报告状态有误。");
        }
        // 更新历史记录表--待处置数据
        ApApproveHistoryDo historyDo = getApApproveHistoryDo(id, loLoopholeDo);
        historyDo.setBaseId(id);
        historyDo.setApproveUserId(loLoopholeDo.getUpdateBy());
        historyDo.setApproveUserName(loLoopholeDo.getUpdateBy());
        historyDo.setApproveEndTime(new Date());
        historyDo.setApproveResult(ApproveResultEnum.APPROVERD.getName());
        iApApproveHistoryRepository.updateHistoryDo(historyDo);
        // 漏洞报告结束
        boolean approveStatus = iLoLoopholeRepository.loopholeReportEndById(id, ReportStatusEnum.END.getCode(), ApproveStatusEnum.VULNERABILITYORDERPENDING.getCode(), userId);
        // 插入历史记录表--待审批数据
        List<ApApproveHistoryDo> historyDos = new ArrayList<>();
        Date date = new Date();
        ApApproveHistoryDo historyApply = new ApApproveHistoryDo();
        historyApply.setBaseId(loLoopholeDo.getId());
        historyApply.setFormdataTypeCode(loLoopholeDo.getFormdataTypeCode());
        historyApply.setFormdataTypeName(loLoopholeDo.getFormdataTypeName());
        historyApply.setFormdataModule(loLoopholeDo.getFormdataModule());
        historyApply.setInstanceNode(InstanceNodeOfLoopholeEnum.ADMIN.getName());
        historyApply.setApproveStartTime(date);
        historyApply.setStatus(ModelStatusEnum.VAILD.getCode());
        historyDos.add(historyApply);
        iApApproveHistoryRepository.addApproveHistory(historyDos);
        return approveStatus;
    }

    private ApApproveHistoryDo getApApproveHistoryDo(Integer id, LoLoopholeDo loLoopholeDo) {
        ApApproveHistorySearchDo searchDo = new ApApproveHistorySearchDo();
        searchDo.setBaseId(id);
        searchDo.setFormdataModule(loLoopholeDo.getFormdataModule());
        ApApproveHistoryDo historyDo = iApApproveHistoryRepository.historyDo(searchDo);
        return historyDo;
    }


    private LoLoopholeDo VerifyLoopholeExists(Integer id) {
        LoLoopholeDo loLoopholeDo = iLoLoopholeRepository.selectLoopholeById(id);
        if (ObjectUtils.isEmpty(loLoopholeDo)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "漏洞信息不存在。");
        }
        return loLoopholeDo;
    }

    /**
     * @param loLoopholeSearchDo
     * @return LoLoopholeDo
     * @description 漏洞工单审批通过
     * @author poet_wei
     * @date 2023/9/12
     */
    public Boolean approved(LoLoopholeSearchDo loLoopholeSearchDo) {
        // 校验工单是否存在
        Integer id = loLoopholeSearchDo.getId();
        LoLoopholeDo loLoopholeDo = VerifyLoopholeExists(id);
        // 校验工单状态是否是待审批
        if (!ApproveStatusEnum.VULNERABILITYORDERPENDING.getCode().equals(loLoopholeDo.getApproveStatus())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "非法操作。");
        }
        // 校验是否是总厂管理员
        verifyPermission(loLoopholeDo, loLoopholeSearchDo);
        String userId = loLoopholeSearchDo.getUserId();
        String userName = loLoopholeSearchDo.getUserName();
        loLoopholeDo.setApproveUserId(userId);
        loLoopholeDo.setApproveUserName(userName);
        loLoopholeDo.setUpdateBy(userId);
        loLoopholeDo.setApproveStatus(ApproveStatusEnum.VULNERABILITYORDERAPPROVED.getCode());
        boolean approvedStatus = iLoLoopholeRepository.approved(loLoopholeDo);
        // 审批历史表
        ApApproveHistoryDo historyDo = getApApproveHistoryDo(id, loLoopholeDo);
        historyDo.setBaseId(id);
        historyDo.setApproveUserId(userId);
        historyDo.setApproveUserName(userName);
        historyDo.setApproveEndTime(new Date());
        historyDo.setApproveResult(ApproveResultEnum.APPROVERD.getName());
        iApApproveHistoryRepository.updateHistoryDo(historyDo);
        return approvedStatus;
    }


    /**
     * @param loLoopholeSearchDo
     * @return LoLoopholeDo
     * @description 漏洞工单审批驳回
     * @author poet_wei
     * @date 2023/9/12
     */
    public Boolean rejection(LoLoopholeSearchDo loLoopholeSearchDo) {
        // 校验工单是否存在
        Integer id = loLoopholeSearchDo.getId();
        LoLoopholeDo loLoopholeDo = VerifyLoopholeExists(id);
        // 权限校验
        verifyPermission(loLoopholeDo, loLoopholeSearchDo);
        // 校验工单状态是否是待审批
        if (!ApproveStatusEnum.VULNERABILITYORDERPENDING.getCode().equals(loLoopholeDo.getApproveStatus())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "漏洞信息不是待审批状态，无法驳回。");
        }
        String userId = loLoopholeSearchDo.getUserId();
        String userName = loLoopholeSearchDo.getUserName();
        loLoopholeDo.setApproveUserId(userId);
        loLoopholeDo.setApproveUserName(userName);
        loLoopholeDo.setUpdateBy(userId);
        loLoopholeDo.setReportStatus(ReportStatusEnum.NOTSTARTED.getCode());
        loLoopholeDo.setApproveStatus(ApproveStatusEnum.VULNERABILITYORDERREJECTION.getCode());
        boolean rejectionStatus = iLoLoopholeRepository.rejection(loLoopholeDo);
        // 审批历史表
        ApApproveHistoryDo historyDo = getApApproveHistoryDo(id, loLoopholeDo);
        historyDo.setBaseId(id);
        historyDo.setApproveUserId(userId);
        historyDo.setApproveUserName(userName);
        historyDo.setApproveEndTime(new Date());
        historyDo.setApproveResult(ApproveResultEnum.REJECTION.getName());
        iApApproveHistoryRepository.updateHistoryDo(historyDo);
        return rejectionStatus;

    }

    /**
     * @param loLoopholeSearchDo
     * @return LoLoopholeDo
     * @description 漏洞信息忽略
     * @author poet_wei
     * @date 2023/9/12
     */
    public Boolean ignore(LoLoopholeSearchDo loLoopholeSearchDo) {
        // 校验工单是否存在
        Integer id = loLoopholeSearchDo.getId();
        LoLoopholeDo loLoopholeDo = VerifyLoopholeExists(id);
        // 权限校验
        verifyPermission(loLoopholeDo, loLoopholeSearchDo);
        // 校验漏洞信息是否是待审批状态
        if (!ApproveStatusEnum.SOURCEPENDING.getCode().equals(loLoopholeDo.getApproveStatus())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "漏洞信息不是待审批状态，无法忽略。");
        }
        String userId = loLoopholeSearchDo.getUserId();
        String userName = loLoopholeSearchDo.getUserName();
        loLoopholeDo.setApproveUserName(userName);
        loLoopholeDo.setApproveUserId(userId);
        loLoopholeDo.setUpdateBy(userId);
        loLoopholeDo.setApproveStatus(ApproveStatusEnum.SOURCEIGNORE.getCode());
        boolean ignoreStatus = iLoLoopholeRepository.ignore(loLoopholeDo);
        // 审批历史表
        ApApproveHistoryDo historyDo = getApApproveHistoryDo(id, loLoopholeDo);
        historyDo.setBaseId(id);
        historyDo.setApproveUserId(userId);
        historyDo.setApproveUserName(userName);
        historyDo.setApproveEndTime(new Date());
        historyDo.setApproveResult(ApproveResultEnum.IGNORE.getName());
        iApApproveHistoryRepository.updateHistoryDo(historyDo);
        return ignoreStatus;
    }

    /**
     * @param
     * @return LoLoopholeDo
     * @description 查询代办任务
     * @author poet_wei
     * @date 2023/9/15
     */
    public PageInfo<LoLoopholeDo> selectTaskQuery(LoLoopholeSearchDo loLoopholeSearchDo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<LoLoopholeDo> loLoopholeDos = iLoLoopholeRepository.selectTaskQuery(loLoopholeSearchDo);
        if (CollectionUtils.isEmpty(loLoopholeDos)) {
            return null;
        }
        return new PageInfo<>(loLoopholeDos);
    }


    /**
     * @param id loopholeReportId loopholeReportName userId
     * @return null
     * @description 漏洞报告上传
     * @author poet_wei
     * @date 2023/9/19
     */
    public int upload(Integer id, String loopholeReportId, String loopholeReportName, String userId) {
        return iLoLoopholeRepository.upload(id, loopholeReportId, loopholeReportName, userId);
    }

    public void verifyLoopholeReportStatus(Integer id, String userId) {
        // 校验漏洞信息是否存在
        LoLoopholeDo loLoopholeDo = VerifyLoopholeExists(id);
        // 用户是否是此条漏洞的处置人
        if (!userId.equals(loLoopholeDo.getProcessedId())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "非处置人不能操作漏洞报告。");
        }
        // 此时漏洞工单是否处于上传阶段
        if (!ReportStatusEnum.UPLOADING.getCode().equals(loLoopholeDo.getReportStatus())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "此时不处于漏洞报告上传阶段。");
        }
    }

    /**
     * @param id     漏洞id
     * @param userId 用户id
     * @return
     */
    public Boolean deleteReport(Integer id, String userId) {
        // 校验漏洞信息是否存在
        LoLoopholeDo loLoopholeDo = VerifyLoopholeExists(id);
        // 用户是否是此条漏洞的处置人
        if (!userId.equals(loLoopholeDo.getProcessedId())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "非处置人不能操作漏洞报告。");
        }
        // 此时漏洞工单是否处于上传阶段
        if (!ReportStatusEnum.UPLOADING.getCode().equals(loLoopholeDo.getReportStatus())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "此时不处于漏洞报告上传阶段。");
        }
        // 校验漏洞报告是否已经上传
        if (ObjectUtils.isEmpty(loLoopholeDo.getLoopholeReportId())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "未上传报告，无法删除。");
        }
        return iLoLoopholeRepository.deleteReport(id, userId);
    }

    /**
     * 新增漏洞批量导入 批量导入
     *
     * @param loLoopholeDos userId
     * @return LoLoopholeDo
     */
    public List<LoLoopholeDo> batchSaveLoophole(List<LoLoopholeDo> loLoopholeDos, String userId) {
        // 完善漏洞状态信息
        for (LoLoopholeDo loLoopholeDo : loLoopholeDos) {
            loLoopholeDo.setStatus(ModelStatusEnum.VAILD.getCode());
            loLoopholeDo.setFormdataTypeCode(FormDataTypeEnum.LOUPLOADAPPROVAL.getCode());
            loLoopholeDo.setFormdataTypeName(FormDataTypeEnum.LOUPLOADAPPROVAL.getName());
            loLoopholeDo.setFormdataModule(FormDataTypeEnum.LOUPLOADAPPROVAL.getModule());
            loLoopholeDo.setApproveStatus(ApproveStatusEnum.SOURCEPENDING.getCode());
            loLoopholeDo.setCreateBy(userId);
        }
        // 批量导入漏洞信息表
        List<LoLoopholeDo> loLoopholeDoList = iLoLoopholeRepository.batchSaveLoophole(loLoopholeDos);
        // 批量导入历史记录表
        iApApproveHistoryRepository.addApproveHistory(getHistoryDos(loLoopholeDoList));
        return loLoopholeDoList;

    }

    /**
     * @description 获取负责人的单位信息
     *
     * @param userName 负责人名称
     * @return UserDo
     * @author poet_wei
     * @date 2023/9/21
     */
    public UserDo getUserInfo(String userName) {
        ApiResponse<UserDo> selectUserInfo = securityClient.getUserInfo(userName, ThreadLocalUtil.getUserInfo().getToken());
        return selectUserInfo.getData();
    }

    // 校验如果是总厂管理员不需要unitId比较，分厂需要比较
    private static void verifyPermission(LoLoopholeDo loLoopholeDo, LoLoopholeSearchDo loLoopholeSearchDo) {
        if (!RoleEnum.SYSGENERALADMIN.getRoleId().equals(loLoopholeSearchDo.getRoleCode()) && !loLoopholeSearchDo.getUnitId().equals(loLoopholeDo.getUnitId())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "非法操作。");
        }
    }

    // 校验处置人
    private void verifyLoopholeProcessed(LoLoopholeDo loLoopholeDo, String userId) {
        if (!userId.equals(loLoopholeDo.getProcessedId())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "处置人有误。");
        }
    }


}
