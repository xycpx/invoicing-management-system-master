package com.px.admin.service;

import com.px.admin.pojo.DamageListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.px.admin.query.DamageListGoodsQuery;

import java.util.Map;

/**
 * 报损单商品服务表
 */
public interface DamageListGoodsService extends IService<DamageListGoods> {

    Map<String, Object> damageListGoodsList(DamageListGoodsQuery damageListGoodsQuery);
}
