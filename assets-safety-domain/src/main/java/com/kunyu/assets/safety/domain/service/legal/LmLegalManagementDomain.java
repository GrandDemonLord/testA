/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.service.legal;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.common.enums.common.ApproveResultEnum;
import com.kunyu.assets.safety.common.enums.common.ApproveStatusEnum;
import com.kunyu.assets.safety.common.enums.common.FormDataTypeEnum;
import com.kunyu.assets.safety.common.enums.common.ReportStatusEnum;
import com.kunyu.assets.safety.common.enums.legal.InstanceNodeOfLegalEnum;
import com.kunyu.assets.safety.domain.model.common.ApApproveHistoryDo;
import com.kunyu.assets.safety.domain.model.common.ApApproveHistorySearchDo;
import com.kunyu.assets.safety.domain.model.legal.*;
import com.kunyu.assets.safety.domain.model.loophole.LoLoopholeDo;
import com.kunyu.assets.safety.domain.respository.commoon.IApApproveHistoryRepository;
import com.kunyu.assets.safety.domain.respository.legal.LmCorporateGovernanceRepository;
import com.kunyu.assets.safety.domain.respository.legal.LmLegalManagementRepository;
import com.kunyu.common.enums.ModelStatusEnum;
import com.kunyu.common.enums.RoleEnum;
import com.kunyu.common.exception.PlatformException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author poet_wei
 * @description 安全合规管理领域层
 * @date 2023-09-27 9:09
 */
@Service
public class LmLegalManagementDomain {
    @Autowired
    private LmLegalManagementRepository lmLegalManagementRepository;

    @Autowired
    private IApApproveHistoryRepository iApApproveHistoryRepository;

    @Autowired
    private LmCorporateGovernanceRepository lmCorporateGovernanceRepository;

