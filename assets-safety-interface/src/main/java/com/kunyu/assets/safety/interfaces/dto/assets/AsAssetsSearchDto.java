/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.dto.assets;

import lombok.Data;

import java.util.List;

/**
* 资产查询dto
*
* @author yangliu
* @date 2023-08-25
*/
@Data
public class AsAssetsSearchDto {
    private String assetName;
    private String assetTypeId;
    private String startTime;
    private String endTime;
    private List<Integer> ids; // 导出excel
}
