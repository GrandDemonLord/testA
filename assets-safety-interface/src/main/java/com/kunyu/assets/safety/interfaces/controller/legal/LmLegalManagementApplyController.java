/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.controller.legal;

import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.application.legal.LegalManagementApplication;
import com.kunyu.assets.safety.domain.model.legal.LmCorporateGovernanceDo;
import com.kunyu.assets.safety.domain.model.legal.LmCorporateGovernanceSearchDo;
import com.kunyu.assets.safety.domain.model.legal.LmLegalManagementDo;
import com.kunyu.assets.safety.domain.model.legal.LmLegalManagementSearchDo;
import com.kunyu.assets.safety.interfaces.converter.legal.LmDtoDoConverter;
import com.kunyu.assets.safety.interfaces.dto.legal.LmCorporateGovernanceDto;
import com.kunyu.assets.safety.interfaces.dto.legal.LmCorporateGovernanceSearchDto;
import com.kunyu.assets.safety.interfaces.dto.legal.LmLegalManagementDto;
import com.kunyu.assets.safety.interfaces.dto.legal.LmLegalManagementSearchDto;
import com.kunyu.assets.safety.interfaces.valid.legal.LmCorporateGovernanceDtoAddValid;
import com.kunyu.assets.safety.interfaces.valid.legal.LmLagalManagementDtoAddValid;
import com.kunyu.assets.safety.interfaces.valid.legal.LmLagalManagementDtoCheckValid;
import com.kunyu.assets.safety.interfaces.valid.legal.LmLagalManagementDtoUpdateValid;
import com.kunyu.common.enums.RoleEnum;
import com.kunyu.common.exception.PlatformException;
import com.kunyu.common.models.UserInfo;
import com.kunyu.common.result.ApiResponse;
import com.kunyu.common.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author poet_wei
 * @description 法律法规信息接口层
 * @date 2023-09-26 17:45
 */
@RestController
@RequestMapping("/legal/apply")
public class LmLegalManagementApplyController {
    @Autowired
    private LegalManagementApplication legalManagementApplication;

    /**
     * @param lmLegalManagementDto
     * @return LmLegalManagementDo
     * @description 添加法律法规
     * @author poet_wei
     * @date 2023/9/27
     */
    @RequestMapping(value = "/saveLegal", method = RequestMethod.POST)
    public ApiResponse<LmLegalManagementDo> saveLegal(@Validated(LmLagalManagementDtoAddValid.class) @RequestBody LmLegalManagementDto lmLegalManagementDto) {
        UserInfo userInfo = checkPermissions();
        LmLegalManagementDo legalManagementDo = LmDtoDoConverter.INSTANCE.getLegalManagementDo(lmLegalManagementDto);
        legalManagementDo.setUnitId(userInfo.getUnitId());
        legalManagementDo.setUnitName(userInfo.getUnitName());
        legalManagementDo.setDeptId(userInfo.getDeptId());
        legalManagementDo.setDeptName(userInfo.getDeptName());
        legalManagementDo.setCreateBy(userInfo.getUserId());
        return ApiResponse.success(legalManagementApplication.saveLegal(legalManagementDo));
    }

