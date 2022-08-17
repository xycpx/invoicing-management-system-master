package com.px.admin.service;

import com.px.admin.pojo.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.px.admin.query.GoodsQuery;

import java.util.Map;

/**
 * 商品表服务
 */
public interface GoodsService extends IService<Goods> {

    Map<String, Object> goodsList(GoodsQuery goodsQuery);


    String genGoodsCode();

    void saveGoods(Goods goods);

    void updateGoods(Goods goods);

    void deleteGoods(Integer id);

    void updateStock(Goods goods);

    void deleteStock(Integer id);

    Goods getGoodsInfoById(Integer gid);

    Map<String, Object> stockList(GoodsQuery goodsQuery);


}
