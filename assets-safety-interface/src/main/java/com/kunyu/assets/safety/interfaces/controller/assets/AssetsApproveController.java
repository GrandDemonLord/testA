/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.controller.assets;

import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.application.assets.AssetsApplication;
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
* 资产上/下架审批接口层
*
* @author yangliu
* @date 2023-08-30
*/
@RestController
@RequestMapping("/assets/approve")
public class AssetsApproveController {

    private final String userId = "yuxi-guanliyuan";

    private final String userName = "玉溪-管理员";

    @Autowired
    private AssetsApplication assetsApplication;

    /**
     * 待审批列表
     *
     * @param assetsSearchDto assetsSearchDto
     * @return PageInfo AsAssetsDo
     * @author yangliu
     * @date 2023/8/29
     */
    @RequestMapping(path = "/pendingList", method = RequestMethod.POST)
    public ApiResponse<PageInfo<AsAssetsDo>> pendingList(@RequestBody AsAssetsSearchDto assetsSearchDto,
                                                                 @RequestParam(defaultValue = "1") Integer pageNum,
                                                                 @RequestParam(defaultValue = "10") Integer pageSize) {
        if (pageSize > 200) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "每页条数不能超过200。");
        }
        AsAssetsSearchDo assetsSearchDo = AsDtoDoConverter.INSTANCE.getAssetsSearchDo(assetsSearchDto);
        // todo 从ThreadLocalUtil内拿
        // todo 判断权限和数据范围 总厂和分厂都可以看到数据
        assetsSearchDo.setUpdateBy(userId);
        return ApiResponse.success(assetsApplication.pendingList(assetsSearchDo, pageNum, pageSize));
    }

    /**
     * 审批通过
     *
     * @param id id
     * @return Boolean
     * @author yangliu
     * @date 2023/8/30
     */
    @RequestMapping(path = "/approved/{id}", method = RequestMethod.GET)
    public ApiResponse<Boolean> approved(@PathVariable Integer id) {
        // todo 从ThreadLocalUtil内拿
        return ApiResponse.success(assetsApplication.approved(id, userId, userName));
    }

    /**
     * 审批驳回
     *
     * @param id id
     * @return Boolean
     * @author yangliu
     * @date 2023/8/30
     */
    @RequestMapping(path = "/rejection/{id}", method = RequestMethod.GET)
    public ApiResponse<Boolean> rejection(@PathVariable Integer id) {
        // todo 从ThreadLocalUtil内拿
        return ApiResponse.success(assetsApplication.rejection(id, userId, userName));
    }
}
