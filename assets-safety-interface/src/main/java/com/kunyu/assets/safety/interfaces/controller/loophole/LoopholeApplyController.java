/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.controller.loophole;

import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.application.loophole.LoopholeApplication;
import com.kunyu.assets.safety.domain.model.loophole.LoLoopholeDo;
import com.kunyu.assets.safety.domain.model.loophole.LoLoopholeSearchDo;
import com.kunyu.assets.safety.interfaces.converter.loophole.LoLoopholeDtoDoConverter;
import com.kunyu.assets.safety.interfaces.dto.loophole.LoLoopholeDto;
import com.kunyu.assets.safety.interfaces.dto.loophole.LoLoopholeImportDto;
import com.kunyu.assets.safety.interfaces.dto.loophole.LoLoopholeSearchDto;
import com.kunyu.assets.safety.interfaces.valid.common.ValidList;
import com.kunyu.assets.safety.interfaces.valid.loophole.LoLoopholeDtoValid;
import com.kunyu.common.enums.RoleEnum;
import com.kunyu.common.exception.PlatformException;
import com.kunyu.common.models.UserInfo;
import com.kunyu.common.result.ApiResponse;
import com.kunyu.common.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author poet_wei
 * @description 漏洞信息管理接口层
 * @date 2023-09-06 10:43
 */
@RestController
@RequestMapping("/loophole/apply")
public class LoopholeApplyController {


    @Autowired
    private LoopholeApplication loopholeApplication;


