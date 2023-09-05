/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.controller.common;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.application.assets.AssetsApplication;
import com.kunyu.assets.safety.application.assets.AssetsReportFormsApplication;
import com.kunyu.assets.safety.domain.model.assets.AsAssetsDo;
import com.kunyu.assets.safety.domain.model.assets.AsAssetsSearchDo;
import com.kunyu.assets.safety.interfaces.converter.assets.DtoDoConverter;
import com.kunyu.assets.safety.interfaces.dto.assets.AsAssetsSearchDto;
import com.kunyu.common.exception.PlatformException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
* 导出接口层
*
* @author yangliu
* @date 2023-08-30
*/
@RestController
@RequestMapping("/export")
@Slf4j
public class ExportController {
    private final Integer pageNum = 1;
    private final Integer pageSize = 200;
    private final Integer sheetSize = 3; // 每个sheet3条 todo 记得改


    private String fileName = "资产清单.xlsx";
    private final String userId = "yuxi-putongyonghu";

    @Autowired
    private AssetsApplication assetsApplication;

    @Autowired
    private AssetsReportFormsApplication assetsReportFormsApplication;

    /**
     *  资产上架清单导出 查询逻辑要跟/assets/apply/listingList接口保持一致
     *
     * @param assetsSearchDto assetsSearchDto
     * @param response response
     * @author yangliu
     * @date 2023/8/30
     */
    @RequestMapping(path = "/assets/listingList", method = RequestMethod.POST)
    public void listingList(@RequestBody AsAssetsSearchDto assetsSearchDto, HttpServletResponse response) {
        log.info("export start");
        // 这里复用资产上架清单查询接口
        AsAssetsSearchDo assetsSearchDo = DtoDoConverter.INSTANCE.getAssetsSearchDo(assetsSearchDto);
        // todo 从ThreadLocalUtil内拿
        assetsSearchDo.setCreateBy(userId);
        PageInfo<AsAssetsDo> pageInfo = assetsApplication.listingList(assetsSearchDo, pageNum, pageSize);

        // 封装数据
        encapsulatingData(response, pageInfo, assetsSearchDo);
    }

    /**
     *  资产下架清单导出 查询逻辑要跟/assets/apply/delistList接口保持一致
     *
     * @param assetsSearchDto assetsSearchDto
     * @param response response
     * @author yangliu
     * @date 2023/8/30
     */
    @RequestMapping(path = "/assets/delistList", method = RequestMethod.POST)
    public void delistList(@RequestBody AsAssetsSearchDto assetsSearchDto, HttpServletResponse response) {
        log.info("export start");
        // 这里复用资产下架清单查询接口
        AsAssetsSearchDo assetsSearchDo = DtoDoConverter.INSTANCE.getAssetsSearchDo(assetsSearchDto);
        // todo 从ThreadLocalUtil内拿
        assetsSearchDo.setCreateBy(userId);
        PageInfo<AsAssetsDo> pageInfo = assetsApplication.delistList(assetsSearchDo, pageNum, pageSize);

        // 封装数据
        encapsulatingData(response, pageInfo, assetsSearchDo);
    }

    /**
     *  资产总清单导出 查询逻辑要跟/assets/reportforms/masterList接口保持一致
     *
     * @param assetsSearchDto assetsSearchDto
     * @param response response
     * @author yangliu
     * @date 2023/8/30
     */
    @RequestMapping(path = "/assets/masterList", method = RequestMethod.POST)
    public void masterList(@RequestBody AsAssetsSearchDto assetsSearchDto, HttpServletResponse response) {
        log.info("export start");
        // 这里复用资产总清单查询接口
        AsAssetsSearchDo assetsSearchDo = DtoDoConverter.INSTANCE.getAssetsSearchDo(assetsSearchDto);
        // todo 从ThreadLocalUtil内拿
        // todo 判断权限和数据范围 总厂和分厂都可以看到数据
        assetsSearchDo.setUserId(userId);
        PageInfo<AsAssetsDo> pageInfo = assetsReportFormsApplication.masterList(assetsSearchDo, pageNum, pageSize);

        // 封装数据
        encapsulatingData(response, pageInfo, assetsSearchDo);
    }

    /**
     * 封装数据 公共方法
     *
     * @param response response
     * @param pageInfo pageInfo
     * @param assetsSearchDo assetsSearchDo
     * @return null
     * @author yangliu
     * @date 2023/8/31
     */
    private void encapsulatingData(HttpServletResponse response, PageInfo<AsAssetsDo> pageInfo, AsAssetsSearchDo assetsSearchDo) {
        List<AsAssetsDo> asAssetsDos = new ArrayList<>();
        if (null == pageInfo) {
            log.info("export total：{}", 0);
            exportExcel(response, asAssetsDos);
            return;
        }
        log.info("export total：{}", pageInfo.getTotal());

        // 防止内存溢出 进行循环查询
        if (pageInfo.getTotal() > 200) {
            int searchCount = (int) ((pageInfo.getTotal() % pageSize == 0) ? (pageInfo.getTotal() / pageSize) : ((pageInfo.getTotal() / pageSize) + 1));
            for (int i = 1; i < searchCount + 1; i++) {
                asAssetsDos.addAll(assetsApplication.listingList(assetsSearchDo, i, pageSize).getList());
            }
        } else {
            asAssetsDos.addAll(pageInfo.getList());
        }

        // 导出excel
        exportExcel(response, asAssetsDos);
    }

    /**
     * 导出excel 公共方法
     *
     * @param response response
     * @param asAssetsDos asAssetsDos
     * @return excel excel
     * @author yangliu
     * @date 2023/8/31
     */
    private void exportExcel(HttpServletResponse response, List<AsAssetsDo> asAssetsDos) {
        try (ServletOutputStream out = response.getOutputStream();
             ExcelWriter excelWriter = EasyExcel.write(out, AsAssetsDo.class).build()) {
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
                    List<AsAssetsDo> batch = asAssetsDos.subList(startIndex, endIndex);
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
