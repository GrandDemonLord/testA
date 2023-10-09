/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.dto.loophole;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.kunyu.assets.safety.common.enums.loophole.LoopholeLevelEnum;
import com.kunyu.assets.safety.common.enums.loophole.LoopholeSourceEnum;
import com.kunyu.assets.safety.common.enums.loophole.LoopholeTypeEnum;
import com.kunyu.common.exception.PlatformException;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 漏洞批量导入监听器 封装数据
 *
 * @author yangliu
 * @date 2023-09-04
 */
public class LoLoopholeImportListener extends AnalysisEventListener {

    private List<LoLoopholeImportDto> loLoopholeImportDtos = new ArrayList<>();



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
        LoLoopholeImportDto importDto = (LoLoopholeImportDto) data;
        // 校验非空
        validEmptyValue(context, importDto);

        // 校验下拉框
        validDropDownBox(context, importDto);

        loLoopholeImportDtos.add(importDto);
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
        loLoopholeImportDtos.clear();
    }

    private static void validEmptyValue(AnalysisContext context, LoLoopholeImportDto importDto) {
        if (StringUtils.isEmpty(importDto.getName())) {
            throwErrorOfEmptyValue(context.readRowHolder().getRowIndex(), "漏洞名称");
        }
        if (StringUtils.isEmpty(importDto.getLoopholeTypeName())) {
            throwErrorOfEmptyValue(context.readRowHolder().getRowIndex(), "漏洞类型");
        }
        if (StringUtils.isEmpty(importDto.getLoopholeLevelName())) {
            throwErrorOfEmptyValue(context.readRowHolder().getRowIndex(), "漏洞等级");
        }
        if (StringUtils.isEmpty(importDto.getLoopholeSourceName())) {
            throwErrorOfEmptyValue(context.readRowHolder().getRowIndex(), "漏洞来源");
        }
        if (StringUtils.isEmpty(importDto.getTargetAddress())) {
            throwErrorOfEmptyValue(context.readRowHolder().getRowIndex(), "目标地址");
        }
        if (StringUtils.isEmpty(importDto.getAffectedComponents())) {
            throwErrorOfEmptyValue(context.readRowHolder().getRowIndex(), "受影响组件");
        }
        if (StringUtils.isEmpty(importDto.getResponsiblePersonName())) {
            throwErrorOfEmptyValue(context.readRowHolder().getRowIndex(), "部门负责人名称");
        }
        if (StringUtils.isEmpty(importDto.getContactInformation())) {
            throwErrorOfEmptyValue(context.readRowHolder().getRowIndex(), "负责人联系方式");
        }
        if (StringUtils.isEmpty(importDto.getFixRecommendation())) {
            throwErrorOfEmptyValue(context.readRowHolder().getRowIndex(), "修复建议");
        }
    }

    private static void throwErrorOfEmptyValue(Integer rowIndex, String name) {
        throw new PlatformException(HttpStatus.BAD_REQUEST.value(), String.format("第【%s】行【%s】为空，请核实。", rowIndex + 1, name));
    }

    private void validDropDownBox(AnalysisContext context, LoLoopholeImportDto importDto) {
        String loopholeTypeId = LoopholeTypeEnum.getLoopholeTypeIdByName(importDto.getLoopholeTypeName());
        if (ObjectUtils.isEmpty(loopholeTypeId)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), String.format("第【%s】行【%s】为非法值，请核实。", context.readRowHolder().getRowIndex() + 1, "漏洞类型"));
        }
        importDto.setLoopholeTypeId(loopholeTypeId);

        String loopholeLevelId = LoopholeLevelEnum.getLoopholeLevelIdByName(importDto.getLoopholeLevelName());
        if (ObjectUtils.isEmpty(loopholeLevelId)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), String.format("第【%s】行【%s】为非法值，请核实。", context.readRowHolder().getRowIndex() + 1, "漏洞等级"));
        }
        importDto.setLoopholeLevelId(loopholeLevelId);

        String loopholeSourceId = LoopholeSourceEnum.getLoopholeTypeIdByName(importDto.getLoopholeSourceName());
        if (ObjectUtils.isEmpty(loopholeSourceId)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), String.format("第【%s】行【%s】为非法值，请核实。", context.readRowHolder().getRowIndex() + 1, "漏洞来源"));
        }
        importDto.setLoopholeSourceId(loopholeSourceId);
    }

}
