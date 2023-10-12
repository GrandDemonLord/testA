package com.kunyu.assets.safety.interfaces.controller.legal;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.kunyu.assets.safety.application.legal.LegalManagementApplication;
import com.kunyu.assets.safety.domain.model.legal.LmCorporateGovernanceDo;
import com.kunyu.assets.safety.domain.model.legal.LmCorporateGovernanceSearchDo;
import com.kunyu.assets.safety.domain.model.legal.LmLegalManagementDo;
import com.kunyu.assets.safety.domain.model.legal.LmLegalManagementSearchDo;
import com.kunyu.assets.safety.interfaces.converter.legal.LmDtoDoConverter;
import com.kunyu.assets.safety.interfaces.dto.legal.LmCorporateGovernanceSearchDto;
import com.kunyu.assets.safety.interfaces.dto.legal.LmLegalManagementSearchDto;
import com.kunyu.common.exception.PlatformException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 批量导出接口层
 *
 * @author poet_wei
 * @date 2023-10-10
 */
@RestController
@RequestMapping("/export")
@Slf4j
public class LegalExportController {

    private final Integer pageNum = 1;
    private final Integer pageSize = 200;
    private final Integer sheetSize = 10; // 每个sheet 3 条，根据需要修改
    private final String legalFileName = "法律法规信息.zip"; // 压缩包文件名
    private final String corporateFileName = "企业制度信息.zip"; // 压缩包文件名
    private final String attachmentsFolderName = "法律法规附件"; // 法律法规附件文件夹名
    private final String corporateGovernanceFolderName = "企业制度附件"; // 企业制度附件文件夹名

    @Value("${ky.upload.files.path:}")
    private String uploadFilesPath;

    @Autowired
    private LegalManagementApplication legalManagementApplication;

