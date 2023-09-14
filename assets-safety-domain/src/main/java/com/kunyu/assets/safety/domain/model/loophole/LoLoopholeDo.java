/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.model.loophole;

import com.kunyu.common.models.BaseModel;
import lombok.Data;

import java.time.LocalDate;

/**
 * 
 * @TableName lo_loophole
 */
@Data
public class LoLoopholeDo extends BaseModel {

    // 工单类型code
    private String dataTypeCode;

    // 工单类型name
    private String dataTypeName;

    // 漏洞名称
    private String name;

    // 漏洞类型id
    private Integer loopholeTypeId;

    // 漏洞类型名称
    private String loopholeTypeName;

    // 漏洞等级 id
    private Integer loopholeLevelId;

    // 漏洞等级名称
    private String loopholeLevelName;

    // 漏洞来源
    private String source;

    // 目标地址
    private String targetAddress;

    // 受影响的组件
    private String affectedComponents;

    // 任务周期开始时间（管理员指定）
    private LocalDate taskStartTime;

    // 任务周期结束时间（管理员指定）
    private LocalDate taskEndTime;

    // 任务开启时间（点击开始时间）
    private LocalDate orderStartTime;

    // 任务结束时间（点击结束时间）
    private LocalDate orderEndTime;

    // 任务状态 (待修复：fixing、已修复：fixed、已忽略：ignored)
    private String taskStatus;

    // 报告状态（待开始：pending、待上传：upload、待结束：submission、已结束：finished）
    private String reportStatus;

    // 负责人
    private String responsiblePerson;

    // 负责人联系方式
    private String contactInformation;

    // 修复建议
    private String fixRecommendation;

    // 处置人
    private String processedBy;

    // 处置人联系方式
    private String processedInformation;

    // 直属/所属单位id
    private String owningUnitId;

    // 直属/所属单位name
    private String owningUnitName;

    // 管理单位id
    private String managementUnitId;

    // 管理单位name
    private String managementUnitName;

    // 审批人账号
    private String approveUserId;

    // 审批人名称
    private String approveUserName;

    // 审批状态 (pending：待审批、approved：审批通过、rejection：审批驳回)
    private String approveStatus;

    // 修复漏洞报告
    private String remediationReport;
}