/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.controller.common;

import com.kunyu.assets.safety.interfaces.dto.common.FilesDto;
import com.kunyu.assets.safety.interfaces.valid.common.FilesDownloadValid;
import com.kunyu.assets.safety.interfaces.valid.common.FilesTemplateDownloadValid;
import com.kunyu.assets.safety.common.enums.common.FilesTemplateEnum;
import com.kunyu.common.exception.PlatformException;
import com.kunyu.common.result.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
* 附件上传和下载、模板下载
*
* @author yangliu
* @date 2023-08-28
*/
@RestController
@RequestMapping("/files")
@RefreshScope
@Slf4j
public class FilesController {

    @Value("${ky.upload.files.path:}")
    private String uploadFilesPath;

    @Value("${ky.download.files.path:}")
    private String downloadFilesPath;

    /**
     * 附件上传 公共方法
     *
     * @param multipartFile multipartFile
     * @return FilesDto FilesDto
     * @author yangliu
     * @date 2023/8/29
     */
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public ApiResponse<FilesDto> upload(@RequestParam("file") MultipartFile multipartFile) {
        log.info("upload file start");
        if (ObjectUtils.isEmpty(uploadFilesPath)) {
            throw new PlatformException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未配置附件上传目录，请联系系统管理员。");
        }
        if (multipartFile.isEmpty()) {
            throw new PlatformException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "请选择一个文件上传。");
        }
        String originalFileName = multipartFile.getOriginalFilename();
        log.info("upload file：{}", originalFileName);
        String originalFileNameNew;
        try {
            File folder = new File(uploadFilesPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            String fileExtension = StringUtils.getFilenameExtension(originalFileName);
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
        filesDto.setFileName(originalFileName);

        return ApiResponse.success(filesDto);
    }

    /**
     * 附件下载 公共方法
     *
     * @param filesDto filesDto
     * @author yangliu
     * @date 2023/8/29
     */
    @RequestMapping(path = "/download", method = RequestMethod.POST)
    public void download(@Validated(FilesDownloadValid.class) @RequestBody FilesDto filesDto,
                         HttpServletResponse response) {
        log.info("download file start：{}", filesDto.getFileName());
        String path = uploadFilesPath.concat("/").concat(filesDto.getFileId());

        // 下载附件
        downloadFile(response, path, filesDto.getFileName());
        log.info("download file end：{}", filesDto.getFileName());
    }

    /**
     * 模板下载 公共方法
     *
     * @param filesDto filesDto
     * @param response response
     */
    @RequestMapping(path = "/templateDownload", method = RequestMethod.POST)
    public void templateDownload(@Validated(FilesTemplateDownloadValid.class) @RequestBody FilesDto filesDto,
                                 HttpServletResponse response) {
        log.info("download template file start：{}", filesDto);
        if (ObjectUtils.isEmpty(downloadFilesPath)) {
            throw new PlatformException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未配置模板存放目录，请联系系统管理员。");
        }
        String templateFileName = FilesTemplateEnum.getFileNameByFileType(filesDto.getFileType());
        if (ObjectUtils.isEmpty(templateFileName)) {
            throw new PlatformException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "附件类型不存在。");
        }
        String path = downloadFilesPath.concat("/").concat(templateFileName);

        // 下载附件 文件名跟前端保持一致
        downloadFile(response, path, filesDto.getFileName());
        log.info("download template file end：{}", filesDto);
    }

    /**
     * 下载附件
     *
     * @param response response
     * @param path path
     * @param fileName fileName
     * @author yangliu
     * @date 2023/8/31
     */
    private static void downloadFile(HttpServletResponse response, String path, String fileName) {
        try (InputStream inputStream = new FileInputStream(path);
             ServletOutputStream outputStream = response.getOutputStream()) {

            response.reset();
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            byte[] b = new byte[1024];
            int len;
            // 从输入流中读取一定数量的字节，并将其存储在缓冲区字节数组中，读到末尾返回-1
            while ((len = inputStream.read(b)) > 0) {
                outputStream.write(b, 0, len);
            }
        } catch (FileNotFoundException ex) {
            log.error("download file：{}, error：{}", fileName, ex.getMessage());
            throw new PlatformException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "下载失败，附件不存在。");
        } catch (IOException ex) {
            log.error("download file：{}, error：{}", fileName, ex.getMessage());
            throw new PlatformException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "下载失败，系统异常。");
        }
    }
}