    /**
     * @return null
     * @description 批量导出法律法规
     * @author poet_wei
     * @date 2023/10/10
     */
    @RequestMapping(path = "/legal/legalList", method = RequestMethod.POST)
    public void legalList(@RequestBody LmLegalManagementSearchDto dto, HttpServletResponse response) {
        log.info("export start");
        List<LmLegalManagementDo> legalManagementDos = legalManagementApplication.selectLegalListById(dto.getIds());
        LmLegalManagementSearchDo lmLegalManagementSearchDo = LmDtoDoConverter.INSTANCE.getLmLegalManagementSearchDo(dto);

        // 创建临时文件夹来存放导出的文件和附件
        File tempFolder = new File(System.getProperty("java.io.tmpdir") + File.separator + "exported_files");
        tempFolder.mkdirs();

        try (FileOutputStream fos = new FileOutputStream(tempFolder.getAbsolutePath() + File.separator + legalFileName);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            encapsulatingData(zos, lmLegalManagementSearchDo, legalManagementDos, tempFolder, attachmentsFolderName);

            sendZipFileToClient(response, tempFolder, legalFileName);
        } catch (IOException e) {
            log.error("export error: {}", e.getMessage());
            throw new PlatformException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "导出失败，系统异常。");
        } finally {
            // 删除临时文件夹及其内容
            deleteFolder(tempFolder);
        }
        log.info("export end");
    }

    /**
     * @return null
     * @description 批量导出企业制度
     * @author poet_wei
     * @date 2023/10/10
     */
    @RequestMapping(path = "/legal/governanceList", method = RequestMethod.POST)
    public void governanceList(@RequestBody LmCorporateGovernanceSearchDto dto, HttpServletResponse response) {
        log.info("export start");
        List<LmCorporateGovernanceDo> lmCorporateGovernanceDos = legalManagementApplication.selectCorporateGovernanceListById(dto.getIds());
        LmCorporateGovernanceSearchDo lmCorporateGovernanceSearchDo = LmDtoDoConverter.INSTANCE.getLmCorporateGovernanceSearchDo(dto);

        // 创建临时文件夹来存放导出的文件和附件
        File tempFolder = new File(System.getProperty("java.io.tmpdir") + File.separator + "exported_files");
        tempFolder.mkdirs();

        try (FileOutputStream fos = new FileOutputStream(tempFolder.getAbsolutePath() + File.separator + corporateFileName);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            encapsulatingCorporateGovernanceData(zos, lmCorporateGovernanceSearchDo, lmCorporateGovernanceDos, tempFolder, corporateGovernanceFolderName);

            sendZipFileToClient(response, tempFolder, corporateFileName);
        } catch (IOException e) {
            log.error("export error: {}", e.getMessage());
            throw new PlatformException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "导出失败，系统异常。");
        } finally {
            // 删除临时文件夹及其内容
            deleteFolder(tempFolder);
        }
        log.info("export end");
    }


    private void sendZipFileToClient(HttpServletResponse response, File tempFolder, String fileName) throws IOException {
        // 设置响应头，告诉浏览器下载 Zip 文件
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setContentType("application/zip");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
        response.setHeader(HttpHeaders.PRAGMA, "no-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0);

        // 将 Zip 文件写入响应流
        try (FileInputStream fis = new FileInputStream(tempFolder.getAbsolutePath() + File.separator + fileName)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            ServletOutputStream out = response.getOutputStream();
            while ((bytesRead = fis.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.flush();
        }
    }

    // 梳理法律法规Excel信息
    private void encapsulatingData(ZipOutputStream zos, LmLegalManagementSearchDo searchDo, List<LmLegalManagementDo> dataList, File tempFolder, String folderName) throws IOException {
        if (CollectionUtils.isEmpty(dataList)) {
            log.info("export total: {}", 0);
            return;
        }
        log.info("export total: {}", dataList.size());
        String sheetName = "法律法规信息";

        // 创建法律法规附件文件夹
        File attachmentsFolder = new File(tempFolder.getAbsolutePath() + File.separator + attachmentsFolderName);
        attachmentsFolder.mkdirs();

        // 控制每个 sheet 页的数量
        if (dataList.size() > sheetSize) {
            int sheetCount = (dataList.size() % sheetSize == 0) ? (dataList.size() / sheetSize) : ((dataList.size() / sheetSize) + 1);
            for (int i = 0; i < sheetCount; i++) {
                int startIndex = i * sheetSize;
                int endIndex = Math.min(startIndex + sheetSize, dataList.size());
                List<LmLegalManagementDo> batch = dataList.subList(startIndex, endIndex);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                // 将数据写入 Excel 文件
                writeDataToExcel(baos, batch);

                // 将每个 sheet 的数据写入 Zip 文件
                ZipEntry zipEntry = new ZipEntry(sheetName + (i + 1) + ".xlsx");
                zos.putNextEntry(zipEntry);
                zos.write(baos.toByteArray());
                zos.closeEntry();
                baos.close();
            }
        } else {
            // 直接写入一个 sheet
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // 将数据写入 Excel 文件
            writeDataToExcel(baos, dataList);

            ZipEntry zipEntry = new ZipEntry(sheetName + ".xlsx");
            zos.putNextEntry(zipEntry);
            zos.write(baos.toByteArray());
            zos.closeEntry();
            baos.close();
        }

        // 压缩法律法规附件
        compressAttachments(attachmentsFolder, zos, dataList, folderName);
    }

    // 梳理企业制度Excel信息
    private void encapsulatingCorporateGovernanceData(ZipOutputStream zos, LmCorporateGovernanceSearchDo searchDo, List<LmCorporateGovernanceDo> dataList, File tempFolder, String folderName) throws IOException {
        if (CollectionUtils.isEmpty(dataList)) {
            log.info("export total: {}", 0);
            return;
        }
        log.info("export total: {}", dataList.size());
        String sheetName = "企业制度信息";

        // 创建法律法规附件文件夹
        File attachmentsFolder = new File(tempFolder.getAbsolutePath() + File.separator + corporateGovernanceFolderName);
        attachmentsFolder.mkdirs();

        // 控制每个 sheet 页的数量
        if (dataList.size() > sheetSize) {
            int sheetCount = (dataList.size() % sheetSize == 0) ? (dataList.size() / sheetSize) : ((dataList.size() / sheetSize) + 1);
            for (int i = 0; i < sheetCount; i++) {
                int startIndex = i * sheetSize;
                int endIndex = Math.min(startIndex + sheetSize, dataList.size());
                List<LmCorporateGovernanceDo> batch = dataList.subList(startIndex, endIndex);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                // 将数据写入 Excel 文件
                writeCorporateGovernanceDataToExcel(baos, batch);

                // 将每个 sheet 的数据写入 Zip 文件
                ZipEntry zipEntry = new ZipEntry(sheetName + (i + 1) + ".xlsx");
                zos.putNextEntry(zipEntry);
                zos.write(baos.toByteArray());
                zos.closeEntry();
                baos.close();
            }
        } else {
            // 直接写入一个 sheet
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // 将数据写入 Excel 文件
            writeCorporateGovernanceDataToExcel(baos, dataList);

            ZipEntry zipEntry = new ZipEntry(sheetName + ".xlsx");
            zos.putNextEntry(zipEntry);
            zos.write(baos.toByteArray());
            zos.closeEntry();
            baos.close();
        }

        // 压缩法律法规附件
        compressCorporateGovernanceAttachments(attachmentsFolder, zos, dataList, folderName);
    }


    private void writeDataToExcel(OutputStream outputStream, List<LmLegalManagementDo> dataList) throws IOException {
        // 创建工作簿和工作
        ExcelWriter excelWriter = EasyExcel.write(outputStream, LmLegalManagementDo.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("法律法规信息").build();

        // 写入数据
        excelWriter.write(dataList, writeSheet);

        // 关闭工作簿
        excelWriter.finish();
    }

    // 处理企业制度Excel
    private void writeCorporateGovernanceDataToExcel(OutputStream outputStream, List<LmCorporateGovernanceDo> dataList) throws IOException {
        // 创建工作簿和工作
        ExcelWriter excelWriter = EasyExcel.write(outputStream, LmCorporateGovernanceDo.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("企业制度信息").build();

        // 写入数据
        excelWriter.write(dataList, writeSheet);

        // 关闭工作簿
        excelWriter.finish();
    }

    // 处理法律法规附件
    private void compressAttachments(File attachmentsFolder, ZipOutputStream zos, List<LmLegalManagementDo> dataList, String folderName) throws IOException {
        if (attachmentsFolder.exists() && attachmentsFolder.isDirectory()) {
            File folder = new File(uploadFilesPath);
            File[] attachmentFiles = attachmentsFolder.listFiles();
            // 检查文件夹是否存在
            if (folder.exists() && folder.isDirectory()) {
                // 获取文件夹中的所有文件
                File[] files = folder.listFiles();
                if (attachmentFiles != null && files != null) {
                    // 遍历文件列表
                    for (File file : files) {
                        for (LmLegalManagementDo lmLegalManagementDo : dataList) {
                            if (lmLegalManagementDo.getLawAttachmentUsingId().equals(file.getName())) {
                                FileInputStream fis = new FileInputStream(file);
                                ZipEntry zipEntry = new ZipEntry(folderName + File.separator + lmLegalManagementDo.getLawName() + file.getName().replaceAll(".*(\\..*)", "$1"));
                                zos.putNextEntry(zipEntry);
                                byte[] buffer = new byte[1024];
                                int bytesRead;
                                while ((bytesRead = fis.read(buffer)) != -1) {
                                    zos.write(buffer, 0, bytesRead);
                                }
                                fis.close();
                                zos.closeEntry();
                            }
                        }
                    }
                }
            }
        }
    }

    // 处理企业制度附件
    private void compressCorporateGovernanceAttachments(File attachmentsFolder, ZipOutputStream zos, List<LmCorporateGovernanceDo> dataList, String folderName) throws IOException {
        if (attachmentsFolder.exists() && attachmentsFolder.isDirectory()) {
            File folder = new File(uploadFilesPath);
            File[] attachmentFiles = attachmentsFolder.listFiles();
            // 检查文件夹是否存在
            if (folder.exists() && folder.isDirectory()) {
                // 获取文件夹中的所有文件
                File[] files = folder.listFiles();
                if (attachmentFiles != null && files != null) {
                    // 遍历文件列表
                    for (File file : files) {
                        for (LmCorporateGovernanceDo governanceDo : dataList) {
                            if (governanceDo.getCorporateGovernanceAttachmentId().equals(file.getName())) {
                                FileInputStream fis = new FileInputStream(file);
                                ZipEntry zipEntry = new ZipEntry(folderName + File.separator + governanceDo.getCorporateGovernanceName() + file.getName().replaceAll(".*(\\..*)", "$1"));
                                zos.putNextEntry(zipEntry);
                                byte[] buffer = new byte[1024];
                                int bytesRead;
                                while ((bytesRead = fis.read(buffer)) != -1) {
                                    zos.write(buffer, 0, bytesRead);
                                }
                                fis.close();
                                zos.closeEntry();
                            }
                        }
                    }
                }
            }
        }
    }

    private void deleteFolder(File folder) {
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteFolder(file);
                    } else {
                        file.delete();
                    }
                }
            }
            folder.delete();
        }
    }
}
