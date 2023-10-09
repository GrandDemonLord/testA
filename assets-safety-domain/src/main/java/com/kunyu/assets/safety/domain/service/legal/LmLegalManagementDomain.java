/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.service.legal;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.common.enums.common.ApproveResultEnum;
import com.kunyu.assets.safety.common.enums.common.FormDataTypeEnum;
import com.kunyu.assets.safety.common.enums.loophole.InstanceNodeOfLoopholeEnum;
import com.kunyu.assets.safety.domain.model.common.ApApproveHistoryDo;
import com.kunyu.assets.safety.domain.model.legal.*;
import com.kunyu.assets.safety.domain.respository.commoon.IApApproveHistoryRepository;
import com.kunyu.assets.safety.domain.respository.legal.LmCorporateGovernanceRepository;
import com.kunyu.assets.safety.domain.respository.legal.LmLegalManagementRepository;
import com.kunyu.common.enums.ModelStatusEnum;
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
        legalManagementDo.setStatus(ModelStatusEnum.VAILD.getCode());
        legalManagementDo.setApplicableObject("红塔集团");
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
        LmLegalManagementDo lmLegalManagementDo = lmLegalManagementRepository.selectLegalById(id);
        if (ObjectUtils.isEmpty(lmLegalManagementDo)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "法律不存在。");
        }
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
     * @param id, originalFileNameNew, originalFileName, userId
     * @return int
     * @description 法律法规附件上传
     * @author poet_wei
     * @date 2023/9/27
     */
    public int uploadLegal(Integer id, String originalFileNameNew, String lawName, String userId) {

        return lmLegalManagementRepository.uploadLegal(id, originalFileNameNew, lawName, userId);
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
        LmLegalManagementDo lmLegalManagementDo = lmLegalManagementRepository.selectLegalById(legalManagementDo.getId());
        if (ObjectUtils.isEmpty(lmLegalManagementDo)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "法律不存在。");
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
        LmLegalManagementDo lmLegalManagementDo = lmLegalManagementRepository.selectLegalById(legalManagementDo.getId());
        if (ObjectUtils.isEmpty(lmLegalManagementDo)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "法律不存在。");
        }
        // 创建工单
        LmLegalManagementDo legalWorkOrder = lmLegalManagementRepository.createLegalWorkOrder(lmLegalManagementDo);
        // 历史记录表--记录查询法律法规检查工单信息
        List<LmLegalManagementDo> legalManagementDos = new ArrayList<>();
        legalManagementDos.add(lmLegalManagementDo);
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
            historyApply.setInstanceNode(InstanceNodeOfLoopholeEnum.APPLY.getName());
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
            historyAdmin.setInstanceNode(InstanceNodeOfLoopholeEnum.PROCESSED.getName());
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

    public Boolean batchDeleteCorporateGovernance(List<Integer> ids, String userId) {
        List<LmCorporateGovernanceDo> lmCorporateGovernanceList = lmCorporateGovernanceRepository.selectCorporateGovernanceByIds(ids);
        if (ObjectUtils.isEmpty(lmCorporateGovernanceList) || lmCorporateGovernanceList.size() != ids.size()) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "企业制度不存在不存在。");
        }
        return lmCorporateGovernanceRepository.batchDeleteCorporateGovernance(ids, userId);
    }

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
        LmLegalManagementDo lmLegalManagementDo = lmLegalManagementRepository.selectLegalById(lmLegalManagementSearchDo.getId());
        if (ObjectUtils.isEmpty(lmLegalManagementDo)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "法律不存在。");
        }
        // 查询内部制度关联
        return lmCorporateGovernanceRepository.selectCorporateGovernanceByName(lmLegalManagementDo.getLawName());
    }
}