    /**
     * @param loopholeSearchDto pageNum pageSize
     * @return LoLoopholeDo
     * @description 漏洞信息条件分页查询 （普通用户）
     * @author poet_wei
     * @date 2023/9/6
     */
    @RequestMapping(path = "/selectLoophole", method = RequestMethod.POST)
    public ApiResponse<PageInfo<LoLoopholeDo>> selectLoophole(@RequestBody LoLoopholeSearchDto loopholeSearchDto,
                                                              @RequestParam(defaultValue = "1") int pageNum,
                                                              @RequestParam(defaultValue = "10") int pageSize) {
        if (pageSize > 200) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "每页条数不能超过200。");
        }
        LoLoopholeSearchDo loLoopholeSearchDo = LoLoopholeDtoDoConverter.INSTANCE.getLoopholeSearchDo(loopholeSearchDto);
        UserInfo userInfo = ThreadLocalUtil.getUserInfo();
        loLoopholeSearchDo.setCreateBy(userInfo.getUserId());
        return ApiResponse.success(loopholeApplication.selectLoophole(loLoopholeSearchDo, pageNum, pageSize));
    }

    /**
     * @param loopholeSearchDto pageNum pageSize
     * @return LoLoopholeDo
     * @description 漏洞信息条件分页查询 (管理员查询)
     * @author poet_wei
     * @date 2023/9/6
     */
    @RequestMapping(path = "/adminSelectLoophole", method = RequestMethod.POST)
    public ApiResponse<PageInfo<LoLoopholeDo>> adminSelectLoophole(@RequestBody LoLoopholeSearchDto loopholeSearchDto,
                                                                   @RequestParam(defaultValue = "1") int pageNum,
                                                                   @RequestParam(defaultValue = "10") int pageSize) {
        checkPermissions();
        if (pageSize > 200) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "每页条数不能超过200。");
        }
        LoLoopholeSearchDo loLoopholeSearchDo = LoLoopholeDtoDoConverter.INSTANCE.getLoopholeSearchDo(loopholeSearchDto);
        loLoopholeSearchDo.setUnitId(ThreadLocalUtil.getUserInfo().getUnitId());
        loLoopholeSearchDo.setRoleCode(ThreadLocalUtil.getUserInfo().getRoleCode());
        return ApiResponse.success(loopholeApplication.adminSelectLoophole(loLoopholeSearchDo, pageNum, pageSize));
    }

    /**
     * @param loopholeDto
     * @return LoLoopholeDo
     * @description 新增漏洞信息
     * @author poet_wei
     * @date 2023/9/6
     */
    @RequestMapping(path = "/saveLoophole", method = RequestMethod.POST)
    public ApiResponse<LoLoopholeDo> saveLoophole(@Validated(LoLoopholeDtoValid.class) @RequestBody LoLoopholeDto loopholeDto) {
        LoLoopholeDo loLoopholeDo = LoLoopholeDtoDoConverter.INSTANCE.getLoopholeDo(loopholeDto);
        UserInfo userInfo = ThreadLocalUtil.getUserInfo();
        loLoopholeDo.setCreateBy(userInfo.getUserId());
        loLoopholeDo.setUnitId(userInfo.getUnitId());
        loLoopholeDo.setUnitName(userInfo.getUnitName());
        loLoopholeDo.setDeptId(userInfo.getDeptId());
        loLoopholeDo.setDeptName(userInfo.getDeptName());
        return ApiResponse.success(loopholeApplication.saveLoophole(loLoopholeDo));
    }


    /**
     * @param id 漏洞id
     * @return Boolean
     * @description 漏洞报告开始
     * @author poet_wei
     * @date 2023/9/12
     */
    @RequestMapping(path = "/loopholeReortStart/{id}", method = RequestMethod.GET)
    public ApiResponse<Boolean> loopholeReportStart(@PathVariable("id") Integer id) {
        return ApiResponse.success(loopholeApplication.loopholeReportStart(id, ThreadLocalUtil.getUserInfo().getUserId()));
    }

    /**
     * @param id 漏洞id
     * @return Boolean
     * @description 漏洞报告结束
     * @author poet_wei
     * @date 2023/9/12
     */
    @RequestMapping(path = "/loopholeReortEnd/{id}", method = RequestMethod.GET)
    public ApiResponse<Boolean> loopholeReportEnd(@PathVariable("id") Integer id) {
        return ApiResponse.success(loopholeApplication.loopholeReportEnd(id, ThreadLocalUtil.getUserInfo().getUserId()));
    }

    /**
     * @param id 漏洞id
     * @return Boolean
     * @description 漏洞报告删除
     * @author poet_wei
     * @date 2023/9/12
     */
    @RequestMapping(path = "/deleteReport/{id}", method = RequestMethod.GET)
    public ApiResponse<Boolean> deleteReport(@PathVariable("id") Integer id) {
        return ApiResponse.success(loopholeApplication.deleteReport(id, ThreadLocalUtil.getUserInfo().getUserId()));
    }


    /**
     * @param
     * @return LoLoopholeDo
     * @description 查询代办任务
     * @author poet_wei
     * @date 2023/9/15
     */
    @RequestMapping(path = "/selectTaskQuery", method = RequestMethod.GET)
    public ApiResponse<PageInfo<LoLoopholeDo>> selectTaskQuery(@RequestParam(defaultValue = "1") int pageNum,
                                                           @RequestParam(defaultValue = "10") int pageSize) {
        if (pageSize > 200) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "每页条数不能超过200。");
        }
        LoLoopholeSearchDo loLoopholeSearchDo = new LoLoopholeSearchDo();
        loLoopholeSearchDo.setRoleCode(ThreadLocalUtil.getUserInfo().getRoleCode());
        loLoopholeSearchDo.setProcessedId(ThreadLocalUtil.getUserInfo().getUserId());
        loLoopholeSearchDo.setUnitId(ThreadLocalUtil.getUserInfo().getUnitId());
        return ApiResponse.success(loopholeApplication.selectTaskQuery(loLoopholeSearchDo,pageNum,pageSize));
    }

    /**
     * 新增漏洞批量导入 批量导入
     *
     * @param importDtos
     * @return LoLoopholeDo
     */
    @RequestMapping(value = "/batchSaveLoophole", method = RequestMethod.POST)
    public ApiResponse<List<LoLoopholeDo>> batchSaveLoophole(@Validated(LoLoopholeDtoValid.class) @RequestBody ValidList<LoLoopholeImportDto> importDtos) {
        List<LoLoopholeDo> loLoopholeDos = LoLoopholeDtoDoConverter.INSTANCE.getLoLoopholeDos(importDtos);
        UserInfo userInfo = ThreadLocalUtil.getUserInfo();
        for (LoLoopholeDo loLoopholeDo : loLoopholeDos) {
            loLoopholeDo.setUnitId(userInfo.getUnitId());
            loLoopholeDo.setUnitName(userInfo.getUnitName());
            loLoopholeDo.setDeptId(userInfo.getDeptId());
            loLoopholeDo.setDeptName(userInfo.getDeptName());
            loLoopholeDo.setCreateBy(userInfo.getUserId());
        }
        return ApiResponse.success(loopholeApplication.batchSaveLoophole(loLoopholeDos, ThreadLocalUtil.getUserInfo().getUserId()));
    }

    // 权限校验
    private static String checkPermissions() {
        String roleCode = ThreadLocalUtil.getUserInfo().getRoleCode();
        if (!RoleEnum.SYSGENERALADMIN.getRoleId().equals(roleCode) && !RoleEnum.SYSSUBADMIN.getRoleId().equals(roleCode)) {
           throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "权限不够无法操作。");
        }
        return ThreadLocalUtil.getUserInfo().getUnitId();
    }


}
