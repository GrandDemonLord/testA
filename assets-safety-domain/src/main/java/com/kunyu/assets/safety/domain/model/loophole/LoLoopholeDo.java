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

    // 表单类型code
    private String formdataTypeCode;

    // 表单类型name
    private String formdataTypeName;

    // 表单模块
    private String formdataModule;

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

    // 漏洞来源id
    private String loopholeSourceId;

    // 漏洞来源名称
    private String loopholeSourceName;

    // 目标地址
    private String targetAddress;

    // 受影响的组件
    private String affectedComponents;

    // 任务周期开始时间（管理员指定）
    private String taskStartTime;

    // 任务周期结束时间（管理员指定）
    private String taskEndTime;

    // 任务开启时间（点击开始时间）
    private String orderStartTime;

    // 任务结束时间（点击结束时间）
    private String orderEndTime;

    // 任务状态 (待修复：fixing、已修复：fixed、已忽略：ignored)
    private String taskStatus;

    // 报告状态（待开始：pending、待上传：upload、待结束：submission、已结束：finished）
    private String reportStatus;

    // 负责人id
    private String responsiblePerson;

    // 负责人
    private String responsiblePersonName;

    // 负责人联系方式
    private String contactInformation;

    // 修复建议
    private String fixRecommendation;

    // 处置人
    private String processedBy;

    // 处置人id
    private String processedId;

    // 处置人联系方式
    private String processedInformation;

    // 负责人单位id
    private String responsibleUnitId;

    // 负责人单位名称
    private String responsibleUnitName;

    // 负责人部门id
    private String responsibleDeptId;

    // 负责人部门名称
    private String responsibleDeptName;

    // 处置人单位id
    private String processedUnitId;

    // 处置人单位名称
    private String processedUnitName;

    // 处置人部门id
    private String processedDeptId;

    // 处置人部门名称
    private String processedDeptName;

    // 审批人账号
    private String approveUserId;

    // 审批人名称
    private String approveUserName;

    // 审批状态 (pending：待审批、approved：审批通过、rejection：审批驳回)
    private String approveStatus;

    // 修复漏洞报告id
    private String loopholeReportId;

    // 修复漏洞报告名称
    private String loopholeReportName;
}