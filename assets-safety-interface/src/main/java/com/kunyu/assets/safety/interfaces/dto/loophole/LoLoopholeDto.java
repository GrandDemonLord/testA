/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.dto.loophole;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kunyu.assets.safety.interfaces.valid.loophole.LoLoopholeDtoValid;
import lombok.Data;

import javax.validation.constraints.NotBlank;
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

    private String dataTypeCode;

    private String dataTypeName;

    @NotBlank(message = "漏洞类型不能为空", groups = LoLoopholeDtoValid.class)
    private Integer loopholeTypeId;

    @NotBlank(message = "漏洞类型名称不能为空", groups = LoLoopholeDtoValid.class)
    private String loopholeTypeName;

    @NotBlank(message = "漏洞等级不能为空", groups = LoLoopholeDtoValid.class)
    private Integer loopholeLevelId;

    @NotBlank(message = "漏洞登记名称不能为空", groups = LoLoopholeDtoValid.class)
    private String loopholeLevelName;

    /**
     * 漏洞名称
     */
    @NotBlank(message = "漏洞名称不能为空", groups = LoLoopholeDtoValid.class)
    private String name;

    /**
     * 漏洞来源
     */
    @NotBlank(message = "漏洞来源不能为空", groups = LoLoopholeDtoValid.class)
    private String source;

    /**
     * 目标地址
     */
    @NotBlank(message = "目标地址不能为空", groups = LoLoopholeDtoValid.class)
    private String targetAddress;

    /**
     * 受影响的组件
     */
    @NotBlank(message = "受影响的组件不能为空", groups = LoLoopholeDtoValid.class)
    private String affectedComponents;

    /**
     * 直属/所属单位id
     */
    @NotBlank(message = "单位不能为空", groups = LoLoopholeDtoValid.class)
    private String owningUnitId;

    /**
     * 直属/所属单位name
     */
    @NotBlank(message = "单位名称不能为空", groups = LoLoopholeDtoValid.class)
    private String owningUnitName;

    /**
     * 部门id
     */
    @NotBlank(message = "部门不能为空", groups = LoLoopholeDtoValid.class)
    private String managementUnitId;

    /**
     * 部门名称
     */
    @NotBlank(message = "部门名称不能为空", groups = LoLoopholeDtoValid.class)
    private String managementUnitName;

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
    @NotBlank(message = "部门名称不能为空", groups = LoLoopholeDtoValid.class)
    private String deptId;

    /**
     * 部门id（用户组）
     */
    private String deptName;

    /**
     * 任务开始时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private LocalDate taskStartTime;

    /**
     * 任务结束时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private LocalDate taskEndTime;

    /**
     * 任务开始时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private LocalDate orderStartTime;

    /**
     * 任务结束时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private LocalDate orderEndTime;

    /**
     * 工单报告状态
     */
    private String reportStatus;
    /**
     * 负责人
     */
    @NotBlank(message = "负责人不能为空", groups = LoLoopholeDtoValid.class)
    private String responsible_person;

    /**
     * 负责人联系方式
     */
    private String contact_information;

    /**
     * 修复建议
     */
    @NotBlank(message = "修复建议不能为空", groups = LoLoopholeDtoValid.class)
    private String fixRecommendation;

    /**
     * 设备处置人
     */
    @NotBlank(message = "处置人不能为空", groups = LoLoopholeDtoValid.class)
    private String processedBy;

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


}
