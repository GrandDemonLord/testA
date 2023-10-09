/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.dto.loophole;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kunyu.assets.safety.interfaces.valid.loophole.LoLoopholeDtoValid;
import com.kunyu.assets.safety.interfaces.valid.loophole.LoLoopholeOrderDtoValid;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author poet_wei
 * @description 漏洞信息Dto
 * @date 2023-09-06 14:04
 */
@Data
public class LoLoopholeDto {

    private Integer id;

    // 表单类型code
    private String formdataTypeCode;

    // 表单类型name
    private String formdataTypeName;

    // 表单模块
    private String formdataModule;

    @NotNull(message = "漏洞类型不能为空", groups = LoLoopholeDtoValid.class)
    private Integer loopholeTypeId;

    @NotBlank(message = "漏洞类型名称不能为空", groups = {LoLoopholeDtoValid.class,LoLoopholeOrderDtoValid.class})
    private String loopholeTypeName;

    @NotNull(message = "漏洞等级不能为空", groups = {LoLoopholeDtoValid.class,LoLoopholeOrderDtoValid.class})
    private Integer loopholeLevelId;

    @NotBlank(message = "漏洞等级名称不能为空", groups = {LoLoopholeDtoValid.class,LoLoopholeOrderDtoValid.class})
    private String loopholeLevelName;

    /**
     * 漏洞名称
     */
    @NotBlank(message = "漏洞名称不能为空", groups = {LoLoopholeDtoValid.class,LoLoopholeOrderDtoValid.class})
    private String name;

    /**
     * 漏洞来源id
     */
    @NotBlank(message = "漏洞来源id不能为空", groups = {LoLoopholeDtoValid.class,LoLoopholeOrderDtoValid.class})
    private String loopholeSourceId;

    /**
     * 漏洞来源名称
     */
    @NotBlank(message = "漏洞来源名称不能为空", groups = {LoLoopholeDtoValid.class,LoLoopholeOrderDtoValid.class})
    private String loopholeSourceName;

    /**
     * 目标地址
     */
    @NotBlank(message = "目标地址不能为空", groups = {LoLoopholeDtoValid.class,LoLoopholeOrderDtoValid.class})
    private String targetAddress;

    /**
     * 受影响的组件
     */
    @NotBlank(message = "受影响的组件不能为空", groups = {LoLoopholeDtoValid.class,LoLoopholeOrderDtoValid.class})
    private String affectedComponents;

    /**
     * 单位（用户组）
     */
    private String unitId;
    /**
     * 单位id（用户组）
     */
    private String unitName;

    /**
     * 部门（用户组）
     */
    private String deptId;

    /**
     * 部门id（用户组）
     */
    private String deptName;

    /**
     * 任务开始时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private String taskStartTime;

    /**
     * 任务结束时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private String taskEndTime;

    /**
     * 任务开始时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    @NotBlank(message = "任务开始时间不能为空", groups = LoLoopholeOrderDtoValid.class)
    private String orderStartTime;

    /**
     * 任务结束时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    @NotBlank(message = "任务结束时间不能为空", groups = LoLoopholeOrderDtoValid.class)
    private String orderEndTime;

    /**
     * 工单报告状态
     */
    private String reportStatus;
    /**
     * 负责人
     */
    @NotBlank(message = "负责人账号不能为空", groups = LoLoopholeDtoValid.class)
    private String responsiblePerson;

    /**
     * 负责人
     */
    @NotBlank(message = "负责人名称不能为空", groups = LoLoopholeDtoValid.class)
    private String responsiblePersonName;

    /**
     * 负责人单位id
     */
    @NotBlank(message = "负责人单位id不能为空", groups = LoLoopholeDtoValid.class)
    private String responsibleUnitId;

    /**
     * 负责人单位名称
     */
    @NotBlank(message = "负责人单位名称不能为空", groups = LoLoopholeDtoValid.class)
    private String responsibleUnitName;

    /**
     * 负责人部门id
     */
    @NotBlank(message = "负责人部门id不能为空", groups = LoLoopholeDtoValid.class)
    private String responsibleDeptId;

    /**
     * 负责人部门
     */
    @NotBlank(message = "负责人部门名称不能为空", groups = LoLoopholeOrderDtoValid.class)
    private String responsibleDeptName;

    /**
     * 处置人单位id
     */
    @NotBlank(message = "处置人单位id不能为空", groups = LoLoopholeOrderDtoValid.class)
    private String processedUnitId;

    /**
     * 负责人单位名称
     */
    @NotBlank(message = "处置人单位名称不能为空", groups = LoLoopholeOrderDtoValid.class)
    private String processedUnitName;

    /**
     * 处置人部门id
     */
    @NotBlank(message = "处置人部门id不能为空", groups = LoLoopholeOrderDtoValid.class)
    private String processedDeptId;

    /**
     * 处置人部门名称
     */
    @NotBlank(message = "处置人部门名称不能为空", groups = LoLoopholeOrderDtoValid.class)
    private String processedDeptName;
    /**
     * 负责人联系方式
     */
    private String contactInformation;

    /**
     * 修复建议
     */
    @NotBlank(message = "修复建议不能为空", groups = {LoLoopholeDtoValid.class,LoLoopholeOrderDtoValid.class})
    private String fixRecommendation;

    /**
     * 设备处置人
     */
    private String processedBy;

    /**
     * 处置人id
     */
    private String processedId;

    /**
     * 处置人联系方式
     */
    private String processedInformation;

    /**
     * 审批人账号
     */
    private String approveUserId;

    /**
     * 审批人名称
     */
    private String approveUserName;

    /**
     * 审批状态
     */
    private String approveStatus;

}
