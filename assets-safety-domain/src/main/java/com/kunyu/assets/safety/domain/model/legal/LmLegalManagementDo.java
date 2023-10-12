/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.model.legal;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.kunyu.common.models.BaseModel;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 法律法规管理表
 *
 * @TableName lm_legal_management
 */
@Data
@ExcelIgnoreUnannotated
public class LmLegalManagementDo extends BaseModel {

    /**
     * 表单类型code
     */
    private String formdataTypeCode;

    /**
     * 表单类型name
     */
    private String formdataTypeName;

    /**
     * 表单模块
     */
    private String formdataModule;

    /**
     * 法律法规id
     */
    @ExcelProperty("法律法规id")
    private Integer id;

    /**
     * 法律法规名称
     */
    @ColumnWidth(20)
    @ExcelProperty("法律法规名称")
    private String lawName;

    /**
     * 正在使用法律附件id
     */
    private String lawAttachmentUsingId;

    /**
     * 法律附件名称
     */
    @ColumnWidth(20)
    @ExcelProperty("法律法规附件名称")
    private String lawAttachmentName;

    /**
     * 待审批法律附件id
     */
    private String lawAttachmentPendingId;

    /**
     * 年度
     */
    @ColumnWidth(20)
    @ExcelProperty("年度")
    private String annual;

    /**
     * 适用对象
     */
    @ColumnWidth(20)
    @ExcelProperty("适用对象")
    private String applicableObject;

    /**
     * 处置人id
     */
    private String processedId;

    /**
     * 处置人
     */
    @ColumnWidth(20)
    @ExcelProperty("负责人")
    private String processedBy;

    /**
     * 处置人单位id
     */
    private String processedUnitId;

    /**
     * 处置人单位名称
     */
    @ColumnWidth(20)
    @ExcelProperty("检查单位")
    private String processedUnitName;

    /**
     * 任务开启时间（点击开始时间）
     */
    private Date orderStartTime;

    /**
     * 任务结束时间（点击结束时间）
     */
    @ColumnWidth(20)
    @ExcelProperty("检查时间")
    private LocalDateTime orderEndTime;

    /**
     * 处置人部门id
     */
    private String processedDeptId;

    /**
     * 处置人部门名称
     */
    private String processedDeptName;

    /**
     * 处置人联系方式
     */
    private String processedInformation;

    /**
     * 审批状态：pending（待审批）、approved（审批通过）、rejection（审批驳回）
     */
    private String approveStatus;

    /**
     * 任务报告状态
     */
    private String  reportStatus;

    /**
     * 任务周期开始时间（管理员指定）
     */
    private Date taskStartTime;

    /**
     * 任务周期结束时间（管理员指定）
     */
    private Date taskEndTime;

    /**
     * 发布日期
     */
    @ColumnWidth(20)
    @ExcelProperty("发布日期")
    private Date releaseDate;

    /**
     * 实施日期
     */
    @ColumnWidth(20)
    @ExcelProperty("实施日期")
    private Date implementationDate;

    /**
     * 是否是最新（true 是，false 否）
     */
    private Boolean latest;
}