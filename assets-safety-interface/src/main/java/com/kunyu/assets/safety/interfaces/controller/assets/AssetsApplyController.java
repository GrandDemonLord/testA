/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.controller.assets;

import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.application.assets.AssetsApplication;
import com.kunyu.assets.safety.domain.model.assets.AsAssetsDo;
import com.kunyu.assets.safety.domain.model.assets.AsAssetsSearchDo;
import com.kunyu.assets.safety.interfaces.converter.assets.DtoDoConverter;
import com.kunyu.assets.safety.interfaces.dto.assets.AsAssetsDto;
import com.kunyu.assets.safety.interfaces.dto.assets.AsAssetsImportDto;
import com.kunyu.assets.safety.interfaces.dto.assets.AsAssetsSearchDto;
import com.kunyu.assets.safety.interfaces.valid.common.ValidList;
import com.kunyu.assets.safety.interfaces.valid.assets.AsAssetsDelistValid;
import com.kunyu.assets.safety.interfaces.valid.assets.AsAssetsListingValid;
import com.kunyu.common.exception.PlatformException;
import com.kunyu.common.result.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* 资产上/下架申请接口层
*
* @author yangliu
* @date 2023-08-25
*/
@RestController
@RequestMapping("/assets/apply")
public class AssetsApplyController {

    private final String userId = "yuxi-putongyonghu";

    @Autowired
    private AssetsApplication assetsApplication;

