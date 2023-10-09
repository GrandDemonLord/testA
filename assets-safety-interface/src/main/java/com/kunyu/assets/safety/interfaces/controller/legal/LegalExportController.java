/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.controller.legal;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.application.assets.AssetsApplication;
import com.kunyu.assets.safety.application.assets.AssetsReportFormsApplication;
import com.kunyu.assets.safety.application.legal.LegalManagementApplication;
import com.kunyu.assets.safety.domain.model.assets.AsAssetsDo;
import com.kunyu.assets.safety.domain.model.assets.AsAssetsSearchDo;
import com.kunyu.assets.safety.domain.model.legal.LmLegalManagementDo;
import com.kunyu.assets.safety.domain.model.legal.LmLegalManagementSearchDo;
import com.kunyu.assets.safety.interfaces.converter.assets.AsDtoDoConverter;
import com.kunyu.assets.safety.interfaces.converter.legal.LmDtoDoConverter;
import com.kunyu.assets.safety.interfaces.dto.assets.AsAssetsSearchDto;
import com.kunyu.assets.safety.interfaces.dto.legal.LmLegalManagementSearchDto;
import com.kunyu.common.exception.PlatformException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 法律法规导出接口层
 *
 * @author poet_wei
 * @date 2023-08-30
 */
@RestController
@RequestMapping("/export")
@Slf4j
public class LegalExportController {
    private final Integer pageNum = 1;
    private final Integer pageSize = 200;
    private final Integer sheetSize = 10; // 每个sheet3条 todo 记得改


    private String fileName = "法律法规信息.xlsx";

    @Autowired
    private LegalManagementApplication legalManagementApplication;


    /**
     * 资产上架清单导出 查询逻辑要跟/assets/apply/listingList接口保持一致
     *
     * @param response  response
     * @author yangliu
     * @date 2023/8/30
     */
    @RequestMapping(path = "/legal/listingList", method = RequestMethod.POST)
    public void listingList(@RequestBody LmLegalManagementSearchDto dto, HttpServletResponse response) {
        log.info("export start");
        // 这里复用查询接口
        List<LmLegalManagementDo> legalManagementDos = legalManagementApplication.selectLegalListById(dto.getIds());
        LmLegalManagementSearchDo lmLegalManagementSearchDo = LmDtoDoConverter.INSTANCE.getLmLegalManagementSearchDo(dto);
        // 封装数据
        encapsulatingData(response,lmLegalManagementSearchDo, legalManagementDos);
    }

    /**
     * 封装数据 公共方法
     *
     * @param response response
     * @return null
     * @author yangliu
     * @date 2023/8/31
     */
    private void encapsulatingData(HttpServletResponse response, LmLegalManagementSearchDo lmLegalManagementSearchDo,List<LmLegalManagementDo> legalManagementDoList) {
        if (CollectionUtils.isEmpty(legalManagementDoList)) {
            log.info("export total：{}", 0);
            exportExcel(response, legalManagementDoList);
            return;
        }
        log.info("export total：{}", legalManagementDoList.size());
        List<LmLegalManagementDo> lmLegalManagementDos = new ArrayList<>();
        // 防止内存溢出 进行循环查询
        if (legalManagementDoList.size() > 200) {
            int searchCount = (int) ((legalManagementDoList.size() % pageSize == 0) ? (legalManagementDoList.size() / pageSize) : ((legalManagementDoList.size() / pageSize) + 1));
            for (int i = 1; i < searchCount + 1; i++) {
                lmLegalManagementDos.addAll(legalManagementApplication.searchLegalList(lmLegalManagementSearchDo, i, pageSize).getList());
            }
        } else {
            lmLegalManagementDos.addAll(legalManagementDoList);
        }
        // 导出excel
        exportExcel(response, lmLegalManagementDos);
    }

    /**
     * 导出excel 公共方法
     *
     * @param response           response
     * @param  asAssetsDos
     * @return excel excel
     * @author yangliu
     * @date 2023/8/31
     */
    private void exportExcel(HttpServletResponse response, List<LmLegalManagementDo> asAssetsDos) {
        try (ServletOutputStream out = response.getOutputStream();
             ExcelWriter excelWriter = EasyExcel.write(out, LmLegalManagementDo.class).build()) {
            response.reset();
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.flushBuffer();

            String sheetName = "资产清单";
            int totalSize = asAssetsDos.size();

            // 控制每条sheet页的数量
            if (totalSize > sheetSize) {
                int sheetCount = (totalSize % sheetSize == 0) ? (totalSize / sheetSize) : ((totalSize / sheetSize) + 1);
                for (int i = 0; i < sheetCount; i++) {
                    int startIndex = i * sheetSize;
                    int endIndex = Math.min(startIndex + sheetSize, totalSize);
                    List<LmLegalManagementDo> batch = asAssetsDos.subList(startIndex, endIndex);
                    WriteSheet writeSheet = EasyExcel.writerSheet(i, sheetName + (i + 1)).build();
                    excelWriter.write(batch, writeSheet);
                }
            } else {
                WriteSheet writeSheet = EasyExcel.writerSheet(0, sheetName).build();
                excelWriter.write(asAssetsDos, writeSheet);
            }
            excelWriter.finish();
            // EasyExcel.write(out, AsAssetsDo.class).sheet("资产清单").doWrite(asAssetsDos);
        } catch (IOException ex) {
            log.error("export error：{}", ex.getMessage());
            throw new PlatformException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "导出失败，系统异常。");
        }
        log.info("export end");
    }
}
