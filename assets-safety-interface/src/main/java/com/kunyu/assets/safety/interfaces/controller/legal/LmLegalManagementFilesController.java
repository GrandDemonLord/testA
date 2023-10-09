/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.controller.legal;

import com.kunyu.assets.safety.application.legal.LegalManagementApplication;
import com.kunyu.assets.safety.domain.model.legal.LmCorporateGovernanceDo;
import com.kunyu.assets.safety.domain.model.legal.LmLegalManagementDo;
import com.kunyu.assets.safety.interfaces.dto.common.FilesDto;
import com.kunyu.common.exception.PlatformException;
import com.kunyu.common.result.ApiResponse;
import com.kunyu.common.util.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * 法律法规附件上传
 *
 * @author yangliu
 * @date 2023-08-28
 */
@RestController
@RequestMapping("/legalFiles")
@RefreshScope
@Slf4j
public class LmLegalManagementFilesController {

    @Value("${ky.upload.files.path:}")
    private String uploadFilesPath;

    @Value("${ky.download.files.path:}")
    private String downloadFilesPath;

    @Autowired
    private LegalManagementApplication legalManagementApplication;

    /**
     * 法律法规附件上传
     *
     * @param multipartFile multipartFile
     * @return FilesDto FilesDto
     * @author poet_wei
     * @date 2023/9/19
     */
    @RequestMapping(path = "/uploadLegal", method = RequestMethod.POST)
    public ApiResponse<FilesDto> uploadLegal(@RequestParam("file") MultipartFile multipartFile, @RequestParam("id") Integer id) {
        log.info("upload file start");
        if (ObjectUtils.isEmpty(uploadFilesPath)) {
            throw new PlatformException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未配置附件上传目录，请联系系统管理员。");
        }
        if (multipartFile.isEmpty()) {
            throw new PlatformException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "请选择一个文件上传。");
        }
        // 进行数据校验
        LmLegalManagementDo lmLegalManagementDo = legalManagementApplication.selectLegalById(id);
        String originalFileName = multipartFile.getOriginalFilename();
        log.info("upload file：{}", originalFileName);
        String originalFileNameNew;
        String fileExtension;
        try {
            File folder = new File(uploadFilesPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            fileExtension = StringUtils.getFilenameExtension(originalFileName);
            // uuid拼接附件扩展名上传 保证唯一性
            originalFileNameNew = UUID.randomUUID().toString().concat(".").concat(fileExtension);
            Path filePath = Paths.get(uploadFilesPath, originalFileNameNew);
            Files.copy(multipartFile.getInputStream(), filePath);
        } catch (Exception ex) {
            log.error("upload file：{}, error：{}", originalFileName, ex.getMessage());
            throw new PlatformException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "上传失败，系统异常。");
        }
        log.info("upload file end：{}", originalFileName);
        FilesDto filesDto = new FilesDto();
        filesDto.setFileId(originalFileNameNew);
        String lawName = lmLegalManagementDo.getLawName().concat(".").concat(fileExtension);
        filesDto.setFileName(lawName);
        legalManagementApplication.uploadLegal(id, originalFileNameNew, lawName, ThreadLocalUtil.getUserInfo().getUserId());
        return ApiResponse.success(filesDto);
    }

    /**
     * 企业制度附件上传
     *
     * @param multipartFile multipartFile
     * @return FilesDto FilesDto
     * @author poet_wei
     * @date 2023/9/19
     */
    @RequestMapping(path = "/uploadCorporateGovernance", method = RequestMethod.POST)
    public ApiResponse<FilesDto> uploadCorporateGovernance(@RequestParam("file") MultipartFile multipartFile, @RequestParam("id") Integer id) {
        log.info("upload file start");
        if (ObjectUtils.isEmpty(uploadFilesPath)) {
            throw new PlatformException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未配置附件上传目录，请联系系统管理员。");
        }
        if (multipartFile.isEmpty()) {
            throw new PlatformException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "请选择一个文件上传。");
        }
        // 进行数据校验
        LmCorporateGovernanceDo lmCorporateGovernanceDo = legalManagementApplication.selectCorporateGovernanceById(id);
        String originalFileName = multipartFile.getOriginalFilename();
        log.info("upload file：{}", originalFileName);
        String originalFileNameNew;
        String fileExtension;
        try {
            File folder = new File(uploadFilesPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            fileExtension = StringUtils.getFilenameExtension(originalFileName);
            // uuid拼接附件扩展名上传 保证唯一性
            originalFileNameNew = UUID.randomUUID().toString().concat(".").concat(fileExtension);
            Path filePath = Paths.get(uploadFilesPath, originalFileNameNew);
            Files.copy(multipartFile.getInputStream(), filePath);
        } catch (Exception ex) {
            log.error("upload file：{}, error：{}", originalFileName, ex.getMessage());
            throw new PlatformException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "上传失败，系统异常。");
        }
        log.info("upload file end：{}", originalFileName);
        FilesDto filesDto = new FilesDto();
        filesDto.setFileId(originalFileNameNew);
        String lawName = lmCorporateGovernanceDo.getCorporateGovernanceName().concat(".").concat(fileExtension);
        filesDto.setFileName(lawName);
        legalManagementApplication.uploadCorporateGovernance(id, originalFileNameNew, lawName, ThreadLocalUtil.getUserInfo().getUserId());
        return ApiResponse.success(filesDto);
    }

}
