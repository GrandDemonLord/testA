/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.dto.legal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kunyu.assets.safety.interfaces.valid.legal.LmCorporateGovernanceDtoAddValid;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 企业制度查询Dto
 *
 * @TableName lm_corporate_governance
 */
@Data
public class LmCorporateGovernanceSearchDto {

    /**
     * 企业制度名称
     */
    private String corporateGovernanceName;

    /**
     * 企业制度附件id
     */
    private String corporateGovernanceAttachmentId;

    /**
     * 企业制度附件名称
     */
    private String corporateGovernanceAttachmentName;

    /**
     * 依据法律id
     */
    private List<Integer> lawIdList;

    /**
     * 依据法律名称
     */
    private List<String> lawNamesList;

    /**
     * 发布日期
     */
    private Date releaseDate;

    /**
     * 实施日期
     */
    private Date implementationDate;

    /**
     * ids
     */
    private List<Integer> ids; // 导出excel
}