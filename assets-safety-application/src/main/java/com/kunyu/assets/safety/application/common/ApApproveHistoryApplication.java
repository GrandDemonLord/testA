/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.application.common;

import com.kunyu.assets.safety.domain.model.common.ApApproveHistoryDo;
import com.kunyu.assets.safety.domain.model.common.ApApproveHistorySearchDo;
import com.kunyu.assets.safety.domain.service.common.ApApproveHistoryDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 审批历史应用层
 *
 * @author yangliu
 * @date 2023-09-12
 */
@Service
public class ApApproveHistoryApplication {

    @Autowired
    private ApApproveHistoryDomain apApproveHistoryDomain;

    /**
     * 审批历史查询
     *
     * @param searchDo searchDo
     * @return List ApApproveHistoryDo
     * @author yangliu
     * @date 2023/9/12
     */
    public List<ApApproveHistoryDo> historyList(ApApproveHistorySearchDo searchDo) {
        return apApproveHistoryDomain.historyList(searchDo);
    }
}
