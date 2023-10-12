/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.model.legal;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.kunyu.common.models.BaseModel;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 企业制度管理表
 * @TableName lm_corporate_governance
 */
@Data
@ExcelIgnoreUnannotated
public class LmCorporateGovernanceDo extends BaseModel {

    @ExcelProperty("企业制度id")
    private Integer id;
    /**
     * 企业制度名称
     */
    @ColumnWidth(20)
    @ExcelProperty("企业制度名称")
    private String corporateGovernanceName;

    /**
     * 企业制度附件id
     */
    private String corporateGovernanceAttachmentId;

    /**
     * 企业制度附件名称
     */
    @ColumnWidth(20)
    @ExcelProperty("企业制度附件")
    private String corporateGovernanceAttachmentName;

    /**
     * 依据法律id
     */
    private List<Integer> lawIdList;

    /**
     * 依据法律名称
     */
    private List<String> lawNameList;

    /**
     * 依据法律名称 (导出Excel时使用)
     */
    @ColumnWidth(20)
    @ExcelProperty("依据法律名称")
    private String lawNames;

    /**
     * 发布日期
     */
    @ExcelProperty("发布日期")
    @ColumnWidth(20)
    private Date releaseDate;

    /**
     * 实施日期
     */
    @ColumnWidth(20)
    @ExcelProperty("实施日期")
    private Date implementationDate;

    // 在数据转换时将法律名称连接为一个字符串
    public void setLawNameList(List<String> lawNameList) {
        this.lawNames = String.join(", ", lawNameList); // 使用逗号分隔法律名称
    }
}