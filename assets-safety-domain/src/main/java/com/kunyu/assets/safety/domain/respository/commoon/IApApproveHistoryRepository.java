/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.respository.commoon;

import com.kunyu.assets.safety.domain.model.common.ApApproveHistoryDo;
import com.kunyu.assets.safety.domain.model.common.ApApproveHistorySearchDo;
import com.kunyu.assets.safety.domain.model.loophole.LoLoopholeDo;
import com.kunyu.assets.safety.domain.model.loophole.LoLoopholeSearchDo;

import java.util.List;

/**
 * 审批记录数据仓储接口
 *
 * @author yangliu
 * @date 2023-09-12
 */
public interface IApApproveHistoryRepository {
    /**
     * 批量插入审批历史
     *
     * @param historyDos historyDos
     * @return int
     * @author yangliu
     * @date 2023/9/12
     */
    int addApproveHistory(List<ApApproveHistoryDo> historyDos);



    /**
     * 审批历史查询
     *
     * @param searchDo searchDo
     * @return List ApApproveHistoryDo
     * @author yangliu
     * @date 2023/9/12
     */
    List<ApApproveHistoryDo> historyList(ApApproveHistorySearchDo searchDo);

    /**
     * 审批历史查询 单条
     * 
     * @param searchDo searchDo
     * @return ApApproveHistoryDo
     * @author yangliu
     * @date 2023/9/12
     */
    ApApproveHistoryDo historyDo(ApApproveHistorySearchDo searchDo);

    /**
     * 更新审批历史
     *
     * @param historyDo
     * @return boolean
     * @author yangliu
     * @date 2023/9/12
     */
    boolean updateHistoryDo(ApApproveHistoryDo historyDo);

}