    /**
     * @param id 删除法律法规信息id
     * @return Boolean
     * @description 删除法律法规信息
     * @author poet_wei
     * @date 2023/9/27
     */
    @RequestMapping(value = "/deleteLegal/{id}", method = RequestMethod.DELETE)
    public ApiResponse<Boolean> deleteLegal(@PathVariable Integer id) {
        UserInfo userInfo = checkPermissions();
        if (ObjectUtils.isEmpty(id)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "删除对象为空。");
        }
        return ApiResponse.success(legalManagementApplication.deleteLegal(userInfo.getUserId(), id));
    }

    /**
     * @param searchDto
     * @return LmLegalManagementDo
     * @description 法律法规信息查询
     * @author poet_wei
     * @date 2023/9/27
     */
    @RequestMapping(value = "/selectLegalList", method = RequestMethod.POST)
    public ApiResponse<PageInfo<LmLegalManagementDo>> selectLegalList(@RequestBody LmLegalManagementSearchDto searchDto,
                                                                      @RequestParam(defaultValue = "1") int pageNum,
                                                                      @RequestParam(defaultValue = "10") int pageSize) {
        checkPermissions();
        if (pageSize > 200) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "每页条数不能超过200。");
        }
        LmLegalManagementSearchDo searchDo = LmDtoDoConverter.INSTANCE.getLmLegalManagementSearchDo(searchDto);
        return ApiResponse.success(legalManagementApplication.searchLegalList(searchDo, pageNum, pageSize));
    }

    /**
     * @param lmLegalManagementDto
     * @return LmLegalManagementDo
     * @description 修改法律法规
     * @author poet_wei
     * @date 2023/9/27
     */
    @RequestMapping(value = "/updateLegal", method = RequestMethod.POST)
    public ApiResponse<LmLegalManagementDo> updateLegal(@Validated(LmLagalManagementDtoUpdateValid.class) @RequestBody LmLegalManagementDto lmLegalManagementDto) {
        UserInfo userInfo = checkPermissions();
        LmLegalManagementDo legalManagementDo = LmDtoDoConverter.INSTANCE.getLegalManagementDo(lmLegalManagementDto);
        legalManagementDo.setUpdateBy(userInfo.getUserId());
        return ApiResponse.success(legalManagementApplication.updateLegal(legalManagementDo));
    }

    /**
     * @param lmLegalManagementDto
     * @return LmLegalManagementDo
     * @description 创建工单
     * @author poet_wei
     * @date 2023/9/28
     */
    @RequestMapping(value = "/createLegalWorkOrder", method = RequestMethod.POST)
    public ApiResponse<LmLegalManagementDo> createLegalWorkOrder(@Validated(LmLagalManagementDtoCheckValid.class) @RequestBody LmLegalManagementDto lmLegalManagementDto) {
        UserInfo userInfo = checkPermissions();
        LmLegalManagementDo legalManagementDo = LmDtoDoConverter.INSTANCE.getLegalManagementDo(lmLegalManagementDto);
        legalManagementDo.setUpdateBy(userInfo.getUserId());
        return ApiResponse.success(legalManagementApplication.createLegalWorkOrder(legalManagementDo));
    }


    /**
     * @param lmCorporateGovernanceDto
     * @return LmCorporateGovernanceDo
     * @description 保存企业制度
     * @author poet_wei
     * @date 2023/9/28
     */
    @RequestMapping(value = "/saveCorporateGovernance", method = RequestMethod.POST)
    public ApiResponse<LmCorporateGovernanceDo> saveCorporateGovernance(@Validated(LmCorporateGovernanceDtoAddValid.class) @RequestBody LmCorporateGovernanceDto lmCorporateGovernanceDto) {
        UserInfo userInfo = checkBranchPermissions();
        LmCorporateGovernanceDo lmCorporateGovernanceDo = LmDtoDoConverter.INSTANCE.getLmCorporateGovernanceDo(lmCorporateGovernanceDto);
        lmCorporateGovernanceDo.setUnitId(userInfo.getUnitId());
        lmCorporateGovernanceDo.setUnitName(userInfo.getUnitName());
        lmCorporateGovernanceDo.setDeptId(userInfo.getDeptId());
        lmCorporateGovernanceDo.setDeptName(userInfo.getDeptName());
        lmCorporateGovernanceDo.setUpdateBy(userInfo.getUserId());
        return ApiResponse.success(legalManagementApplication.saveCorporateGovernance(lmCorporateGovernanceDo));
    }

    /**
     * @param searchDto
     * @return LmLegalManagementDo
     * @description 查询企业制度
     * @author poet_wei
     * @date 2023/9/27
     */
    @RequestMapping(value = "/selectCorporateGovernance", method = RequestMethod.POST)
    public ApiResponse<PageInfo<LmCorporateGovernanceDo>> selectCorporateGovernance(@RequestBody LmCorporateGovernanceSearchDto searchDto,
                                                                                    @RequestParam(defaultValue = "1") int pageNum,
                                                                                    @RequestParam(defaultValue = "10") int pageSize) {
        checkBranchPermissions();
        if (pageSize > 200) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "每页条数不能超过200。");
        }
        LmCorporateGovernanceSearchDo searchDo = LmDtoDoConverter.INSTANCE.getLmCorporateGovernanceSearchDo(searchDto);
        return ApiResponse.success(legalManagementApplication.selectCorporateGovernanceList(searchDo, pageNum, pageSize));
    }

    /**
     * @param searchDto
     * @return LmLegalManagementDo
     * @description 查询法律法规内部制度关联
     * @author poet_wei
     * @date 2023/9/27
     */
    @RequestMapping(value = "/selectLegalGovernance", method = RequestMethod.POST)
    public ApiResponse<List<LmCorporateGovernanceDo>> selectLegalGovernance(@RequestBody LmLegalManagementSearchDto searchDto) {
        checkPermissions();
        LmLegalManagementSearchDo lmLegalManagementSearchDo = LmDtoDoConverter.INSTANCE.getLmLegalManagementSearchDo(searchDto);
        return ApiResponse.success(legalManagementApplication.selectLegalGovernance(lmLegalManagementSearchDo));
    }

    /**
     * @param lmCorporateGovernanceDto
     * @return LmCorporateGovernanceDo
     * @description 修改企业制度
     * @author poet_wei
     * @date 2023/9/28
     */
    @RequestMapping(value = "/updateCorporateGovernance", method = RequestMethod.POST)
    public ApiResponse<LmCorporateGovernanceDo> updateCorporateGovernance(@Validated(LmCorporateGovernanceDtoAddValid.class) @RequestBody LmCorporateGovernanceDto lmCorporateGovernanceDto) {
        UserInfo userInfo = checkBranchPermissions();
        LmCorporateGovernanceDo lmCorporateGovernanceDo = LmDtoDoConverter.INSTANCE.getLmCorporateGovernanceDo(lmCorporateGovernanceDto);
        lmCorporateGovernanceDo.setUpdateBy(userInfo.getUserId());
        return ApiResponse.success(legalManagementApplication.updateCorporateGovernance(lmCorporateGovernanceDo));
    }

    // todo 删除使用post searchdo

    /**
     * @param ids
     * @return Boolean
     * @description 删除企业制度
     * @author poet_wei
     * @date 2023/9/28
     */
    @RequestMapping(value = "/batchDeleteCorporateGovernance/{ids}", method = RequestMethod.POST)
    public ApiResponse<Boolean> batchDeleteCorporateGovernance(@RequestBody List<Integer> ids) {
        UserInfo userInfo = checkBranchPermissions();
        // 检查参数是否为空或无效
        if(CollectionUtils.isEmpty(ids)){
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "未选择所删除的企业制度。");
        }
        return ApiResponse.success(legalManagementApplication.batchDeleteCorporateGovernance(ids,userInfo.getUserId()));
    }

    // 权限校验是否是总厂管理员
    private static UserInfo checkPermissions() {
        UserInfo userInfo = ThreadLocalUtil.getUserInfo();
        if (!RoleEnum.SYSGENERALADMIN.getRoleId().equals(userInfo.getRoleCode())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "无权限操作。");
        }
        return userInfo;
    }

    // 权限校验是否是分厂管理员
    private static UserInfo checkBranchPermissions() {
        UserInfo userInfo = ThreadLocalUtil.getUserInfo();
        if (!RoleEnum.SYSSUBADMIN.getRoleId().equals(userInfo.getRoleCode())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "无权限操作。");
        }
        return userInfo;
    }
}
