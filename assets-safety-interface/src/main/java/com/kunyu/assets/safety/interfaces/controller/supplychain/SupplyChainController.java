/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.controller.supplychain;

import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.application.supplychain.SupplyChainApplication;
import com.kunyu.assets.safety.domain.model.supplychain.ScSupplyChainDo;
import com.kunyu.assets.safety.domain.model.supplychain.ScSupplyChainSelectDo;
import com.kunyu.assets.safety.interfaces.converter.supplychain.ScDtoDoConverter;
import com.kunyu.assets.safety.interfaces.dto.supplychain.ScSupplyChainDto;
import com.kunyu.assets.safety.interfaces.dto.supplychain.ScSupplyChainSelectDto;
import com.kunyu.assets.safety.interfaces.valid.supplychain.ScSuppyChainAddValid;
import com.kunyu.common.enums.RoleEnum;
import com.kunyu.common.exception.PlatformException;
import com.kunyu.common.models.UserInfo;
import com.kunyu.common.result.ApiResponse;
import com.kunyu.common.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author poet_wei
 * @description 供应管理接口层
 * @date 2023-09-22 16:06
 */
@RestController
@RequestMapping("/supplyChain")
public class SupplyChainController {
    @Autowired
    private SupplyChainApplication supplyChainApplication;

    /**
     * @param scSupplyChainDto
     * @return ScSupplyChainDo
     * @description 添加评估任务
     * @author poet_wei
     * @date 2023/9/22
     */
    @RequestMapping(path = "/saveAssessmentTask", method = RequestMethod.POST)
    public ApiResponse<ScSupplyChainDo> saveAssessmentTask(@Validated(ScSuppyChainAddValid.class) @RequestBody ScSupplyChainDto scSupplyChainDto) {
        UserInfo userInfo = checkPermissions();
        ScSupplyChainDo scSupplyChainDo = ScDtoDoConverter.INSTANCE.getScSupplyChainDo(scSupplyChainDto);
        scSupplyChainDo.setUnitId(userInfo.getUnitId());
        scSupplyChainDo.setUnitName(userInfo.getUnitName());
        scSupplyChainDo.setDeptId(userInfo.getDeptId());
        scSupplyChainDo.setDeptName(userInfo.getUnitName());
        scSupplyChainDo.setCreateBy(userInfo.getUserId());
        return ApiResponse.success(supplyChainApplication.saveAssessmentTask(scSupplyChainDo));
    }

    /**
     * @param scSupplyChainSelectDto 产品名称 pageNum 页码 pageSize 页面内容数量
     * @return ScSupplyChainDo
     * @description 查询供应链
     * @author poet_wei
     * @date 2023/9/25
     */
    @RequestMapping(path = "/selectSupplyChain", method = RequestMethod.POST)
    public ApiResponse<PageInfo<ScSupplyChainDo>> selectSupplyChain(@RequestBody ScSupplyChainSelectDto scSupplyChainSelectDto,
                                                                    @RequestParam(defaultValue = "1") int pageNum,
                                                                    @RequestParam(defaultValue = "10") int pageSize) {
        checkPermissions();
        if (pageSize > 200) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "每页条数不能超过200。");
        }
        scSupplyChainSelectDto.setPageNum(pageNum);
        scSupplyChainSelectDto.setPageSize(pageSize);
        ScSupplyChainSelectDo scSupplyChainSelectDo = ScDtoDoConverter.INSTANCE.getScSupplyChainSelectDo(scSupplyChainSelectDto);
        return ApiResponse.success(supplyChainApplication.selectSupplyChain(scSupplyChainSelectDo));
    }

    /**
     * @param id 供应链id
     * @return ScSupplyChainDo
     * @description 删除供应链信息
     * @author poet_wei
     * @date 2023/9/25
     */
    @RequestMapping(path = "/deleteSupplyChain/{id}", method = RequestMethod.DELETE)
    public ApiResponse<Boolean> deleteSupplyChain(@PathVariable Integer id) {
        UserInfo userInfo = checkPermissions();
        if (ObjectUtils.isEmpty(id)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "未选择所要删除的供应链信息。");
        }
        return ApiResponse.success(supplyChainApplication.deleteSupplyChain(id, userInfo.getUserId()));
    }


    /**
     * @param id 供应链id assessmentScore 评分表信息 score 总分
     * @return ScSupplyChainDo
     * @description 保存评分表信息
     * @author poet_wei
     * @date 2023/9/25
     */
    @RequestMapping(path = "/saveAssessmentScore/{id}/{answer}", method = RequestMethod.PUT)
    public ApiResponse<ScSupplyChainDo> saveAssessmentScore(@PathVariable Integer id, @PathVariable String[] answer) {
        if (ObjectUtils.isEmpty(answer)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "请先打分。");
        }
        UserInfo userInfo = checkPermissions();
        return ApiResponse.success(supplyChainApplication.updateAssessmentScore(id, answer, userInfo.getUserId()));
    }

    // 权限校验
    private static UserInfo checkPermissions() {
        UserInfo userInfo = ThreadLocalUtil.getUserInfo();
        if (!RoleEnum.SYSGENERALADMIN.getRoleId().equals(userInfo.getRoleCode())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "无权限操作。");
        }
        return userInfo;
    }
}
