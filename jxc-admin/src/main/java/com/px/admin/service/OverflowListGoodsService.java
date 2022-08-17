package com.px.admin.service;

import com.px.admin.pojo.OverflowListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.px.admin.query.OverflowListGoodsQuery;

import java.util.Map;

/**
 * 报溢单商品服务类
 */
public interface OverflowListGoodsService extends IService<OverflowListGoods> {

    Map<String, Object> overflowListGoodsList(OverflowListGoodsQuery overflowListGoodsQuery);
}