    /**
     * 资产上架清单
     *
     * @param assetsSearchDto assetsSearchDto
     * @return PageInfo AsAssetsDo
     * @author yangliu
     * @date 2023/8/25
     */
    @RequestMapping(path = "/listingList", method = RequestMethod.POST)
    public ApiResponse<PageInfo<AsAssetsDo>> listingList(@RequestBody AsAssetsSearchDto assetsSearchDto,
                                                               @RequestParam(defaultValue = "1") int pageNum,
                                                               @RequestParam(defaultValue = "10") int pageSize) {
        if (pageSize > 200) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "每页条数不能超过200。");
        }
        AsAssetsSearchDo assetsSearchDo = DtoDoConverter.INSTANCE.getAssetsSearchDo(assetsSearchDto);
        // todo 从ThreadLocalUtil内拿
        assetsSearchDo.setCreateBy(userId);
        return ApiResponse.success(assetsApplication.listingList(assetsSearchDo, pageNum, pageSize));
    }

    /**
     * 新增资产上架
     *
     * @param asAssetsDto asAssetsDto
     * @return ApiResponse asAssetsDo
     */
    @RequestMapping(value = "/listing", method = RequestMethod.POST)
    public ApiResponse<AsAssetsDo> listing(@Validated(AsAssetsListingValid.class) @RequestBody AsAssetsDto asAssetsDto) {
        AsAssetsDo asAssetsDo = DtoDoConverter.INSTANCE.getAsAssetsDo(asAssetsDto);
        // todo 从ThreadLocalUtil内拿
        asAssetsDo.setCreateBy(userId);
        return ApiResponse.success(assetsApplication.listing(asAssetsDo));
    }

    /**
     * 上架驳回的资产，重新申请上架
     *
     * @param asAssetsDto asAssetsDto
     * @return ApiResponse asAssetsDo
     * @author yangliu
     * @date 2023/9/5
     */
    @RequestMapping(value = "/listingReapply", method = RequestMethod.POST)
    public ApiResponse<AsAssetsDo> listingReapply(@Validated(AsAssetsListingValid.class) @RequestBody AsAssetsDto asAssetsDto) {
        if (ObjectUtils.isEmpty(asAssetsDto.getId())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "id不能为空。");
        }
        AsAssetsDo asAssetsDo = DtoDoConverter.INSTANCE.getAsAssetsDo(asAssetsDto);
        // todo 从ThreadLocalUtil内拿
        asAssetsDo.setUpdateBy(userId);
        return ApiResponse.success(assetsApplication.listingReapply(asAssetsDo));
    }

    /**
     * 新增资产上架 批量导入
     *
     * @param importDtos importDtos
     * @return ApiResponse asAssetsDo
     */
    @RequestMapping(value = "/batchListing", method = RequestMethod.POST)
    public ApiResponse<List<AsAssetsDo>> batchListing(@Validated(AsAssetsListingValid.class) @RequestBody ValidList<AsAssetsImportDto> importDtos) {
        List<AsAssetsDo> asAssetsDos = DtoDoConverter.INSTANCE.getAsAssetsDos(importDtos);
        return ApiResponse.success(assetsApplication.batchListing(asAssetsDos, userId));
    }

    /**
     * 资产下架清单
     *
     * @param assetsSearchDto assetsSearchDto
     * @return PageInfo AsAssetsDo
     * @author yangliu
     * @date 2023/8/25
     */
    @RequestMapping(path = "/delistList", method = RequestMethod.POST)
    public ApiResponse<PageInfo<AsAssetsDo>> delistList(@RequestBody AsAssetsSearchDto assetsSearchDto,
                                                  @RequestParam(defaultValue = "1") int pageNum,
                                                  @RequestParam(defaultValue = "10") int pageSize) {
        if (pageSize > 200) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "每页条数不能超过200。");
        }
        AsAssetsSearchDo assetsSearchDo = DtoDoConverter.INSTANCE.getAssetsSearchDo(assetsSearchDto);
        // todo 从ThreadLocalUtil内拿
        assetsSearchDo.setCreateBy(userId);
        return ApiResponse.success(assetsApplication.delistList(assetsSearchDo, pageNum, pageSize));
    }

    /**
     * 资产下架
     *
     * @param asAssetsDto asAssetsDto
     * @return Boolean Boolean
     */
    @RequestMapping(value = "/delist", method = RequestMethod.POST)
    public ApiResponse<Boolean> delist(@Validated(AsAssetsDelistValid.class) @RequestBody AsAssetsDto asAssetsDto) {
        AsAssetsDo asAssetsDo = DtoDoConverter.INSTANCE.getAsAssetsDo(asAssetsDto);
        // todo 从ThreadLocalUtil内拿
        asAssetsDo.setUpdateBy(userId);
        return ApiResponse.success(assetsApplication.delist(asAssetsDo));
    }

    /**
     * 下架驳回的资产，重新申请下架
     *
     * @param asAssetsDto asAssetsDto
     * @return Boolean Boolean
     */
    @RequestMapping(value = "/delistReapply", method = RequestMethod.POST)
    public ApiResponse<Boolean> delistReapply(@Validated(AsAssetsDelistValid.class) @RequestBody AsAssetsDto asAssetsDto) {
        AsAssetsDo asAssetsDo = DtoDoConverter.INSTANCE.getAsAssetsDo(asAssetsDto);
        // todo 从ThreadLocalUtil内拿
        asAssetsDo.setUpdateBy(userId);
        return ApiResponse.success(assetsApplication.delistReapply(asAssetsDo));
    }

    /**
     * 审批驳回列表查询接口
     *
     * @param assetsSearchDto assetsSearchDto
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @return PageInfo AsAssetsDo
     * @author yangliu
     * @date 2023/9/5
     */
    @RequestMapping(path = "/rejectionList", method = RequestMethod.POST)
    public ApiResponse<PageInfo<AsAssetsDo>> rejectionList(@RequestBody AsAssetsSearchDto assetsSearchDto,
                                                        @RequestParam(defaultValue = "1") int pageNum,
                                                        @RequestParam(defaultValue = "10") int pageSize) {
        if (pageSize > 200) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "每页条数不能超过200。");
        }
        AsAssetsSearchDo assetsSearchDo = DtoDoConverter.INSTANCE.getAssetsSearchDo(assetsSearchDto);
        // todo 从ThreadLocalUtil内拿
        assetsSearchDo.setCreateBy(userId);
        return ApiResponse.success(assetsApplication.rejectionList(assetsSearchDo, pageNum, pageSize));
    }

    /**
     * 资产删除
     *
     * @param id id
     * @return Boolean
     * @author yangliu
     * @date 2023/8/30
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ApiResponse<Boolean> deleteById(@PathVariable Integer id) {
        // todo 从ThreadLocalUtil内拿
        return ApiResponse.success(assetsApplication.deleteById(id, userId));
    }
}
