/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.controller.legal;

import com.kunyu.assets.safety.application.legal.LegalManagementApplication;
import com.kunyu.assets.safety.domain.model.legal.LmCorporateGovernanceDo;
import com.kunyu.assets.safety.domain.model.legal.LmLegalManagementDo;
import com.kunyu.assets.safety.interfaces.dto.common.FilesDto;
import com.kunyu.common.enums.RoleEnum;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
        // 校验之前是否已经上传过附件 上传过则删除之前的附件信息
        String userId = ThreadLocalUtil.getUserInfo().getUserId();
        String roleId = ThreadLocalUtil.getUserInfo().getRoleCode();
        Path path;
        if (!ObjectUtils.isEmpty(lmLegalManagementDo.getLawAttachmentPendingId()) && !ObjectUtils.isEmpty(lmLegalManagementDo.getLawAttachmentUsingId())) {
            if (RoleEnum.SYSGENERALADMIN.getRoleId().equals(roleId)) {
                path = Paths.get(uploadFilesPath, lmLegalManagementDo.getLawAttachmentUsingId());
            } else if (!userId.equals(lmLegalManagementDo.getProcessedId())) {
                throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "无权操作。");
            } else {
                // 如果目前法规状态是最新的则不需要上传
                if(lmLegalManagementDo.getLatest()){
                    throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "法律附件是最新的不需要更新附件。");
                }
                path = Paths.get(uploadFilesPath, lmLegalManagementDo.getLawAttachmentPendingId());
            }
            try {
                Files.delete(path);
            } catch (
                    IOException e) {
                log.error("error：{}", e.getMessage());
                throw new PlatformException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "上传失败，系统异常。");
            }
        }
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
        } catch (
                Exception ex) {
            log.error("upload file：{}, error：{}", originalFileName, ex.getMessage());
            throw new PlatformException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "上传失败，系统异常。");
        }
        log.info("upload file end：{}", originalFileName);
        FilesDto filesDto = new FilesDto();
        filesDto.setFileId(originalFileNameNew);
        String lawName = lmLegalManagementDo.getLawName().concat(".").concat(fileExtension);
        filesDto.setFileName(lawName);
        legalManagementApplication.uploadLegal(lmLegalManagementDo, originalFileNameNew, lawName, ThreadLocalUtil.getUserInfo().

                getUserId());
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
        String userId = ThreadLocalUtil.getUserInfo().getUserId();
        // 进行数据校验
        LmCorporateGovernanceDo lmCorporateGovernanceDo = legalManagementApplication.selectCorporateGovernanceById(id);
        // 校验之前是否已经上传过附件 上传过则删除之前的附件信息
        if (!ObjectUtils.isEmpty(lmCorporateGovernanceDo.getCorporateGovernanceAttachmentId())) {
            Path path = Paths.get(uploadFilesPath, lmCorporateGovernanceDo.getCorporateGovernanceAttachmentId());
            try {
                Files.delete(path);
            } catch (IOException e) {
                log.error("error：{}", e.getMessage());
                throw new PlatformException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "上传失败，系统异常。");
            }
        }
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
        legalManagementApplication.uploadCorporateGovernance(id, originalFileNameNew, lawName, userId);
        return ApiResponse.success(filesDto);
    }

    /**
     * @param id 法律法规工单id
     * @return Boolean
     * @description 删除附件
     * @author poet_wei
     * @date 2023/10/11
     */
    @RequestMapping(path = "/deleteAttachment/{id}", method = RequestMethod.DELETE)
    public ApiResponse<Boolean> deleteAttachment(@PathVariable("id") Integer id) {
        // 法规对象是否存在
        LmLegalManagementDo lmLegalManagementDo = legalManagementApplication.selectLegalById(id);
        if (ObjectUtils.isEmpty(lmLegalManagementDo)) {
            throw new PlatformException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "附件不存在。");
        }
        String userId = ThreadLocalUtil.getUserInfo().getUserId();
        String roleId = ThreadLocalUtil.getUserInfo().getRoleCode();
        Path path;
        lmLegalManagementDo.setUpdateBy(userId);
        if (RoleEnum.SYSGENERALADMIN.getRoleId().equals(roleId)) {
            // 校验附件是否已经删除
            if(ObjectUtils.isEmpty(lmLegalManagementDo.getLawAttachmentUsingId())){
                return ApiResponse.success(true);
            }
            path = Paths.get(uploadFilesPath, lmLegalManagementDo.getLawAttachmentUsingId());
        } else {
            if (!userId.equals(lmLegalManagementDo.getProcessedId())) {
                throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "无权操作。");
            } else {
                // 校验附件是否已经删除
                if(ObjectUtils.isEmpty(lmLegalManagementDo.getLawAttachmentPendingId())){
                    return ApiResponse.success(true);
                }
                path = Paths.get(uploadFilesPath, lmLegalManagementDo.getLawAttachmentPendingId());
            }
        }
        try {
            Files.delete(path);
        } catch (IOException e) {
            log.error("error：{}", e.getMessage());
            throw new PlatformException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "附件删除失败，系统异常。");
        }
        return ApiResponse.success(legalManagementApplication.deleteAttachment(lmLegalManagementDo, roleId));
    }
}
