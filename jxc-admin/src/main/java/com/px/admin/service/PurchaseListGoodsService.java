package com.px.admin.service;

import com.px.admin.pojo.PurchaseListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.px.admin.query.PurchaseListGoodsQuery;

import java.util.Map;

/**
 * 进货单商品表服务类
 */
public interface PurchaseListGoodsService extends IService<PurchaseListGoods> {

    Map<String, Object> purchaseListGoodsList(PurchaseListGoodsQuery purchaseListGoodsQuery);
}
