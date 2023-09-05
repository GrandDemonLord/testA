/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.infrastructure.po.assets;

import com.kunyu.common.models.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * 资产表 as_assets
 *
 * @author yangliu
 * @date 2023-08-25
 */
@Data
public class AsAssetsPo extends BaseModel {
    /**
     * 资产名称
     */
    private String assetName;

    /**
     * 资产类型id
     */
    private String assetTypeId;

    /**
     * 资产类型name
     */
    private String assetTypeName;

    /**
     * 资产等级id
     */
    private String assetLevelId;

    /**
     * 资产等级名称
     */
    private String assetLevelName;

    /**
     * 操作系统详细发行版本
     */
    private String osVersion;

    /**
     * 必要运行环境、程序组件版本
     */
    private String environmentComponents;

    /**
     * 数据库版本
     */
    private String dbVersion;

    /**
     * 系统名称与版本
     */
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
    private String owningNetwork;

    /**
     * 物理地址
     */
    private String physicalAddress;

    /**
     * 网络地址
     */
    private String networkAddress;

    /**
     * 域名
     */
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
     * 负责人
     */
    private String responsiblePerson;

    /**
     * 负责人联系方式
     */
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

    /**
     * 操作类型，listing：上架申请、delist：下架申请
     */
    private String typeOfOperation;

    /**
     * 审批人账号
     */
    private String approveUserId;

    /**
     * 审批人姓名
     */
    private String approveUserName;

    /**
     * 审批时间
     */
    private Date approveTime;

    /**
     * 审批状态，pending：待审批、approved：审批通过、rejection：审批驳回
     */
    private String approveStatus;

    /**
     * 资产状态，资产状态，valid：使用中、inValid：已下架
     */
    private String assetStatus;

    /**
     * 下架原因
     */
    private String reasonOfDelist;
}