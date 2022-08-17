package com.px.admin.service;

import com.px.admin.pojo.PurchaseList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.px.admin.pojo.PurchaseListGoods;
import com.px.admin.query.PurchaseListQuery;

import java.util.List;
import java.util.Map;

/**
 * 进货单服务类
 */
public interface PurchaseListService extends IService<PurchaseList> {

    String getNextPurchaseNumber();

    void savePurchaseList(PurchaseList purchaseList, List<PurchaseListGoods> plgList);

    Map<String, Object> purchaseList(PurchaseListQuery purchaseListQuery);

    void deletePurchaseList(Integer id);

    Map<String, Object> countPurchase(PurchaseListQuery purchaseListQuery);
}
