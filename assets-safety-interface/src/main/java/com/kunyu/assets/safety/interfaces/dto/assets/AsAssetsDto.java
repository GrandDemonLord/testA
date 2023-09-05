/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.dto.assets;

import com.kunyu.assets.safety.interfaces.valid.assets.AsAssetsDelistValid;
import com.kunyu.assets.safety.interfaces.valid.assets.AsAssetsListingValid;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
* 资产新增dto
*
* @author yangliu
* @date 2023-08-25
*/
@Data
public class AsAssetsDto {
    @NotNull(message = "资产id不能为空", groups = AsAssetsDelistValid.class)
    private Integer id;

    /**
     * 资产名称
     */
    @NotBlank(message = "资产名称不能为空", groups = AsAssetsListingValid.class)
    private String assetName;

    /**
     * 资产类型id
     */
    @NotBlank(message = "资产类型不能为空", groups = AsAssetsListingValid.class)
    private String assetTypeId;

    /**
     * 资产类型name
     */
    @NotBlank(message = "资产类型不能为空", groups = AsAssetsListingValid.class)
    private String assetTypeName;

    /**
     * 资产等级id
     */
    @NotBlank(message = "资产等级不能为空", groups = AsAssetsListingValid.class)
    private String assetLevelId;

    /**
     * 资产等级名称
     */
    @NotBlank(message = "资产等级不能为空", groups = AsAssetsListingValid.class)
    private String assetLevelName;

    /**
     * 操作系统详细发行版本
     */
    @NotBlank(message = "操作系统详细发行版本不能为空", groups = AsAssetsListingValid.class)
    private String osVersion;

    /**
     * 必要运行环境、程序组件版本
     */
    @NotBlank(message = "必要运行环境、程序组件版本不能为空", groups = AsAssetsListingValid.class)
    private String environmentComponents;

    /**
     * 数据库版本
     */
    @NotBlank(message = "数据库版本不能为空", groups = AsAssetsListingValid.class)
    private String dbVersion;

    /**
     * 系统名称与版本
     */
    @NotBlank(message = "系统名称与版本不能为空", groups = AsAssetsListingValid.class)
    private String systemNameAndVersion;

    /**
     * 所属单位id
     */
    @NotBlank(message = "所属单位不能为空", groups = AsAssetsListingValid.class)
    private String owningUnitId;

    /**
     * 所属单位名称
     */
    @NotBlank(message = "所属单位不能为空", groups = AsAssetsListingValid.class)
    private String owningUnitName;

    /**
     * 所属网络
     */
    @NotBlank(message = "所属网络不能为空", groups = AsAssetsListingValid.class)
    private String owningNetwork;

    /**
     * 物理地址
     */
    @NotBlank(message = "物理地址不能为空", groups = AsAssetsListingValid.class)
    private String physicalAddress;

    /**
     * 网络地址
     */
    @NotBlank(message = "网络地址不能为空", groups = AsAssetsListingValid.class)
    private String networkAddress;

    /**
     * 域名
     */
    @NotBlank(message = "域名不能为空", groups = AsAssetsListingValid.class)
    private String domainName;

    /**
     * 管理单位id
     */
    @NotBlank(message = "管理单位不能为空", groups = AsAssetsListingValid.class)
    private String managementUnitId;

    /**
     * 管理单位
     */
    @NotBlank(message = "管理单位不能为空", groups = AsAssetsListingValid.class)
    private String managementUnitName;

    /**
     * 负责人
     */
    @NotBlank(message = "负责人不能为空", groups = AsAssetsListingValid.class)
    private String responsiblePerson;

    /**
     * 负责人联系方式
     */
    @NotBlank(message = "负责人联系方式不能为空", groups = AsAssetsListingValid.class)
    private String contactInformation;

    /**
     * 渗透测试报告id
     */
    @NotBlank(message = "渗透测试报告不能为空", groups = AsAssetsListingValid.class)
    private String penetrationTestReportId;

    /**
     * 渗透测试报告
     */
    @NotBlank(message = "渗透测试报告不能为空", groups = AsAssetsListingValid.class)
    private String penetrationTestReportName;

    /**
     * 基线检查报告id
     */
    @NotBlank(message = "基线检查报告不能为空", groups = AsAssetsListingValid.class)
    private String baselineCheckReportId;

    /**
     * 基线检查报告
     */
    @NotBlank(message = "基线检查报告不能为空", groups = AsAssetsListingValid.class)
    private String baselineCheckReportName;

    /**
     * 漏洞扫描报告id
     */
    @NotBlank(message = "漏洞扫描报告不能为空", groups = AsAssetsListingValid.class)
    private String vulnerabilityScanReportId;

    /**
     * 漏洞扫描报告
     */
    @NotBlank(message = "漏洞扫描报告不能为空", groups = AsAssetsListingValid.class)
    private String vulnerabilityScanReportName;

    /**
     * 其他报告id
     */
    private String otherReportsId;

    /**
     * 其他报告
     */
    private String otherReportsName;

    /**
     * 下架原因
     */
    @NotBlank(message = "下架原因不能为空", groups = AsAssetsDelistValid.class)
    private String reasonOfDelist;
}
