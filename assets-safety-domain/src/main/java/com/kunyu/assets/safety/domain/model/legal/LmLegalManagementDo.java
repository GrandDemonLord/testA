/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.model.legal;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.kunyu.common.models.BaseModel;
import lombok.Data;

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
    @ExcelProperty("法律法规名称")
    private String lawName;

    /**
     * 正在使用法律附件id
     */
    private String lawAttachmentUsingId;

    /**
     * 法律附件名称
     */
    @ExcelProperty("法律法规附件名称")
    private String lawAttachmentName;

    /**
     * 待审批法律附件id
     */
    private String lawAttachmentPendingId;

    /**
     * 年度
     */
    @ExcelProperty("年度")
    private String annual;

    /**
     * 检查单位id
     */
    private Integer checkUnitId;

    /**
     * 检查单位名称
     */
    @ExcelProperty("检查单位")
    private String checkUnitName;

    /**
     * 负责人账号
     */
    private String responsiblePerson;

    /**
     * 负责人名称
     */
    @ExcelProperty("负责人")
    private String responsiblePersonName;

    /**
     * 检查时间
     */
    @ExcelProperty("检查时间")
    private Date checkTime;

    /**
     * 适用对象
     */
    @ExcelProperty("适用对象")
    private String applicableObject;

    /**
     * 发布日期
     */
    @ExcelProperty("发布日期")
    private Date releaseDate;

    /**
     * 实施日期
     */
    @ExcelProperty("实施日期")
    private Date implementationDate;

}