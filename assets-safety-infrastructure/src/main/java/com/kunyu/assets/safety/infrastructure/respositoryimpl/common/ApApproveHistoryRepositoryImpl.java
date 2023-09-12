/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.infrastructure.respositoryimpl.common;

import com.kunyu.assets.safety.domain.model.common.ApApproveHistoryDo;
import com.kunyu.assets.safety.domain.model.common.ApApproveHistorySearchDo;
import com.kunyu.assets.safety.domain.respository.commoon.IApApproveHistoryRepository;
import com.kunyu.assets.safety.infrastructure.converter.common.ApDoPoConverter;
import com.kunyu.assets.safety.infrastructure.dao.common.ApApproveHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 审批记录数据仓储接口实现类
 *
 * @author yangliu
 * @date 2023-09-12
 */
@Repository
public class ApApproveHistoryRepositoryImpl implements IApApproveHistoryRepository {

    @Autowired
    private ApApproveHistoryMapper apApproveHistoryMapper;

    @Override
    public int addApproveHistory(List<ApApproveHistoryDo> historyDos) {
        return apApproveHistoryMapper.addApproveHistory(ApDoPoConverter.INSTANCE.getApApproveHistoryPos(historyDos));
    }

    @Override
    public List<ApApproveHistoryDo> historyList(ApApproveHistorySearchDo searchDo) {
        return ApDoPoConverter.INSTANCE.getApApproveHistoryDos(apApproveHistoryMapper.historyList(searchDo));
    }

    @Override
    public ApApproveHistoryDo historyDo(ApApproveHistorySearchDo searchDo) {
        return ApDoPoConverter.INSTANCE.getApApproveHistoryDo(apApproveHistoryMapper.history(searchDo));
    }

    @Override
    public boolean updateHistoryDo(ApApproveHistoryDo historyDo) {
        return apApproveHistoryMapper.updateHistory(ApDoPoConverter.INSTANCE.getApApproveHistoryPo(historyDo)) > 0 ? true : false;
    }
}
