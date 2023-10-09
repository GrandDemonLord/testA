/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.dto.loophole;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.kunyu.assets.safety.interfaces.valid.loophole.LoLoopholeDtoValid;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 漏洞信息导入dto
 *
 * @author poet_wei
 * @date 2023-09-20
 */
@Data
@ExcelIgnoreUnannotated
public class LoLoopholeImportDto {
    /**
     * 漏洞名称
     */
    @ExcelProperty
    @NotBlank(message = "漏洞名称不能为空", groups = LoLoopholeDtoValid.class)
    @Size(max = 255, message = "漏洞名称长度不能超过255", groups = LoLoopholeDtoValid.class)
    private String Name;

    /**
     * 漏洞类型id
     */
    private String loopholeTypeId;

    /**
     * 漏洞类型name
     */
    @ExcelProperty
    private String loopholeTypeName;

    /**
     * 漏洞等级id
     */
    private String loopholeLevelId;

    /**
     * 漏洞等级名称
     */
    @ExcelProperty
    private String loopholeLevelName;

    /**
     * 漏洞来源id
     */
    private String loopholeSourceId;

    /**
     * 漏洞来源名称
     */
    @ExcelProperty
    private String loopholeSourceName;

    /**
     * 目标地址
     */
    @ExcelProperty
    private String targetAddress;

    /**
     * 受影响的组件
     */
    @ExcelProperty
    private String affectedComponents;

    /**
     * 部门负责人账号
     */
    private String responsiblePerson;

    /**
     * 部门负责人名称
     */
    @ExcelProperty
    private String responsiblePersonName;

    /**
     * 负责人联系方式
     */
    @ExcelProperty
    private String contactInformation;

    /**
     * 负责人单位id
     */
    private String responsibleUnitId;

    /**
     * 负责人单位名称
     */
    private String responsibleUnitName;

    /**
     * 负责人部门id
     */
    private String responsibleDeptId;

    /**
     * 负责人部门名称
     */
    private String responsibleDeptName;

    /**
     * 修复建议
     */
    @ExcelProperty
    private String fixRecommendation;


}
