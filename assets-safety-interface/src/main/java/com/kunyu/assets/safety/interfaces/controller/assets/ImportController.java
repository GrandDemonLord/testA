/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.controller.assets;

import com.alibaba.excel.EasyExcel;
import com.kunyu.assets.safety.interfaces.dto.assets.AsAssetsImportDto;
import com.kunyu.assets.safety.interfaces.dto.assets.AsAssetsImportListener;
import com.kunyu.common.exception.PlatformException;
import com.kunyu.common.result.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;


/**
* excel导入
*
* @author yangliu
* @date 2023-09-04
*/
@RestController
@RequestMapping("/import")
@Slf4j
public class ImportController {
    private final String[] EXCEL_EXTENSION = {"xls", "xlsx"};



    /**
     * 资产上架导入 监听器内封装数据返回给前端
     * 
     * @param file file
     * @return AsAssetsImportDto AsAssetsImportDto
     * @author yangliu
     * @date 2023/9/4
     */
    @RequestMapping(path = "/assets", method = RequestMethod.POST)
    public ApiResponse<List<AsAssetsImportDto>> importCustomerDaily(@RequestParam("file") MultipartFile file) {
        if (!Arrays.asList(EXCEL_EXTENSION).contains(StringUtils.getFilenameExtension(file.getOriginalFilename()))) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "这不是一个Excel文件。");
        }
        List<AsAssetsImportDto> importDtos;
        try (InputStream inputStream = file.getInputStream()) {
            importDtos = EasyExcel.read(inputStream)
                    .registerReadListener(new AsAssetsImportListener())
                    .head(AsAssetsImportDto.class)
                    .sheet()
                    .headRowNumber(2)
                    .doReadSync();
            if (CollectionUtils.isEmpty(importDtos)) {
                throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "导入数据不能为空。");
            }
        } catch (IOException ex) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "操作失败，请联系系统管理员。");
        }
        return ApiResponse.success(importDtos);
    }
}
