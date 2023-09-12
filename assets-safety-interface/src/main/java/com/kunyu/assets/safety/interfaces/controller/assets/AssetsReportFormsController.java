/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.controller.assets;

import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.application.assets.AssetsReportFormsApplication;
import com.kunyu.assets.safety.domain.model.assets.AsAssetsDo;
import com.kunyu.assets.safety.domain.model.assets.AsAssetsSearchDo;
import com.kunyu.assets.safety.interfaces.converter.assets.AsDtoDoConverter;
import com.kunyu.assets.safety.interfaces.dto.assets.AsAssetsSearchDto;
import com.kunyu.common.exception.PlatformException;
import com.kunyu.common.result.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
* 资产报表接口层
*
* @author yangliu
* @date 2023-08-30
*/
@RestController
@RequestMapping("/assets/reportforms")
public class AssetsReportFormsController {
    private final String userId = "yuxi-guanliyuan";

    @Autowired
    private AssetsReportFormsApplication assetsReportFormsApplication;

    /**
     * 资产总清单查询
     * 
     * @param assetsSearchDto assetsSearchDto
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @return null
     * @author yangliu
     * @date 2023/9/5
     */
    @RequestMapping(path = "/masterList", method = RequestMethod.POST)
    public ApiResponse<PageInfo<AsAssetsDo>> pendingList(@RequestBody AsAssetsSearchDto assetsSearchDto,
                                                         @RequestParam(defaultValue = "1") int pageNum,
                                                         @RequestParam(defaultValue = "10") int pageSize) {
        if (pageSize > 200) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "每页条数不能超过200。");
        }
        AsAssetsSearchDo assetsSearchDo = AsDtoDoConverter.INSTANCE.getAssetsSearchDo(assetsSearchDto);
        // todo 从ThreadLocalUtil内拿
        // todo 判断权限和数据范围 总厂和分厂都可以看到数据
        assetsSearchDo.setUserId(userId);
        return ApiResponse.success(assetsReportFormsApplication.masterList(assetsSearchDo, pageNum, pageSize));
    }
}
