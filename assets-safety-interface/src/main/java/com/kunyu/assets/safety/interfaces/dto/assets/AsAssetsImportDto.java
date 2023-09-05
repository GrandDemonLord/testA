/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.dto.assets;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.kunyu.assets.safety.interfaces.valid.assets.AsAssetsListingValid;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
* 资产导入dto
*
* @author yangliu
* @date 2023-09-04
*/
@Data
@ExcelIgnoreUnannotated
public class AsAssetsImportDto {
    /**
     * 资产名称
     */
    @ExcelProperty
    @NotBlank(message = "资产名称不能为空", groups = AsAssetsListingValid.class)
    @Size(max = 255, message = "资产名称长度不能超过255", groups = AsAssetsListingValid.class)
    private String assetName;

    /**
     * 资产类型id
     */
    private String assetTypeId;

    /**
     * 资产类型name
     */
    @ExcelProperty
    private String assetTypeName;

    /**
     * 资产等级id
     */
    private String assetLevelId;

    /**
     * 资产等级名称
     */
    @ExcelProperty
    private String assetLevelName;

    /**
     * 操作系统详细发行版本
     */
    @ExcelProperty
    private String osVersion;

    /**
     * 必要运行环境、程序组件版本
     */
    @ExcelProperty
    private String environmentComponents;

    /**
     * 数据库版本
     */
    @ExcelProperty
    private String dbVersion;

    /**
     * 系统名称与版本
     */
    @ExcelProperty
    private String systemNameAndVersion;

    /**
     * 所属单位id
     */
    private String owningUnitId;

    /**
     * 所属单位名称
     */
    private String owningUnitName;

    /**
     * 所属网络
     */
    @ExcelProperty
    private String owningNetwork;

    /**
     * 物理地址
     */
    @ExcelProperty
    private String physicalAddress;

    /**
     * 网络地址
     */
    @ExcelProperty
    private String networkAddress;

    /**
     * 域名
     */
    @ExcelProperty
    private String domainName;

    /**
     * 管理单位id
     */
    private String managementUnitId;

    /**
     * 管理单位
     */
    private String managementUnitName;

    /**
     * 负责人账号
     */
    @ExcelProperty
    private String responsiblePerson;

    /**
     * 负责人联系方式
     */
    @ExcelProperty
    private String contactInformation;

    /**
     * 渗透测试报告id
     */
    private String penetrationTestReportId;

    /**
     * 渗透测试报告
     */
    private String penetrationTestReportName;

    /**
     * 基线检查报告id
     */
    private String baselineCheckReportId;

    /**
     * 基线检查报告
     */
    private String baselineCheckReportName;

    /**
     * 漏洞扫描报告id
     */
    private String vulnerabilityScanReportId;

    /**
     * 漏洞扫描报告
     */
    private String vulnerabilityScanReportName;

    /**
     * 其他报告id
     */
    private String otherReportsId;

    /**
     * 其他报告
     */
    private String otherReportsName;
}
