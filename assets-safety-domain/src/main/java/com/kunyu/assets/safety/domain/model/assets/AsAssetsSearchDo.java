/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.model.assets;

import lombok.Data;

import java.util.List;

/**
* @description
*
* @author yangliu
* @date 2023-08-25
*/
@Data
public class AsAssetsSearchDo {
    /**
     * 操作类型，listing：上架、delist：下架
     */
    private String typeOfOperation;
    private String assetName;
    private String assetTypeId;
    private String startTime;
    private String endTime;
    private String createBy;
    private String updateBy;
    private Integer status;
    /**
     * 审批状态
     */
    private String approveStatus;
    private String userId;
    private List<Integer> ids; // 导出excel
}
