package com.px.admin.service;

import com.px.admin.pojo.SaleListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.px.admin.query.saleListGoodsQuery;

import java.util.Map;

/**
 * <p>
 * 销售单商品表 服务类
 * </p>
 */
public interface SaleListGoodsService extends IService<SaleListGoods> {

    Integer getSaleTotalByGoodsId(Integer id);

    Map<String, Object> saleListGoodsList(saleListGoodsQuery saleListGoodsQuery);


}
