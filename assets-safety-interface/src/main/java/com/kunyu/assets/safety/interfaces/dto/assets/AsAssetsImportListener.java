/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.dto.assets;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.kunyu.assets.safety.common.enums.assets.AssetsLevelEnum;
import com.kunyu.assets.safety.common.enums.assets.AssetsTypeEnum;
import com.kunyu.common.exception.PlatformException;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
* 资产批量导入监听器 封装数据
*
* @author yangliu
* @date 2023-09-04
*/
public class AsAssetsImportListener extends AnalysisEventListener {

    private List<AsAssetsImportDto> asAssetsImportDtos = new ArrayList<>();

    /**
     * 每解析一行，回调该方法
     *
     * @param data
     * @param context
     * @author yangliu
     * @date 2023-09-04
     */
    @Override
    public void invoke(Object data, AnalysisContext context) {
        AsAssetsImportDto importDto = (AsAssetsImportDto) data;
        // 校验非空
        validEmptyValue(context, importDto);

        // 校验下拉框
        validDropDownBox(context, importDto);

        asAssetsImportDtos.add(importDto);
    }

    /**
     * 解析完全部回调
     *
     * @param context
     * @author yangliu
     * @date 2023-09-04
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        asAssetsImportDtos.clear();
    }

    private static void validEmptyValue(AnalysisContext context, AsAssetsImportDto importDto) {
        if (StringUtils.isEmpty(importDto.getAssetName())) {
            throwErrorOfEmptyValue(context.readRowHolder().getRowIndex(), "资产名称");
        }
        if (StringUtils.isEmpty(importDto.getAssetTypeName())) {
            throwErrorOfEmptyValue(context.readRowHolder().getRowIndex(), "资产类型");
        }
        if (StringUtils.isEmpty(importDto.getAssetLevelName())) {
            throwErrorOfEmptyValue(context.readRowHolder().getRowIndex(), "资产等级");
        }
        if (StringUtils.isEmpty(importDto.getOsVersion())) {
            throwErrorOfEmptyValue(context.readRowHolder().getRowIndex(), "操作系统详细发行版本");
        }
        if (StringUtils.isEmpty(importDto.getEnvironmentComponents())) {
            throwErrorOfEmptyValue(context.readRowHolder().getRowIndex(), "必要运行环境、程序组件版本");
        }
        if (StringUtils.isEmpty(importDto.getDbVersion())) {
            throwErrorOfEmptyValue(context.readRowHolder().getRowIndex(), "数据库版本");
        }
        if (StringUtils.isEmpty(importDto.getSystemNameAndVersion())) {
            throwErrorOfEmptyValue(context.readRowHolder().getRowIndex(), "系统名称与版本");
        }
        if (StringUtils.isEmpty(importDto.getOwningNetwork())) {
            throwErrorOfEmptyValue(context.readRowHolder().getRowIndex(), "所属网络");
        }
        if (StringUtils.isEmpty(importDto.getPhysicalAddress())) {
            throwErrorOfEmptyValue(context.readRowHolder().getRowIndex(), "物理地址");
        }
        if (StringUtils.isEmpty(importDto.getNetworkAddress())) {
            throwErrorOfEmptyValue(context.readRowHolder().getRowIndex(), "网络地址");
        }
        if (StringUtils.isEmpty(importDto.getDomainName())) {
            throwErrorOfEmptyValue(context.readRowHolder().getRowIndex(), "域名");
        }
        if (StringUtils.isEmpty(importDto.getResponsiblePerson())) {
            throwErrorOfEmptyValue(context.readRowHolder().getRowIndex(), "负责人账号");
        }
        if (StringUtils.isEmpty(importDto.getContactInformation())) {
            throwErrorOfEmptyValue(context.readRowHolder().getRowIndex(), "负责人联系方式");
        }
    }

    private static void throwErrorOfEmptyValue(Integer rowIndex, String name) {
        throw new PlatformException(HttpStatus.BAD_REQUEST.value(), String.format("第【%s】行【%s】为空，请核实。", rowIndex + 1, name));
    }

    private static void validDropDownBox(AnalysisContext context, AsAssetsImportDto importDto) {
        String assetTypeId = AssetsTypeEnum.getAssetsTypeIdByName(importDto.getAssetTypeName());
        if (ObjectUtils.isEmpty(assetTypeId)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), String.format("第【%s】行【%s】为非法值，请核实。", context.readRowHolder().getRowIndex() + 1, "资产类型"));
        }
        importDto.setAssetTypeId(assetTypeId);

        String assetLevelId = AssetsLevelEnum.getAssetsLevelIdByName(importDto.getAssetLevelName());
        if (ObjectUtils.isEmpty(assetLevelId)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), String.format("第【%s】行【%s】为非法值，请核实。", context.readRowHolder().getRowIndex() + 1, "资产等级"));
        }
        importDto.setAssetLevelId(assetLevelId);
    }
}