    /**
     * @param legalManagementDo
     * @return LmLegalManagementDo
     * @description 添加法律法规
     * @author poet_wei
     * @date 2023/9/27
     */
    public LmLegalManagementDo saveLegal(LmLegalManagementDo legalManagementDo) {

        // 查询法律法规名称是否已经存在
        LmLegalManagementDo lmLegalExistDo = lmLegalManagementRepository.selectLegalByName(legalManagementDo);

        if (!ObjectUtils.isEmpty(lmLegalExistDo)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "法律名称重复。");
        }
        legalManagementDo.setStatus(ModelStatusEnum.VAILD.getCode());
        // 适用对象
        String applicableObject = "红塔集团";
        legalManagementDo.setApplicableObject(applicableObject);
        legalManagementDo.setReleaseDate(new Date());
        legalManagementDo.setFormdataTypeCode(FormDataTypeEnum.LEGALWORKORDER.getCode());
        legalManagementDo.setFormdataTypeName(FormDataTypeEnum.LEGALWORKORDER.getName());
        legalManagementDo.setFormdataModule(FormDataTypeEnum.LEGALWORKORDER.getModule());
        // 保存法律法规信息
        return lmLegalManagementRepository.addLegal(legalManagementDo);

    }

    /**
     * @param userId id 删除法律法规信息id
     * @return Boolean
     * @description 删除法律法规信息
     * @author poet_wei
     * @date 2023/9/27
     */
    public Boolean deleteLegal(String userId, Integer id) {
        // 查询法律法规是否存在
        LmLegalManagementDo lmLegalManagementDo = validateLegalRegulation(id);
        // 校验内部制度是否有所关联
        List<LmCorporateGovernanceDo> lmCorporateGovernanceDos = lmCorporateGovernanceRepository.selectCorporateGovernanceByName(lmLegalManagementDo.getLawName());
        if (!CollectionUtils.isEmpty(lmCorporateGovernanceDos)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "企业制度有关联此法律，无法删除。");
        }
        // 删除法律法规
        return lmLegalManagementRepository.deleteLegalById(userId, id);
    }

    /**
     * @param searchDo, pageNum, pageSize
     * @return LmLegalManagementDo
     * @description 法律法规信息查询
     * @author poet_wei
     * @date 2023/9/27
     */
    public PageInfo<LmLegalManagementDo> searchLegalList(LmLegalManagementSearchDo searchDo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<LmLegalManagementDo> legalList = lmLegalManagementRepository.selectLegalList(searchDo);
        if (CollectionUtils.isEmpty(legalList)) {
            return null;
        }
        return new PageInfo<>(legalList);
    }

    /**
     * @param id
     * @return LmLegalManagementDo
     * @description 查询根据id法律法规对象
     * @author poet_wei
     * @date 2023/9/27
     */
    public LmLegalManagementDo selectLegalById(Integer id) {
        LmLegalManagementDo lmLegalManagementDo = lmLegalManagementRepository.selectLegalById(id);
        if (ObjectUtils.isEmpty(lmLegalManagementDo)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "法律法规数据信息不存在。");
        }
        return lmLegalManagementDo;
    }

    /**
     * @param ids
     * @return LmLegalManagementDo
     * @description 查询根据id法律法规对象
     * @author poet_wei
     * @date 2023/9/27
     */
    public List<LmLegalManagementDo> selectLegalListById(List<Integer> ids) {
        List<LmLegalManagementDo> managementDoList = lmLegalManagementRepository.selectLegalListById(ids);
        if (ObjectUtils.isEmpty(managementDoList)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "法律法规数据信息不存在。");
        }
        return managementDoList;
    }

    /**
     * @param originalFileNameNew, originalFileName, userId
     * @return int
     * @description 法律法规附件上传
     * @author poet_wei
     * @date 2023/9/27
     */
    public int uploadLegal(LmLegalManagementDo lmLegalManagementDo, String originalFileNameNew, String lawName, String userId) {

        return lmLegalManagementRepository.uploadLegal(lmLegalManagementDo, originalFileNameNew, lawName, userId);
    }

    /**
     * @param id, originalFileNameNew, originalFileName, userId
     * @return int
     * @description 法律法规附件上传
     * @author poet_wei
     * @date 2023/9/27
     */
    public int uploadCorporateGovernance(Integer id, String originalFileNameNew, String lawName, String userId) {

        return lmCorporateGovernanceRepository.uploadCorporateGovernance(id, originalFileNameNew, lawName, userId);
    }

    /**
     * @param legalManagementDo
     * @return LmLegalManagementDo
     * @description 修改法律法规
     * @author poet_wei
     * @date 2023/9/27
     */
    public LmLegalManagementDo updateLegal(LmLegalManagementDo legalManagementDo) {
        // 查询法律法规是否存在
        validateLegalRegulation(legalManagementDo.getId());
        // 查询法律法规名称是否已经存在
        LmLegalManagementDo lmLegalExistDo = lmLegalManagementRepository.selectLegalByName(legalManagementDo);
        if (!ObjectUtils.isEmpty(lmLegalExistDo)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "法律名称重复。");
        }
        // 修改法律法规
        return lmLegalManagementRepository.updateLegal(legalManagementDo);
    }

    /**
     * @param legalManagementDo
     * @return LmLegalManagementDo
     * @description 创建工单
     * @author poet_wei
     * @date 2023/9/28
     */
    public LmLegalManagementDo createLegalWorkOrder(LmLegalManagementDo legalManagementDo) {
        // 查询法律法规是否存在
        LmLegalManagementDo lmLegalExistDo = validateLegalRegulation(legalManagementDo.getId());
        legalManagementDo.setApproveStatus(ApproveStatusEnum.SOURCEPROCESSED.getCode());
        legalManagementDo.setReportStatus(ReportStatusEnum.NOTSTARTED.getCode());
        legalManagementDo.setLatest(false);
        // 创建工单
        LmLegalManagementDo legalWorkOrder = lmLegalManagementRepository.createLegalWorkOrder(legalManagementDo);
        // 历史记录表--记录查询法律法规检查工单信息
        List<LmLegalManagementDo> legalManagementDos = new ArrayList<>();
        legalManagementDos.add(lmLegalExistDo);
        iApApproveHistoryRepository.addApproveHistory(getHistoryDos(legalManagementDos));
        return legalWorkOrder;
    }

    private List<ApApproveHistoryDo> getHistoryDos(List<LmLegalManagementDo> legalManagementDos) {
        List<ApApproveHistoryDo> historyDos = new ArrayList<>();
        for (LmLegalManagementDo lmLegalManagementDo : legalManagementDos) {
            Date date = new Date();
            ApApproveHistoryDo historyApply = new ApApproveHistoryDo();
            historyApply.setBaseId(lmLegalManagementDo.getId());
            historyApply.setFormdataTypeCode(lmLegalManagementDo.getFormdataTypeCode());
            historyApply.setFormdataTypeName(lmLegalManagementDo.getFormdataTypeName());
            historyApply.setFormdataModule(lmLegalManagementDo.getFormdataModule());
            historyApply.setInstanceNode(InstanceNodeOfLegalEnum.APPLY.getName());
            historyApply.setApproveUserId(lmLegalManagementDo.getCreateBy());
            historyApply.setApproveUserName(lmLegalManagementDo.getCreateBy());
            historyApply.setApproveStartTime(date);
            historyApply.setApproveEndTime(date);
            historyApply.setApproveResult(ApproveResultEnum.APPLY.getName());
            historyApply.setStatus(ModelStatusEnum.VAILD.getCode());
            historyDos.add(historyApply);

            ApApproveHistoryDo historyAdmin = new ApApproveHistoryDo();
            historyAdmin.setBaseId(lmLegalManagementDo.getId());
            historyAdmin.setFormdataTypeCode(lmLegalManagementDo.getFormdataTypeCode());
            historyAdmin.setFormdataTypeName(lmLegalManagementDo.getFormdataTypeName());
            historyAdmin.setFormdataModule(lmLegalManagementDo.getFormdataModule());
            historyAdmin.setInstanceNode(InstanceNodeOfLegalEnum.PROCESSED.getName());
            historyAdmin.setApproveStartTime(date);
            historyAdmin.setStatus(ModelStatusEnum.VAILD.getCode());
            historyDos.add(historyAdmin);
        }
        return historyDos;
    }

    /**
     * @param lmCorporateGovernanceDo
     * @return LmCorporateGovernanceDo
     * @description 保存企业制度
     * @author poet_wei
     * @date 2023/9/28
     */
    public LmCorporateGovernanceDo saveCorporateGovernance(LmCorporateGovernanceDo lmCorporateGovernanceDo) {
        // 企业制度名称是否已存在
        LmCorporateGovernanceDo lmExistDo = lmCorporateGovernanceRepository.selectLagalByName(lmCorporateGovernanceDo);

        if (!ObjectUtils.isEmpty(lmExistDo)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "企业制度名称重复。");
        }
        // 法律依据是否存在
        List<String> nameList = new ArrayList<>();
        lmCorporateGovernanceDo.getLawIdList().forEach(lawId -> {
            LmLegalManagementDo lmLegalManagementDo = lmLegalManagementRepository.selectLegalById(lawId);
            if (ObjectUtils.isEmpty(lmLegalManagementDo)) {
                throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "法律依据不存在。");
            }
            nameList.add(lmLegalManagementDo.getLawName());
        });
        lmCorporateGovernanceDo.setStatus(ModelStatusEnum.VAILD.getCode());
        lmCorporateGovernanceDo.setLawNameList(nameList);
        return lmCorporateGovernanceRepository.saveCorporateGovernance(lmCorporateGovernanceDo);
    }

    /**
     * @param searchDo
     * @return LmLegalManagementDo
     * @description 查询企业制度
     * @author poet_wei
     * @date 2023/9/27
     */
    public PageInfo<LmCorporateGovernanceDo> selectCorporateGovernanceList(LmCorporateGovernanceSearchDo searchDo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<LmCorporateGovernanceDo> governancelList = lmCorporateGovernanceRepository.selectCorporateGovernanceList(searchDo);
        if (CollectionUtils.isEmpty(governancelList)) {
            return null;
        }
        PageInfo<LmCorporateGovernanceDo> pageInfo = new PageInfo<>(governancelList);
        pageInfo.setTotal(governancelList.size());
        return pageInfo;
    }

    /**
     * @param lmCorporateGovernanceDo
     * @return LmCorporateGovernanceDo
     * @description 修改企业制度
     * @author poet_wei
     * @date 2023/9/28
     */
    public LmCorporateGovernanceDo updateCorporateGovernance(LmCorporateGovernanceDo lmCorporateGovernanceDo) {
        LmCorporateGovernanceDo lmCorporateGovernanceExist = lmCorporateGovernanceRepository.selectCorporateGovernanceById(lmCorporateGovernanceDo.getId());
        if (ObjectUtils.isEmpty(lmCorporateGovernanceExist)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "企业制度不存在不存在。");
        }
        // 企业制度名称是否已存在
        LmCorporateGovernanceDo lmExistDo = lmCorporateGovernanceRepository.selectLagalByName(lmCorporateGovernanceDo);

        if (!ObjectUtils.isEmpty(lmExistDo)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "企业制度名称重复。");
        }
        List<String> nameList = new ArrayList<>();
        lmCorporateGovernanceDo.getLawIdList().forEach(lawId -> {
            LmLegalManagementDo lmLegalManagementDo = lmLegalManagementRepository.selectLegalById(lawId);
            if (ObjectUtils.isEmpty(lmLegalManagementDo)) {
                throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "法律依据不存在。");
            }
            nameList.add(lmLegalManagementDo.getLawName());
        });
        lmCorporateGovernanceDo.setLawNameList(nameList);
        return lmCorporateGovernanceRepository.updateCorporateGovernance(lmCorporateGovernanceDo);
    }

    public Boolean batchDeleteCorporateGovernance(LmCorporateGovernanceSearchDo searchDo, String userId) {
        List<Integer> ids = searchDo.getIds();
        List<LmCorporateGovernanceDo> lmCorporateGovernanceList = lmCorporateGovernanceRepository.selectCorporateGovernanceByIds(ids);
        if (ObjectUtils.isEmpty(lmCorporateGovernanceList) || lmCorporateGovernanceList.size() != ids.size()) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "企业制度不存在不存在。");
        }
        return lmCorporateGovernanceRepository.batchDeleteCorporateGovernance(ids, userId);
    }

    /**
     * @param id
     * @return LmCorporateGovernanceDo
     * @description 查询根据id企业制度对象
     * @author poet_wei
     * @date 2023/9/27
     */
    public LmCorporateGovernanceDo selectCorporateGovernanceById(Integer id) {
        LmCorporateGovernanceDo lmCorporateGovernanceDo = lmCorporateGovernanceRepository.selectCorporateGovernanceById(id);
        if (ObjectUtils.isEmpty(lmCorporateGovernanceDo)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "企业制度不存在。");
        }
        return lmCorporateGovernanceDo;
    }

    /**
     * @param lmLegalManagementSearchDo
     * @return LmLegalManagementDo
     * @description 查询法律法规内部制度关联
     * @author poet_wei
     * @date 2023/9/27
     */
    public List<LmCorporateGovernanceDo> selectLegalGovernance(LmLegalManagementSearchDo lmLegalManagementSearchDo) {
        // 查询法律法规是否存在
        Integer id = lmLegalManagementSearchDo.getId();
        LmLegalManagementDo lmLegalManagementDo = validateLegalRegulation(id);
        // 查询内部制度关联
        return lmCorporateGovernanceRepository.selectCorporateGovernanceByName(lmLegalManagementDo.getLawName());
    }


    /**
     * @param ids
     * @return LmCorporateGovernanceDo
     * @description 查询根据id企业制度对象
     * @author poet_wei
     * @date 2023/9/27
     */
    public List<LmCorporateGovernanceDo> selectCorporateGovernanceListById(List<Integer> ids) {
        List<LmCorporateGovernanceDo> governanceList = lmCorporateGovernanceRepository.selectCorporateGovernanceListById(ids);
        if (CollectionUtils.isEmpty(governanceList)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "企业制度不存在。");
        }
        return governanceList;
    }

    /**
     * @param
     * @return LmLegalManagementDo
     * @description 查询代办任务
     * @author poet_wei
     * @date 2023/9/15
     */
    public PageInfo<LmLegalManagementDo> selectTaskQuery(LmLegalManagementSearchDo searchDo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<LmLegalManagementDo> managementDoList = lmLegalManagementRepository.selectTaskQuery(searchDo);
        if (CollectionUtils.isEmpty(managementDoList)) {
            return null;
        }
        return new PageInfo<>(managementDoList);
    }

    /**
     * @param id 法律法规工单id userId 处置人id
     * @return Boolean
     * @description 法律法规工单开始
     * @author poet_wei
     * @date 2023/10/11
     */
    public Boolean legalReportStart(Integer id, String userId) {
        // 法律信息是否存在
        LmLegalManagementDo lmLegalManagementDo = validateLegalRegulation(id);
        // 校验处置人是否对应
        verifyLegalProcessed(lmLegalManagementDo, userId);
        // 报告是否是待开始状态
        if (!ReportStatusEnum.NOTSTARTED.getCode().equals(lmLegalManagementDo.getReportStatus())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "报告状态有误。");
        }
        String reportStatus = ReportStatusEnum.UPLOADING.getCode();
        return lmLegalManagementRepository.updateReportStartById(id, reportStatus, userId);
    }

    /**
     * @param id 法律法规工单id
     * @return Boolean
     * @description 法律法规工单 最新状态
     * @author poet_wei
     * @date 2023/10/11
     */
    public Boolean legalUpdateLatest(Integer id, Boolean latest, String userId) {
        LmLegalManagementDo lmLegalManagementDo = validateLegalRegulation(id);
        verifyLegalProcessed(lmLegalManagementDo, userId);
        lmLegalManagementDo.setLatest(latest);
        return lmLegalManagementRepository.legalUpdateLatest(lmLegalManagementDo);
    }

    /**
     * @param id 法律法规工单id
     * @return Boolean
     * @description 法律法规工单结束
     * @author poet_wei
     * @date 2023/10/11
     */
    public Boolean legalReportEnd(Integer id, String userId) {
        LmLegalManagementDo lmLegalManagementDo = validateLegalRegulation(id);
        verifyLegalProcessed(lmLegalManagementDo, userId);
        // 任务状态是否是UPLOADIN
        if (!ReportStatusEnum.UPLOADING.getCode().equals(lmLegalManagementDo.getReportStatus())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "任务报告提交失败。");
        }
        // 校验是否是最新状态 不是最新要进行 报告校验
        if (!lmLegalManagementDo.getLatest()) {
            // 是否上传了最新的报告
            if (ObjectUtils.isEmpty(lmLegalManagementDo.getLawAttachmentPendingId())) {
                throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "请上传最新的法律法规附件。");
            }
        }
        // 更新历史记录表--待处置数据
        ApApproveHistoryDo historyDo = getApApproveHistoryDo(id, lmLegalManagementDo);
        historyDo.setBaseId(id);
        historyDo.setApproveUserId(lmLegalManagementDo.getUpdateBy());
        historyDo.setApproveUserName(lmLegalManagementDo.getUpdateBy());
        historyDo.setApproveEndTime(new Date());
        historyDo.setApproveResult(ApproveResultEnum.APPROVERD.getName());
        iApApproveHistoryRepository.updateHistoryDo(historyDo);
        // 法律法规报告结束
        boolean approveStatus = lmLegalManagementRepository.legalReportEndById(id, ReportStatusEnum.END.getCode(), ApproveStatusEnum.VULNERABILITYORDERPENDING.getCode(), userId);
        // 插入历史记录表--待审批数据
        List<ApApproveHistoryDo> historyDos = new ArrayList<>();
        Date date = new Date();
        ApApproveHistoryDo historyApply = new ApApproveHistoryDo();
        historyApply.setBaseId(lmLegalManagementDo.getId());
        historyApply.setFormdataTypeCode(lmLegalManagementDo.getFormdataTypeCode());
        historyApply.setFormdataTypeName(lmLegalManagementDo.getFormdataTypeName());
        historyApply.setFormdataModule(lmLegalManagementDo.getFormdataModule());
        historyApply.setInstanceNode(InstanceNodeOfLegalEnum.ADMIN.getName());
        historyApply.setApproveStartTime(date);
        historyApply.setStatus(ModelStatusEnum.VAILD.getCode());
        historyDos.add(historyApply);
        iApApproveHistoryRepository.addApproveHistory(historyDos);
        return approveStatus;

    }


    /**
     * @param lmLegalManagementDo roleId
     * @return Boolean
     * @description 删除工单报告
     * @author poet_wei
     * @date 2023/10/11
     */
    public Boolean deleteAttachment(LmLegalManagementDo lmLegalManagementDo, String roleId) {
        // 如果不是总厂管理员 报告是否是上传中状态
        if (!RoleEnum.SYSGENERALADMIN.getRoleId().equals(roleId) && !ReportStatusEnum.UPLOADING.getCode().equals(lmLegalManagementDo.getReportStatus())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "报告状态有误。");
        }
        return lmLegalManagementRepository.deleteAttachment(lmLegalManagementDo, roleId);
    }

    /**
     * @param searchDo
     * @return LoLoopholeDo
     * @description 法规工单审批通过
     * @author poet_wei
     * @date 2023/9/12
     */
    public Boolean approved(LmLegalManagementSearchDo searchDo) {
        // 法规工单是否存在
        Integer id = searchDo.getId();
        LmLegalManagementDo lmLegalManagementDo = validateLegalRegulation(id);
        // 校验工单状态是否是待审批
        if (!ApproveStatusEnum.VULNERABILITYORDERPENDING.getCode().equals(lmLegalManagementDo.getApproveStatus())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "非法操作。");
        }
        String userId = searchDo.getUserId();
        String userName = searchDo.getUserName();
        lmLegalManagementDo.setUpdateBy(userId);
        lmLegalManagementDo.setLatest(true);
        lmLegalManagementDo.setApproveStatus(ApproveStatusEnum.VULNERABILITYORDERAPPROVED.getCode());
        boolean approvedStatus = lmLegalManagementRepository.approved(lmLegalManagementDo);
        // 审批历史表
        ApApproveHistoryDo historyDo = getApApproveHistoryDo(id, lmLegalManagementDo);
        historyDo.setBaseId(id);
        historyDo.setApproveUserId(userId);
        historyDo.setApproveUserName(userName);
        historyDo.setApproveEndTime(new Date());
        historyDo.setApproveResult(ApproveResultEnum.APPROVERD.getName());
        iApApproveHistoryRepository.updateHistoryDo(historyDo);
        return approvedStatus;
    }

    /**
     * @param  loLoopholeSearchDo
     * @return LoLoopholeDo
     * @description 法规工单审批驳回
     * @author poet_wei
     * @date 2023/9/12
     */
    public Boolean rejection(LmLegalManagementSearchDo loLoopholeSearchDo) {
        // 校验工单是否存在
        // 法规工单是否存在
        Integer id = loLoopholeSearchDo.getId();
        LmLegalManagementDo lmLegalManagementDo = validateLegalRegulation(id);
        // 校验工单状态是否是待审批
        if (!ApproveStatusEnum.VULNERABILITYORDERPENDING.getCode().equals(lmLegalManagementDo.getApproveStatus())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "非法操作。");
        }
        String userId = loLoopholeSearchDo.getUserId();
        String userName = loLoopholeSearchDo.getUserName();
        lmLegalManagementDo.setUpdateBy(userId);
        lmLegalManagementDo.setReportStatus(ReportStatusEnum.NOTSTARTED.getCode());
        lmLegalManagementDo.setLatest(false);
        lmLegalManagementDo.setApproveStatus(ApproveStatusEnum.VULNERABILITYORDERREJECTION.getCode());
        boolean rejectionStatus = lmLegalManagementRepository.rejection(lmLegalManagementDo);
        // 审批历史表
        ApApproveHistoryDo historyDo = getApApproveHistoryDo(id, lmLegalManagementDo);
        historyDo.setBaseId(id);
        historyDo.setApproveUserId(userId);
        historyDo.setApproveUserName(userName);
        historyDo.setApproveEndTime(new Date());
        historyDo.setApproveResult(ApproveResultEnum.REJECTION.getName());
        iApApproveHistoryRepository.updateHistoryDo(historyDo);
        return rejectionStatus;

    }

    private ApApproveHistoryDo getApApproveHistoryDo(Integer id, LmLegalManagementDo lmLegalManagementDo) {
        ApApproveHistorySearchDo searchDo = new ApApproveHistorySearchDo();
        searchDo.setBaseId(id);
        searchDo.setFormdataModule(lmLegalManagementDo.getFormdataModule());
        ApApproveHistoryDo historyDo = iApApproveHistoryRepository.historyDo(searchDo);
        return historyDo;
    }

    // 校验法律是否存在
    private LmLegalManagementDo validateLegalRegulation(Integer id) {
        LmLegalManagementDo lmLegalManagementDo = lmLegalManagementRepository.selectLegalById(id);
        if (ObjectUtils.isEmpty(lmLegalManagementDo)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "法律不存在。");
        }
        return lmLegalManagementDo;
    }

    // 校验处置人是否对应
    private void verifyLegalProcessed(LmLegalManagementDo lmLegalManagementDo, String userId) {
        if (!userId.equals(lmLegalManagementDo.getProcessedId())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "处置人有误。");
        }
    }

}
