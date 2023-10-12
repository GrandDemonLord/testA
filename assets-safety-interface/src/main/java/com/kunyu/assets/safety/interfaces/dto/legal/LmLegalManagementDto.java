/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.dto.legal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kunyu.assets.safety.interfaces.valid.legal.LmLagalManagementDtoAddValid;
import com.kunyu.assets.safety.interfaces.valid.legal.LmLagalManagementDtoCheckValid;
import com.kunyu.assets.safety.interfaces.valid.legal.LmLagalManagementDtoUpdateValid;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 法律法规管理表
 *
 * @TableName lm_legal_management
 */
@Data
public class LmLegalManagementDto {

    /**
     * 法律法规id
     */
    @NotNull(message = "法律法规不能为空", groups = {LmLagalManagementDtoUpdateValid.class, LmLagalManagementDtoCheckValid.class})
    private Integer id;

    /**
     * 法律法规名称
     */
    @NotNull(message = "法律法规名称不能为空", groups = {LmLagalManagementDtoAddValid.class, LmLagalManagementDtoUpdateValid.class})
    private String lawName;

    /**
     * 正在使用法律附件id
     */
    private String lawAttachmentUsingId;

    /**
     * 法律附件名称
     */
    private String lawAttachmentName;

    /**
     * 待审批法律附件id
     */
    private String lawAttachmentPendingId;

    /**
     * 年度
     */
    @NotNull(message = "未选择年度", groups = {LmLagalManagementDtoAddValid.class, LmLagalManagementDtoUpdateValid.class})
    private String annual;

    /**
     * 适用对象
     */
    private String applicableObject;

    /**
     * 发布日期
     */
    private Date releaseDate;

    /**
     * 实施日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    @NotNull(message = "未填写实施日期", groups = {LmLagalManagementDtoAddValid.class, LmLagalManagementDtoUpdateValid.class})
    private Date implementationDate;
    /**
     * 处置人id
     */
    private String processedId;

    /**
     * 处置人
     */
    @NotNull(message = "未填写负责人账号", groups = LmLagalManagementDtoCheckValid.class)
    private String processedBy;

    /**
     * 处置人单位id
     */
    @NotNull(message = "未填写检查单位", groups = LmLagalManagementDtoCheckValid.class)
    private String processedUnitId;

    /**
     * 处置人单位名称
     */
    @NotNull(message = "未填写检查单位", groups = LmLagalManagementDtoCheckValid.class)
    private String processedUnitName;

    /**
     * 处置人部门id
     */
    @NotNull(message = "未填写检查部门", groups = LmLagalManagementDtoCheckValid.class)
    private String processedDeptId;

    /**
     * 处置人部门名称
     */
    @NotNull(message = "未填写负责人部门", groups = LmLagalManagementDtoCheckValid.class)
    private String processedDeptName;

    /**
     * 处置人联系方式
     */
    @NotNull(message = "未填写处置人联系方式", groups = LmLagalManagementDtoCheckValid.class)
    private String processedInformation;

    /**
     * 审批状态：pending（待审批）、approved（审批通过）、rejection（审批驳回）
     */
    private String approveStatus;

    /**
     * 任务报告状态
     */
    private String reportStatus;

    /**
     * 是否是最新（true 是，false 否）
     */
    private Boolean latest;

    /**
     * 任务周期开始时间（管理员指定）
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    @NotNull(message = "未填任务周期开始时间", groups = {LmLagalManagementDtoUpdateValid.class})
    private Date taskStartTime;

    /**
     * 任务周期结束时间（管理员指定）
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    @NotNull(message = "未填任务周期结束时间", groups = {LmLagalManagementDtoUpdateValid.class})
    private Date taskEndTime;

    /**
     * 任务开启时间（点击开始时间）
     */
    private Date orderStartTime;

    /**
     * 任务结束时间（点击结束时间）
     */
    private Date orderEndTime;
}