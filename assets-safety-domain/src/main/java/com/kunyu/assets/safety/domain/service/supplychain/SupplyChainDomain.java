/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.service.supplychain;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.common.enums.supplychain.AssessmentStatusEnum;
import com.kunyu.assets.safety.domain.model.supplychain.ScSupplyChainDo;
import com.kunyu.assets.safety.domain.model.supplychain.ScSupplyChainSelectDo;
import com.kunyu.assets.safety.domain.respository.supplychain.ScSupplyChainRepository;
import com.kunyu.common.enums.ModelStatusEnum;
import com.kunyu.common.exception.PlatformException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author poet_wei
 * @description 供应链管理领域层
 * @date 2023-09-22 16:00
 */
@Service
public class SupplyChainDomain {
    @Autowired
    private ScSupplyChainRepository scSupplyChainRepository;

    /**
     * @param scSupplyChainDo
     * @return ScSupplyChainDo
     * @description 添加评估任务
     * @author poet_wei
     * @date 2023/9/22
     */
    public ScSupplyChainDo saveAssessmentTask(ScSupplyChainDo scSupplyChainDo) {
        scSupplyChainDo.setStatus(ModelStatusEnum.VAILD.getCode());
        scSupplyChainDo.setAssessmentStatus(AssessmentStatusEnum.NOTASSESSED.getCode());
        return scSupplyChainRepository.saveAssessmentTask(scSupplyChainDo);
    }

    /**
     * @param scSupplyChainSelectDo
     * @return ScSupplyChainDo
     * @description 查询供应链
     * @author poet_wei
     * @date 2023/9/25
     */
    public PageInfo<ScSupplyChainDo> selectSupplyChain(ScSupplyChainSelectDo scSupplyChainSelectDo) {
        PageHelper.startPage(scSupplyChainSelectDo.getPageNum(), scSupplyChainSelectDo.getPageSize());
        List<ScSupplyChainDo> scSupplyChainDoList = scSupplyChainRepository.selectSupplyChain(scSupplyChainSelectDo);
        if (CollectionUtils.isEmpty(scSupplyChainDoList)) {
            return null;
        }
        return new PageInfo<>(scSupplyChainDoList);
    }

    /**
     * @param id 供应链id
     * @return ScSupplyChainDo
     * @description 删除供应链信息
     * @author poet_wei
     * @date 2023/9/25
     */
    public Boolean deleteSupplyChain(Integer id, String userId) {
        // 查询供应链信息是否存在
        ScSupplyChainDo scSupplyChainDo = scSupplyChainRepository.selectSupplyChainById(id);
        if (ObjectUtils.isEmpty(scSupplyChainDo)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "此供应链信息不存在。");
        }
        // 删除供应链信息
        return scSupplyChainRepository.deleteSupplyChain(id, userId);
    }

    /**
     * @param id 供应链id assessmentScore 评分表信息 score 总分
     * @return ScSupplyChainDo
     * @description 保存评分表信息
     * @author poet_wei
     * @date 2023/9/25
     */
    public ScSupplyChainDo updateAssessmentScore(Integer id, String[] answer, String userId) {
        // 查询供应链信息是否存在
        ScSupplyChainDo scSupplyChainDo = scSupplyChainRepository.selectSupplyChainById(id);
        if (ObjectUtils.isEmpty(scSupplyChainDo)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "此供应链信息不存在。");
        }
        // 保存供应链信息
        scSupplyChainDo.setScore(answer[answer.length - 1]);
        scSupplyChainDo.setScoreCardContent(Arrays.toString(answer));
        scSupplyChainDo.setUpdateBy(userId);
        scSupplyChainDo.setAssessmentStatus(AssessmentStatusEnum.ASSESSED.getCode());
        scSupplyChainRepository.updateAssessmentScore(scSupplyChainDo);
        return scSupplyChainDo;
    }
}
