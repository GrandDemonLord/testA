/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.model.loophole;

import com.kunyu.common.models.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * 
 * @TableName lo_loophole
 */
@Data
public class LoLoopholeDo extends BaseModel {

    /**
     * 漏洞名称
     */
    private String name;

    /**
     * 漏洞类型
     */
    private String type;

    /**
     * 漏洞等级 
     */
    private String level;

    /**
     * 漏洞来源 
     */
    private String source;

    /**
     * 目标地址 
     */
    private String targetAddress;

    /**
     * 受影响的组件
     */
    private String affectedComponents;

    /**
     * 任务开始时间
     */
    private Date taskStartTime;

    /**
     * 任务结束时间
     */
    private Date taskEndTime;

    /**
     * 任务状态 
     */
    private String taskStatus;

    /**
     * 单位id（用户组）
     */
    private String groupUnitId;

    /**
     * 单位（用户组）
     */
    private String groupUnit;

    /**
     * 部门id（用户组）
     */
    private String groupDepartmentId;

    /**
     * 部门（用户组）
     */
    private String groupDepartment;

    /**
     * 负责人
     */
    private String responsible;

    /**
     * 负责人联系方式
     */
    private String phone;

    /**
     * 设备处置人
     */
    private String disposer;

    /**
     * 修复建议
     */
    private String fixRecommendation;

    /**
     * 审批人
     */
    private String approver;

    /**
     * 审批状态
     */
    private String approvalStatus;

    /**
     * 修复漏洞报告
     */
    private String remediationReport;

}