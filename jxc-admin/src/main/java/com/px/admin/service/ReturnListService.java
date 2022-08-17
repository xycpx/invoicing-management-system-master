package com.px.admin.service;

import com.px.admin.pojo.ReturnList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.px.admin.pojo.ReturnListGoods;
import com.px.admin.query.ReturnListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 退货单表 服务类
 * </p>
 */
public interface ReturnListService extends IService<ReturnList> {

    String getNextReturnNumber();

    void saveReturnList(ReturnList returnList, List<ReturnListGoods> rlgList);

    Map<String, Object> returnList(ReturnListQuery returnListQuery);

    void deleteReturnList(Integer id);
}
