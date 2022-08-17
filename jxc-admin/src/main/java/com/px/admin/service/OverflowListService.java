package com.px.admin.service;

import com.px.admin.pojo.OverflowList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.px.admin.pojo.OverflowListGoods;
import com.px.admin.query.OverFlowListQuery;

import java.util.List;
import java.util.Map;

/**
 * 报溢单服务类
 */
public interface OverflowListService extends IService<OverflowList> {

    String getOverflowNumber();

    void saveOverflowList(OverflowList overflowList, List<OverflowListGoods> plgList);

    Map<String, Object> overFlowList(OverFlowListQuery overFlowListQuery);

    void deleteoverflowList(Integer id);
}
