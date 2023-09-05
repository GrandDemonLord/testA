package com.kunyu.assets.safety.common.enums.common;

/**
* 模板附件枚举类
*
* @author yangliu
* @date 2023-08-31
*/
public enum FilesTemplateEnum {

    ASSETSIMPORT("assetsImport", "资产批量导入模板.xlsx");

    private String fileType;
    private String fileName;

    FilesTemplateEnum(String fileType, String fileName) {
        this.fileType = fileType;
        this.fileName = fileName;
    }

    public static String getFileNameByFileType(String fileType) {
        for (FilesTemplateEnum template : FilesTemplateEnum.values()) {
            if (template.fileType.equals(fileType)) {
                return template.fileName;
            }
        }
        return null;
    }
}
